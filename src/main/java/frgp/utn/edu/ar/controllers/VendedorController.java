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
	@RequestMapping(value ="/altaArticulo.html" , method= { RequestMethod.GET, RequestMethod.POST})
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
		
		}
	
		MV.addObject("Mensaje", Message);
		MV.addObject("listaArticulos",this.service.obtenerArticulos());
		MV.setViewName("vendedor/Articulos"); 
		return MV;
		
	}
	
	// MODIFICAR ARTICULO | "/modificar_articulo.html"
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
		
		}
	
		MV.addObject("Mensaje", Message);
		MV.addObject("listaArticulos",this.service.obtenerArticulos());
		MV.setViewName("vendedor/Articulos"); 
		return MV;
		
	}
	
	// ELIMINAR ARTICULO | "/eliminarArticulo.html"
	@RequestMapping(value ="/eliminarArticulo.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView eliminarArticulo(String nombre){
		ModelAndView MV = new ModelAndView();
		service.eliminarArticulo(nombre);
		MV.addObject("listaArticulos",this.service.obtenerArticulos());
		MV.setViewName("vendedor/HomeVendedor");
		MV.addObject("Mensaje", "Articulo eliminado");
		return MV;
	}
	
	// ELIMINAR ARTICULO | "/delete-articulos-{nombre}" 
	@RequestMapping(value = { "/delete-articulos-{nombre}" }, method = RequestMethod.GET)
    public ModelAndView deleteArticulo(@PathVariable String nombre) {
		service.eliminarArticulo(nombre);
		ModelAndView MV = new ModelAndView();
		MV.setViewName("vendedor/HomeVendedor");
		
		//Actualiza los usuarios
		MV.addObject("listaArticulos",this.service.obtenerArticulos());
		MV.setViewName("vendedor/HomeVendedor");
		return MV;
    }
	
	/*
	@RequestMapping(value ="/recargaGrillaArticulos.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView recargarArticulo(){
		ModelAndView MV = new ModelAndView();
		MV.addObject("listaArticulos",this.service.obtenerArticulos());
		MV.setViewName("Articulos"); 
		return MV;	
	}
	*/
	
	// Clientes | "consulta-ventas.html"
	@RequestMapping("listado_clientes.html")
	public ModelAndView eventoRedireccionarClientes()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("vendedor/Clientes");
		return MV;
	}
	
	
	
}