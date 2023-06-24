package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Tipo_Articulo;
import frgp.utn.edu.ar.dominio.Tipo_Usuario;

public interface TipoUsuarioServicio {
	public ArrayList<Tipo_Usuario> obtenerTiposDeUsuarios();

	public Tipo_Usuario obtenerUnRegistro(int ID);
}
