package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.LocalidadDao;
import frgp.utn.edu.ar.dominio.Localidad;

public class LocalidadDaoImpl implements LocalidadDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Localidad> obtenerLocalidades() {
		return (ArrayList<Localidad>) this.hibernateTemplate.loadAll(Localidad.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Localidad obtenerLocalidadPorID(int id) {
		return this.hibernateTemplate.get(Localidad.class, id);
	}

}
