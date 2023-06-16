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
@Table(name = "DETALLES_VENTA")
public class Stock implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int ID;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="ARTICULO", nullable = false)
	private Articulo articulo;
	
	@Column(name="FECHA_INGRESO", nullable = true)
	private Date fecha_ingreso;
	
	@Column(name="CANTIDAD", nullable = true)
	private int cantidad;
	
	@Column(name="PRECIO_COMPRA", nullable = true)
	private float precio_compra;
	
	public Stock() {}
	public Stock(Articulo articulo, Date fecha, int cantidad, float precio_compra) {
		this.articulo=articulo;
		this.fecha_ingreso=fecha;
		this.cantidad=cantidad;
		this.precio_compra=precio_compra;
	}
	@Override
	public String toString() {
		return "Stock [ID=" + ID + ", articulo=" + articulo + ", fecha=" + fecha_ingreso + ", cantidad=" + cantidad
				+ ", precio_compra=" + precio_compra + "]";
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public Date getFecha() {
		return fecha_ingreso;
	}
	public void setFecha(Date fecha) {
		this.fecha_ingreso = fecha;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecio_compra() {
		return precio_compra;
	}
	public void setPrecio_compra(float precio_compra) {
		this.precio_compra = precio_compra;
	}

}
