package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;
import java.util.Map;

import frgp.utn.edu.ar.dominio.Articulo;

public interface ArticuloServicio {

	public ArrayList<Articulo> obtenerArticulos();

	public Articulo obtenerUnRegistro(String nombre);

	public String insertarArticulo(Articulo nuevo);

	public void eliminarArticulo(String nombre) ;

	public String actualizarArticulo(Articulo modificar);

	public Map<String, Long> obtenerCantidadArticulosPorTipo();
	
}
