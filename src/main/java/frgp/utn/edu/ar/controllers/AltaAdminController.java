package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.servicio.LocalidadServicio;
import frgp.utn.edu.ar.servicio.ProvinciaServicio;

@Controller
public class AltaAdminController {

	@Autowired
	private ProvinciaServicio serviceProvincia;
	@Autowired
	private LocalidadServicio serviceLocalidad;
	@Autowired
	private ModelAndView MV;
	
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.serviceProvincia = (ProvinciaServicio) ctx.getBean("ProvinciaServiceBean");
		this.serviceLocalidad = (LocalidadServicio) ctx.getBean("LocalidadServiceBean");
		
		this.MV = (ModelAndView) ctx.getBean("ModelAndViewBean");
	}	
	
	// ALTA USER | "alta-usuario.html"
	@RequestMapping("alta-usuario.html")
	public ModelAndView eventoRedireccionarAltaUsuario()
	{
		MV.addObject("Mensaje", "");
		MV = cargadorDeListasRegistros(MV);
		MV.setViewName("admin/AltaUsuario");
		return MV;
	}	
	
	private ModelAndView cargadorDeListasRegistros(ModelAndView MV) 
	{
		MV.addObject("listaLocalidades",this.serviceLocalidad.obtenerLocalidades());
		MV.addObject("listaProvincias",this.serviceProvincia.obtenerProvincias());
		return MV;
	}
}
