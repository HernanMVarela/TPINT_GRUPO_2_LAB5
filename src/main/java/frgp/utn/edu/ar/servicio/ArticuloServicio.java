package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Articulo;

public interface ArticuloServicio {

	public ArrayList<Articulo> obtenerArticulos();

	public Articulo obtenerUnRegistro(String nombre);

	public boolean insertarArticulo(Articulo nuevo);

	public void eliminarArticulo(String nombre) ;

	public boolean actualizarArticulo(Articulo modificar);
	
}
