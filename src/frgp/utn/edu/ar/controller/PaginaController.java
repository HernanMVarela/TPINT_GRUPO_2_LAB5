package frgp.utn.edu.ar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaginaController {
	
	@RequestMapping("home.html")
	public ModelAndView eventoRedireccionarHome()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("Home");
		return MV;
	}
	
	@RequestMapping("admin.html")
	public ModelAndView eventoRedireccionarHomeAdmin()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("admin/HomeAdmin");
		return MV;
	}
	
	@RequestMapping("alta-usuario.html")
	public ModelAndView eventoRedireccionarAltaUsuario()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("admin/AltaUsuario");
		return MV;
	}
	
	@RequestMapping("listar-usuarios.html")
	public ModelAndView eventoRedireccionarListarUsuarios()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("admin/ListaUsuarios");
		return MV;
	}
	
	@RequestMapping("contador.html")
	public ModelAndView eventoRedireccionarHomeContador()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("contador/HomeContador");
		return MV;
	}
	
	@RequestMapping("consulta-ventas.html")
	public ModelAndView eventoRedireccionarConsultaVentas()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("contador/ConsultaVentas");
		return MV;
	}
	
	
	
	@RequestMapping("vendedor.html")
	public ModelAndView eventoRedireccionarHomeVendedor()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("vendedor/HomeVendedor");
		return MV;
	}

	@RequestMapping("articulos.html")
	public ModelAndView eventoRedireccionarArticulos()
	{
		ModelAndView MV = new ModelAndView();

		MV.setViewName("vendedor/ListadoArticulos");
		return MV;
	}
	
	@RequestMapping("clientes.html")
	public ModelAndView eventoRedireccionarClientes()
	{
		ModelAndView MV = new ModelAndView();

		MV.setViewName("vendedor/ListadoClientes");
		return MV;
	}
	
	@RequestMapping("stock.html")
	public ModelAndView eventoRedireccionarStock()
	{
		ModelAndView MV = new ModelAndView();

		MV.setViewName("vendedor/Stock");
		return MV;
	}	
	
	@RequestMapping("gestion-ventas.html")
	public ModelAndView eventoRedireccionarGestionVentas()
	{
		ModelAndView MV = new ModelAndView();

		MV.setViewName("vendedor/GestionVentas");
		return MV;
	}
	
	@RequestMapping("registro-usuario.html")
	public ModelAndView eventoRedireccionarRegistroUsuario()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("registro/RegistroUsuario");
		return MV;
	}
}
