package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Cliente;

public interface ClienteServicio {
	
	public ArrayList<Cliente> obtenerClientes();

	public Cliente obtenerUnRegistro(String nombre);

	public String insertarCliente(Cliente nuevo);

	public void eliminarCliente(String nombre) ;

	public String actualizarCliente(Cliente modificar);

}