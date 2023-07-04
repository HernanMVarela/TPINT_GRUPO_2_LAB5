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
	
	@ManyToOne(cascade= {CascadeType.MERGE})
	@JoinColumn(name="ID_MARCA",nullable = false)
	private Marca marca;
	
	@ManyToOne(cascade= {CascadeType.MERGE})
	@JoinColumn(name="ID_TIPOART",nullable = false)
	private Tipo_Articulo tipo;
	
	@Column(name="PRECIO_VENTA", nullable = true)
	private float precio_venta;
	
	private boolean estado;
	
	public Articulo(){}
	
	@Override
	public String toString() {
		return nombre;
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

	public float getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(float precio_venta) {
		this.precio_venta = precio_venta;
	}
	
	
}
