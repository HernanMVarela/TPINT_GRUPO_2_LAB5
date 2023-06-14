package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Articulo;


public interface ArticuloDao {

	//Alta de persona
	public void insertarArticulo(Articulo nuevo);

	//Obtiene una persona por dni
	public Articulo obtenerArticuloPorNombre(String nombre);

	//Obtiene todas las presonas
	public ArrayList<Articulo> obtenerArticulos();

	//Elimina una presona a aprtir del dni
	public void eliminarArticulo(String nombre);

	//Actualiza los datos de una persona
	public void actualizarArticulo(Articulo modificar);
	

}
