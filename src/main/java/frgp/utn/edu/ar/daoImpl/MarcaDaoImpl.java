package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.MarcaDao;
import frgp.utn.edu.ar.dominio.Marca;

public class MarcaDaoImpl implements MarcaDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Marca> obtenerMarcas() {
		return (ArrayList<Marca>) this.hibernateTemplate.loadAll(Marca.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Marca obtenerMarcaPorID(int id) {
		return this.hibernateTemplate.get(Marca.class, id);
	}

}
