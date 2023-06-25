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

@Controller
public class HomeController {

	@Autowired
	public UsuarioServicio serviceUsuario;
	@Autowired
	public Usuario usuario;
	
	@Autowired
	private ModelAndView MV;
	
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.serviceUsuario = (UsuarioServicio) ctx.getBean("UsuarioServiceBean");
		this.usuario = (Usuario) ctx.getBean("UsuarioEstandar");
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
		
		Usuario login = serviceUsuario.login(usernameLogin, passLogin);
		
		if(login == null) {
			MV.addObject("userLogin", null);
			MV.setViewName("Home"); 
		} else {
			login.getTipo().setNombre("CONTADOR");;
			MV.addObject("userLogin", login);
			if(login.getTipo().getNombre().equals("ADMIN")) {
				MV.setViewName("admin/HomeAdmin"); 
			}
			if(login.getTipo().getNombre().equals("VENDEDOR")) {
				MV.setViewName("vendedor/HomeVendedor"); 
			}
			if(login.getTipo().getNombre().equals("CONTADOR")) {
				MV.setViewName("contador/HomeContador"); 
			}
		}
		return MV;
	}	
}
