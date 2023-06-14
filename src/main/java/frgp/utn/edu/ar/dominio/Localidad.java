package frgp.utn.edu.ar.dominio;

import java.io.Serializable;

public class Localidad implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int ID;
	private String localidad;
	private Provincia provincia;
	
	Localidad(){}

	@Override
	public String toString() {
		return "Localidad [ID=" + ID + ", localidad=" + localidad + ", provincia=" + provincia + "]";
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	
}
