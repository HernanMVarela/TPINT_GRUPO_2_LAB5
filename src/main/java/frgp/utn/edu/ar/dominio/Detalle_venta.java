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
@Table(name = "DETALLES_VENTA")
public class Detalle_venta implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_DETALLE")
	private int ID;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="NOMBRE",nullable = false)
	private Articulo articulo;
	
	@Column(name="CANTIDAD", nullable = true)
	private int cantidad;
	
	@Column(name="IMPORTE", nullable = true)
	private float importe;
	
	public Detalle_venta() {}
	public Detalle_venta(Articulo articulo, int cantidad) 
	{
		this.articulo=articulo;
		this.cantidad=cantidad;
		this.importe= this.articulo.getPrecio_venta()*this.cantidad;
	}
	@Override
	public String toString() {
		return "Detalle_venta [id=" + ID + ", articulo=" + articulo + ", cantidad=" + cantidad + ", importe=" + importe
				+ "]";
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
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	
}
