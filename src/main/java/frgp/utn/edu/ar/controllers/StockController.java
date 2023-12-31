package frgp.utn.edu.ar.controllers;

import java.sql.Date;

import javax.servlet.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import frgp.utn.edu.ar.dominio.Stock;
import frgp.utn.edu.ar.servicio.ArticuloServicio;
import frgp.utn.edu.ar.servicio.StockServicio;

@Controller
public class StockController {

	@Autowired
	private  ArticuloServicio serviceArticulo;
	@Autowired
	private  StockServicio serviceStock;
	@Autowired
	private ApplicationContext ctx;
	@Autowired
	private ModelAndView MV;
	@Autowired
	private Stock stock;
	
	// NO TOCAR - Servlets
	public void init(ServletConfig config) {
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		
		this.serviceArticulo = (ArticuloServicio) ctx.getBean("ArticuloServiceBean");
		this.serviceStock = (StockServicio) ctx.getBean("StockServiceBean");
		
		this.MV = (ModelAndView) ctx.getBean("ModelAndViewBean");
		this.stock = (Stock) ctx.getBean("StockEstandar");
	}
	
	
	// Stock | "stock.html"
	@RequestMapping("stock.html")
	public ModelAndView eventoRedireccionarStock()
	{
		MV.addObject("Mensaje", "");
		MV = cargadorDeListasStocks(MV);
		MV.setViewName("vendedor/Stock");
		return MV;
	}
	
	// Ingreso de Stock | "/ingreso_stock.html"
		@RequestMapping(value ="/ingreso_stock.html" , method= { RequestMethod.GET, RequestMethod.POST})
		public ModelAndView validarStock(String art, Date date, String cantidad, String precio_compra){
			
			stock = (Stock) ctx.getBean("StockEstandar");
			stock.setArticulo(serviceArticulo.obtenerUnRegistro(art));
			stock.setCantidad(Integer.parseInt(cantidad));
			stock.setPreciocompra(Float.parseFloat(precio_compra));
			stock.setFechaingreso(date);
			
			String Message = "";
			try{
				Message = asignarMensajeStocks(serviceStock.ingresarStock(stock));
				MV.addObject("Mensaje", Message);
				MV = cargadorDeListasStocks(MV);
				MV.setViewName("vendedor/Stock"); 
				return MV;
			}
			catch(Exception e)
			{
				/// REEMPLAZAR POR DIRECCIONAMIENTO A PAGINA DE ERROR
				Message = e.toString();
				System.out.println(e.toString());
				MV.addObject("Mensaje", Message);
				MV.setViewName("Error"); 
				return MV;
			}
		}
	
	/// METODOS COMUNES
	
	private String asignarMensajeStocks(String error) {
		if (error.equals("AGREGADO")) {
			return "Stock agregado";
		}
		if (error.equals("NO AGREGADO")) {
			return "Stock no agregado";
		}
		if (error.equals("PRECIO")) {
			return "El precio de compra excede al de venta";
		}
		if (error.equals("ERROR")) {
			return "ERROR";
		}
		return "ERROR";
	}
	
	private ModelAndView cargadorDeListasStocks(ModelAndView MV) 
	{
		MV.addObject("listaArticulos",this.serviceArticulo.obtenerArticulosActivos());
		MV.addObject("listaStocks",this.serviceStock.obtenerStock());
		return MV;
	}
	
}
