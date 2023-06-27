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
import frgp.utn.edu.ar.servicio.ArticuloServicio;
import frgp.utn.edu.ar.servicio.MarcaServicio;
import frgp.utn.edu.ar.servicio.TipoArticuloServicio;

@Controller
public class ArticuloController {

	@Autowired
	private  ArticuloServicio serviceArticulo;
	@Autowired
	private  TipoArticuloServicio serviceTipoArticulo;
	@Autowired
	private  MarcaServicio serviceMarca;
	
	@Autowired
	private ModelAndView MV;
	@Autowired
	private Articulo articulo;
	
	// NO TOCAR - Servlets
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.serviceArticulo = (ArticuloServicio) ctx.getBean("ArticuloServiceBean");
		this.serviceTipoArticulo = (TipoArticuloServicio) ctx.getBean("TipoArticuloServiceBean");
		this.serviceMarca = (MarcaServicio) ctx.getBean("MarcaServiceBean");
		
		this.MV = (ModelAndView) ctx.getBean("ModelAndViewBean");
		this.articulo = (Articulo) ctx.getBean("ArticuloEstandar");
	}
	
	//HOME VENDEDOR | vendedor.html"
	@RequestMapping("vendedor.html")
	public ModelAndView eventoRedireccionarHomeVendedor()
	{		
		MV.setViewName("vendedor/HomeVendedor");
		return MV;
	}
	
	/// AMBL ARTICULOS | "articulos.html"
	@RequestMapping("articulos.html")
	public ModelAndView eventoRedireccionarArticulos()
	{
		MV = cargadorDeListasArticulos(MV);
		MV.setViewName("vendedor/Articulos");
		return MV;
	}
	
	// ALTA DE NUEVO ARTICULO | "/altaArticulo.html"
	@RequestMapping(value ="/alta_articulo.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView validarArticulo(String nombre, int marca, int tipo, String precio_venta, String descripcion){
		System.out.println("LLEGA A ALTA ARTICULO");
		
		articulo.setNombre(nombre);
		articulo.setPrecio_venta(Float.parseFloat(precio_venta));
		articulo.setMarca(serviceMarca.obtenerUnRegistro(marca));
		articulo.setTipo(serviceTipoArticulo.obtenerUnRegistro(tipo));
		articulo.setDescripcion(descripcion);
		articulo.setEstado(true);

		String Message = "";
		try{
			Message = asignarMensajeArticulos(serviceArticulo.insertarArticulo(articulo));
			
			MV.addObject("Mensaje", Message);
			MV = cargadorDeListasArticulos(MV);
			MV.setViewName("vendedor/Articulos"); 
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
	
	// MODIFICAR ARTICULO | "/modificar_articulo.html"
	@RequestMapping(value ="/modificar_articulo.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modificarArticulo(String nombre, int marca, int tipo, String precio_venta, String descripcion){	
		
		articulo.setNombre(nombre);
		articulo.setPrecio_venta(Float.parseFloat(precio_venta));
		articulo.setMarca(serviceMarca.obtenerUnRegistro(marca));
		articulo.setTipo(serviceTipoArticulo.obtenerUnRegistro(tipo));
		articulo.setDescripcion(descripcion);
		articulo.setEstado(true);
		
		String Message="";
		
		try{
			Message = asignarMensajeArticulos(serviceArticulo.actualizarArticulo(articulo));
			MV.addObject("Mensaje", Message);
			MV = cargadorDeListasArticulos(MV);
			MV.setViewName("vendedor/Articulos"); 
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
	
	// ELIMINAR ARTICULO | "/eliminar_articulo.html"
	@RequestMapping(value ="/eliminar_articulo.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView eliminarArticulo(String nombreEliminar){

		articulo = serviceArticulo.obtenerUnRegistro(nombreEliminar);
		articulo.setEstado(false);
		
		String Message="";
		
		try{
			Message = asignarMensajeArticulos(serviceArticulo.actualizarArticulo(articulo));
			MV.addObject("Mensaje", Message);
			MV = cargadorDeListasArticulos(MV);
			MV.setViewName("vendedor/Articulos"); 
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
	
	// Clientes | "ventas.html"
	// @RequestMapping("ventas.html")
	// public ModelAndView eventoRedireccionarVentas()
	// {
	// 	MV.setViewName("vendedor/Ventas");
	// 	return MV;
	// }	
	
	/// METODOS COMUNES
	private String asignarMensajeArticulos(String error) {
		if (error.equals("AGREGADO")) {
			return "Articulo agregado";
		}
		if (error.equals("MODIFICADO")) {
			return "El articulo fue modificado";
		}
		if (error.equals("NO MODIFICADO")) {
			return "El articulo no fue modificado";
		}
		if (error.equals("ACTIVADO")) {
			return "Articulo re-activado";
		}
		if (error.equals("NO AGREGADO")) {
			return "Articulo no agregado";
		}
		if (error.equals("ELIMINADO")) {
			return "Articulo eliminado";
		}
		if (error.equals("NO ELIMINADO")) {
			return "Articulo no fue eliminado";
		}
		if (error.equals("EXISTE")) {
			return "El articulo ya existe";
		}
		if (error.equals("ERROR")) {
			return "REDIRECCIONAR A ERROR";
		}
		return "ERROR";
	}
	
	private ModelAndView cargadorDeListasArticulos(ModelAndView MV) 
	{
		MV.addObject("listaArticulos",this.serviceArticulo.obtenerArticulos());
		MV.addObject("listaTipoArticulos",this.serviceTipoArticulo.obtenerTiposDeArticulo());
		MV.addObject("listaMarcas",this.serviceMarca.obtenerMarcas());
		return MV;
	}
	
}
