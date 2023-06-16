<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/Header.jspf" %>
</head>

<body>
<%@ include file="../common/NavigatorVendedor.jspf" %>

<div class="container-fluid">
  <div class="p-5 bg-light text-dark border rounded-3" style="width: 100%">
  <form  id="myForm" method="get">
       
            <div class="d-flex  align-content-center bd-highlight mb-3">
              <div class="me-auto p-2 bd-highlight align-self-center">
                <h1 >Gestion de Ventas</h1>
              </div>
              <div class="p-2 bd-highlight align-self-center">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addClientModal">
                  AGREGAR VENTA
                </button>
              </div>		
            </div>
          
          <table id="tabla_clientes" class="table table-hover text-center">
			  		<thead>
						<tr>
						    <th class="text-center" scope="col"> Fecha </th>
						    <th class="text-center" scope="col"> N venta </th>
							<th class="text-center" scope="col"> ID Cliente </th>
							<th class="text-center" scope="col"> Total </th>
              <th class="text-center" scope="col">  </th>
							
							<th ></th>
							<th></th>						
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
                <td>
									<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteClientModal">
                    ELIMINAR
                  </button>
								</td>							
								
							</tr>
						<%}%>  					
					</tbody>
					</table> </div>
					
					
        </div>
        </div>
        </div>
        </form></div>
</div>
      
      
  <!-- Modal AGREGAR VENTA -->
<div class="modal fade" id="addClientModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
	  <div class="modal-content">
		<div class="modal-header">
		  <h5 class="modal-title" id="exampleModalLabel">AGREGAR VENTA</h5>
		  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		</div>
		<div class="modal-body">
		  <div class="row align-items-md-stretch">

      <div style="padding-bottom: 20px; display: flex;">
			  <div>
          <label style="float: left">Fecha</label>
          <input class="form-control" type="date" name="date">
         </div>  
         
			 <div style="float: left; padding-left: 20px;">
        <label style="float: left">Cliente</label><br/>
        <div style="display: flex; ">
				 <input style="width: 200px;" class="form-control" placeholder="Ingrese nombre">
         
         <button  type="button" class="btn btn-danger" data-bs-dismiss="modal">Buscar</button>
         <label style="float: left; padding-left: 20px;">Fulanito</label>
        </div>
			 </div>
      </div>

       
			 <div style="padding-bottom: 20px;" class="form-group col-md-6">
        <label style="float: left">Monto total</label>
        <input id="precio_compra" type="number" name="precio_compra" class="form-control">
       </div> 
        <hr/>

      <div style="padding-bottom: 20px ; display: flex;">
       <div class="form-group col-md-4">
         <label style="float: left">Articulo</label>
         <select class="form-select">
         <option value="administrador">Articulo1</option>
         <option value="administrador">Articulo2</option>
         <option value="administrador">Articulo3</option>
         </select>
        </div>   

        <div style="float: left; padding-left: 20px; width: 100px;" >
          <label style="float: left">Cantidad</label>
          <input id="" type="number" name="precio_compra" class="form-control">
        </div>

        <div style="float: left; padding-left: 20px;" >
          <label style="float: left">Cantidad</label>
          <input id="" type="number" name="precio_compra" class="form-control">
        </div>
        
      </div>
        <br/>   

      <button type="button" class="btn btn-success" data-bs-dismiss="modal">Agregar articulo</button>
           
		 </div>

		</div>
		<div class="modal-footer">
		  <button type="button" class="btn btn-danger" data-bs-dismiss="modal">CANCELAR</button>
		  <button type="button" class="btn btn-success">CONFIRMAR</button>
		</div>
	  </div>
	</div>
  </div>

<!-- Modal ELIMINAR CLIENTE -->
<div class="modal fade" id="deleteClientModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">ELIMINAR VENTA</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        DESEA ELIMINAR LA VENTA SELECCIONADO?
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