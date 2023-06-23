package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Localidad;

public interface LocalidadServicio {
	
	public ArrayList<Localidad> obtenerLocalidades();

	public Localidad obtenerUnRegistro(int ID);
}
