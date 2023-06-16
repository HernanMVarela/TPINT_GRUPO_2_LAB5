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
@Table(name = "USUARIOS")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="USERNAME", nullable = true)
	private String nombreU;
	
	@Column(name="PASSWORD", nullable = true)
	private String passU;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="ID_TIPOUSER",nullable = false)
	private Tipo_Usuario tipo;
	
	public Usuario()
	{
		
	}

	public String getNombreU() {
		return nombreU;
	}

	public void setNombreU(String nombreU) {
		this.nombreU = nombreU;
	}

	public String getPassU() {
		return passU;
	}

	public void setPassU(String passU) {
		this.passU = passU;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario(int id, String nombreU, String passU) {
		super();
		this.id = id;
		this.nombreU = nombreU;
		this.passU = passU;
	}

	public Tipo_Usuario getTipo() {
		return tipo;
	}

	public void setTipo(Tipo_Usuario tipo) {
		this.tipo = tipo;
	}
	
}
