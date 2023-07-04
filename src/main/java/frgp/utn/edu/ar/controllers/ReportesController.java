package frgp.utn.edu.ar.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.servicio.Detalle_ventaServicio;
import frgp.utn.edu.ar.servicio.UsuarioServicio;

@Controller
public class ReportesController {

	@Autowired
	private UsuarioServicio serviceUsuario;
	
	@Autowired
	private Detalle_ventaServicio serviceDetalleVenta;

	@Autowired
	private ModelAndView MV;
	
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.serviceUsuario = (UsuarioServicio) ctx.getBean("UsuarioServiceBean");
		this.serviceDetalleVenta = (Detalle_ventaServicio) ctx.getBean("Detalle_ventaServiceBean");;
		
		this.MV = (ModelAndView) ctx.getBean("ModelAndViewBean");
	}	
	
	// REPORTES | "reportes.html"
	@RequestMapping("reportes.html")
	public ModelAndView eventoRedireccionarReportes()
	{
		MV = cargadorDeListasReportes(MV);
		MV.setViewName("admin/Reportes");
		return MV;
	}
	
	@RequestMapping("calcularReportes.html")
	public ModelAndView eventoCalcularReportes(Date start, Date end)
	{
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String desde = formatter.format(start);
        String hasta = formatter.format(end);
		
		String Message = "";
		try{
			if(start != null && end != null) {
				MV.addObject("desde", desde);
				MV.addObject("hasta", hasta);
				MV.addObject("listaProductos",this.serviceDetalleVenta.obtenerProductosPorCantidad(start, end));
				MV.addObject("listaRoles",this.serviceUsuario.obtenerUsuariosPorRol());
			}else {
				MV= cargadorDeListasReportes(MV);	
			}
			
	        MV.addObject("Mensaje", Message);
			MV.setViewName("admin/Reportes"); 
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
	
	private ModelAndView cargadorDeListasReportes(ModelAndView MV) 
	{
		MV.addObject("listaRoles",this.serviceUsuario.obtenerUsuariosPorRol());
		MV.addObject("listaProductos",this.serviceDetalleVenta.obtenerProductosPorCantidad(null, null));		
		return MV;
	}
}
