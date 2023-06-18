package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Tipo_Articulo;

public interface TipoArticuloDao {
	//Obtiene todos los tipos de articulos
	public ArrayList<Tipo_Articulo> obtenerTiposArticulo();
	
	//Obtiene una tipo de articulo por id
	public Tipo_Articulo obtenerTiposArticuloPorID(int id);

}
