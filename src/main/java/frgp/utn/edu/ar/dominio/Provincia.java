package frgp.utn.edu.ar.dominio;

import java.io.Serializable;

public class Provincia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int ID;
	private String nombre;
	
	Provincia(){}

	@Override
	public String toString() {
		return "Provincia [ID=" + ID + ", nombre=" + nombre + "]";
	}

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
