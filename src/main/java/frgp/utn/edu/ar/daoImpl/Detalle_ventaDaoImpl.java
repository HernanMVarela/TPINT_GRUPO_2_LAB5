package frgp.utn.edu.ar.daoImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.Detalle_ventaDao;
import frgp.utn.edu.ar.dominio.Detalle_venta;

public class Detalle_ventaDaoImpl implements Detalle_ventaDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insertarDetalle_venta(Detalle_venta nuevo) {
		try {
			this.hibernateTemplate.save(nuevo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Detalle_venta obtenerDetalle_ventaPorNombre(String nombre) {
		return this.hibernateTemplate.get(Detalle_venta.class, nombre);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public boolean existeDetalle_venta(String nombre) {
		return this.hibernateTemplate.get(Detalle_venta.class, nombre) != null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Detalle_venta> obtenerDetalle_ventas() {
		return (ArrayList<Detalle_venta>) this.hibernateTemplate.loadAll(Detalle_venta.class);
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void eliminarDetalle_venta(String nombre) {
		Detalle_venta det = new Detalle_venta();
		// det.setNombre(nombre);
		this.hibernateTemplate.delete(det);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean actualizarDetalle_venta(Detalle_venta modificar) {
		try {
			this.hibernateTemplate.update(modificar);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Map<String, Long> obtenerProductosPorCantidad(Date startDate, Date endDate) {
	    String query = "SELECT dv.articulo.nombre, SUM(dv.cantidad) AS totalAmount "
	                 + "FROM Venta v "
	                 + "JOIN v.detalle dv "
	                 + "WHERE dv.articulo.estado = true "
	                 + "AND v.fecha BETWEEN :startDate AND :endDate "
	                 + "GROUP BY dv.articulo.nombre "
	                 + "ORDER BY totalAmount DESC";

	    @SuppressWarnings("unchecked")
	    List<Object[]> resultados = (List<Object[]>) this.hibernateTemplate
	            .findByNamedParam(query, new String[]{"startDate", "endDate"}, new Object[]{startDate, endDate});

	    List<Object[]> limitedResultados = resultados.subList(0, Math.min(resultados.size(), 5));

	    Map<String, Long> map = new HashMap<>();
	    for (Object[] resultado : limitedResultados) {
	        map.put((String) resultado[0], (Long) resultado[1]);
	    }
	    return map;
	}


}
