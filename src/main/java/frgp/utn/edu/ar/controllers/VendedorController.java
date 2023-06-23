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
import frgp.utn.edu.ar.servicio.MarcaServicio;
import frgp.utn.edu.ar.servicio.StockServicio;
import frgp.utn.edu.ar.servicio.TipoArticuloServicio;

@Controller
public class VendedorController {

	@Autowired
	public  ArticuloServicio serviceArticulo;
	@Autowired
	public  ClienteServicio serviceCliente;
	@Autowired
	public  TipoArticuloServicio serviceTipoArticulo;
	@Autowired
	public  MarcaServicio serviceMarca;
	@Autowired
	public  StockServicio serviceStock;
	
	// NO TOCAR - Servlets
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.serviceArticulo = (ArticuloServicio) ctx.getBean("ArticuloServiceBean");
		this.serviceTipoArticulo = (TipoArticuloServicio) ctx.getBean("TipoArticuloServiceBean");
		this.serviceMarca = (MarcaServicio) ctx.getBean("MarcaServiceBean");
		this.serviceStock = (StockServicio) ctx.getBean("StockServiceBean");
		this.serviceCliente = (ClienteServicio) ctx.getBean("ClienteServiceBean");
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
		MV = cargadorDeListasArticulos(MV);
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
			Message = asignarMensajeArticulos(serviceArticulo.insertarArticulo(x));
			
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
			Message = asignarMensajeArticulos(serviceArticulo.actualizarArticulo(x));
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
			
			Message = asignarMensajeArticulos(serviceArticulo.actualizarArticulo(x));
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
		
		Cliente x = new Cliente();
		x.setDNI(Long.parseLong(DNI));
		x.setNombre(nombre);
		x.setApellido(apellido);
		x.setSexo(sexo);
		x.setCorreo(cor);
		x.setDireccion(dir);
		x.setFecha_nac(date);
		//x.setLocalidad(loc);
		x.setTelefono(tel);
		

		String Message = "";
		try{
			Message = asignarMensajeCliente(serviceCliente.insertarCliente(x));
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
	
	// Stock | "stock.html"
	@RequestMapping("stock.html")
	public ModelAndView eventoRedireccionarStock()
	{
		ModelAndView MV = new ModelAndView();
		MV = cargadorDeListasStocks(MV);
		MV.setViewName("vendedor/Stock");
		return MV;
	}
	
	// Ingreso de Stock | "/ingreso_stock.html"
		@RequestMapping(value ="/ingreso_stock.html" , method= { RequestMethod.GET, RequestMethod.POST})
		public ModelAndView validarStock(String art, Date date, String cantidad, String precio_compra){
			ModelAndView MV = new ModelAndView();
			
			Stock x = new Stock();
			x.setArticulo(serviceArticulo.obtenerUnRegistro(art));
			x.setCantidad(Integer.parseInt(cantidad));
			x.setPreciocompra(Float.parseFloat(precio_compra));
			x.setFechaingreso(date);
			
			String Message = "";
			try{
				Message = asignarMensajeStocks(serviceStock.ingresarStock(x));
				MV.addObject("Mensaje", Message);
				MV = cargadorDeListasStocks(MV);
				MV.setViewName("vendedor/Stock"); 
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
	
	// Clientes | "ventas.html"
	@RequestMapping("ventas.html")
	public ModelAndView eventoRedireccionarVentas()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("vendedor/Ventas");
		return MV;
	}	
	
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
	
	private String asignarMensajeStocks(String error) {
		if (error.equals("AGREGADO")) {
			return "Stock agregado";
		}
		if (error.equals("NO AGREGADO")) {
			return "Stock no agregado";
		}
		if (error.equals("ERROR")) {
			return "ERROR";
		}
		return "ERROR";
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
	
	private ModelAndView cargadorDeListasArticulos(ModelAndView MV) 
	{
		MV.addObject("listaArticulos",this.serviceArticulo.obtenerArticulos());
		MV.addObject("listaTipoArticulos",this.serviceTipoArticulo.obtenerTiposDeArticulo());
		MV.addObject("listaMarcas",this.serviceMarca.obtenerMarcas());
		MV.addObject("listaStock",this.serviceStock.obtenerStock());
		return MV;
	}
	
	private ModelAndView cargadorDeListasStocks(ModelAndView MV) 
	{
		MV.addObject("listaArticulos",this.serviceArticulo.obtenerArticulos());
		MV.addObject("listaStocks",this.serviceStock.obtenerStock());
		MV.addObject("listaClientes",this.serviceCliente.obtenerClientes());
		return MV;
	}
	
	private ModelAndView cargadorDeListasClientes(ModelAndView MV) 
	{
		MV.addObject("listaClientes",this.serviceCliente.obtenerClientes());
		return MV;
	}
	
}
