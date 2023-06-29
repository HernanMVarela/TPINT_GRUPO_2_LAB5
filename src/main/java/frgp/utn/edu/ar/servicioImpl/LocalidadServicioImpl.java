package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import frgp.utn.edu.ar.dao.LocalidadDao;
import frgp.utn.edu.ar.dominio.Localidad;
import frgp.utn.edu.ar.servicio.LocalidadServicio;

@Service
public class LocalidadServicioImpl implements LocalidadServicio {
	
	private LocalidadDao dataAccess = null;

	public void setDataAccess(LocalidadDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Localidad> obtenerLocalidades() {
		return dataAccess.obtenerLocalidades();
	}

	@Override
	public Localidad obtenerUnRegistro(int ID) {
		return dataAccess.obtenerLocalidadPorID(ID);
	}

}
