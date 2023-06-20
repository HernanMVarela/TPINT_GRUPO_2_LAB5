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
@Table(name = "STOCKS")
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
	private Date fechaingreso;
	
	@Column(name="CANTIDAD", nullable = true)
	private int cantidad;
	
	@Column(name="PRECIO_COMPRA", nullable = true)
	private float preciocompra;
	
	public Stock() {}
	public Stock(Articulo articulo, Date fechaingreso, int cantidad, float preciocompra) {
		this.articulo=articulo;
		this.fechaingreso=fechaingreso;
		this.cantidad=cantidad;
		this.preciocompra=preciocompra;
	}
	@Override
	public String toString() {
		return "Stock [ID=" + ID + ", articulo=" + articulo + ", fecha=" + fechaingreso + ", cantidad=" + cantidad
				+ ", preciocompra=" + preciocompra + "]";
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
	public Date getFechaingreso() {
		return fechaingreso;
	}
	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPreciocompra() {
		return preciocompra;
	}
	public void setPreciocompra(float preciocompra) {
		this.preciocompra = preciocompra;
	}

}
