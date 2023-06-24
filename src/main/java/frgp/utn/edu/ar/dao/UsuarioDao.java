package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Usuario;


public interface UsuarioDao {
	public boolean insertarUsuario(Usuario nuevo);
	public Usuario obtenerUsuarioPorID(int ID);
	public boolean existeUsuario(int ID);
	public ArrayList<Usuario> obtenerUsuarios();
	public void eliminarUsuario(int ID);
	public boolean actualizarUsuario(Usuario modificar);
}
