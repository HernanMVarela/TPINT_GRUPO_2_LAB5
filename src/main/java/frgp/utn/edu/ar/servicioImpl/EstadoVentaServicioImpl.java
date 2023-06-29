package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import frgp.utn.edu.ar.dao.EstadoVentaDao;
import frgp.utn.edu.ar.dominio.Estado_Venta;
import frgp.utn.edu.ar.servicio.EstadoVentaServicio;

@Service
public class EstadoVentaServicioImpl implements EstadoVentaServicio {

	private EstadoVentaDao dataAccess = null;

	public void setDataAccess(EstadoVentaDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Estado_Venta> obtenerEstados() {
		return dataAccess.obtenerEstados();
	}

	@Override
	public Estado_Venta obtenerUnRegistro(int ID) {
		return dataAccess.obtenerEstadoPorID(ID);
	}
}
