package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.servicio.EstadoUsuarioServicio;
import frgp.utn.edu.ar.servicio.UsuarioServicio;

@Controller
public class ReportesController {

	@Autowired
	private UsuarioServicio serviceUsuario;

	@Autowired
	private ModelAndView MV;
	
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.serviceUsuario = (UsuarioServicio) ctx.getBean("UsuarioServiceBean");

		this.MV = (ModelAndView) ctx.getBean("ModelAndViewBean");
	}	
	
	// REPORTES | "reportes.html"
	@RequestMapping("reportes.html")
	public ModelAndView eventoRedireccionarReportes()
	{
		MV = cargadorDeListasReportes(MV);
		MV.setViewName("admin/Reportes");
		return MV;
	}
	
	private ModelAndView cargadorDeListasReportes(ModelAndView MV) 
	{
		MV.addObject("listaRoles",this.serviceUsuario.obtenerUsuariosPorRol());
		return MV;
	}
}
