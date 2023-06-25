package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.ClienteDao;
import frgp.utn.edu.ar.dominio.Cliente;

public class ClienteDaoImpl implements ClienteDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insertarCliente(Cliente nuevo) {
		try {
			this.hibernateTemplate.save(nuevo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Cliente obtenerClientePorID(int ID) {
		return this.hibernateTemplate.get(Cliente.class, ID);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Cliente obtenerClientePorDNI(String DNI) {
	    String query = "FROM Cliente c where c.DNI LIKE :dni";
	    @SuppressWarnings("unchecked")
		List<Cliente> resultados = (List<Cliente>) this.hibernateTemplate.findByNamedParam(query, "dni", DNI);
	    if (!resultados.isEmpty()) {
	        return resultados.get(0);
	    }
	    return null;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public boolean existeCliente(int ID) {
		return this.hibernateTemplate.get(Cliente.class, ID) != null;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public boolean existeCliente(String DNI) {
	    @SuppressWarnings("unchecked")
		List<Cliente> resultados = (List<Cliente>) this.hibernateTemplate.find("FROM Cliente c where c.DNI LIKE ?", DNI);
	    return !resultados.isEmpty();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Cliente> obtenerClientes() {
		return (ArrayList<Cliente>) this.hibernateTemplate.loadAll(Cliente.class);
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void eliminarCliente(int ID) {
		Cliente art = new Cliente();
		art.setID(ID);
		this.hibernateTemplate.delete(art);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean actualizarCliente(Cliente modificar) {
		try {
			this.hibernateTemplate.update(modificar);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


}
