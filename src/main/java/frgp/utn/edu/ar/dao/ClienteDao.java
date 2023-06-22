package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Cliente;


public interface ClienteDao {

	//Alta de un articulo
	public boolean insertarCliente(Cliente nuevo);

	//Obtiene una articulo por nombre
	public Cliente obtenerClientePorDNI(long DNI);
	
	//Verifica si existe el articulo
	public boolean existeCliente(long DNI);

	//Obtiene todos los articulo
	public ArrayList<Cliente> obtenerClientes();

	//Elimina un articulo a aprtir del nombre
	public void eliminarCliente(long DNI);

	//Actualiza los datos de unaarticulo
	public boolean actualizarCliente(Cliente modificar);
	

}
