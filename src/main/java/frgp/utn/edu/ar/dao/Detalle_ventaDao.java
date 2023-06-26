package frgp.utn.edu.ar.dao;

import java.util.ArrayList;
import java.util.Map;

import frgp.utn.edu.ar.dominio.Detalle_venta;


public interface Detalle_ventaDao {

	//Alta de un Detalle_venta
	public boolean insertarDetalle_venta(Detalle_venta nuevo);

	//Obtiene una Detalle_venta por nombre
	public Detalle_venta obtenerDetalle_ventaPorNombre(String nombre);
	
	//Verifica si existe el Detalle_venta
	public boolean existeDetalle_venta(String nombre);

	//Obtiene todos los Detalle_venta
	public ArrayList<Detalle_venta> obtenerDetalle_ventas();

	//Elimina un Detalle_venta a aprtir del nombre
	public void eliminarDetalle_venta(String nombre);

	//Actualiza los datos de un Detalle_venta
	public boolean actualizarDetalle_venta(Detalle_venta modificar);

	Map<String, Long> obtenerProductosPorCantidad();
	

}
