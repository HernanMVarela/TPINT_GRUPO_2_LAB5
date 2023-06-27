package frgp.utn.edu.ar.servicioImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import frgp.utn.edu.ar.dao.StockDao;
import frgp.utn.edu.ar.dao.VentaDao;
import frgp.utn.edu.ar.dominio.Detalle_venta;
import frgp.utn.edu.ar.dominio.Stock;
import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.dominio.Venta;
import frgp.utn.edu.ar.servicio.VentaServicio;

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
		List<Detalle_venta> lista = nuevo.getDetalle();		
		
		/// VALIDA QUE LA CANTIDAD TOTAL DE STOCK SEA MAYOR A LA VENTA
		for (Detalle_venta detalle : lista) {
			List<Stock> listaStock = dataAccess2.obtenerDeArticulo(detalle.getArticulo().getNombre());
			int cantidad_total = 0;
			for (Stock stock : listaStock) {	
				cantidad_total += stock.getCantidad();
			}
			
			if(detalle.getCantidad()>cantidad_total) {
				return "VENTA CANCELADA";
			}
		}
		
		boolean flag = true;
		
		for (Detalle_venta detalle : lista) {
			int descontar = detalle.getCantidad();
			List<Stock> listaStock = dataAccess2.obtenerDeArticulo(detalle.getArticulo().getNombre());
			
			Collections.sort(listaStock);
			Iterator<Stock> ite = listaStock.iterator();			
			
			while(ite.hasNext()) {
				Stock aux = ite.next();
				if(aux.getCantidad()!=0) {
					if(aux.getCantidad()>=descontar && descontar != 0) {
						aux.setCantidad(aux.getCantidad()-descontar);
						if(!dataAccess2.modificarStock(aux)) {
							flag = false;
						} else {
							nuevo.setGanancia(nuevo.getGanancia()+((detalle.getArticulo().getPrecio_venta()*descontar)-(aux.getPreciocompra()*descontar))); // ACUMULACION DE GANANCIA
							descontar = 0;
						}
					} else if (aux.getCantidad()<descontar && descontar != 0) {
						int cant_vendida = aux.getCantidad();
						aux.setCantidad(0);
						if(!dataAccess2.modificarStock(aux)) {
							flag = false;
						} else {
							nuevo.setGanancia(nuevo.getGanancia()+((detalle.getArticulo().getPrecio_venta()*descontar)-(aux.getPreciocompra()*descontar))); // ACUMULACION DE GANANCIA
							descontar -= cant_vendida;
						}
					}
				}

			}
		}
		if (!flag) {
			return "ERROR";
		}
		
		if (dataAccess.insertarVenta(nuevo)) {
			return "AGREGADO";
		} else {
			return "NO AGREGADO";
		}
	}

	@Override
	public String eliminarVenta(Venta eliminar) {
		Venta aux = dataAccess.obtenerVentaPorID(eliminar.getNum_venta());
		if(aux != null) {
			if(aux.getEstado().getNombre().equals("ACTIVO")) {
				return this.actualizarVenta(eliminar);
			} else {
				return "ELIMINACION PREVIA";
			}
		} else {
			return "ERROR";
		}	
		
	}

	@Override
	public String actualizarVenta(Venta modificar) {
		if(modificar.getEstado().getNombre().equals("INACTIVO")) {
			if (dataAccess.actualizarVenta(modificar)) {
				return "ELIMINADO";
			}else {
				return "NO ELIMINADO";
			}
		} else {
			if (dataAccess.actualizarVenta(modificar)) {
				return "MODIFICADO";
			}else {
				return "NO MODIFICADO";
			}
		}
	}
	public float gananciaEntreFechas (Date fechaInicio, Date fechaFin) {
		ArrayList<Venta> ListaVentas = dataAccess.obtenerVentas();
		float gananciaTotal = 0;
	    
	    for (Venta venta : ListaVentas) {
	        if (venta.getFecha().after(fechaInicio) && venta.getFecha().before(fechaFin)) {
	            gananciaTotal += venta.getGanancia();
	        }
	    }
	    
	    return gananciaTotal;
	}
}
