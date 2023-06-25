package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Venta;


public interface VentaDao {

	//Alta de un Venta
	public boolean insertarVenta(Venta nuevo);

	//Obtiene una Venta por nombre
	public Venta obtenerVentaPorNombre(String nombre);
	
	//Verifica si existe el Venta
	public boolean existeVenta(String nombre);

	//Obtiene todos los Venta
	public ArrayList<Venta> obtenerVentas();

	//Elimina un Venta a aprtir del nombre
	public void eliminarVenta(String nombre);

	//Actualiza los datos de unaVenta
	public boolean actualizarVenta(Venta modificar);
	

}
