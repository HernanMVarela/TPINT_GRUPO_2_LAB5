<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<%@ include file="common/Header.jspf" %>
</head>

<body>
<%@ include file="common/NavigatorCommon.jspf" %>

<h2> Alta de Articulos </h2>

<form action="altaArticulo.html" method="post">
	<table>
	<tr > <td> Nombre:  </td> <td> <input name="nombre"/> </td></tr>
	<tr>  <td> Marca: </td> <td>  <input name="marca"/> </td></tr>
	<tr>  <td> Tipo: </td> <td>  <input name="tipo"/> </td></tr>
	<tr>  <td> Precio: </td> <td>  <input name="precio_compra"/> </td></tr>
	<tr>  <td> Descripcion: </td> <td>  <input name="descripcion"/> </td></tr>
	<tr>  <td> </td> <td> <input type="submit" name="btnAceptar" value="Aceptar"> </td></tr>
	</table>
</form>


${Mensaje}

<br/><br/><br/>

 <a href="recargaGrillaArticulos.html">Recargar Grillas</a>

<h2> Listado de Articulos</h2>


	<table border="2px" class="table align-middle table-info table-hover th-lg">
		<thead>
			<tr class="table-secondary">
				<th >Nombre</th>
				<th>Marca</th>
				<th>Tipo</th>
				<th>Precio_compra</th>
				<th>Descripcion</th>
				<th>Estado</th>
				<th>Borrar</th>
			</tr>
		</thead>
		
			<c:forEach items="${listaArticulos}" var="item">
				
				<tr class="table-primary">
			
				<td>${item.nombre} </td>
				<td>${item.marca.nombre}</td>
				<td>${item.tipo.nombre}</td>
				<td>${item.precio_compra}</td>
				<td>${item.descripcion}</td>
				<td>${item.estado}</td>
				<td><a href="<c:url value='/delete-articulos-${item.nombre}' />"  >delete</a></td>
				</tr>
				
			</c:forEach>
	
	</table>
</body>
</html>