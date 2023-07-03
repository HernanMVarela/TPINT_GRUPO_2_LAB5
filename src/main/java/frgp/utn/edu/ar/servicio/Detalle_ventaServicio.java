package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;
import java.util.Map;

import frgp.utn.edu.ar.dominio.Detalle_venta;

public interface Detalle_ventaServicio {

	public ArrayList<Detalle_venta> obtenerDetalle_ventas();

	public Detalle_venta obtenerUnRegistro(String nombre);

	public String insertarDetalle_venta(Detalle_venta nuevo);

	public void eliminarDetalle_venta(String nombre) ;

	public String actualizarDetalle_venta(Detalle_venta modificar);

	public Map<String, Long> obtenerProductosPorCantidad();
	
}
