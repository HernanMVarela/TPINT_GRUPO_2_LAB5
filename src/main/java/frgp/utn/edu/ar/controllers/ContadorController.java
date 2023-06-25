package frgp.utn.edu.ar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.dominio.Tipo_Usuario;
import frgp.utn.edu.ar.dominio.Usuario;

@Controller
public class ContadorController {

	// HOME CONTADOR | "contador.html"
	@RequestMapping("contador.html")
	public ModelAndView eventoRedireccionarHomeContador()
	{
		ModelAndView MV = new ModelAndView();
		Usuario user = new Usuario();
		user.setTipo(new Tipo_Usuario("ADMIN"));
		user.setNombreU("hvarela");
		MV.addObject("userLogin", user);
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
