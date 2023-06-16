package frgp.utn.edu.ar.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Estado_User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_ESTADOUSER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	@Column(name="NOMBRE", nullable = false)
	private String nombre;
	
	public Estado_User() {}
	public Estado_User(String nombre)
	{
		this.nombre=nombre;
	}
	@Override
	public String toString() {
		return "Estado_User [ID=" + ID + ", nombre=" + nombre + "]";
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
