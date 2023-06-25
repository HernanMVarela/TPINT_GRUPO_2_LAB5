package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.VentaDao;
import frgp.utn.edu.ar.dominio.Venta;
import frgp.utn.edu.ar.servicio.VentaServicio;

public class VentaServicioImpl implements VentaServicio{

	private VentaDao dataAccess = null;

	public void setDataAccess(VentaDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Venta> obtenerVentas() {
		return dataAccess.obtenerVentas();
	}

	@Override
	public Venta obtenerUnRegistro(String nombre) {
		return dataAccess.obtenerVentaPorNombre(nombre);
	}

	@Override
	public String insertarVenta(Venta nuevo) {

			if (dataAccess.insertarVenta(nuevo)) {
				return "AGREGADO";
			} else {
				return "NO AGREGADO";
			}
	}

	@Override
	public void eliminarVenta(String nombre) {
		dataAccess.eliminarVenta(nombre);
		
	}

	@Override
	public String actualizarVenta(Venta modificar) {
		// if (modificar.getEstado()) {
		// 	if (dataAccess.actualizarVenta(modificar)) {
		// 		return "MODIFICADO";
		// 	}else {
		// 		return "NO MODIFICADO";
		// 	}
		// } else {
		// 	if (dataAccess.actualizarVenta(modificar)) {
		// 		return "ELIMINADO";
		// 	}else {
		// 		return "NO ELIMINADO";
		// 	}
		// }
		return "actualizarVenta";
	}

}
