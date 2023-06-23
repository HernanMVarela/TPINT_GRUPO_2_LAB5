package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Localidad;

public interface LocalidadDao {
	//Obtiene todos los tipos de articulos
	public ArrayList<Localidad> obtenerLocalidades();
	
	//Obtiene una tipo de articulo por id
	public Localidad obtenerLocalidadPorID(int id);
}
