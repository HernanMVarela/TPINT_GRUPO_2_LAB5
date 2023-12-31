package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;
import java.util.List;

import frgp.utn.edu.ar.dominio.Stock;

public interface StockServicio {

	public ArrayList<Stock> obtenerStock();

	public String ingresarStock(Stock nuevo);

	public List<Stock> obtenerRegistrosUnicos();
	
}
