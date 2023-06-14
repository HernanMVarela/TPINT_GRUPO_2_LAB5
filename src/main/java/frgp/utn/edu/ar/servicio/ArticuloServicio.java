package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Articulo;

public interface ArticuloServicio {

	ArrayList<Articulo> obtenerArticulos();

	Articulo obtenerUnRegistro(String nombre);

	void insertarArticulo(Articulo nuevo);

    void eliminarArticulo(String nombre) ;

	void actualizarArticulo(Articulo modificar);
	
}
