package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;
import java.util.Map;

import frgp.utn.edu.ar.dominio.Usuario;

public interface UsuarioServicio {
	
	public ArrayList<Usuario> obtenerUsuarios();

	public Usuario obtenerUnRegistro(int ID);

	public String insertarUsuario(Usuario nuevo);

	public String eliminarUsuario(Usuario eliminar);

	public String actualizarUsuario(Usuario modificar);

	public Usuario login(String username, String pass);

	public Map<String, Long> obtenerUsuariosPorRol();

}