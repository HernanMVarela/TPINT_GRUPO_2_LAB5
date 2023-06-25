package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.Detalle_ventaDao;
import frgp.utn.edu.ar.dominio.Detalle_venta;
import frgp.utn.edu.ar.servicio.Detalle_ventaServicio;

public class Detalle_ventaServicioImpl implements Detalle_ventaServicio{

	private Detalle_ventaDao dataAccess = null;

	public void setDataAccess(Detalle_ventaDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Detalle_venta> obtenerDetalle_ventas() {
		return dataAccess.obtenerDetalle_ventas();
	}

	@Override
	public Detalle_venta obtenerUnRegistro(String nombre) {
		return dataAccess.obtenerDetalle_ventaPorNombre(nombre);
	}

	@Override
	public String insertarDetalle_venta(Detalle_venta nuevo) {
			if (dataAccess.insertarDetalle_venta(nuevo)) {
				return "AGREGADO";
			} else {
				return "NO AGREGADO";
			}
	}

	@Override
	public void eliminarDetalle_venta(String nombre) {
		dataAccess.eliminarDetalle_venta(nombre);
		
	}

	@Override
	public String actualizarDetalle_venta(Detalle_venta modificar) {
		
			if (dataAccess.actualizarDetalle_venta(modificar)) {
				return "MODIFICADO";
			}else {
				return "NO MODIFICADO";
			}
		
	}

}
