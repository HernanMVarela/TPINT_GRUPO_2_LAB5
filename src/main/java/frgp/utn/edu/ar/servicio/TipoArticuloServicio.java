package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Tipo_Articulo;

public interface TipoArticuloServicio {
	public ArrayList<Tipo_Articulo> obtenerTiposDeArticulo();

	public Tipo_Articulo obtenerUnRegistro(int ID);
}
