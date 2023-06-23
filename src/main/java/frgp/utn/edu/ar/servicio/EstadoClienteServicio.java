package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Estado_Cli;

public interface EstadoClienteServicio {
	public ArrayList<Estado_Cli> obtenerEstados();

	public Estado_Cli obtenerUnRegistro(int ID);
}
