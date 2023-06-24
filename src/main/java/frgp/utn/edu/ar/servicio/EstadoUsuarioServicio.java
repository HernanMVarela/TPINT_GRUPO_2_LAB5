package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Estado_Cli;
import frgp.utn.edu.ar.dominio.Estado_User;

public interface EstadoUsuarioServicio {
	public ArrayList<Estado_User> obtenerEstados();

	public Estado_User obtenerUnRegistro(int ID);
}
