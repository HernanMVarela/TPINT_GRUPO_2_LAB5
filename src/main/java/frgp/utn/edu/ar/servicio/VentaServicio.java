package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Venta;

public interface VentaServicio {

	public ArrayList<Venta> obtenerVentas();

	public Venta obtenerUnRegistro(String nombre);

	public String insertarVenta(Venta nuevo);

	public void eliminarVenta(String nombre) ;

	public String actualizarVenta(Venta modificar);
	
}
