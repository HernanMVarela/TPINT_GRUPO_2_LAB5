package frgp.utn.edu.ar.dominio;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "CLIENTES")
public class Cliente extends Persona implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int ID;
	
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="ID_ESTADOCLI",nullable = false)
	private Estado_Cli estado;

	public Cliente() {}
	public Cliente(Persona persona, Estado_Cli estado) 
	{
		
		this.estado=estado;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@Override
	public String toString() {
		return "Cliente [ID=" + ID + ", estado=" + estado + "]";
	}
	public Estado_Cli getEstado() {
		return estado;
	}
	public void setEstado(Estado_Cli estado) {
		this.estado = estado;
	}

}
