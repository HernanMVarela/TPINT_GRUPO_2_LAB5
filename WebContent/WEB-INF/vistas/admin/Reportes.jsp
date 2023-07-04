<%@page import="frgp.utn.edu.ar.dominio.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../common/Header.jspf" %>
	<%@ include file="../common/NavigatorAdmin.jspf" %>		
</head>

<script type="text/javascript" >
	function actualizarEstadoBoton(form) {
		var calcularButton = form.querySelector('button[type="submit"]');

		var fechaInicioInput = form.querySelector('input[name="start"]');
		var fechaFinInput = form.querySelector('input[name="end"]');

		if (fechaInicioInput.value === "" || fechaFinInput.value === "") {
		  calcularButton.disabled = true;
		} else {
		  calcularButton.disabled = false;
		}
	  }
</script>

 <body>
<div class="container-fluid">
<% Usuario user = null; %>
			<% user =  (Usuario)request.getAttribute("userLogin"); %>			
			<% if(user == null) {%> 
        <div class="col-md-12" >
			<%@ include file="../common/ErrorLogin.jspf" %>
       </div> 
       <%} else { 
    	   if (user.getTipo().getNombre().equals("VENDEDOR") || user.getTipo().getNombre().equals("CONTADOR")){ %>
    		   <div class="col-md-12" >
	    		   <%@ include file="../common/ErrorPermisos.jspf" %>
	    		</div>
    	    <%} else {%>
   	    <div class="d-flex justify-content-start" style="text-align: center;">
               	<h3 class="w-auto"><% if (user != null){%> Usuario activo: <%= user.getNombreU() %> <% }else{%> NO HAY USUARIO LOGUEADO <%}  %></h3>	
            </div>
  <form  action="calcularReportes.html" method="post">
      <div class="p-5 bg-light border rounded-3" style="width: 100%">
        <div class="row w-auto justify-content-center">
          <h1>REPORTES</h1>
          <hr>
          <div class="row align-items-md-stretch">
	          <div class="col-md-4">
	          <br>
	          <br>
	          <h6>CANTIDAD DE USUARIOS ACTIVOS POR ROL</h6>
	          <hr>
	          <table id="tabla_roles" class="table table-hover text-center">
	                  <thead>
	                      <tr>                         
	                          <th scope="col">ROL</th>
	                          <th scope="col">CANTIDAD</th>                          
	                      </tr>
	                  </thead>
	                  <tbody>
	                      <c:forEach var="entry" items="${listaRoles}">
	                      <tr>
	                            <td>${entry.key}</td>
	                            <td>${entry.value} </td>
	                      </tr>
						  </c:forEach>
	                  </tbody>
	              </table>
	           	</div>
	           	
	          <div class="col-md-8">
	          
	          <div class="d-flex align-items-center">
	          			<div class="col-md-4">
	                  	<h6>TOP 5 PRODUCTOS MAS VENDIDOS</h6>
	                  	<h6>${desde} - ${hasta}</h6>
	                  	</div>
	                  	
	                  	<div style="width:200px;margin-left:20px;margin-right:20px;" >
			         		 <label style="float: left">Fecha de inicio</label>
		        		     <input onchange="actualizarEstadoBoton(this.form)" class="form-control" type="date" name="start">
						</div>
			          
						<div style="width:200px;">
							<label style="float: left">Fecha de fin</label>
							<input onchange="actualizarEstadoBoton(this.form)" class="form-control" type="date" name="end">
						</div>
						
	                    <button disabled="true" type="submit" class="btn btn-primary m-2">
	                      CALCULAR
	                    </button>
                    </div> 
	          <hr>   
	          <table id="tabla_productos_ventas" class="table table-hover text-center">
	                  <thead>
	                      <tr>                         
	                          <th scope="col">PRODUCTO</th>
	                          <th scope="col">CANTIDAD</th>                          
	                      </tr>
	                  </thead>
	                  <tbody>
	                      <c:forEach var="entry" items="${listaProductos}">
	                      <tr>
	                            <td>${entry.key}</td>
	                            <td>${entry.value} </td>
	                      </tr>
						  </c:forEach>
	                  </tbody>
	              </table>
	           	</div>
      		</div>              
        </div>
      </div>
      </form>   
<% }}%>  
</div>  

</body>
</html>