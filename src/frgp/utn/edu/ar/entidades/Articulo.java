package frgp.utn.edu.ar.entidades;

import java.io.Serializable;

public class Articulo implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	private String nombre;
	private String descripcion;
	private Marca marca;
	private Tipo_Articulo tipo;
	private float precio_compra;
	
	Articulo(){}
	
	@Override
	public String toString() {
		return "Articulo [nombre=" + nombre + ", descripcion=" + descripcion + ", marca=" + marca + ", tipo=" + tipo
				+ ", precio_compra=" + precio_compra + "]";
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
