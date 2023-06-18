package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Marca;

public interface MarcaDao {
	//Obtiene todos los tipos de articulos
	public ArrayList<Marca> obtenerMarcas();
	
	//Obtiene una tipo de articulo por id
	public Marca obtenerMarcaPorID(int id);
}
