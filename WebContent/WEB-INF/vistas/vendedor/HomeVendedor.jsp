<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/Header.jspf" %>
<%@ include file="../common/NavigatorVendedor.jspf" %>
</head>

<body>
<div class="container-fluid">
		<% Usuario user = null; %>
		<% user =  (Usuario)request.getAttribute("userLogin"); %>

		<% if(user == null) {%>
		<div class="col-md-12">
			<%@ include file="../common/ErrorLogin.jspf"%>
		</div>
		<%} else { 
  	   if (user.getTipo().getNombre().equals("CONTADOR")){ %>
		     <div class="col-md-12" >
	    		   <%@ include file="../common/ErrorPermisos.jspf" %>
	    		</div>
		<%} else {%>
		<div class="row align-items-md-stretch">
        <div class="col-md-3 ">
       		<%@ include file="../common/UserData.jspf" %>        
        </div>
        
        <div class="col-md-9 ">
           <div class="h-100 p-5 bg-light border rounded-3">
        	<form  id="home-vendedor.html" method="post">
        		<div class="d-flex justify-content-center">
        			<div class="w-25 mx-2">
        				<input type="submit" class="btn btn-primary w-100" value="CLIENTES" name="btnRedirigir" formaction="clientes.html">
        			</div>
        			<div class="w-25 mx-2">
        				<input type="submit" class="btn btn-primary w-100" value="ARTICULO" name="btnRedirigir" formaction="articulos.html">
        			</div>
        			<div class="w-25 mx-2">
        				<input type="submit" class="btn btn-primary w-100" value="STOCK" name="btnRedirigir" formaction="stock.html">
        			</div>
        			<div class="w-25 mx-2">
        				<input type="submit" class="btn btn-primary w-100" value="VENTAS" name="btnRedirigir" formaction="ventas.html">
        			</div> 
        		</div>        	
          	</form>
          </div>
        </div>
    </div>
    <% }}%>	
</div>

</body>
</html>