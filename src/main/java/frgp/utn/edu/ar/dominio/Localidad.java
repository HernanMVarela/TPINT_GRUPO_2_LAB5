package frgp.utn.edu.ar.dominio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="LOCALIDADES")
public class Localidad implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_LOCALIDAD")
	private int ID;
	
	@Column(name="NOMBRE", nullable = false)
	private String nombre;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="ID_PROVINCIA", nullable = false)
	private Provincia provincia;
	
	public Localidad(){}
	public Localidad(String nombre)
	{
		this.nombre=nombre;
	}

	@Override
	public String toString() {
		return "Localidad [ID=" + ID + ", localidad=" + nombre + ", provincia=" + provincia + "]";
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getLocalidad() {
		return nombre;
	}

	public void setLocalidad(String localidad) {
		this.nombre = localidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	
}
