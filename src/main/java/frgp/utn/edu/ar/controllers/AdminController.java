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
public class AdminController {

	@Autowired
	private UsuarioServicio serviceUsuario;
	@Autowired
	private EstadoUsuarioServicio serviceEstadoUsuario;

	@Autowired
	private ModelAndView MV;
	
	@Autowired
	private Usuario usuario;
	
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.serviceUsuario = (UsuarioServicio) ctx.getBean("UsuarioServiceBean");
		this.serviceEstadoUsuario = (EstadoUsuarioServicio) ctx.getBean("EstadoUsuarioServiceBean");

		this.MV = (ModelAndView) ctx.getBean("ModelAndViewBean");
		this.usuario = (Usuario) ctx.getBean("UsuarioEstandar");
	}	
	
	// HOME ADMIN | "admin.html"
	@RequestMapping("admin.html")
	public ModelAndView eventoRedireccionarHomeAdmin()
	{
		MV = cargadorDeListasAdmin(MV);
		MV.setViewName("admin/HomeAdmin");
		return MV;
	}	
	
	//Eliminar Usuario | "/eliminar_usuario.html"
	@RequestMapping(value ="/eliminar_usuario.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView eliminarCliente(int idEliminar){		
		
		System.out.println(idEliminar);

		usuario = serviceUsuario.obtenerUnRegistro(idEliminar);
		usuario.setEstado(serviceEstadoUsuario.obtenerUnRegistro(2));
	
		String Message = "";	
		
		try{
			Message = asignarMensajeUsuario(serviceUsuario.eliminarUsuario(usuario));
			MV.addObject("Mensaje", Message);
			MV = cargadorDeListasAdmin(MV);
			MV.setViewName("admin/HomeAdmin"); 
			return MV;
		}
		catch(Exception e)
		{
			/// REEMPLAZAR POR DIRECCIONAMIENTO A PAGINA DE ERROR
			Message = e.toString();
			System.out.println(e.toString());
			MV.addObject("Mensaje", Message);
			MV.setViewName("Error"); 
			return MV;
		}
	}
	
	private String asignarMensajeUsuario(String error) {
		if (error.equals("AGREGADO")) {
			return "Cliente Agregado";
		}
		if (error.equals("NO AGREGADO")) {
			return "Cliente no agregado";
		}
		if (error.equals("ERROR")) {
			return "ERROR";
		}
		if (error.equals("EXISTE")) {
			return "Cliente ya exise";
		}
		if (error.equals("REACTIVADO")) {
			return "Cliente Reactivado";
		}
		if (error.equals("ELIMINACION PREVIA")) {
			return "El cliente ya estaba eliminado";
		}
		if (error.equals("ELIMINADO")) {
			return "Cliente eliminado";
		}
		if (error.equals("NO ELIMINADO")) {
			return "Cliente no eliminado";
		}
		if (error.equals("MODIFICADO")) {
			return "Cliente no modificado";
		}
		if (error.equals("NO MODIFICADO")) {
			return "Cliente no modificado";
		}
		if (error.equals("ERROR")) {
			return "ERROR";
		}
		return "ERROR";
	}

	private ModelAndView cargadorDeListasAdmin(ModelAndView MV) 
	{
		MV.addObject("listaUsuarios",this.serviceUsuario.obtenerUsuarios());
		return MV;
	}
}
