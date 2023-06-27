package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Estado_Venta;

public interface EstadoVentaServicio {
	public ArrayList<Estado_Venta> obtenerEstados();

	public Estado_Venta obtenerUnRegistro(int ID);
}
