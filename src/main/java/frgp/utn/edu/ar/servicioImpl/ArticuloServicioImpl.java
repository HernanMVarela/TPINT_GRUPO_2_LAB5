package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import frgp.utn.edu.ar.dao.ArticuloDao;
import frgp.utn.edu.ar.dominio.Articulo;
import frgp.utn.edu.ar.servicio.ArticuloServicio;

@Service
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
	public String insertarArticulo(Articulo nuevo) {
		if(!dataAccess.existeArticulo(nuevo.getNombre())) {
			if (dataAccess.insertarArticulo(nuevo)) {
				return "AGREGADO";
			} else {
				return "NO AGREGADO";
			}
		}else {
			Articulo existente = dataAccess.obtenerArticuloPorNombre(nuevo.getNombre());
			if(!existente.getEstado()) {
				if (dataAccess.actualizarArticulo(nuevo)) {
					return "ACTIVADO"; 
				} else {
					return "ERROR";
				}
			}
		}
		return "EXISTE";	
	}

	@Override
	public void eliminarArticulo(String nombre) {
		dataAccess.eliminarArticulo(nombre);
	}

	@Override
	public String actualizarArticulo(Articulo modificar) {
		if (modificar.getEstado()) {
			if (dataAccess.actualizarArticulo(modificar)) {
				return "MODIFICADO";
			}else {
				return "NO MODIFICADO";
			}
		} else {
			if (dataAccess.actualizarArticulo(modificar)) {
				return "ELIMINADO";
			}else {
				return "NO ELIMINADO";
			}
		}
	}
	
	@Override
	public Map<String, Long> obtenerCantidadArticulosPorTipo() {
		return dataAccess.obtenerCantidadArticulosPorTipo();
	}

}
