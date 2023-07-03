package frgp.utn.edu.ar.daoImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.VentaDao;
import frgp.utn.edu.ar.dominio.Detalle_venta;
import frgp.utn.edu.ar.dominio.Venta;

public class VentaDaoImpl implements VentaDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean insertarVenta(Venta nuevo) {
	    try {
	        List<Detalle_venta> detalles = nuevo.getDetalle();

	        // Guardar los detalles explícitamente antes de guardar la venta
	        for (Detalle_venta detalle : detalles) {
	            this.hibernateTemplate.save(detalle);
	        }

	        this.hibernateTemplate.save(nuevo);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Venta obtenerVentaPorNombre(String nombre) {
		return this.hibernateTemplate.get(Venta.class, nombre);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public boolean existeVenta(String nombre) {
		return this.hibernateTemplate.get(Venta.class, nombre) != null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Venta> obtenerVentas() {
		return (ArrayList<Venta>) this.hibernateTemplate.loadAll(Venta.class);
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void eliminarVenta(String nombre) {
		Venta vent = new Venta();
		// vent.setNombre(nombre);
		this.hibernateTemplate.delete(vent);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean actualizarVenta(Venta modificar) {
		try {
			this.hibernateTemplate.update(modificar);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Venta obtenerVentaPorID(int ID) {
		return this.hibernateTemplate.get(Venta.class, ID);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ArrayList<Venta> obtenerVentasEntreFechas(Date fechaInicio, Date fechaFin) {
	    Criteria criteria = this.hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Venta.class);
	    criteria.add(Restrictions.between("fecha", fechaInicio, fechaFin));
	    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	    return (ArrayList<Venta>) criteria.list();
	}
}
