package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Tipo_Usuario;

public interface TipoUsuarioDao {
	
	public ArrayList<Tipo_Usuario> obtenerTiposUsuarios();	
	public Tipo_Usuario obtenerTiposUsuariosPorID(int id);
}
