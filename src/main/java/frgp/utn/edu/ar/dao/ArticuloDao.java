package frgp.utn.edu.ar.dao;

import java.util.ArrayList;
import java.util.Map;

import frgp.utn.edu.ar.dominio.Articulo;


public interface ArticuloDao {

	//Alta de un articulo
	public boolean insertarArticulo(Articulo nuevo);

	//Obtiene una articulo por nombre
	public Articulo obtenerArticuloPorNombre(String nombre);
	
	//Verifica si existe el articulo
	public boolean existeArticulo(String nombre);

	//Obtiene todos los articulo
	public ArrayList<Articulo> obtenerArticulos();

	//Elimina un articulo a aprtir del nombre
	public void eliminarArticulo(String nombre);

	//Actualiza los datos de unaarticulo
	public boolean actualizarArticulo(Articulo modificar);

	Map<String, Long> obtenerCantidadArticulosPorTipo();
	

}
