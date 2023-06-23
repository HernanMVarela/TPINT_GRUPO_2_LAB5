package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Estado_Cli;

public interface EstadoClienteDao {
	//Obtiene todos los tipos de articulos
	public ArrayList<Estado_Cli> obtenerEstados();
	
	//Obtiene una tipo de articulo por id
	public Estado_Cli obtenerEstadoPorID(int id);

}
