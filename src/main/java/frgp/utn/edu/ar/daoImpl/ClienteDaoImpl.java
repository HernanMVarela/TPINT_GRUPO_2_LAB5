package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.ArticuloDao;
import frgp.utn.edu.ar.dao.ClienteDao;
import frgp.utn.edu.ar.dominio.Articulo;
import frgp.utn.edu.ar.dominio.Cliente;
import frgp.utn.edu.ar.dominio.Usuario;

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
	public Cliente obtenerClientePorDNI(long DNI) {
		return this.hibernateTemplate.get(Cliente.class, DNI);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public boolean existeCliente(long DNI) {
		return this.hibernateTemplate.get(Articulo.class, DNI) != null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Cliente> obtenerClientes() {
		return (ArrayList<Cliente>) this.hibernateTemplate.loadAll(Cliente.class);
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void eliminarCliente(long DNI) {
		Cliente art = new Cliente();
		art.setDNI(DNI);
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
