package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Cliente;


public interface ClienteDao {

	//Alta de un articulo
	public boolean insertarCliente(Cliente nuevo);

	//Obtiene una articulo por nombre
	public Cliente obtenerClientePorID(int ID);
	
	//Obtiene una articulo por nombre
	public Cliente obtenerClientePorDNI(String DNI);
	
	//Verifica si existe el articulo
	public boolean existeCliente(int ID);
	
	//Verifica si existe el articulo
	public boolean existeCliente(String DNI);

	//Obtiene todos los articulo
	public ArrayList<Cliente> obtenerClientes();

	//Elimina un articulo a aprtir del nombre
	public void eliminarCliente(int ID);

	//Actualiza los datos de unaarticulo
	public boolean actualizarCliente(Cliente modificar);
	

}
