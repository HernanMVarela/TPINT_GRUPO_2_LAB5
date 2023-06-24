package frgp.utn.edu.ar.controllers;

import java.sql.Date;

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
import frgp.utn.edu.ar.servicio.LocalidadServicio;
import frgp.utn.edu.ar.servicio.ProvinciaServicio;
import frgp.utn.edu.ar.servicio.UsuarioServicio;

@Controller
public class RegisterController {
	
	@Autowired
	public ProvinciaServicio serviceProvincia;
	@Autowired
	public LocalidadServicio serviceLocalidad;
	@Autowired
	public EstadoUsuarioServicio serviceEstadoUsuario;
	@Autowired
	public UsuarioServicio serviceUsuario;
	
	@Autowired
	public Usuario usuario;

	// NO TOCAR - Servlets
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.serviceProvincia = (ProvinciaServicio) ctx.getBean("ProvinciaServiceBean");
		this.serviceLocalidad = (LocalidadServicio) ctx.getBean("LocalidadServiceBean");
		this.serviceUsuario = (UsuarioServicio) ctx.getBean("UsuarioServiceBean");
		this.serviceEstadoUsuario = (EstadoUsuarioServicio) ctx.getBean("EstadoUsuarioServiceBean");
		this.usuario = (Usuario) ctx.getBean("UsuarioEstandar");
	}	

	// ALTA USER | "alta-usuario.html"
	@RequestMapping("registro-usuario.html")
	public ModelAndView eventoRedireccionarAltaUsuario()
	{
		ModelAndView MV = new ModelAndView();
		MV = cargadorDeListasUsuarios(MV);
		MV.setViewName("registro/RegistroUsuario");
		return MV;
	}
	
	//Ingreso de Usuario | "/alta_usuario.html"
	@RequestMapping(value ="/alta_usuario.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView validarUsuario(String nombre, 
									   String apellido, 
									   String dni, 
									   String sexo,  
									   String direccion,
									   int localidad,
									   String correo, 
									   String telefono, 
									   Date fechaNacimiento,
									   String user,
									   String pass){
		
		ModelAndView MV = new ModelAndView();
		
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setDNI(dni);
		usuario.setSexo(sexo);		
		usuario.setDireccion(direccion);
		usuario.setLocalidad(serviceLocalidad.obtenerUnRegistro(localidad));
		usuario.setCorreo(correo);
		usuario.setTelefono(telefono);
		usuario.setFecha_nac(fechaNacimiento);
		usuario.setNombreU(user);
		usuario.setPassU(pass);		
		usuario.setEstado(serviceEstadoUsuario.obtenerUnRegistro(1));

		String Message = "";
		try{
			Message = asignarMensajeCliente(serviceUsuario.insertarUsuario(usuario));
			MV.addObject("Mensaje", Message);
			MV = cargadorDeListasUsuarios(MV);
			MV.setViewName("Home"); 
			return MV;
		}
		catch(Exception e)
		{
			Message = e.toString();
			System.out.println(e.toString());
			MV.addObject("Mensaje", Message);
			MV.setViewName("Error"); 
			return MV;
		}
	}
	
	private String asignarMensajeCliente(String error) {
		if (error.equals("AGREGADO")) {
			return "Usuario Agregado";
		}
		if (error.equals("NO AGREGADO")) {
			return "Usuario no agregado";
		}
		if (error.equals("ERROR")) {
			return "ERROR";
		}
		return "ERROR";
	}
	
	private ModelAndView cargadorDeListasUsuarios(ModelAndView MV) 
	{
		MV.addObject("listaUsuarios",this.serviceUsuario.obtenerUsuarios());
		MV.addObject("listaLocalidades",this.serviceLocalidad.obtenerLocalidades());
		MV.addObject("listaProvincias",this.serviceProvincia.obtenerProvincias());
		return MV;
	}	
}
