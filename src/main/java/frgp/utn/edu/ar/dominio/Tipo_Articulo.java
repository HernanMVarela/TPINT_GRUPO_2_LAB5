package frgp.utn.edu.ar.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TIPOS_ARTICULO")
public class Tipo_Articulo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_TIPOSART")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	@Column(name="NOMBRE", nullable = false)
	private String nombre;
	
	public Tipo_Articulo(){}
	public Tipo_Articulo(String nombre)
	{
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return ID + " - " + nombre;
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
