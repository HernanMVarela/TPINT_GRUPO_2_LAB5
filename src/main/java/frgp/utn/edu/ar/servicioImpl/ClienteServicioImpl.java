package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.ArticuloDao;
import frgp.utn.edu.ar.dao.ClienteDao;
import frgp.utn.edu.ar.dominio.Articulo;
import frgp.utn.edu.ar.dominio.Cliente;
import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.servicio.ArticuloServicio;
import frgp.utn.edu.ar.servicio.ClienteServicio;

public class ClienteServicioImpl implements ClienteServicio{

	private ClienteDao dataAccess = null;

	public void setDataAccess(ClienteDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Cliente> obtenerClientes() {
		return dataAccess.obtenerClientes();
	}

	@Override
	public Cliente obtenerUnRegistro(String nombre) {
		return dataAccess.obtenerClientePorNombre(nombre);
	}

	@Override
	public String insertarCliente(Cliente nuevo) {
		if(!dataAccess.existeCliente(nuevo.getNombre())) {
			if (dataAccess.insertarCliente(nuevo)) {
				return "AGREGADO";
			} else {
				return "NO AGREGADO";
			}
		}
		
		return "EXISTE";	
	}

	@Override
	public void eliminarCliente(String nombre) {
		dataAccess.eliminarCliente(nombre);
		
	}

	@Override
	public String actualizarCliente(Cliente modificar) {
		
		return "MODIFICADO";
	}

}
