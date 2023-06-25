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
import frgp.utn.edu.ar.dominio.Venta;
import frgp.utn.edu.ar.servicio.ArticuloServicio;
import frgp.utn.edu.ar.servicio.ClienteServicio;
import frgp.utn.edu.ar.servicio.EstadoClienteServicio;
import frgp.utn.edu.ar.servicio.LocalidadServicio;
import frgp.utn.edu.ar.servicio.ProvinciaServicio;
import frgp.utn.edu.ar.servicio.VentaServicio;

@Controller
public class VentasController {
	
	@Autowired
	public VentaServicio serviceVenta;

	@Autowired
	public ClienteServicio serviceCliente;
	
	@Autowired
	public ArticuloServicio serviceArticulo;
	
	@Autowired
	public Venta venta;
	
	@Autowired
	public ModelAndView MV;





	// NO TOCAR - Servlets
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.serviceVenta = (VentaServicio) ctx.getBean("VentaServiceBean");
		this.serviceCliente = (ClienteServicio) ctx.getBean("ClienteServiceBean");
		this.serviceArticulo = (ArticuloServicio) ctx.getBean("ArticuloServiceBean");
		
		this.MV = (ModelAndView) ctx.getBean("ModelAndViewBean");
		 this.venta = (Venta) ctx.getBean("VentaEstandar");
	}	
		
	// ventas | "ventas.html"
	@RequestMapping("ventas.html")
	public ModelAndView eventoRedireccionarVentas()
	{
		MV = cargadorDeListasVentas(MV);
		MV.setViewName("vendedor/Ventas");
		return MV;
	}
	
	//Ingreso de venta | "/alta_venta.html"
	@RequestMapping(value ="/ingreso_venta.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView validarVenta(Date fechaNuevo){
		
		 venta.setFecha(fechaNuevo);
		 venta.setCliente(serviceCliente.obtenerUnRegistro(3));
	
       System.out.println(venta);
		String Message = "";
		try{
			 Message = asignarMensajeVenta(serviceVenta.insertarVenta(venta));
			MV.addObject("Mensaje", Message);
			// MV = cargadorDeListasVentas(MV);
			// MV.setViewName("vendedor/Ventas"); 
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

	
	
	private String asignarMensajeVenta(String error) {
		if (error.equals("AGREGADO")) {
			return "Venta Agregado";
		}
		if (error.equals("NO AGREGADO")) {
			return "Venta no agregado";
		}
		if (error.equals("ERROR")) {
			return "ERROR";
		}
		if (error.equals("EXISTE")) {
			return "Venta ya exise";
		}
		if (error.equals("REACTIVADO")) {
			return "Venta Reactivado";
		}
		if (error.equals("ELIMINACION PREVIA")) {
			return "El venta ya estaba eliminado";
		}
		if (error.equals("ELIMINADO")) {
			return "Venta eliminado";
		}
		if (error.equals("NO ELIMINADO")) {
			return "Venta no eliminado";
		}
		if (error.equals("MODIFICADO")) {
			return "Venta no modificado";
		}
		if (error.equals("NO MODIFICADO")) {
			return "Venta no modificado";
		}
		if (error.equals("ERROR")) {
			return "ERROR";
		}
		return "ERROR";
	}
	
	private ModelAndView cargadorDeListasVentas(ModelAndView MV) 
	{
		MV.addObject("listaVentas",this.serviceVenta.obtenerVentas());
		MV.addObject("listaLocalidades",this.serviceArticulo.obtenerArticulos());
		MV.addObject("listaProvincias",this.serviceCliente.obtenerClientes());
		return MV;
	}
	
}
