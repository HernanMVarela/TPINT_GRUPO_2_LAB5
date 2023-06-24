package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.TipoArticuloDao;
import frgp.utn.edu.ar.dao.TipoUsuarioDao;
import frgp.utn.edu.ar.dominio.Tipo_Articulo;
import frgp.utn.edu.ar.dominio.Tipo_Usuario;

public class TipoUsuarioDaoImpl implements TipoUsuarioDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Tipo_Usuario> obtenerTiposUsuarios() {
		return (ArrayList<Tipo_Usuario>) this.hibernateTemplate.loadAll(Tipo_Usuario.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Tipo_Usuario obtenerTiposUsuariosPorID(int id) {
		return this.hibernateTemplate.get(Tipo_Usuario.class, id);
	}

}
