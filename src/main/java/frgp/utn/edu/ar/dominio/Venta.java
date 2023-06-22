package frgp.utn.edu.ar.dominio;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "VENTAS")
public class Venta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NOMBRE")
	private int num_venta;

	@OneToMany(cascade= {CascadeType.ALL})
	@JoinColumn(name = "ID_VENTA", referencedColumnName = "NOMBRE")
	private List<Detalle_venta> detalle;
	
	@Column(name="FECHA", nullable = true)
	private Date fecha;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="ID", nullable = false)
	private Cliente cliente;
	
	public Venta(List<Detalle_venta> detalle, Date fecha, Cliente cliente) {
		this.detalle=detalle;
		this.fecha=fecha;
		this.cliente = cliente;
	}
	public Venta() {}
	
	@Override
	public String toString() {
		return "Venta [num_venta=" + num_venta + ", detalle=" + detalle + ", fecha=" + fecha + ", cliente=" + cliente
				+ "]";
	}
	public int getNum_venta() {
		return num_venta;
	}
	public void setNum_venta(int num_venta) {
		this.num_venta = num_venta;
	}
	public List<Detalle_venta> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<Detalle_venta> detalle) {
		this.detalle = detalle;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
