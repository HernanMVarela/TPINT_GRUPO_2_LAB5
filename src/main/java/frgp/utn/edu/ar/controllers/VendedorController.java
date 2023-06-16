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

@Controller
public class VendedorController {

	@Autowired
	public  ArticuloServicio service;
	
	// NO TOCAR - Servlet
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.service = (ArticuloServicio) ctx.getBean("serviceBean");
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
		MV.addObject("listaArticulos",this.service.obtenerArticulos());
		MV.setViewName("vendedor/Articulos");
		return MV;
	}
	
	// ALTA DE NUEVO ARTICULO | "/altaArticulo.html"
	@SuppressWarnings("finally")
	@RequestMapping(value ="/alta_articulo.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView validarArticulo(String nombre, String marca, String tipo, String precio_compra, String descripcion){
		ModelAndView MV = new ModelAndView();
		
		Articulo x = new Articulo();
		x.setNombre(nombre);
		x.setPrecio_compra(Float.parseFloat(precio_compra));
		x.setMarca(new Marca(marca));
		x.setTipo(new Tipo_Articulo(tipo));
		x.setDescripcion(descripcion);
		x.setEstado(true);
		
		String Message="";
		
		try{
			
			service.insertarArticulo(x);
			System.out.println("Articulo agregado");
		}
		catch(Exception e)
		{
			System.out.println("Articulo no agregado");
		}
		finally
		{
			MV.addObject("Mensaje", Message);
			MV.addObject("listaArticulos",this.service.obtenerArticulos());
			MV.setViewName("vendedor/Articulos"); 
			return MV;
		}		
	}
	
	// MODIFICAR ARTICULO | "/modificar_articulo.html"
	@SuppressWarnings("finally")
	@RequestMapping(value ="/modificar_articulo.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modificarArticulo(String nombre, String marca, String tipo, String precio_compra, String descripcion){
		ModelAndView MV = new ModelAndView();
		
		Articulo x = new Articulo();
		x.setNombre(nombre);
		x.setPrecio_compra(Float.parseFloat(precio_compra));
		x.setMarca(new Marca(marca));
		x.setTipo(new Tipo_Articulo(tipo));
		x.setDescripcion(descripcion);
		x.setEstado(true);
		
		String Message="";
		
		try{
			service.actualizarArticulo(x);
			System.out.println("Articulo Actualizado");
		}
		catch(Exception e)
		{
			System.out.println("Articulo no Actualizado");
		}
		finally
		{
			MV.addObject("Mensaje", Message);
			MV.addObject("listaArticulos",this.service.obtenerArticulos());
			MV.setViewName("vendedor/Articulos"); 
			return MV;
		}		
	}
	
	// ELIMINAR ARTICULO (BAJA LOGICA) | "/eliminar_articulo.html"
	@SuppressWarnings("finally")
	@RequestMapping(value ="/eliminar_articulo.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView eliminarArticulo(String nombre, String marca, String tipo, String precio_compra, String descripcion){
		ModelAndView MV = new ModelAndView();
		
		System.out.println(nombre);
		System.out.println(marca);
		System.out.println(tipo);
		System.out.println(precio_compra);
		System.out.println(descripcion);

		Articulo x = new Articulo();
		x.setNombre(nombre);
		x.setPrecio_compra(Float.parseFloat(precio_compra));
		x.setMarca(new Marca(marca));
		x.setTipo(new Tipo_Articulo(tipo));
		x.setDescripcion(descripcion);
		x.setEstado(false);
		
		String Message="";
		
		try{
			service.actualizarArticulo(x);
			System.out.println("Articulo Eliminado");
		}
		catch(Exception e)
		{
			System.out.println("Articulo no eliminado");
		}
		finally
		{
			MV.addObject("Mensaje", Message);
			MV.addObject("listaArticulos",this.service.obtenerArticulos());
			MV.setViewName("vendedor/Articulos"); 
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
}
