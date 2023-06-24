package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Estado_Cli;
import frgp.utn.edu.ar.dominio.Estado_User;

public interface EstadoUsuarioDao {
	public ArrayList<Estado_User> obtenerEstados();	
	public Estado_User obtenerEstadoPorID(int id);
}
