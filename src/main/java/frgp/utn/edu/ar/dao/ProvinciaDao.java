package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Provincia;

public interface ProvinciaDao {
	//Obtiene todos los tipos de articulos
	public ArrayList<Provincia> obtenerProvincias();
	
	//Obtiene una tipo de articulo por id
	public Provincia obtenerProvinciaPorID(int id);

}
