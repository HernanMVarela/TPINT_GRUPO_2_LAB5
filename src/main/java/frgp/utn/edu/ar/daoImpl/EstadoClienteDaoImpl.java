package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.EstadoClienteDao;
import frgp.utn.edu.ar.dominio.Estado_Cli;

public class EstadoClienteDaoImpl implements EstadoClienteDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Estado_Cli> obtenerEstados() {
		return (ArrayList<Estado_Cli>) this.hibernateTemplate.loadAll(Estado_Cli.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Estado_Cli obtenerEstadoPorID(int id) {
		return this.hibernateTemplate.get(Estado_Cli.class, id);
	}

}
