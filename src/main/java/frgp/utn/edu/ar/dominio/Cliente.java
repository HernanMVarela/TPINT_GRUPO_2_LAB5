package frgp.utn.edu.ar.dominio;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTES")
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="DNI")
	private long DNI;
	
	@Column(name="NOMBRE", nullable = false)
	private String nombre;
	@Column(name="APELLIDO", nullable = false)
	private String apellido;
	@Column(name="SEXO", nullable = false)
	private String sexo;
	@Column(name="FECHA_NACIMIENTO", nullable = false)
	private Date fecha_nac;
	@Column(name="DIRECCION", nullable = false)
	private String direccion;
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="ID_LOCALIDAD", nullable = false)
	private Localidad localidad;
	@Column(name="CORREO", nullable = false)
	private String correo;
	@Column(name="TELEFONO", nullable = false)
	private String telefono;

	public Cliente(){}

	@Override
	public String toString() {
		return "Cliente [DNI=" + DNI + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo=" + sexo
				+ ", fecha_nac=" + fecha_nac + ", direccion=" + direccion + ", localidad=" + localidad + ", correo="
				+ correo + ", telefono=" + telefono + "]";
	}

	public long getDNI() {
		return DNI;
	}

	public void setDNI(long dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}
