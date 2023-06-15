<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<%@ include file="../common/Header.jspf" %>

<script type="text/javascript">
$(document).ready( function () {
    $('#tabla_articulos').DataTable();
} );

function abrirModificarModal(nombre, marca, descripcion, tipo, precio_compra) {

		$('#nombre').val(nombre);
	    $('#marca').val(marca);
	    $('#descripcion').val(descripcion);
	    $('#tipo').val(tipo);
	    $('#precio_compra').val(precio_compra);
   
}

</script>
</head>

<body>
<%@ include file="../common/NavigatorVendedor.jspf" %>
<div class="container-fluid">
	<form action="articulos.html" method="post">
	<div class="cuadro_uno justify-content-center">
      <div class="p-5 bg-light border rounded-3" style="width: 100%">
		<div class="d-flex  align-content-center bd-highlight mb-3">
			<div class="me-auto p-2 bd-highlight align-self-center">
				<h1 >ARTICULOS</h1>
			</div>
			<div class="p-2 bd-highlight align-self-center">
				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addItemModal">
		  			AGREGAR ARTICULO
				</button>
			</div>		
		</div>
	
		  <div class="row mx-2 d-flex flex-wrap align-middle justify-content-evenly">
			  	<div class="col-md-auto table-responsive w-100">
			  		<table id="tabla_articulos" class="table table-hover text-center">
			  		<thead>
						<tr>
							<th class="text-center" scope="col"> Articulo </th>
							<th class="text-center" scope="col"> Marca </th>
							<th class="text-center" scope="col"> Tipo </th> 
							<th class="text-center" scope="col"> Precio de venta </th> 
							<th class="text-center" scope="col"> Descripcion </th> 
							<th class="text-center" scope="col"> Estado </th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaArticulos}" var="item">
							<tr>
								<td>${item.nombre} </td>
								<td>${item.marca.nombre}</td>
								<td>${item.tipo.nombre}</td>
								<td>${item.precio_compra}</td>
								<td>${item.descripcion}</td>
								<td>${item.estado}</td>
							<td> 
								<button type="button" class="btn btn-success" data-bs-toggle="modal" 
									data-bs-target="#modifyItemModal" 
									onclick="abrirModificarModal(
									'${item.nombre}',
									'${item.marca.nombre}',
									'${item.descripcion}',
									'${item.tipo.nombre}',
									'${item.precio_compra}',
									'${item.estado}'									
									)" >
							  		MODIFICAR
								</button>
							</td>
							<td> 
								<button type="button" class="btn btn-danger" data-bs-toggle="modal"
									 data-bs-target="#deleteItemModal" value='/delete-articulos-${item.nombre}'>
							  		ELIMINAR
								</button>
							</td>
						</c:forEach>			
					</tbody>
					</table>
			  	</div> 
		  	</div>
	  	</div>
  	</div>		
	</form>
</div>

<!-- Modal ELIMINAR ARTICULO -->
<div class="modal fade" id="deleteItemModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">ELIMINAR ARTICULO</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ¿DESEA ELIMINAR EL ARTICULO SELECCIONADO?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">CANCELAR</button>
        <a  class="btn btn-success" href="<c:url value='/delete-articulos-${item.nombre}' />"  >CONFIRMAR</a>
      </div>
    </div>
  </div>
</div>

<!-- Modal MODIFICAR ARTICULO -->
<div class="modal fade" id="modifyItemModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    <form action="modificar_articulo.html" method="post">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">MODIFICAR ARTICULO</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-6">
               <label style="float: left">Nombre</label>
               <input id="nombre" name="nombre" class="form-control" placeholder="Ingrese nombre">
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Marca</label>
               <input id="marca" name="marca" class="form-control" placeholder="Ingrese marca">
           </div>                     
       </div>
       
       <div class="row align-items-md-stretch mt-1">
           <div class="form-group col-md-12">
               <label style="float: left">Descripción</label>
               <textarea id="descripcion" name="descripcion" class="form-control" id="textAreaExample1" rows="4" placeholder="Ingrese descripción"></textarea>
           </div>           
       </div>
       
        <div class="row align-items-md-stretch mt-1">
     	   <div class="form-group col-md-6">
               <label style="float: left">Tipo</label>
               <input id="tipo" name="tipo" class="form-control" placeholder="Ingrese tipo">
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Precio de compra</label>
               <input id="precio_compra" type="number" name="precio_compra" class="form-control" placeholder="Ingrese precio de compra">
           </div>                   
       </div>     
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">CANCELAR</button>
        <input type="submit"  class="btn btn-primary" name="btnAceptar" value="Aceptar">
      </div>
    </form>
    </div>
  </div>
</div>

<!-- Modal AGREGAR ARTICULO -->
<div class="modal fade" id="addItemModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    <form action="altaArticulo.html" method="post">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">AGREGAR ARTICULO</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
        <div class="modal-body">
       <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-6">
               <label style="float: left">Nombre</label>
               <input name="nombre" class="form-control" placeholder="Ingrese nombre">
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Marca</label>
               <input name="marca" class="form-control" placeholder="Ingrese marca">
           </div>                     
       </div>
       
       <div class="row align-items-md-stretch mt-1">
           <div class="form-group col-md-12">
               <label style="float: left">Descripción</label>
               <textarea name="descripcion" class="form-control" id="textAreaExample1" rows="4" placeholder="Ingrese descripción"></textarea>
           </div>           
       </div>
       
        <div class="row align-items-md-stretch mt-1">
     	   <div class="form-group col-md-6">
               <label style="float: left">Tipo</label>
               <input name="tipo" class="form-control" placeholder="Ingrese tipo">
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Precio de compra</label>
               <input type="number" name="precio_compra" class="form-control" placeholder="Ingrese precio de compra">
           </div>                   
       </div>     
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">CANCELAR</button>
        <input type="submit"  class="btn btn-primary" name="btnAceptar" value="Aceptar">
      </div>
      </form>
    </div>
  </div>
</div>

</body>
</html>