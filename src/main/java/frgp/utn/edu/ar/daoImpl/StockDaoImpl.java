package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.StockDao;
import frgp.utn.edu.ar.dominio.Stock;

public class StockDaoImpl implements StockDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insertarStock(Stock nuevo) 
	{
		try 
		{
			this.hibernateTemplate.save(nuevo);
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Stock> obtenerStock() 
	{
		return (ArrayList<Stock>) this.hibernateTemplate.loadAll(Stock.class);
	}

}
