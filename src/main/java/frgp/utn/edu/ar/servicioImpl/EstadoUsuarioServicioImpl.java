package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.EstadoUsuarioDao;
import frgp.utn.edu.ar.dominio.Estado_User;
import frgp.utn.edu.ar.servicio.EstadoUsuarioServicio;

public class EstadoUsuarioServicioImpl implements EstadoUsuarioServicio {

	private EstadoUsuarioDao dataAccess = null;

	public void setDataAccess(EstadoUsuarioDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Estado_User> obtenerEstados() {
		return dataAccess.obtenerEstados();
	}

	@Override
	public Estado_User obtenerUnRegistro(int ID) {
		return dataAccess.obtenerEstadoPorID(ID);
	}

}
