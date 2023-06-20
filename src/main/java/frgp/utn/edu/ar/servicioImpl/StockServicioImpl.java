package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.StockDao;
import frgp.utn.edu.ar.dominio.Stock;
import frgp.utn.edu.ar.servicio.StockServicio;

public class StockServicioImpl implements StockServicio{

	private StockDao dataAccess = null;

	public void setDataAccess(StockDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Stock> obtenerStock() {
		return dataAccess.obtenerStock();
	}
	
	@Override
	public String ingresarStock(Stock nuevo) 
	{
		if (dataAccess.insertarStock(nuevo)) 
		{
			return "AGREGADO";
		} 
		else 
		{
			return "NO AGREGADO";
		}
	}

}
