package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import frgp.utn.edu.ar.dao.ProvinciaDao;
import frgp.utn.edu.ar.dominio.Provincia;
import frgp.utn.edu.ar.servicio.ProvinciaServicio;

@Service
public class ProvinciaServicioImpl implements ProvinciaServicio {

	private ProvinciaDao dataAccess = null;

	public void setDataAccess(ProvinciaDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Provincia> obtenerProvincias() {
		return dataAccess.obtenerProvincias();
	}

	@Override
	public Provincia obtenerUnRegistro(int ID) {
		return dataAccess.obtenerProvinciaPorID(ID);
	}

}
