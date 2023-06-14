<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/Header.jspf" %>
</head>

<body>
<%@ include file="../common/NavigatorVendedor.jspf" %>
<div class="container-fluid">
  
    <div class="cuadro_uno justify-content-center">
      <div class="d-flex col col-12 mh-2 justify-content-center p-2">
        <div class="row w-auto justify-content-center">
          <h1>Portal Vendedor</h1>
        </div>
      </div>
      
      <div class="d-flex col col-12 mh-2 justify-content-center p-2">
        <div class="row w-25 justify-content-center">
        	<form  id="articulos.html" method="get">
          		<input type="submit" class="btn btn-primary" value="Listado Articulos" name="btnRedirigir" formaction="articulos.html">
          	</form>
        </div>
      </div>
      <div class="d-flex col col-12 mh-2 justify-content-center p-2">
        <div class="row w-25 justify-content-center">
        	<form  id="listado_clientes.html" method="get">
 				<input type="submit" class="btn btn-primary" value="Listado Clientes" name="btnRedirigir" formaction="listado_clientes.html">
 			</form>
        </div>
      </div>      
	</div>
	
</div>

</body>
</html>