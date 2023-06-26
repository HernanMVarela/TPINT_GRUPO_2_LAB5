package frgp.utn.edu.ar.dao;

import java.util.ArrayList;
import java.util.List;

import frgp.utn.edu.ar.dominio.Stock;

public interface StockDao {
	
		//Obtiene todos los elementos en Stock
				public ArrayList<Stock> obtenerStock();
		
		//Agrega Stock
				public boolean insertarStock(Stock nuevo);
				
		//Modifica Stock
				public boolean modificarStock(Stock modif);
				
		//Busca elementos de stock de X articulo
				public List<Stock> obtenerDeArticulo(String articulo);
		
}
