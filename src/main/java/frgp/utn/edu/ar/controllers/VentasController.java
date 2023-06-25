package frgp.utn.edu.ar.controllers;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import frgp.utn.edu.ar.dominio.Cliente;
import frgp.utn.edu.ar.dominio.Detalle_venta;
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
	public ModelAndView validarVenta(@RequestParam("fechaNuevo") Date fechaNuevo,
                                 @RequestParam("cliente") int cliente,
                                 @RequestParam("articulo") String articulo,
                                 @RequestParam("cantidad") int cantidad,
                                 @RequestParam("articulosLista") String articulosLista){
		
    venta.setFecha(fechaNuevo);
    venta.setCliente(serviceCliente.obtenerUnRegistro(cliente));

    // Crear una lista para almacenar los objetos de detalle
    List<Detalle_venta> detalleLista = new ArrayList<>();

    // Eliminar los corchetes "[" y "]" del String de entrada
    articulosLista = articulosLista.replace("[", "").replace("]", "");

    // Separar el String en objetos individuales
    String[] objetos = articulosLista.split("\\},\\{");

    // Recorrer cada objeto individual y crear instancias de Detalle_venta
    for (String objeto : objetos) {
        // Eliminar las llaves "{" y "}" del objeto individual
        objeto = objeto.replace("{", "").replace("}", "");

        // Separar el objeto en sus pares de clave-valor
        String[] pares = objeto.split(",");

        // Variables para almacenar los valores de nombre, cantidad y precio
        String nombre = null;
        int cantidadObjeto = 0;
        int precio = 0;

        // Recorrer los pares de clave-valor y extraer los valores necesarios
        for (String par : pares) {
            String[] keyValue = par.split(":");
            String clave = keyValue[0].trim();
            String valor = keyValue[1].trim();

            // Eliminar las comillas dobles del valor
            clave = clave.replace("\"", "");
			valor = valor.replace("\"", "");
			// System.out.println(clave);
			// System.out.println(valor);
		
            // Asignar el valor correspondiente según la clave
            if (clave.equals("nombre")) {
                nombre = valor;
            } else if (clave.equals("cantidad")) {
                cantidadObjeto = Integer.parseInt(valor);
            } else if (clave.equals("precio")) {
                precio = Integer.parseInt(valor);
            }
			 
        }

        // Crear una instancia de Detalle_venta y agregarla a la lista
         Detalle_venta detalle = new Detalle_venta(serviceArticulo.obtenerUnRegistro(nombre), cantidadObjeto);
         detalleLista.add(detalle);
    }
		venta.setDetalle(detalleLista);
		String Message = "";
		try{
		    Message = asignarMensajeVenta(serviceVenta.insertarVenta(venta));
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
		MV.addObject("listaClientes",this.serviceCliente.obtenerClientes());
		MV.addObject("listaArticulos",this.serviceArticulo.obtenerArticulos());
		return MV;
	}
	
}