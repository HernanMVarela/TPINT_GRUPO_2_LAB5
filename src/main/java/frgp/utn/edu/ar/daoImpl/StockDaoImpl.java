package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Stock> obtenerDeArticulo(String articulo) {
		String query = "FROM Stock s WHERE s.articulo.nombre LIKE :ART";
		@SuppressWarnings("unchecked")
		List<Stock> resultados = (ArrayList<Stock>) this.hibernateTemplate.findByNamedParam(query, "ART", articulo);
		return resultados;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public boolean existeStockDeArticulo(String articulo) {
		String query = "FROM Stock s WHERE s.articulo.nombre LIKE :ART and s.cantidad>0";
		@SuppressWarnings("unchecked")
		List<Stock> resultados = (ArrayList<Stock>) this.hibernateTemplate.findByNamedParam(query, "ART", articulo);
		return resultados.isEmpty();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean modificarStock(Stock modif) {
		try {
			this.hibernateTemplate.update(modif);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Stock obtenerUltimoRegistro(String articulo) {
		String query = "FROM Stock s WHERE s.articulo.nombre = :ART AND s.cantidad > 0 ORDER BY s.fechaingreso DESC";
	    @SuppressWarnings("unchecked")
		List<Stock> resultados = (List<Stock>) this.hibernateTemplate.findByNamedParam(query, "ART", articulo);

	    if (resultados != null && !resultados.isEmpty()) {
	        return resultados.get(0);
	    }
	    return null; // Si no se encontró ningún stock con cantidad > 0 de ese artículo
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Stock obtenerUltimoRegistroVacio(String articulo) {
		String query = "FROM Stock s WHERE s.articulo.nombre = :ART AND s.cantidad = 0 ORDER BY s.fechaingreso ASC";
	    @SuppressWarnings("unchecked")
		List<Stock> resultados = (List<Stock>) this.hibernateTemplate.findByNamedParam(query, "ART", articulo);

	    if (resultados != null && !resultados.isEmpty()) {
	        return resultados.get(0);
	    }
	    return null; // Si no se encontró ningún stock con cantidad > 0 de ese artículo
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Stock> obtenerRegistrosUnicos() {
		String query = "SELECT DISTINCT s FROM Stock s JOIN FETCH s.articulo";
	    @SuppressWarnings("unchecked")
		List<Stock> resultados = (ArrayList<Stock>) this.hibernateTemplate.find(query);
	    List<Stock> resultadosUnicos = new ArrayList<Stock>();
	    
	    for (Stock stock : resultados) {
	        boolean flag = false;
	        for (Stock stock2 : resultadosUnicos) {
	            if(stock.getArticulo().getNombre().equals(stock2.getArticulo().getNombre())) {
	                stock2.sumarCantidad(stock.getCantidad());
	                flag = true;
	                break;
	            }
	        }
	        if (!flag) {
	        	resultadosUnicos.add(stock);
	        }
	    
	    }
	    return resultadosUnicos;
	}

}
