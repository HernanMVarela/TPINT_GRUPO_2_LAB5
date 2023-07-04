package frgp.utn.edu.ar.dao;

import java.util.ArrayList;
import java.util.Map;

import frgp.utn.edu.ar.dominio.Usuario;


public interface UsuarioDao {
	public boolean insertarUsuario(Usuario nuevo);
	public Usuario obtenerUsuarioPorID(int ID);
	public Usuario obtenerUsuarioPorDNI(String DNI);
	public boolean existeUsuario(int ID);
	public boolean existeUsuario(String ID);
	public ArrayList<Usuario> obtenerUsuarios();
	public void eliminarUsuario(int ID);
	public boolean actualizarUsuario(Usuario modificar);
	public Usuario obtenerUsuarioPorUser(String username);
	public Map<String, Long> obtenerUsuariosPorRol();
}
