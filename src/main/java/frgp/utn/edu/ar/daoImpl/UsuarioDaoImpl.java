package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.UsuarioDao;
import frgp.utn.edu.ar.dominio.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insertarUsuario(Usuario nuevo) {
		try {
			this.hibernateTemplate.save(nuevo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Usuario obtenerUsuarioPorID(int ID) {
		return this.hibernateTemplate.get(Usuario.class, ID);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Usuario obtenerUsuarioPorDNI(String DNI) {
	    String query = "FROM Usuario u where u.DNI LIKE :dni";
	    @SuppressWarnings("unchecked")
		List<Usuario> resultados = (List<Usuario>) this.hibernateTemplate.findByNamedParam(query, "dni", DNI);
	    if (!resultados.isEmpty()) {
	        return resultados.get(0);
	    }
	    return null;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public boolean existeUsuario(int ID) {
		return this.hibernateTemplate.get(Usuario.class, ID) != null;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public boolean existeUsuario(String user) {
	    @SuppressWarnings("unchecked")
		List<Usuario> resultados = (List<Usuario>) this.hibernateTemplate.find("FROM Usuario u where u.nombreU LIKE ?", user);
	    return !resultados.isEmpty();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Usuario> obtenerUsuarios() {
		return (ArrayList<Usuario>) this.hibernateTemplate.loadAll(Usuario.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void eliminarUsuario(int ID) {
		Usuario usr = new Usuario();
		usr.setId(ID);
		this.hibernateTemplate.delete(usr);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean actualizarUsuario(Usuario modificar) {
		try {
			this.hibernateTemplate.update(modificar);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Usuario obtenerUsuarioPorUser(String username) {
		String query = "FROM Usuario u WHERE u.nombreU = :username";
	    @SuppressWarnings("unchecked")
		List<Usuario> resultados = (List<Usuario>) this.hibernateTemplate.findByNamedParam(query, "username", username);
	    if (!resultados.isEmpty()) {
	        return resultados.get(0);
	    }
	    return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Map<String, Long> obtenerUsuariosPorRol() {
		String query = "SELECT tipo.nombre, COUNT(*) FROM Usuario u JOIN u.tipo tipo JOIN u.estado estado WHERE estado.nombre = 'ACTIVO' GROUP BY tipo.nombre";		
		@SuppressWarnings("unchecked")
		List<Object[]> resultados = (List<Object[]>) this.hibernateTemplate.find(query);
		Map<String, Long> map = new HashMap<String, Long>();
		int limit = 5;
	    for (int i = 0; i < Math.min(limit, resultados.size()); i++) {
	        Object[] resultado = resultados.get(i);
	        map.put((String) resultado[0], (Long) resultado[1]);
	    }
	    return map;
	}
}
