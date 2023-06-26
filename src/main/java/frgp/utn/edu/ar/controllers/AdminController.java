package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.dominio.Articulo;
import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.servicio.ArticuloServicio;
import frgp.utn.edu.ar.servicio.EstadoUsuarioServicio;
import frgp.utn.edu.ar.servicio.LocalidadServicio;
import frgp.utn.edu.ar.servicio.ProvinciaServicio;
import frgp.utn.edu.ar.servicio.UsuarioServicio;

@Controller
public class AdminController {

	@Autowired
	public UsuarioServicio serviceUsuario;
	@Autowired
	public EstadoUsuarioServicio serviceEstadoUsuario;

	@Autowired
	public ModelAndView MV;
	
	@Autowired
	public Usuario usuario;
	
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
	
	// ALTA USER | "alta-usuario.html"
	@RequestMapping("alta-usuario.html")
	public ModelAndView eventoRedireccionarAltaUsuario()
	{
		MV = cargadorDeListasAdmin(MV);
		MV.setViewName("admin/AltaUsuario");
		return MV;
	}
	
	// LISTA USERS | "reportes.html"
	@RequestMapping("reportes.html")
	public ModelAndView eventoRedireccionarListarUsuarios()
	{
		MV = cargadorDeListasAdmin(MV);
		MV.setViewName("admin/Reportes");
		return MV;
	}
	
	//Eliminar Usuario | "/eliminar_usuario.html"
	@RequestMapping(value ="/eliminar_usuario.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView eliminarCliente(int idEliminar){		
		
		System.out.println(idEliminar);

		usuario = serviceUsuario.obtenerUnRegistro(idEliminar);
		usuario.setEstado(serviceEstadoUsuario.obtenerUnRegistro(0));
	
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
