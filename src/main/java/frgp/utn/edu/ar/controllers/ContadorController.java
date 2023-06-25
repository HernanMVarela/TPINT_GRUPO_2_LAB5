package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContadorController {

	@Autowired
	public ModelAndView MV;
	// HOME CONTADOR | "contador.html"
	
	// NO TOCAR - Servlets
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());

		this.MV = (ModelAndView) ctx.getBean("ModelAndViewBean");
	}
	
	@RequestMapping("contador.html")
	public ModelAndView eventoRedireccionarHomeContador()
	{
		MV.setViewName("contador/HomeContador");
		return MV;
	}
	
	// CONSULTA VENTAS | "consulta-ventas.html"
	@RequestMapping("consulta-ventas.html")
	public ModelAndView eventoRedireccionarConsultaVentas()
	{
		MV.setViewName("contador/ConsultaVentas");
		return MV;
	}	
}
