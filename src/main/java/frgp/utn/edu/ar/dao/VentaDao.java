package frgp.utn.edu.ar.dao;

import java.sql.Date;
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
	
	//Obtiene todas las Ventas de ciertas fechas
	public ArrayList<Venta> obtenerVentasEntreFechas(Date fechaInicio, Date fechaFin);

	//Elimina un Venta a aprtir del nombre
	public void eliminarVenta(String nombre);

	//Actualiza los datos de unaVenta
	public boolean actualizarVenta(Venta modificar);

	public Venta obtenerVentaPorID(int ID);
	

}
