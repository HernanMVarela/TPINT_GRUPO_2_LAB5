<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common/Header.jspf"></jsp:include>
</head>

<body>
<jsp:include page="../common/NavigatorVendedor.jspf"></jsp:include>
<div class="container-fluid">
    <div class="cuadro_uno justify-content-center">
      <div class="d-flex col col-12 mh-2 justify-content-center p-2">
        <div class="row w-auto justify-content-center">
          <h1>Stock</h1>
        </div>
      </div>
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
							<th class="text-center" scope="col"> Cantidad </th>
							<th class="text-center" scope="col"> Ingreso de Mercaderia </th>  
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
								<%= (x+1) %>
							</td>
							<td> 
							<div class="p-2 bd-highlight align-self-center">
								<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="addStkModal">
		  						AGREGAR STOCK
								</button>
							</div>
							</td>
						<%}%>  					
					</tbody>
					</table>
			  	</div> 
		  	</div>
	
<!-- Modal AGREGAR Stock -->
<div class="modal fade" id="addStkModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">AGREGAR STOCK</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       <div class="row align-items-md-stretch mt-1">
           <div class="form-group col-md-9">
               <label style="float: left">Articulo</label>
               <select class="form-select">
		           <option value="A">Tipo A</option>
		           <option value="B">Tipo B</option>
		           <option value="C">Tipo C</option>
           </div>
           <div class="form-group col-md-3">
               <label style="float: left">Cantidad</label>
               <input class="form-control" type="integer" name="cantidad">
           </div>
       </div>
       <hr>
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