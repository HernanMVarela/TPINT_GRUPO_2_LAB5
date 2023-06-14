package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.ArticuloDao;
import frgp.utn.edu.ar.dominio.Articulo;
import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.servicio.ArticuloServicio;

public class ArticuloServicioImpl implements ArticuloServicio{

	private ArticuloDao dataAccess = null;

	public void setDataAccess(ArticuloDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Articulo> obtenerArticulos() {
		return dataAccess.obtenerArticulos();
	}

	@Override
	public Articulo obtenerUnRegistro(String nombre) {
		return dataAccess.obtenerArticuloPorNombre(nombre);
	}

	@Override
	public void insertarArticulo(Articulo nuevo) {
		 dataAccess.insertarArticulo(nuevo);
		
	}

	@Override
	public void eliminarArticulo(String nombre) {
		dataAccess.eliminarArticulo(nombre);
		
	}

	@Override
	public void actualizarArticulo(Articulo modificar) {
		dataAccess.actualizarArticulo(modificar);
		
	}

}
