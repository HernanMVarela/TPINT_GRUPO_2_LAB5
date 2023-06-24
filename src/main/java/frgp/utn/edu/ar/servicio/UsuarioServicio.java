package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Usuario;

public interface UsuarioServicio {
	
	public ArrayList<Usuario> obtenerUsuarios();

	public Usuario obtenerUnRegistro(int ID);

	public String insertarUsuario(Usuario nuevo);

	public void eliminarUsuario(int ID) ;

	public String actualizarUsuario(Usuario modificar);

}