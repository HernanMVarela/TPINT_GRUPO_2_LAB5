<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common/Header.jspf"></jsp:include>

<script type="text/javascript">
$(document).ready( function () {
    $('#tabla_articulos').DataTable();
} );
</script>
</head>

<body>
<jsp:include page="../common/NavigatorVendedor.jspf"></jsp:include>
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
							<th ></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<%for (int x=0; x<5; x++){%>
							<tr>
							<td>
								<%= "Articulo " + (x+1) %>
							</td>
							<td> 
								<%= "Marca " + (x+10) %>
							</td> 
							<td> 
								<%= "Tipo " + x*3 %>
							</td> 
							<td> 
								<%= "$" + x*1000 %>
							</td>
							<td> 
								<%= "Este es el articulo " + (x+1) %>
							</td>						
							<td> 
								<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modifyItemModal">
							  		MODIFICAR
								</button>
							</td>
							<td> 
								<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteItemModal">
							  		ELIMINAR
								</button>
							</td>
						<%}%>  					
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
        <button type="button" class="btn btn-success">CONFIRMAR</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal MODIFICAR ARTICULO -->
<div class="modal fade" id="modifyItemModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">MODIFICAR ARTICULO</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-6">
               <label style="float: left">Nombre</label>
               <input class="form-control" placeholder="Ingrese nombre">
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Nombre</label>
               <input class="form-control" placeholder="Ingrese marca">
           </div>                     
       </div>
       
       <div class="row align-items-md-stretch mt-1">
           <div class="form-group col-md-12">
               <label style="float: left">Descripción</label>
               <textarea class="form-control" id="textAreaExample1" rows="4" placeholder="Ingrese descripción"></textarea>
           </div>           
       </div>
       
        <div class="row align-items-md-stretch mt-1">
     	   <div class="form-group col-md-6">
               <label style="float: left">Tipo</label>
               <input class="form-control" placeholder="Ingrese tipo">
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Precio de venta</label>
               <input type="number" class="form-control" placeholder="Ingrese precio de venta">
           </div>                   
       </div>     
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">CANCELAR</button>
        <button type="button" class="btn btn-success">CONFIRMAR</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal AGREGAR ARTICULO -->
<div class="modal fade" id="addItemModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">AGREGAR ARTICULO</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-6">
               <label style="float: left">Nombre</label>
               <input class="form-control" placeholder="Ingrese nombre">
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Nombre</label>
               <input class="form-control" placeholder="Ingrese marca">
           </div>                     
       </div>
       
       <div class="row align-items-md-stretch mt-1">
           <div class="form-group col-md-12">
               <label style="float: left">Descripción</label>
               <textarea class="form-control" id="textAreaExample1" rows="4" placeholder="Ingrese descripción"></textarea>
           </div>           
       </div>
       
        <div class="row align-items-md-stretch mt-1">
     	   <div class="form-group col-md-6">
               <label style="float: left">Tipo</label>
               <input class="form-control" placeholder="Ingrese tipo">
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Precio de venta</label>
               <input type="number" class="form-control" placeholder="Ingrese precio de venta">
           </div>                   
       </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">CANCELAR</button>
        <button type="button" class="btn btn-success">CONFIRMAR</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>