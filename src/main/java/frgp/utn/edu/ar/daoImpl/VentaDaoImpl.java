package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.VentaDao;
import frgp.utn.edu.ar.dominio.Venta;

public class VentaDaoImpl implements VentaDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insertarVenta(Venta nuevo) {
		try {
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


}
