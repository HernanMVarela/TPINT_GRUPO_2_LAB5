package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {

	@Autowired
	private ModelAndView MV;
	
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.MV = (ModelAndView) ctx.getBean("ModelAndViewBean");
	}
	
	// "/common/header"
    @RequestMapping("/common/header.html")
    public String showHeader() {

        return "common/Header.jspf";
    }
	
    // "cssStyles/StyleSheet"
    @RequestMapping("/cssStyles/StyleSheet.html")
	public String getStyleSheet() {
 
        return "cssStyles/StyleSheet.Css";
    }
    
	//NAVBARS
    @RequestMapping("/common/NavigatorCommon.jspf")
    public String getNavigatorCommon() {
   
        return "common/NavigatorCommon";
    }
	
    @RequestMapping("/common/NavigatorVendedor.jspf")
    public String getNavigatorVendedor() {
   
        return "common/NavigatorVendedor";
    }
    
    @RequestMapping("/common/NavigatorContador.hyml")
    public String getNavigatorContador() {
   
        return "common/NavigatorContador.jspf";
    }
        
    @RequestMapping("/common/NavigatorAdmin.jspf")
    public String getNavigatorAdmin() {
   
        return "common/NavigatorAdmin";
    }
    
    // Error | "error.html"
    @RequestMapping("error.html")
    public ModelAndView error() {
		MV.setViewName("Error"); 
		return MV;
    }
}
