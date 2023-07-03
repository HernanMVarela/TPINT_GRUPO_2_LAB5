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

		//Busca el primer stock activo de un artículo
				public Stock obtenerUltimoRegistro(String articulo);

	    //Devuelve lista sin repetición de articulos
				public List<Stock> obtenerRegistrosUnicos();

				Stock obtenerUltimoRegistroVacio(String articulo);
		
}
