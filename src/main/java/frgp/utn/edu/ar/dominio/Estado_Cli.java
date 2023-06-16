package frgp.utn.edu.ar.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ESTADOS_CLIENTE")
public class Estado_Cli implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_ESTADOCLI")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	@Column(name="NOMBRE", nullable = false)
	private String nombre;
	
	public Estado_Cli() {}
	public Estado_Cli(String nombre)
	{
		this.nombre=nombre;
	}
	@Override
	public String toString() {
		return "Estado_Cli [ID=" + ID + ", nombre=" + nombre + "]";
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
