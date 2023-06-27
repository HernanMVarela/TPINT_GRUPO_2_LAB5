package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Estado_Venta;

public interface EstadoVentaDao {
	public ArrayList<Estado_Venta> obtenerEstados();	
	public Estado_Venta obtenerEstadoPorID(int id);
}
