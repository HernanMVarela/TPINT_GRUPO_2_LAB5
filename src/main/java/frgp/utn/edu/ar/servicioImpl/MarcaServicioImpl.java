package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.MarcaDao;
import frgp.utn.edu.ar.dominio.Marca;
import frgp.utn.edu.ar.servicio.MarcaServicio;

public class MarcaServicioImpl implements MarcaServicio {
	
	private MarcaDao dataAccess = null;

	public void setDataAccess(MarcaDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Marca> obtenerMarcas() {
		return dataAccess.obtenerMarcas();
	}

	@Override
	public Marca obtenerUnRegistro(int ID) {
		return dataAccess.obtenerMarcaPorID(ID);
	}

}
