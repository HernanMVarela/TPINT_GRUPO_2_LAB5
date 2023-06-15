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
public class ContadorController {

	// HOME CONTADOR | "contador.html"
	@RequestMapping("contador.html")
	public ModelAndView eventoRedireccionarHomeContador()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("contador/HomeContador");
		return MV;
	}
	
	// CONSULTA VENTAS | "consulta-ventas.html"
	@RequestMapping("consulta-ventas.html")
	public ModelAndView eventoRedireccionarConsultaVentas()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("contador/ConsultaVentas");
		return MV;
	}	
}