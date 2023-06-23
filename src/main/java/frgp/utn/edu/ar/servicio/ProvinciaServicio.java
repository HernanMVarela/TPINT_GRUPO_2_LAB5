package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Provincia;

public interface ProvinciaServicio {
	public ArrayList<Provincia> obtenerProvincias();

	public Provincia obtenerUnRegistro(int ID);
}
