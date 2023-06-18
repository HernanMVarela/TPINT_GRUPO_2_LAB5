package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.TipoArticuloDao;
import frgp.utn.edu.ar.dominio.Tipo_Articulo;

public class TipoArticuloDaoImpl implements TipoArticuloDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Tipo_Articulo> obtenerTiposArticulo() {
		return (ArrayList<Tipo_Articulo>) this.hibernateTemplate.loadAll(Tipo_Articulo.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Tipo_Articulo obtenerTiposArticuloPorID(int id) {
		return this.hibernateTemplate.get(Tipo_Articulo.class, id);
	}

}
