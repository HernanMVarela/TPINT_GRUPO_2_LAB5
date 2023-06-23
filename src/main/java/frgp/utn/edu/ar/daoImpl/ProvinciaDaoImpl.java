package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.ProvinciaDao;
import frgp.utn.edu.ar.dominio.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Provincia> obtenerProvincias() {
		return (ArrayList<Provincia>) this.hibernateTemplate.loadAll(Provincia.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Provincia obtenerProvinciaPorID(int id) {
		return this.hibernateTemplate.get(Provincia.class, id);
	}

}
