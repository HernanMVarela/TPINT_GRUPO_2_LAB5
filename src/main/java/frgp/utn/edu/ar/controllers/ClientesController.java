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
import org.springframework.web.bind.annotation.PathVariable;

import frgp.utn.edu.ar.dominio.Articulo;
import frgp.utn.edu.ar.dominio.Cliente;
import frgp.utn.edu.ar.dominio.Estado_Cli;
import frgp.utn.edu.ar.dominio.Localidad;
import frgp.utn.edu.ar.dominio.Marca;
import frgp.utn.edu.ar.dominio.Stock;
import frgp.utn.edu.ar.dominio.Tipo_Articulo;
import frgp.utn.edu.ar.servicio.ArticuloServicio;
import frgp.utn.edu.ar.servicio.ClienteServicio;
import frgp.utn.edu.ar.servicio.EstadoClienteServicio;
import frgp.utn.edu.ar.servicio.LocalidadServicio;
import frgp.utn.edu.ar.servicio.MarcaServicio;
import frgp.utn.edu.ar.servicio.ProvinciaServicio;
import frgp.utn.edu.ar.servicio.StockServicio;
import frgp.utn.edu.ar.servicio.TipoArticuloServicio;

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

	// NO TOCAR - Servlets
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.serviceProvincia = (ProvinciaServicio) ctx.getBean("ProvinciaServiceBean");
		this.serviceLocalidad = (LocalidadServicio) ctx.getBean("LocalidadServiceBean");
		this.serviceCliente = (ClienteServicio) ctx.getBean("ClienteServiceBean");
		this.serviceEstadoCliente = (EstadoClienteServicio) ctx.getBean("EstadoClienteServiceBean");
		this.cliente = (Cliente) ctx.getBean("ClienteEstandar");
	}	
		
	// Clientes | "clientes.html"
	@RequestMapping("clientes.html")
	public ModelAndView eventoRedireccionarClientes()
	{
		ModelAndView MV = new ModelAndView();
		MV = cargadorDeListasClientes(MV);
		MV.setViewName("vendedor/Clientes");
		return MV;
	}
	
	//Ingreso de Cliente | "/alta_cliente.html"
	@RequestMapping(value ="/alta_cliente.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView validarCliente(String nombre, String apellido, String sexo, String DNI, Date date, String dir, String loc, String cor, String tel){
		ModelAndView MV = new ModelAndView();
		
		cliente.setDNI(DNI);
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setSexo(sexo);
		cliente.setCorreo(cor);
		cliente.setDireccion(dir);
		cliente.setFecha_nac(date);
		cliente.setLocalidad(serviceLocalidad.obtenerUnRegistro(1));
		cliente.setTelefono(tel);
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
		return "ERROR";
	}
	
	private ModelAndView cargadorDeListasClientes(ModelAndView MV) 
	{
		MV.addObject("listaClientes",this.serviceCliente.obtenerClientes());
		return MV;
	}
	
}
