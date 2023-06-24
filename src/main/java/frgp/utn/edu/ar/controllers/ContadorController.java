package frgp.utn.edu.ar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
