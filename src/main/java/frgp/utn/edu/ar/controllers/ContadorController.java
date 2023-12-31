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

import frgp.utn.edu.ar.servicio.VentaServicio;

@Controller
public class ContadorController {

	@Autowired
	private ModelAndView MV;
	// HOME CONTADOR | "contador.html"
	@Autowired
	private VentaServicio serviceVenta;
	
	
	// NO TOCAR - Servlets
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.serviceVenta = (VentaServicio) ctx.getBean("VentaServiceBean");
		this.MV = (ModelAndView) ctx.getBean("ModelAndViewBean");
	}
	
	@RequestMapping("contador.html")
	public ModelAndView eventoRedireccionarHomeContador()
	{
		MV.addObject("Mensaje", "");
		MV= cargadorDeListasVentas(MV);
		MV.setViewName("contador/HomeContador");
		return MV;
	}
	
	// CONSULTA VENTAS | "consulta-ventas.html"
	@RequestMapping("consulta-ventas.html")
	public ModelAndView eventoRedireccionarConsultaVentas()
	{
		MV= cargadorDeListasVentas(MV);
		MV.setViewName("contador/ConsultaVentas");
		return MV;
	}
	
	// Calcular Ganancia | "/HomeContador.html"
	@RequestMapping(value ="/HomeContador.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView controlarGanancia(Date datei, Date datef){
		String Message = "";
		try{
			float gananciaTotal = serviceVenta.gananciaEntreFechas(datei, datef);
			if(datei != null && datef != null) {
				MV.addObject("listaContador", this.serviceVenta.tablaFiltradaFechas(datei, datef));
			}else {
				MV= cargadorDeListasVentas(MV);	
			}
			
	        MV.addObject("Mensaje", Message);
	        MV.addObject("gananciaTotal", gananciaTotal); // Asignar el valor a gananciaTotal
			MV.setViewName("contador/HomeContador"); 
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
	
	private ModelAndView cargadorDeListasVentas(ModelAndView MV) {

		MV.addObject("listaContador", this.serviceVenta.obtenerVentas());
		return MV;
	}
}
