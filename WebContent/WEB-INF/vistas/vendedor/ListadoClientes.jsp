<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common/Header.jspf"></jsp:include>

</head>

<jsp:include page="../common/NavigatorVendedor.jspf"></jsp:include>
<div class="container-fluid">
	<form action="redireccionar_Home.html" method="get">
	<div class="cuadro_uno justify-content-center">
	  <div class="d-flex col col-12 mh-2 justify-content-center p-2">
	 	<div class="row w-auto justify-content-center">
			<h1>Listado de Clientes</h1>
		</div>
	  </div>

	  <div class="row mx-2 d-flex flex-wrap align-middle justify-content-evenly">
		  	<div class="col-md-auto table-responsive w-100">
		  		<table id="tabla_clientes" border=2 class="table align-middle table-info table-hover th-lg">
		  		<thead>
					<tr class="table-secondary">
						<th width="10%"> DNI </th>
						<th width="15%"> Nombre </th>
						<th width="15%"> Apellido </th>
						<th width="5%"> Sexo </th>
						<th width="5%"> Fecha de Nacimiento </th>
						<th width="10%"> Dirección </th>
						<th width="10%"> Localidad </th>
						<th width="10%"> Correo </th>
						<th width="10%"> Telefono </th>
						<th width="10%"> Seleccionar </th>
					</tr>
				</thead>
				<tbody>
					<%for (int x=0; x<20; x++){%>
						<tr <% if(x%3==0){%> class="table-danger" <%}else{ %> class="table-primary"<%} %> >
						<td> <%= (x+382676334) %></td>
						<td> <%= "N_Cliente_" + x %></td> 
						<td> <%= "A_Cliente_" + x %></td> 
						<td> <%= "X" %></td>
						<td> <%= x + "/" + x + "/" + x %></td>
						<td> <%= "Dir_Cliente_" + x%> </td>
						<td> <%= "Loc_Cliente_" + x%> </td>
						<td> <%= "Cor_Cliente_" + x%> </td>
						<td> <%= "Tel_Cliente_" + x%> </td>
						<td align="center"> <input type="radio" name="radSelect" value=""></td>
					<%}%>  					
				</tbody>
				</table>
		  	</div> 
	  	</div>
		<div class="row w-100 justify-content-center">
			<div class="d-flex col col-4 mh-2 w-25 justify-content-center p-2">
				<input type="submit" class="btn btn-primary w-50" value="Agregar" name="btnRedirigir">
			</div>
	 		<div class="d-flex col col-4 mh-2 w-25 justify-content-center p-2">
				<input type="submit" class="btn btn-primary w-50" value="Modificar" name="btnRedirigir2">
			</div>
			<div class="d-flex col col-4 mh-2 w-25 justify-content-center p-2">
				<input type="submit" class="btn btn-danger w-50" value="Eliminar" name="btnRedirigir3">
			</div>
		</div>
	</div>
	</form>
</div>
</body>
</html>