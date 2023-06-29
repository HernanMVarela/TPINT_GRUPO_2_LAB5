package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import frgp.utn.edu.ar.dao.TipoArticuloDao;
import frgp.utn.edu.ar.dominio.Tipo_Articulo;
import frgp.utn.edu.ar.servicio.TipoArticuloServicio;

@Service
public class TipoArticuloServicioImpl implements TipoArticuloServicio {

	private TipoArticuloDao dataAccess = null;

	public void setDataAccess(TipoArticuloDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Tipo_Articulo> obtenerTiposDeArticulo() {
		return dataAccess.obtenerTiposArticulo();
	}

	@Override
	public Tipo_Articulo obtenerUnRegistro(int ID) {
		return dataAccess.obtenerTiposArticuloPorID(ID);
	}

}
