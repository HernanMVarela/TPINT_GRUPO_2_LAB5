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
import frgp.utn.edu.ar.dominio.Cliente;
import frgp.utn.edu.ar.servicio.ClienteServicio;
import frgp.utn.edu.ar.servicio.EstadoClienteServicio;
import frgp.utn.edu.ar.servicio.LocalidadServicio;
import frgp.utn.edu.ar.servicio.ProvinciaServicio;

@Controller
public class ClientesController {

	@Autowired
	public ProvinciaServicio serviceProvincia;
	@Autowired
	public LocalidadServicio serviceLocalidad;
	@Autowired
	public EstadoClienteServicio serviceEstadoCliente;
	@Autowired
	public ClienteServicio serviceCliente;
	
	@Autowired
	public Cliente cliente;
	
	@Autowired
	public ModelAndView MV;


	// NO TOCAR - Servlets
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.serviceProvincia = (ProvinciaServicio) ctx.getBean("ProvinciaServiceBean");
		this.serviceLocalidad = (LocalidadServicio) ctx.getBean("LocalidadServiceBean");
		this.serviceCliente = (ClienteServicio) ctx.getBean("ClienteServiceBean");
		this.serviceEstadoCliente = (EstadoClienteServicio) ctx.getBean("EstadoClienteServiceBean");
		this.cliente = (Cliente) ctx.getBean("ClienteEstandar");
		this.MV = (ModelAndView) ctx.getBean("ModelAndViewBean");
	}	
		
	// Clientes | "clientes.html"
	@RequestMapping("clientes.html")
	public ModelAndView eventoRedireccionarClientes()
	{
		MV = cargadorDeListasClientes(MV);
		MV.setViewName("vendedor/Clientes");
		return MV;
	}
	
	//Ingreso de Cliente | "/alta_cliente.html"
	@RequestMapping(value ="/alta_cliente.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView validarCliente(String nomNuevo, String apeNuevo, String sexoNuevo, String DNINuevo, Date fechaNuevo,
									   String direcNuevo, int localidadNuevo, String corNuevo, String telNuevo){
		
		cliente.setDNI(DNINuevo);
		cliente.setNombre(nomNuevo);
		cliente.setApellido(apeNuevo);
		cliente.setSexo(sexoNuevo);
		cliente.setCorreo(corNuevo);
		cliente.setDireccion(direcNuevo);
		cliente.setFecha_nac(fechaNuevo);
		cliente.setLocalidad(serviceLocalidad.obtenerUnRegistro(localidadNuevo));
		cliente.setTelefono(telNuevo);
		cliente.setEstado(serviceEstadoCliente.obtenerUnRegistro(1));

		String Message = "";
		try{
			Message = asignarMensajeCliente(serviceCliente.insertarCliente(cliente));
			MV.addObject("Mensaje", Message);
			MV = cargadorDeListasClientes(MV);
			MV.setViewName("vendedor/Clientes"); 
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

		//Modificar Cliente | "/modificar_cliente.html"
	@RequestMapping(value ="/modificar_cliente.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modificarCliente(
		int IDModif,Date fechaModif,String DNIModif,String nombreModif,
		String apellidoModif,String sexoModif,String correoModif, String direccionModif,
		String telefonoModif){		
		
		cliente.setID(IDModif);
		cliente.setDNI(DNIModif);
		cliente.setNombre(nombreModif);
		cliente.setApellido(apellidoModif);
		cliente.setSexo(sexoModif);
		cliente.setCorreo(correoModif);
		cliente.setDireccion(direccionModif);
		cliente.setFecha_nac(fechaModif);
		cliente.setLocalidad(serviceLocalidad.obtenerUnRegistro(18));
		cliente.setTelefono(telefonoModif);
		cliente.setEstado(serviceEstadoCliente.obtenerUnRegistro(1));

		String Message = "";	
		
		try{
			Message = asignarMensajeCliente(serviceCliente.actualizarCliente(cliente));
			MV.addObject("Mensaje", Message);
			MV = cargadorDeListasClientes(MV);
			MV.setViewName("vendedor/Clientes"); 
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
	
		//Modificar Cliente | "/eliminar_cliente.html"
	@RequestMapping(value ="/eliminar_cliente.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView eliminarCliente(int IDModif){		
		
		cliente = serviceCliente.obtenerUnRegistro(IDModif);
		cliente.setEstado(serviceEstadoCliente.obtenerUnRegistro(2));
	
		String Message = "";	
		
		try{
			Message = asignarMensajeCliente(serviceCliente.eliminarCliente(cliente));
			MV.addObject("Mensaje", Message);
			MV = cargadorDeListasClientes(MV);
			MV.setViewName("vendedor/Clientes"); 
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
	
	private String asignarMensajeCliente(String error) {
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
	
	private ModelAndView cargadorDeListasClientes(ModelAndView MV) 
	{
		MV.addObject("listaClientes",this.serviceCliente.obtenerClientes());
		MV.addObject("listaLocalidades",this.serviceLocalidad.obtenerLocalidades());
		MV.addObject("listaProvincias",this.serviceProvincia.obtenerProvincias());
		return MV;
	}
	
}
