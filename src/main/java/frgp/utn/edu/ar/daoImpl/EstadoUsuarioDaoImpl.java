package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.EstadoClienteDao;
import frgp.utn.edu.ar.dao.EstadoUsuarioDao;
import frgp.utn.edu.ar.dominio.Estado_Cli;
import frgp.utn.edu.ar.dominio.Estado_User;

public class EstadoUsuarioDaoImpl implements EstadoUsuarioDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Estado_User> obtenerEstados() {
		return (ArrayList<Estado_User>) this.hibernateTemplate.loadAll(Estado_User.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Estado_User obtenerEstadoPorID(int id) {
		return this.hibernateTemplate.get(Estado_User.class, id);
	}

}
