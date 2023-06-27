package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.servicio.UsuarioServicio;
import frgp.utn.edu.ar.servicio.VentaServicio;

@Controller
public class HomeController {

	@Autowired
	private UsuarioServicio serviceUsuario;
	@Autowired
	private Usuario userLogin;
	
	@Autowired
	private VentaServicio serviceVenta;
	
	@Autowired
	private ModelAndView MV;
	
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.serviceUsuario = (UsuarioServicio) ctx.getBean("UsuarioServiceBean");
		this.userLogin = (Usuario) ctx.getBean("UsuarioEstandar");
		this.serviceVenta = (VentaServicio) ctx.getBean("VentaServiceBean");
		this.MV = (ModelAndView) ctx.getBean("ModelAndViewBean");
	}
	
	//Home | "Home.html"
	@RequestMapping("home.html")
	public ModelAndView redireccion(){
		MV.setViewName("Home"); 
		return MV;
	}	
	
	//Home | "login.html"
	@RequestMapping("login.html")
	public ModelAndView login(String usernameLogin, String passLogin){
		
		userLogin = serviceUsuario.login(usernameLogin, passLogin);
		
		if(userLogin == null) {
			MV.addObject("userLogin", null);
			MV.setViewName("Home"); 
		} else {
			MV.addObject("userLogin", userLogin);
			if(userLogin.getTipo().getNombre().equals("ADMIN")) {
				MV = cargadorDeListasAdmin(MV);
				MV.setViewName("admin/HomeAdmin"); 
			}
			if(userLogin.getTipo().getNombre().equals("VENDEDOR")) {
				MV.setViewName("vendedor/HomeVendedor"); 
			}
			if(userLogin.getTipo().getNombre().equals("CONTADOR")) {
				MV= cargadorDeListasVentas(MV);
				MV.setViewName("contador/HomeContador"); 
			}
		}
		return MV;
	}	
	
	//Home | "login.html"
	@RequestMapping("Logout.html")
	public ModelAndView logout(){
		
		Usuario userLogin = null;

		MV.setViewName("Home"); 
		MV.addObject("userLogin", userLogin);
		
		return MV;
	}	
	
	private ModelAndView cargadorDeListasVentas(ModelAndView MV) {

		MV.addObject("listaContador", this.serviceVenta.obtenerVentas());
		return MV;
	}
	
	private ModelAndView cargadorDeListasAdmin(ModelAndView MV) 
	{
		MV.addObject("listaUsuarios",this.serviceUsuario.obtenerUsuarios());
		return MV;
	}
}
