<%@page import="frgp.utn.edu.ar.dominio.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/Header.jspf"%>
<%@ include file="../common/NavigatorContador.jspf"%>

<script type="text/javascript">
            $(document).ready(function () {
              $('#tabla_ventas').DataTable();
            });
</script>

</head>

<body>
 <div class="container-fluid">
 <% Usuario user = null; %>
 			<% user =  (Usuario)request.getAttribute("userLogin"); %>
 			
 			<% if(user == null) {%> 
	        <div class="col-md-9" >
				<%@ include file="../common/ErrorLogin.jspf" %>
	       </div> 
	       <%} else { 
	    	   if (user.getTipo().getNombre().equals("VENDEDOR")){ %>
	    		   <%@ include file="../common/ErrorPermisos.jspf" %>
	    	    <%} else {%>
 <div class="row align-items-md-stretch">
        <div class="col-md-3 ">
       		<%@ include file="../common/UserData.jspf" %>        
        </div>

       <div class="col-md-9">
        	<form action="HomeContador.html" method="post">
              <div class="p-5 bg-light border rounded-3" style="width: 100%">
                <div class="d-flex  align-content-center bd-highlight mb-3">
                  <div class="me-auto p-2 bd-highlight align-self-center">
                    <h1>VENTAS</h1>
                  </div>
                  <div class="p-2 bd-highlight align-self-center">
                  	<div class="d-flex align-items-center">
	                  	
	                  	<div style="width:200px;padding-right:20px;" >
			         		 <label style="float: left">Fecha de inicio</label>
		        		     <input class="form-control" type="date" name="datei">
						</div>
			          
						<div style="width:200px;">
							<label style="float: left">Fecha de fin</label>
							<input class="form-control" type="date" name="datef">
						</div>
						
	                    <button type="submit" class="btn btn-primary m-2">
	                      CALCULAR
	                    </button>
                    </div> 
                    <c:if test="${empty gananciaTotal}">
  						<c:set var="gananciaTotal" value="0" />
					</c:if>
                    <h4 class="m-1">GANANCIAS DEL PERIODO: $${gananciaTotal}</h4>               
                  </div>
                </div>

                <div class="row mx-2 d-flex flex-wrap align-middle justify-content-evenly">
                  <div class="col-md-auto table-responsive w-100">
                    <table id="tabla_ventas" class="table table-hover text-center">
                      <thead>
                        <tr>
                         <th class="text-center" scope="col"> Fecha </th>
						    <th class="text-center" scope="col"> N� venta </th>
							<th class="text-center" scope="col"> ID Cliente </th>
							<th class="text-center" scope="col"> Ganancia </th>
                        </tr>
                      </thead>
                      <tbody>
                        <%for (int x=0; x<5; x++){%>
							<tr>
								<td>
									<%="13/06/2023" %>
								</td>
								<td> 
									<%= "40839274" + x %>
								</td> 
								<td>
									<%= "2" + x %>
								</td> 
								<td>
									<%= "$200" + x %>
								</td>							
							</tr>
						<%}%>  	
                      </tbody>
                    </table>
                    
                  </div>
                </div>
              </div>
            </form>
    </div>
   </div>
    <% }}%>
 </div>
            
</body>
</html>