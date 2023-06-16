<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/Header.jspf" %>
<%@ include file="../common/NavigatorVendedor.jspf" %>
</head>

<body>
<div class="container-fluid">
	<div class="row align-items-md-stretch">
        <div class="col-md-3 ">
       		<%@ include file="../common/UserData.jspf" %>        
        </div>
        
        <div class="col-md-9 ">
           <div class="h-100 p-5 bg-light border rounded-3">
        	<form  id="home-vendedor.html" method="post">
        		<input type="submit" class="btn btn-primary" value="CLIENTES" name="btnRedirigir" formaction="clientes.html">
          		<input type="submit" class="btn btn-primary" value="ARTICULO" name="btnRedirigir" formaction="articulos.html">
        		<input type="submit" class="btn btn-primary" value="STOCK" name="btnRedirigir" formaction="stock.html">
        		<input type="submit" class="btn btn-primary" value="VENTAS" name="btnRedirigir" formaction="ventas.html">          	
          	</form>
        </div>
    </div>	
</div>

</body>
</html>