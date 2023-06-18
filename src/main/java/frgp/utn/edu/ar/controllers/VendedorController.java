package frgp.utn.edu.ar.controllers;

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
import frgp.utn.edu.ar.dominio.Marca;
import frgp.utn.edu.ar.dominio.Tipo_Articulo;
import frgp.utn.edu.ar.servicio.ArticuloServicio;
import frgp.utn.edu.ar.servicio.MarcaServicio;
import frgp.utn.edu.ar.servicio.TipoArticuloServicio;

@Controller
public class VendedorController {

	@Autowired
	public  ArticuloServicio serviceArticulo;
	@Autowired
	public  TipoArticuloServicio serviceTipoArticulo;
	@Autowired
	public  MarcaServicio serviceMarca;
	
	// NO TOCAR - Servlets
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.serviceArticulo = (ArticuloServicio) ctx.getBean("ArticuloServiceBean");
		this.serviceTipoArticulo = (TipoArticuloServicio) ctx.getBean("TipoArticuloServiceBean");
		this.serviceMarca = (MarcaServicio) ctx.getBean("MarcaServiceBean");
	}	
	
	//HOME VENDEDOR | vendedor.html"
	@RequestMapping("vendedor.html")
	public ModelAndView eventoRedireccionarHomeVendedor()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("vendedor/HomeVendedor");
		return MV;
	}
	
	/// AMBL ARTICULOS | "articulos.html"
	@RequestMapping("articulos.html")
	public ModelAndView eventoRedireccionarArticulos()
	{
		ModelAndView MV = new ModelAndView();
		MV = cargadorDeListas(MV);
		MV.setViewName("vendedor/Articulos");
		return MV;
	}
	
	// ALTA DE NUEVO ARTICULO | "/altaArticulo.html"
	@RequestMapping(value ="/alta_articulo.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView validarArticulo(String nombre, int marca, int tipo, String precio_venta, String descripcion){
		ModelAndView MV = new ModelAndView();
		
		Articulo x = new Articulo();
		x.setNombre(nombre);
		x.setPrecio_venta(Float.parseFloat(precio_venta));
		x.setMarca(serviceMarca.obtenerUnRegistro(marca));
		x.setTipo(serviceTipoArticulo.obtenerUnRegistro(tipo));
		x.setDescripcion(descripcion);
		x.setEstado(true);

		String Message = "";
		try{
			Message = asignarMensaje(serviceArticulo.insertarArticulo(x));
			
			MV.addObject("Mensaje", Message);
			MV = cargadorDeListas(MV);
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
		ModelAndView MV = new ModelAndView();
		
		Articulo x = new Articulo();
		x.setNombre(nombre);
		x.setPrecio_venta(Float.parseFloat(precio_venta));
		x.setMarca(serviceMarca.obtenerUnRegistro(marca));
		x.setTipo(serviceTipoArticulo.obtenerUnRegistro(tipo));
		x.setDescripcion(descripcion);
		x.setEstado(true);
		
		String Message="";
		
		try{
			Message = asignarMensaje(serviceArticulo.actualizarArticulo(x));
			MV.addObject("Mensaje", Message);
			MV = cargadorDeListas(MV);
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
	public ModelAndView eliminarArticulo(String nombreEliminar, int marcaEliminar, int tipoEliminar, String precio_ventaEliminar, String descripcionEliminar){
		ModelAndView MV = new ModelAndView();
		
		Articulo x = new Articulo();
		x.setNombre(nombreEliminar);
		x.setPrecio_venta(Float.parseFloat(precio_ventaEliminar));
		x.setMarca(serviceMarca.obtenerUnRegistro(marcaEliminar));
		x.setTipo(serviceTipoArticulo.obtenerUnRegistro(tipoEliminar));
		x.setDescripcion(descripcionEliminar);
		x.setEstado(false);
		
		String Message="";
		
		try{
			
			Message = asignarMensaje(serviceArticulo.actualizarArticulo(x));
			MV.addObject("Mensaje", Message);
			MV = cargadorDeListas(MV);
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
	
	// Clientes | "clientes.html"
	@RequestMapping("clientes.html")
	public ModelAndView eventoRedireccionarClientes()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("vendedor/Clientes");
		return MV;
	}
	
	// Clientes | "stock.html"
	@RequestMapping("stock.html")
	public ModelAndView eventoRedireccionarStock()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("vendedor/Stock");
		return MV;
	}
	
	// Clientes | "ventas.html"
	@RequestMapping("ventas.html")
	public ModelAndView eventoRedireccionarVentas()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("vendedor/Ventas");
		return MV;
	}	
	
	
	/// METODOS COMUNES
	private String asignarMensaje(String error) {
		if (error.equals("AGREGADO")) {
			System.out.println("Articulo agregado");
			return "Articulo agregado";
		}
		if (error.equals("MODIFICADO")) {
			System.out.println("El articulo fue modificado");
			return "El articulo fue modificado";
		}
		if (error.equals("NO MODIFICADO")) {
			System.out.println("El articulo no fue modificado");
			return "El articulo no fue modificado";
		}
		if (error.equals("ACTIVADO")) {
			System.out.println("Articulo re-activado");
			return "Articulo re-activado";
		}
		if (error.equals("NO AGREGADO")) {
			System.out.println("Articulo no agregado");
			return "Articulo no agregado";
		}
		if (error.equals("ELIMINADO")) {
			System.out.println("Articulo eliminado");
			return "Articulo eliminado";
		}
		if (error.equals("NO ELIMINADO")) {
			System.out.println("Articulo no fue eliminado");
			return "Articulo no fue eliminado";
		}
		if (error.equals("EXISTE")) {
			System.out.println("El articulo ya existe");
			return "El articulo ya existe";
		}
		if (error.equals("ERROR")) {
			System.out.println("REDIRECCIONAR A ERROR");
			return "REDIRECCIONAR A ERROR";
		}
		return "ERROR";
	}
	
	private ModelAndView cargadorDeListas(ModelAndView MV) 
	{
		MV.addObject("listaArticulos",this.serviceArticulo.obtenerArticulos());
		MV.addObject("listaTipoArticulos",this.serviceTipoArticulo.obtenerTiposDeArticulo());
		MV.addObject("listaMarcas",this.serviceMarca.obtenerMarcas());
		return MV;
	}
	
}
