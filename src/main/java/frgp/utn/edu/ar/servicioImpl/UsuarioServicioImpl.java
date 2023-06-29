package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import frgp.utn.edu.ar.dao.EstadoUsuarioDao;
import frgp.utn.edu.ar.dao.UsuarioDao;
import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.servicio.UsuarioServicio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

	private UsuarioDao dataAccess = null;
	private EstadoUsuarioDao dataAccess2 = null;

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
		if(dataAccess.existeUsuario(nuevo.getDNI())) {
			Usuario aux = dataAccess.obtenerUsuarioPorDNI(nuevo.getDNI());
			if(aux.getEstado().getNombre().equals("ACTIVO")) {
				return "EXISTE";
			} else {
				aux.setEstado(dataAccess2.obtenerEstadoPorID(1));
				if(dataAccess.actualizarUsuario(aux)) {
					return "REACTIVADO";
				}else {
					return "ERROR";
				}
			}
		}
		
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
	public String eliminarUsuario(Usuario eliminar) {
		Usuario aux = dataAccess.obtenerUsuarioPorID(eliminar.getId());
		if(aux != null) {
			if(aux.getEstado().getNombre().equals("ACTIVO")) {
				return this.actualizarUsuario(eliminar);
			} else {
				return "ELIMINACION PREVIA";
			}
		} else {
			return "ERROR";
		}		
	}

	@Override
	public String actualizarUsuario(Usuario modificar) {		
		if(modificar.getEstado().getNombre().equals("INACTIVO")) {
			if (dataAccess.actualizarUsuario(modificar)) {
				return "ELIMINADO";
			}else {
				return "NO ELIMINADO";
			}
		} else {
			if (dataAccess.actualizarUsuario(modificar)) {
				return "MODIFICADO";
			}else {
				return "NO MODIFICADO";
			}
		}
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
	
	@Override
	public Map<String, Long> obtenerUsuariosPorRol() {
		return dataAccess.obtenerUsuariosPorRol();
	}
}
