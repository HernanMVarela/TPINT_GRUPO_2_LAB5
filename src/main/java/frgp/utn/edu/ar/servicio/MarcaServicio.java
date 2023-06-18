package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Marca;

public interface MarcaServicio {
	
	public ArrayList<Marca> obtenerMarcas();

	public Marca obtenerUnRegistro(int ID);
}
