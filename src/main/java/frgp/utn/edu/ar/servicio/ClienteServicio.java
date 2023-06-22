package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Cliente;

public interface ClienteServicio {
	
	public ArrayList<Cliente> obtenerClientes();

	public Cliente obtenerUnRegistro(long DNI);

	public String insertarCliente(Cliente nuevo);

	public void eliminarCliente(long DNI) ;

	public String actualizarCliente(Cliente modificar);

}