package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import frgp.utn.edu.ar.dao.TipoUsuarioDao;
import frgp.utn.edu.ar.dominio.Tipo_Usuario;
import frgp.utn.edu.ar.servicio.TipoUsuarioServicio;

@Service
public class TipoUsuarioServicioImpl implements TipoUsuarioServicio {

	private TipoUsuarioDao dataAccess = null;

	public void setDataAccess(TipoUsuarioDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Tipo_Usuario> obtenerTiposDeUsuarios() {
		return dataAccess.obtenerTiposUsuarios();
	}

	@Override
	public Tipo_Usuario obtenerUnRegistro(int ID) {
		return dataAccess.obtenerTiposUsuariosPorID(ID);
	}

}
