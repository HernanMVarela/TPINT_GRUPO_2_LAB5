package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;

import frgp.utn.edu.ar.dominio.Articulo;
import frgp.utn.edu.ar.dominio.Marca;
import frgp.utn.edu.ar.dominio.Tipo_Articulo;
import frgp.utn.edu.ar.servicio.ArticuloServicio;

@Controller
public class AdminController {

	// HOME ADMIN | "admin.html"
	@RequestMapping("admin.html")
	public ModelAndView eventoRedireccionarHomeAdmin()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("admin/HomeAdmin");
		return MV;
	}
	
	// ALTA USER | "alta-usuario.html"
	@RequestMapping("alta-usuario.html")
	public ModelAndView eventoRedireccionarAltaUsuario()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("admin/AltaUsuario");
		return MV;
	}
	
	// LISTA USERS | "reportes.html"
	@RequestMapping("reportes.html")
	public ModelAndView eventoRedireccionarListarUsuarios()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("admin/Reportes");
		return MV;
	}
}
