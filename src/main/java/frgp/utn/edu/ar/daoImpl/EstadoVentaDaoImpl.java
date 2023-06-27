package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.EstadoVentaDao;
import frgp.utn.edu.ar.dominio.Estado_Venta;

public class EstadoVentaDaoImpl implements EstadoVentaDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Estado_Venta> obtenerEstados() {
		return (ArrayList<Estado_Venta>) this.hibernateTemplate.loadAll(Estado_Venta.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Estado_Venta obtenerEstadoPorID(int id) {
		return this.hibernateTemplate.get(Estado_Venta.class, id);
	}

}
