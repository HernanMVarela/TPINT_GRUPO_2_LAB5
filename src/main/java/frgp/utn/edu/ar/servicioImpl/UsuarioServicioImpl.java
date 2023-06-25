package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.UsuarioDao;
import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.servicio.UsuarioServicio;

public class UsuarioServicioImpl implements UsuarioServicio{

	private UsuarioDao dataAccess = null;

	public void setDataAccess(UsuarioDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Usuario> obtenerUsuarios() {
		return dataAccess.obtenerUsuarios();
	}

	@Override
	public Usuario obtenerUnRegistro(int ID) {
		return dataAccess.obtenerUsuarioPorID(ID);
	}

	@Override
	public String insertarUsuario(Usuario nuevo) {
		if(!dataAccess.existeUsuario(nuevo.getId())) {
			if (dataAccess.insertarUsuario(nuevo)) {
				return "AGREGADO";
			} else {
				return "NO AGREGADO";
			}
		}
		
		return "EXISTE";	
	}

	@Override
	public void eliminarUsuario(int ID) {
		dataAccess.eliminarUsuario(ID);
		
	}

	@Override
	public String actualizarUsuario(Usuario modificar) {		
		return "MODIFICADO";
	}
	
	@Override
	public Usuario login(String username, String pass) {
		
		Usuario user = dataAccess.obtenerUsuarioPorUser(username);
		
		if(user == null) {
			return null;
		}
		
		if(!user.getPassU().equals(pass)) {
			return null;
		}
		
		if(user.getEstado().getNombre().equals("INACTIVO")) {
			return null;
		}
		
		return user;
	}
}
