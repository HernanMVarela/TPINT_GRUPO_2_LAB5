package frgp.utn.edu.ar.dominio;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@Column(name="NUM_VENTA")
	private int num_venta;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_VENTA", referencedColumnName = "NUM_VENTA")
	private List<Detalle_venta> detalle;
	
	@Column(name="FECHA", nullable = true)
	private Date fecha;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="ID", nullable = false)
	private Cliente cliente;
	
	@Column(name="GANANCIA", nullable = true)
	private float ganancia;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="ID_ESTADOVENTA",nullable = false)
	private Estado_Venta estado;
	
	public Venta(List<Detalle_venta> detalle, Date fecha, Cliente cliente, Estado_Venta estado) {
		this.detalle=detalle;
		this.fecha=fecha;
		this.cliente = cliente;
		this.estado=estado;
	}
	
	public Venta() {}
	
	@Override
	public String toString() {
		return "Venta [num_venta=" + num_venta + ", detalle=" + detalle + ", fecha=" + fecha + ", cliente=" + cliente
				+ "]";
	}
	
	public String generarDetalleString() {
	    StringBuilder detalleString = new StringBuilder();
	    for (Detalle_venta detalle : detalle) {
	        String producto = detalle.getArticulo().getNombre();
	        double precioUnitario = detalle.getArticulo().getPrecio_venta();
	        int cantidad = detalle.getCantidad();
	        double subtotal = detalle.getImporte();
	        
	        String fila = String.format("PRODUCTO: %s | PRECIO U.: %.2f | CANTIDAD: %d | SUBTOTAL: %.2f", producto, precioUnitario, cantidad, subtotal);
	        detalleString.append(fila).append("\n");
	    }
	    return detalleString.toString();
	}
	
	public float totalMonto() {
		float monto = 0;
		for (Detalle_venta detalle_venta : detalle) {
			monto += detalle_venta.getImporte();
		}
		return monto;
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
	public float getGanancia() {
		return ganancia;
	}
	public void setGanancia(float ganancia) {
		this.ganancia = ganancia;
	}
	
	public Estado_Venta getEstado() {
		return estado;
	}
	public void setEstado(Estado_Venta estado) {
		this.estado = estado;
	}
	
}
