package frgp.utn.edu.ar.entidades;

import java.io.Serializable;

public class Marca implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int ID;
	private String Marca;
	
	Marca(){}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getMarca() {
		return Marca;
	}
	public void setMarca(String marca) {
		Marca = marca;
	}
	
	
	
	
}
