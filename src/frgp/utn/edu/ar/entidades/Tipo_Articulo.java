package frgp.utn.edu.ar.entidades;

import java.io.Serializable;

public class Tipo_Articulo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int ID;
	private String nombre;
	
	Tipo_Articulo(){}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
