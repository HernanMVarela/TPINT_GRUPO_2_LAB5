package frgp.utn.edu.ar.entidades;

import java.io.Serializable;
import java.sql.Date;

public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long DNI;
	private String nombre;
	private String apellido;
	private String sexo;
	private Date fecha_nac;
	private String direccion;
	private Localidad localidad;
	private String correo;
	private String telefono;

	Cliente(){}

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
