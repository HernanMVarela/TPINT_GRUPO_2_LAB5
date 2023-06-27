package frgp.utn.edu.ar.servicio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import frgp.utn.edu.ar.dominio.Venta;

public interface VentaServicio {

	public ArrayList<Venta> obtenerVentas();

	public Venta obtenerUnRegistro(int id);

	public String insertarVenta(Venta nuevo);

	public String eliminarVenta(Venta nombre) ;

	public String actualizarVenta(Venta modificar);
	
	public float gananciaEntreFechas (Date fechaInicio, Date fechaFin);

	public List<Venta> tablaFiltradaFechas(Date fechaInicio, Date fechaFin);
}
