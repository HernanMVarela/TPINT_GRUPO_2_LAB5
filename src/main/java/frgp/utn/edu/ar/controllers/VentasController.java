package frgp.utn.edu.ar.controllers;

import java.sql.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import frgp.utn.edu.ar.dominio.Detalle_venta;
import frgp.utn.edu.ar.dominio.Stock;
import frgp.utn.edu.ar.dominio.Venta;
import frgp.utn.edu.ar.servicio.ArticuloServicio;
import frgp.utn.edu.ar.servicio.ClienteServicio;
import frgp.utn.edu.ar.servicio.EstadoVentaServicio;
import frgp.utn.edu.ar.servicio.StockServicio;
import frgp.utn.edu.ar.servicio.VentaServicio;

@Controller
public class VentasController {

	@Autowired
	private VentaServicio serviceVenta;
	
	@Autowired
	private EstadoVentaServicio serviceEstadoVenta;

	@Autowired
	private ClienteServicio serviceCliente;

	@Autowired
	private ArticuloServicio serviceArticulo;

	@Autowired
	public StockServicio serviceStock;

	@Autowired
	private Venta venta;
	
	@Autowired
	private Stock stock;
	
	@Autowired
	private Detalle_venta detalle_venta;
	
	@Autowired
	private ModelAndView MV;

	@Autowired
	private ApplicationContext ctx;

	// NO TOCAR - Servlets
	public void init(ServletConfig config) {
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());

		this.serviceVenta = (VentaServicio) ctx.getBean("VentaServiceBean");
		this.serviceEstadoVenta = (EstadoVentaServicio) ctx.getBean("EstadoVentaServiceBean");
		this.serviceCliente = (ClienteServicio) ctx.getBean("ClienteServiceBean");
		this.serviceArticulo = (ArticuloServicio) ctx.getBean("ArticuloServiceBean");
		this.serviceStock = (StockServicio) ctx.getBean("StockServiceBean");
		this.stock = (Stock) ctx.getBean("StockEstandar");
		this.MV = (ModelAndView) ctx.getBean("ModelAndViewBean");
		this.venta = (Venta) ctx.getBean("VentaEstandar");
	}

	// ventas | "ventas.html"
	@RequestMapping("ventas.html")
	public ModelAndView eventoRedireccionarVentas() {
		MV = cargadorDeListasVentas(MV);
		MV.setViewName("vendedor/Ventas");
		return MV;
	}

	// Ingreso de venta | "/alta_venta.html"
	@RequestMapping(value = "/ingreso_venta.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView validarVenta(@RequestParam("fechaNuevo") Date fechaNuevo,
			@RequestParam("cliente") int cliente,
			@RequestParam("articulo") String articulo,
			@RequestParam("cantidad") int cantidad,
			@RequestParam("articulosLista") String articulosLista) {

		venta.setFecha(fechaNuevo);
		venta.setCliente(serviceCliente.obtenerUnRegistro(cliente));

		// Eliminar los corchetes "[" y "]" del String de entrada
		articulosLista = articulosLista.replace("[", "").replace("]", "");

		// Separar el String en objetos individuales
		String[] objetos = articulosLista.split("\\},\\{");

		// Recorrer cada objeto individual y crear instancias de Detalle_venta
		@SuppressWarnings("unchecked")
		List<Detalle_venta> detalleLista = (List<Detalle_venta>)ctx.getBean("DetalleVentaList");
		detalleLista.removeAll(detalleLista);

		for (String objeto : objetos) {

			// Eliminar las llaves "{" y "}" del objeto individual
			objeto = objeto.replace("{", "").replace("}", "");

			// Separar el objeto en sus pares de clave-valor
			String[] pares = objeto.split(",");

			// Variables para almacenar los valores de nombre, cantidad y precio
			String nombre = null;
			int cantidadObjeto = 0;

			// Recorrer los pares de clave-valor y extraer los valores necesarios
			for (String par : pares) {
				String[] keyValue = par.split(":");
				String clave = keyValue[0].trim();
				String valor = keyValue[1].trim();

				// Eliminar las comillas dobles del valor
				clave = clave.replace("\"", "");
				valor = valor.replace("\"", "");

				// Asignar el valor correspondiente según la clave
				if (clave.equals("nombre")) {
					nombre = valor;
				} else if (clave.equals("cantidad")) {
					cantidadObjeto = Integer.parseInt(valor);
				}
			}

			// Crear una instancia de Detalle_venta y agregarla a la lista
			this.detalle_venta = (Detalle_venta) ctx.getBean("DetalleEstandar");
			
			for (Detalle_venta detalle : detalleLista) {
				if(detalle.getArticulo().getNombre().equals(nombre)){
					detalle.sumarCantidad(cantidadObjeto);
					detalle.sumarImporte(detalle.getArticulo().getPrecio_venta()*cantidadObjeto);
					detalle_venta = null;
				}
			}
			if(detalle_venta != null) {
				detalle_venta.setArticulo(serviceArticulo.obtenerUnRegistro(nombre));
				detalle_venta.setCantidad(cantidadObjeto);
				detalle_venta.setImporte(cantidad * detalle_venta.getArticulo().getPrecio_venta());
				detalleLista.add(detalle_venta);
			}
			
		}
		venta.setDetalle(detalleLista);
		venta.setEstado(serviceEstadoVenta.obtenerUnRegistro(1));
		String Message = "";

		try {
			Message = asignarMensajeVenta(serviceVenta.insertarVenta(venta));
			MV.addObject("Mensaje", Message);
			MV = cargadorDeListasVentas(MV);
			MV.setViewName("vendedor/Ventas");
			return MV;
		} catch (Exception e) {
			/// REEMPLAZAR POR DIRECCIONAMIENTO A PAGINA DE ERROR
			Message = e.toString();
			System.out.println(e.toString());
			MV.addObject("Mensaje", Message);
			MV.setViewName("Error");
			return MV;
		}

	}
	
	//Eliminar Venta | "/eliminar_venta.html"
	@RequestMapping(value ="/eliminar_venta.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView eliminarVenta(int idEliminar){		
		

		venta = serviceVenta.obtenerUnRegistro(idEliminar);
		venta.setEstado(serviceEstadoVenta.obtenerUnRegistro(2));
		String Message = "";

		try{
			Message = asignarMensajeVenta(serviceVenta.eliminarVenta(venta, stock));
			MV.addObject("Mensaje", Message);
			MV = cargadorDeListasVentas(MV);
			MV.setViewName("vendedor/Ventas"); 
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
			return "Venta agregada";
		}
		if (error.equals("NO AGREGADO")) {
			return "Venta no agregada";
		}
		if (error.equals("ERROR")) {
			return "ERROR";
		}
		if (error.equals("EXISTE")) {
			return "Venta ya exise";
		}
		if (error.equals("REACTIVADO")) {
			return "Venta Reactivada";
		}
		if (error.equals("ELIMINACION PREVIA")) {
			return "La venta ya estaba eliminada";
		}
		if (error.equals("ELIMINADO")) {
			return "Venta eliminada";
		}
		if (error.equals("NO ELIMINADO")) {
			return "Venta no eliminada";
		}
		if (error.equals("VENTA CANCELADA")) {
			return "No existe stock disponible";
		}
		if (error.equals("ERROR")) {
			return "ERROR";
		}
		return "ERROR";
	}

	private ModelAndView cargadorDeListasVentas(ModelAndView MV) {

		MV.addObject("listaVentas", this.serviceVenta.obtenerVentas());
		MV.addObject("listaClientes", this.serviceCliente.obtenerClientes());
		MV.addObject("listaArticulos", this.serviceArticulo.obtenerArticulos());
		MV.addObject("listaStock", this.serviceStock.obtenerRegistrosUnicos());
		return MV;
	}

}
