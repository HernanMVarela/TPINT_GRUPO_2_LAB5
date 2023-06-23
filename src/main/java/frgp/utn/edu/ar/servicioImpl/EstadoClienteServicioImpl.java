package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.EstadoClienteDao;
import frgp.utn.edu.ar.dominio.Estado_Cli;
import frgp.utn.edu.ar.servicio.EstadoClienteServicio;

public class EstadoClienteServicioImpl implements EstadoClienteServicio {

	private EstadoClienteDao dataAccess = null;

	public void setDataAccess(EstadoClienteDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Estado_Cli> obtenerEstados() {
		return dataAccess.obtenerEstados();
	}

	@Override
	public Estado_Cli obtenerUnRegistro(int ID) {
		return dataAccess.obtenerEstadoPorID(ID);
	}

}
