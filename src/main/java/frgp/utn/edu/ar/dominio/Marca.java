package frgp.utn.edu.ar.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MARCAS")
public class Marca implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_MARCA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	@Column(name="NOMBRE", nullable = false)
	private String nombre;
	
	public Marca(){}
	public Marca(String nombre)
	{
		this.nombre=nombre;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
