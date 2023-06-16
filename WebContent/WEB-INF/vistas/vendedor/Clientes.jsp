<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/Header.jspf" %>
<%@ include file="../common/NavigatorVendedor.jspf" %>

<script type="text/javascript">
$(document).ready( function () {
    $('#tabla_clientes').DataTable();
} );
</script>
</head>    
<body>

<div class="container-fluid">
	<div class="p-5 bg-light text-dark border rounded-3" style="width: 100%">
		<form action="clientes.html" method="post">
			<div class="d-flex  align-content-center bd-highlight mb-3">
				<div class="me-auto p-2 bd-highlight align-self-center">
					<h1 >CLIENTES</h1>
				</div>
				<div class="p-2 bd-highlight align-self-center">
					<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addClientModal">
			  			AGREGAR CLIENTE
					</button>
				</div>		
			</div>
	
		  <div class="row mx-2 d-flex flex-wrap align-middle justify-content-evenly">
			  	<div class="col-md-auto table-responsive w-100">
			  		<table id="tabla_clientes" class="table table-hover text-center">
			  		<thead>
						<tr>
							<th class="text-center" scope="col"> DNI </th>
							<th class="text-center" scope="col"> Nombre </th>
							<th class="text-center" scope="col"> Apellido </th>
							<th class="text-center" scope="col"> Sexo </th>
							<th class="text-center" scope="col"> Fecha de Nacimiento </th>
							<th class="text-center" scope="col"> Dirección </th>
							<th class="text-center" scope="col"> Localidad </th>
							<th class="text-center" scope="col"> Correo </th>
							<th class="text-center" scope="col"> Telefono </th>
							<th ></th>
							<th></th>						
						</tr>
					</thead>
					<tbody>
						<%for (int x=0; x<5; x++){%>
							<tr>
								<td>
									<%= (x+382676334) %>
								</td>
								<td> 
									<%= "N_Cliente_" + x %>
								</td> 
								<td>
									<%= "A_Cliente_" + x %>
								</td> 
								<td> 
									<%= "X" %>
								</td>
								<td> 
									<%= x + "/" + x + "/" + x %>
								</td>
								<td> 
									<%= "Dir_Cliente_" + x%> 
								</td>
								<td> 
									<%= "Loc_Cliente_" + x%> 
								</td>
								<td> 
									<%= "Cor_Cliente_" + x%> 
								</td>
								<td> 
									<%= "Tel_Cliente_" + x%> 
								</td>
								<td> 
									<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modifyClientModal">
								  		MODIFICAR
									</button>
								</td>
								<td> 
									<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteClientModal">
								  		ELIMINAR
									</button>
								</td>
							</tr>
						<%}%>  					
					</tbody>
					</table>
			  	</div> 
		  	</div>		
		</form>
	</div>	
</div>

<!-- Modal ELIMINAR CLIENTE -->
<div class="modal fade" id="deleteClientModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">ELIMINAR CLIENTE</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ¿DESEA ELIMINAR EL CLIENTE SELECCIONADO?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">CANCELAR</button>
        <button type="button" class="btn btn-success">CONFIRMAR</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal MODIFICAR CLIENTE -->
<div class="modal fade" id="modifyClientModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">MODIFICAR CLIENTE</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-4">
               <label style="float: left">Apellido</label>
               <input class="form-control" placeholder="Ingrese apellido">
           </div>
           <div class="form-group col-md-4">
               <label style="float: left">Nombre</label>
               <input class="form-control" placeholder="Ingrese nombre">
           </div>
           <div class="form-group col-md-4">
               <label style="float: left">Sexo</label>
		       <select class="form-select">
		           <option value="administrador">MASCULINO</option>
		           <option value="vendedor">FEMENINO</option>
		           <option value="contador">OTRO</option>
		       </select>
           </div>           
       </div>
       
       <div class="row align-items-md-stretch mt-1">
           <div class="form-group col-md-9">
               <label style="float: left">DNI</label>
               <input class="form-control" type="number" placeholder="Ingrese DNI">
           </div>
           <div class="form-group col-md-3">
               <label style="float: left">Fecha de Nacimiento</label>
               <input class="form-control" type="date" name="date">
           </div>
       </div>

       <hr>
       
        <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-6">
               <label style="float: left">Direccion</label>
               <input class="form-control" placeholder="Ingrese direccion">
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Localidad</label>
               <input class="form-control" placeholder="Ingrese localidad">
           </div>                   
       </div>
       
       <hr>
       
        <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-6">
               <label style="float: left">Correo Electronico</label>
               <input class="form-control" placeholder="Ingrese correo electronico">
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Numero de Telefono</label>
               <input class="form-control" type="number" placeholder="Ingrese numero de telefono">
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

<!-- Modal AGREGAR CLIENTE -->
<div class="modal fade" id="addClientModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">AGREGAR CLIENTE</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-4">
               <label style="float: left">Apellido</label>
               <input class="form-control" placeholder="Ingrese apellido">
           </div>
           <div class="form-group col-md-4">
               <label style="float: left">Nombre</label>
               <input class="form-control" placeholder="Ingrese nombre">
           </div>
           <div class="form-group col-md-4">
               <label style="float: left">Sexo</label>
		       <select class="form-select">
		           <option value="administrador">MASCULINO</option>
		           <option value="vendedor">FEMENINO</option>
		           <option value="contador">OTRO</option>
		       </select>
           </div>           
       </div>
       
       <div class="row align-items-md-stretch mt-1">
           <div class="form-group col-md-9">
               <label style="float: left">DNI</label>
               <input class="form-control" type="number" placeholder="Ingrese DNI">
           </div>
           <div class="form-group col-md-3">
               <label style="float: left">Fecha de Nacimiento</label>
               <input class="form-control" type="date" name="date">
           </div>
       </div>

       <hr>
       
        <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-6">
               <label style="float: left">Direccion</label>
               <input class="form-control" placeholder="Ingrese direccion">
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Localidad</label>
               <input class="form-control" placeholder="Ingrese localidad">
           </div>                   
       </div>
       
       <hr>
       
        <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-6">
               <label style="float: left">Correo Electronico</label>
               <input class="form-control" placeholder="Ingrese correo electronico">
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Numero de Telefono</label>
               <input class="form-control" type="number" placeholder="Ingrese numero de telefono">
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