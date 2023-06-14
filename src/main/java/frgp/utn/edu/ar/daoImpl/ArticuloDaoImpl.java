package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.ArticuloDao;
import frgp.utn.edu.ar.dominio.Articulo;
import frgp.utn.edu.ar.dominio.Usuario;

public class ArticuloDaoImpl implements ArticuloDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertarArticulo(Articulo nuevo) {
		this.hibernateTemplate.save(nuevo);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Articulo obtenerArticuloPorNombre(String nombre) {
		return this.hibernateTemplate.get(Articulo.class, nombre);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Articulo> obtenerArticulos() {
		return (ArrayList<Articulo>) this.hibernateTemplate.loadAll(Articulo.class);
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void eliminarArticulo(String nombre) {
		Articulo art = new Articulo();
		art.setNombre(nombre);
		this.hibernateTemplate.delete(art);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void actualizarArticulo(Articulo modificar) {
		this.hibernateTemplate.update(modificar);
	}


}
