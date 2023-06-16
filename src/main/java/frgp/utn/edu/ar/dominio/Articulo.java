package frgp.utn.edu.ar.dominio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ARTICULOS")
public class Articulo implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="DESCRIPCION", nullable = true)
	private String descripcion;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="ID_MARCA",nullable = false)
	private Marca marca;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="ID_TIPOART",nullable = false)
	private Tipo_Articulo tipo;
	
	@Column(name="PRECIO_COMPRA", nullable = true)
	private float precio_compra;
	
	private Boolean estado;
	
	public Articulo(){}
	
	@Override
	public String toString() {
		return "Articulo [nombre=" + nombre + ", descripcion=" + descripcion + ", marca=" + marca + ", tipo=" + tipo
				+ ", precio_compra=" + precio_compra + "]";
	}
	
	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Tipo_Articulo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo_Articulo tipo) {
		this.tipo = tipo;
	}

	public float getPrecio_compra() {
		return precio_compra;
	}

	public void setPrecio_compra(float precio_compra) {
		this.precio_compra = precio_compra;
	}
	
	
}
