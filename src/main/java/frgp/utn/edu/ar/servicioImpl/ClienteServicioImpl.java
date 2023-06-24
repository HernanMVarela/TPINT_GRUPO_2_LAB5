package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.ClienteDao;
import frgp.utn.edu.ar.dao.EstadoClienteDao;
import frgp.utn.edu.ar.dominio.Cliente;
import frgp.utn.edu.ar.servicio.ClienteServicio;

public class ClienteServicioImpl implements ClienteServicio{

	private ClienteDao dataAccess = null;
	private EstadoClienteDao dataAccess2 = null;

	public void setDataAccess(ClienteDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Cliente> obtenerClientes() {
		return dataAccess.obtenerClientes();
	}

	@Override
	public Cliente obtenerUnRegistro(int ID) {
		return dataAccess.obtenerClientePorID(ID);
	}

	@Override
	public String insertarCliente(Cliente nuevo) {
		if(dataAccess.existeCliente(nuevo.getDNI())) {
			Cliente aux = dataAccess.obtenerClientePorDNI(nuevo.getDNI());
			if(aux.getEstado().getNombre().equals("ACTIVO")) {
				return "EXISTE";
			} else {
				aux.setEstado(dataAccess2.obtenerEstadoPorID(1));
				if(dataAccess.actualizarCliente(aux)) {
					return "REACTIVADO";
				}else {
					return "ERROR";
				}
			}
		}
		
		if(!dataAccess.existeCliente(nuevo.getID())) {
			if (dataAccess.insertarCliente(nuevo)) {
				return "AGREGADO";
			} else {
				return "NO AGREGADO";
			}
		}
		return "EXISTE";	
		
	}

	@Override
	public String eliminarCliente(Cliente eliminar) {
		Cliente aux = dataAccess.obtenerClientePorID(eliminar.getID());
		if(aux != null) {
			if(aux.getEstado().getNombre().equals("ACTIVO")) {
				return this.actualizarCliente(eliminar);
			} else {
				return "ELIMINACION PREVIA";
			}
		} else {
			return "ERROR";
		}
	}

	@Override
	public String actualizarCliente(Cliente modificar) {
		if(modificar.getEstado().getNombre().equals("INACTIVO")) {
			if (dataAccess.actualizarCliente(modificar)) {
				return "ELIMINADO";
			}else {
				return "NO ELIMINADO";
			}
		} else {
			if (dataAccess.actualizarCliente(modificar)) {
				return "MODIFICADO";
			}else {
				return "NO MODIFICADO";
			}
		}
	}

}
