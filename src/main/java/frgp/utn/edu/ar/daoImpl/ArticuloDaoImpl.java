package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.ArticuloDao;
import frgp.utn.edu.ar.dominio.Articulo;

public class ArticuloDaoImpl implements ArticuloDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insertarArticulo(Articulo nuevo) {
		try {
			this.hibernateTemplate.save(nuevo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Articulo obtenerArticuloPorNombre(String nombre) {
		return this.hibernateTemplate.get(Articulo.class, nombre);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public boolean existeArticulo(String nombre) {
		return this.hibernateTemplate.get(Articulo.class, nombre) != null;
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
	public boolean actualizarArticulo(Articulo modificar) {
		try {
			this.hibernateTemplate.update(modificar);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Map<String, Long> obtenerCantidadArticulosPorTipo() {
		String query = "SELECT t.nombre, COUNT(a) " +
		               "FROM Articulo a " +
		               "JOIN a.tipo t " +
		               "GROUP BY t.nombre";		
		@SuppressWarnings("unchecked")
		List<Object[]> resultados = (List<Object[]>) this.hibernateTemplate.find(query);
		List<Object[]> limitedResultados = resultados.subList(0, Math.min(resultados.size(), 5));

		Map<String, Long> map = new HashMap<String, Long>();
		for (Object[] resultado : limitedResultados) {
			map.put((String) resultado[0], (Long) resultado[1]);
		}
		return map;
	}


}
