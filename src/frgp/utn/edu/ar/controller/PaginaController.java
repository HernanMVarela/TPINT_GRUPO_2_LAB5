package frgp.utn.edu.ar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaginaController {

	@RequestMapping("redireccionar_listado_art.html")
	public ModelAndView eventoRedireccionarPag2()
	{
		ModelAndView MV = new ModelAndView();

		MV.setViewName("Listado_Articulos");
		return MV;
	}
	@RequestMapping("redireccionar_listado_clientes.html")
	public ModelAndView eventoRedireccionarPag3()
	{
		ModelAndView MV = new ModelAndView();

		MV.setViewName("Listado_Clientes");
		return MV;
	}

	@RequestMapping("redireccionar_Home.html")
	public ModelAndView eventoRedireccionarPag1()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("Home");
		return MV;
	}
}
