package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Stock;

public interface StockDao {
	
		//Obtiene todos los elementos en Stock
				public ArrayList<Stock> obtenerStock();
		
		//Agrega Stock
				public boolean insertarStock(Stock nuevo);
		
}
