package frgp.utn.edu.ar.servicioImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import frgp.utn.edu.ar.dao.StockDao;
import frgp.utn.edu.ar.dao.VentaDao;
import frgp.utn.edu.ar.dominio.Detalle_venta;
import frgp.utn.edu.ar.dominio.Stock;
import frgp.utn.edu.ar.dominio.Venta;
import frgp.utn.edu.ar.servicio.VentaServicio;

@Service
public class VentaServicioImpl implements VentaServicio{

	private VentaDao dataAccess = null;
	private StockDao dataAccess2 = null;

	public void setDataAccess(VentaDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	public void setDataAccess2(StockDao dataAccess2) {
		this.dataAccess2 = dataAccess2;
	}
	
	@Override
	public ArrayList<Venta> obtenerVentas() {
		return dataAccess.obtenerVentas();
	}

	@Override
	public Venta obtenerUnRegistro(int ID) {
		return dataAccess.obtenerVentaPorID(ID);
	}

	@Override
	public String insertarVenta(Venta nuevo) {
		
		/// CARGA LISTA DE DETALLES DE LA VENTA RECIBIDA
		List<Detalle_venta> lista = nuevo.getDetalle();		
		
		/// VALIDA QUE LA CANTIDAD TOTAL DE STOCK SEA MAYOR A LA VENTA
		for (Detalle_venta detalle : lista) {
			/// CARGA TODOS LOS STOCKS DEL ARTICULO PASADO
			List<Stock> listaStock = dataAccess2.obtenerDeArticulo(detalle.getArticulo().getNombre());
			int cantidad_total = 0;
			/// CUENTA LA CANTIDAD TOTAL DE ARTICULOS ENTRE TODOS LOS STOCKS
			for (Stock stock : listaStock) {	
				cantidad_total += stock.getCantidad();
			}
			
			/// SI NO HAY CANTIDAD SUFICIENTE, SE CANCELA LA VENTA.
			/// (SE PUEDE LIMITAR A VENDER TODOS LOS QUE EXISTEN EN STOCK - REVISAR)
			if(detalle.getCantidad()>cantidad_total) {
				return "VENTA CANCELADA";
			}
		}
		
		/// VARIABLES AUXILIARES
		boolean flag = true;
		float ganancia = 0;
		
		/// CICLA ENTR TODOS LOS DETALLES
		for (Detalle_venta detalle : lista) {
			/// CARGO LA CANTIDAD DEL DETALLE PARA NO MANIPULAR EL REGISTRO DE LA VENTA
			int descontar = detalle.getCantidad();
			List<Stock> listaStock = dataAccess2.obtenerDeArticulo(detalle.getArticulo().getNombre());
			
			/// SE ORDENA LISTA DE STOCK POR FECHA
			Collections.sort(listaStock);
			
			/// SE ITERA ENTRE LOS STOCKS DISPONIBLES PARA ESE ARTICULO
			for (Stock stock : listaStock) {
				/// FILTRA REGISTROS CON STOCK 0
				if(stock.getCantidad()==0) {
					break;
				}
					
				/// PREGUNTA SI LA CANTIDAD VENIDA ES MENOR AL STOCK ACTUAL Y NO ES 0
				if(stock.getCantidad()>=descontar && descontar != 0) {
					
					/// MODIFICA EL STOCK ACTUAL RESTANDO LA CANTIDAD VENDIDA
					stock.setCantidad(stock.getCantidad()-descontar);
					if(!dataAccess2.modificarStock(stock)) {
						flag = false;
					} else {
						/// SI LOGRA GUARDAR EL CAMBIO DE STOCK, CALCULA LA GANANCIA Y SE ACUMULA EN LA VARIABLE AUXILIAR
						ganancia += (detalle.getArticulo().getPrecio_venta()*descontar)-(stock.getPreciocompra()*descontar); // ACUMULACION DE GANANCIA
						descontar = 0;
					}
					
				/// LA CANTIDAD VENDIDA SUPERA AL STOCK ACTUAL
				} else if (stock.getCantidad()<descontar && descontar != 0) {
					/// OBTIENE LA CANTIDAD TOTAL DEL STOCK ACTUAL
					int cant_vendida = stock.getCantidad();
					/// SE PONE EN 0 EL STOCK ACTUAL
					stock.setCantidad(0);
					if(!dataAccess2.modificarStock(stock)) {
						flag = false;
					} else {
						/// SI LOGRA GUARDAR EL CAMBIO, CALCULA GANANCIA Y RESTA DE LA CANTIDAD TOTAL VENDIDA, LO QUE YA SE VENDIÓ DEL STOCK ACTUAL
						ganancia +=(detalle.getArticulo().getPrecio_venta()*descontar)-(stock.getPreciocompra()*descontar); // ACUMULACION DE GANANCIA
						descontar -= cant_vendida;
					}
				}
			}
			
		}
		/// ASIGNA EL VALOR FINAL DE GANANCIA A LA VENTA
		nuevo.setGanancia(ganancia);
		
		/// SI SE DESACTIVÓ LA BANDERA, CANCELA REGISTRO DE LA VENTA
		if (!flag) {
			return "ERROR";
		}
		
		/// GUARDA LA VENTA Y REGRESA
		if (dataAccess.insertarVenta(nuevo)) {
			return "AGREGADO";
		} else {
			return "NO AGREGADO";
		}
	}

	@Override
	public String eliminarVenta(Venta eliminar, Stock stock) {
		
		/// CARGA LA VENTA COMPLETA DE LA DB USANDO EL ID DE LA VENTA PASADA
		Venta aux = dataAccess.obtenerVentaPorID(eliminar.getNum_venta());
		if(aux != null) {
			/// SI LA VENTA SE ENCUENTRA ACTIVA - SE PROCEDE A ELIMINAR
			if(aux.getEstado().getNombre().equals("ACTIVO")) {
				if (dataAccess.actualizarVenta(eliminar)) {
					/// SI SE ELIMINA LA VENTA, SE INTENTA REGRESAR EL STOCK
					if(recuperarVenta(eliminar, stock)) {
						return "ELIMINADO"; 
					}	
				}else {
					return "NO ELIMINADO";
				}
			} else {
				return "ELIMINACION PREVIA";
			}
		}
			return "ERROR";	
	}

	@Override
	public String actualizarVenta(Venta modificar) {
		if (dataAccess.actualizarVenta(modificar)) {
			return "MODIFICADO";
		}else {
			return "NO MODIFICADO";
		}
	}
	
	@Override
	public float gananciaEntreFechas (Date fechaInicio, Date fechaFin) {
		ArrayList<Venta> ListaVentas = dataAccess.obtenerVentasEntreFechas(fechaInicio,fechaFin);
		float gananciaTotal = 0;
	    for (Venta venta : ListaVentas) {
	            gananciaTotal += venta.getGanancia();
	    }
	    return gananciaTotal;
	}
	
	@Override
	public List<Venta> tablaFiltradaFechas(Date fechaInicio, Date fechaFin){
		return dataAccess.obtenerVentasEntreFechas(fechaInicio,fechaFin);
	}
	
	private boolean recuperarVenta(Venta venta, Stock stock) {
		boolean flag = true;
		Stock aux = stock;
		
		/// ITERA ENTRE LOS DETALLES DE LA VENTA
		for (Detalle_venta detalle : venta.getDetalle()) {
			/// OBTIENE EL STOCK MÁS RECIENTE CON CANTIDAD MAYOR A 0 DEL ARTICULO PEDIDO
			stock = dataAccess2.obtenerUltimoRegistro(detalle.getArticulo().getNombre());
			if(stock != null) {
				/// SI EL STOCK EXISTE, AGREGA LA CANTIDAD DEL DETALLE DE VUELTA AL STOCK
				stock.sumarCantidad(detalle.getCantidad());
				if(!dataAccess2.modificarStock(stock)) {
					flag = false;
				} 
			} else {
				/// SE CARGA EL REGISTRO DE STOCK MÁS RECIENTE CON CANTIDAD = 0
				stock = dataAccess2.obtenerUltimoRegistroVacio(detalle.getArticulo().getNombre());
				if(stock != null) {
					stock.setCantidad(detalle.getCantidad());
					aux = stock;
				} else {
					/// SI NO EXISTE EL STOCK, CREA UNO NUEVO CON UN PRECIO DE COMPRA DEL 70% DEL VALOR DE VENTA
					aux.setArticulo(detalle.getArticulo());
					aux.setCantidad(detalle.getCantidad());
					aux.setFechaingreso(venta.getFecha());
					aux.setPreciocompra((float) ((detalle.getImporte()/detalle.getCantidad())*0.7)); 
				}
				if(!dataAccess2.insertarStock(aux)) {
					flag = false;
				}
			}
		}
		return flag;
	}
	
}
