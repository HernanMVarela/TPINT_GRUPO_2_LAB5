<%@page import="frgp.utn.edu.ar.dominio.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
    $('#tabla_clientes').DataTable();
  });
function abrirModificarModal(id,dni, nombre, apellido, direccion, sexo, localidad, fecha, email, telefono) {
console.log(id)
   $('#IDModif').val(id);
   $('#DNIModif').val(dni);
   $('#nombreModif').val(nombre);
   $('#apellidoModif').val(apellido);
   $('#direccionModif').val(direccion);
   $('#sexoModif').val(sexo);
   $('#localidadModif').val(localidad);
   $('#fechaModif').val(fecha);
   $('#correoModif').val(email);
   $('#telefonoModif').val(telefono);

  }

  function abrirEliminarModal(id, dni, nombre, apellido, email, telefono) {

    $('#idEliminar').val(id);
    $('#dniEliminar').val(dni);
    $('#nombreEliminar').val(nombre);
    $('#apellidoEliminar').val(apellido);
    $('#correoEliminar').val(email);
    $('#telefonoEliminar').val(telefono);
  }

</script>
<%@ include file="../common/Header.jspf" %>
</head>    
<body>
<%@ include file="../common/NavigatorVendedor.jspf" %>

<div class="container-fluid">
           <% Usuario user = null; %>
 			<% user =  (Usuario)request.getAttribute("userLogin"); %>
 			
 			<% if(user == null) {%> 
	        <div class="col-md-12" >
				<%@ include file="../common/ErrorLogin.jspf" %>
	       </div> 
	       <%} else { 
	    	   if (user.getTipo().getNombre().equals("CONTADOR")){ %>
	    	    <div class="col-md-12" >
	    		   <%@ include file="../common/ErrorPermisos.jspf" %>
	    		</div>
	    	    <%} else {%>
	<div class="d-flex justify-content-start" style="text-align: center;">
       	<h3 class="w-auto"><% if (user != null){%> Usuario activo: <%= user.getNombreU() %> <% }else{%> NO HAY USUARIO LOGUEADO <%}  %></h3>	
    </div>  
	<div class="p-5 bg-light text-dark border rounded-3" style="width: 100%">
		<div class="row align-items-md-stretch">
         <div class="d-flex justify-content-center" style="text-align: center;">
             <h3 class="w-auto">${Mensaje}</h3>	
         </div>
        </div>	
		<form action="clientes.html" method="post">
			<div class="d-flex align-content-center bd-highlight mb-3">
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
                          <th class="text-center" scope="col"> Direccion </th>
                          <th class="text-center" scope="col"> Sexo </th>
                          <th class="text-center" scope="col"> Localidad </th>
                          <th class="text-center" scope="col"> Fecha de Nacimiento </th>
                          <th class="text-center" scope="col"> Email </th>
                          <th class="text-center" scope="col"> Telefono </th>  
                          <th></th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${listaClientes}" var="item">
                          <c:if test="${item.estado.ID == 1}">                        
                          <tr>
                            <td>${item.DNI} </td>
                            <td>${item.nombre} ${item.apellido}</td>
                            <td>${item.direccion}</td>
                            <td>${item.sexo}</td>
                            <td>${item.localidad.getNombre()}</td>
                            <td>${item.fecha_nac}</td>
                            <td>${item.correo}</td>
                            <td>${item.telefono}</td>
                            <td>
                              <button type="button" class="btn btn-success" data-bs-toggle="modal"
                                data-bs-target="#modifyClientModal" 
                                onclick="abrirModificarModal('${item.ID}',
               								                 '${item.DNI}',
                                                             '${item.nombre}',
                                                             '${item.apellido}',
                                                             '${item.direccion}',
                                                             '${item.sexo}',
                                                             '${item.localidad}',
                                                             '${item.fecha_nac}',
                                                             '${item.correo}',
                                                             '${item.telefono}')">
                                MODIFICAR
                              </button>
                            </td>
                            <td>
                              <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                                data-bs-target="#deleteClientModal" onclick="abrirEliminarModal('${item.ID}',
                               																	'${item.DNI}',
                                                                                              	'${item.nombre}',
                                                                                              	'${item.apellido}',
                                                                                              	'${item.correo}',
                                                                                              	'${item.telefono}')">
                                ELIMINAR
                              </button>
                            </td>
                          </c:if>
                        </c:forEach>
                      </tbody>
                    </table>
			  	</div> 
		  	</div>		
		</form>	
	</div>		
    <% }} %>	
</div>

<!-- Modal ELIMINAR CLIENTE -->
<div class="modal fade" id="deleteClientModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    <form action="eliminar_cliente.html" method="post">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">¿DESEA ELIMINAR EL SIGUIENTE CLIENTE?</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="row d-flex align-items-center">
    	 	  <div class="col-md-4">
            <label>ID:</label>
          </div>
          <div class="col-md-8">
            <input id="idEliminar" name="idEliminar" class="form-control mt-1" readonly>
          </div>
          <div class="col-md-4">
            <label>NOMBRE:</label>
          </div>
          <div class="col-md-8">
            <input id="nombreEliminar" name="nombreEliminar" class="form-control mt-1" readonly>
          </div>
          <div class="col-md-4">  
            <label>APELLIDO:</label>
          </div>
          <div class="col-md-8">		                      
              <input id="apellidoEliminar" name="apellidoEliminar" class="form-control mt-1" readonly>
          </div>
          <div class="col-md-4">
            <label>DNI:</label>
          </div>
          <div class="col-md-8">
            <input id="dniEliminar" name="dniEliminar" class="form-control mt-1" id="textAreaExample1" readonly>
          </div>
          <div class="col-md-4">
            <label>CORREO:</label>
          </div>
          <div class="col-md-8">
               <input id="correoEliminar" name="correoEliminar" class="form-control mt-1" id="textAreaExample1" readonly>
          </div>
          <div class="col-md-4">
            <label>TELEFONO:</label>
          </div>
          <div class="col-md-8">
               <input id="telefonoEliminar" name="telefonoEliminar" class="form-control mt-1" id="textAreaExample1" readonly>
          </div>	                                            
         </div>     
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">CANCELAR</button>
        <input type="submit" class="btn btn-success" name="btnAceptar" value="CONFIRMAR">
      </div>
     </form>
    </div>
  </div>
</div>

<!-- Modal MODIFICAR CLIENTE -->
<div class="modal fade" id="modifyClientModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    <form action="modificar_cliente.html" method="post">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">MODIFICAR CLIENTE</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       <div class="row align-items-md-stretch">
        <div class="form-group col-md-4">
          <label style="float: left">ID</label>
          <input readonly  id="IDModif" name="IDModif" class="form-control"  required>
      </div>
     	   <div class="form-group col-md-4">
               <label style="float: left">Apellido</label>
               <input  id="apellidoModif" name="apellidoModif"  class="form-control" placeholder="Ingrese apellido" readonly required pattern="[A-Za-z]{20}"/>
           </div>
           <div class="form-group col-md-4">
               <label style="float: left">Nombres</label>
               <input id="nombreModif" name="nombreModif" class="form-control" placeholder="Ingrese nombre" readonly required pattern="[A-Za-z]{20}"/>
           </div>
           <div class="form-group col-md-4">
               <label style="float: left">Sexo</label>
		       <select id="sexoModif" name="sexoModif" class="form-select">
		           <option value="M">MASCULINO</option>
		           <option value="F">FEMENINO</option>
		           <option value="N/C">OTRO</option>
		       </select>
           </div>           
       </div>
       
       <div class="row align-items-md-stretch mt-1">
           <div class="form-group col-md-9">
               <label  style="float: left">DNI</label>     
               <input id="DNIModif" name="DNIModif" class="form-control" type="number" placeholder="Ingrese DNI" readonly required min="10000000" max="99999999" >
           </div>
           <div class="form-group col-md-3">
               <label style="float: left">Fecha de Nacimiento</label>
               <input id="fechaModif" name="fechaModif" class="form-control" type="date" name="date" readonly required>
           </div>
       </div>

       <hr>
       
        <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-6">
               <label style="float: left">Direccion</label>
               <input  id="direccionModif" name="direccionModif" class="form-control" placeholder="Ingrese direccion" required>
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Localidad</label>
               <input id="localidadModif" name="localidadModif" class="form-control" placeholder="Ingrese localidad" required>
           </div>      
           
           
           
       </div>
       
       <hr>
       
        <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-6">
               <label style="float: left">Correo Electronico</label>
               <input id="correoModif" name="correoModif" class="form-control" type="email" placeholder="Ingrese correo electronico" required/>
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Numero de Telefono</label>
               <input id="telefonoModif" name="telefonoModif" class="form-control" type="number" placeholder="Ingrese numero de telefono" required/>
           </div>                   
       </div>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">CANCELAR</button>
		<input type="submit" class="btn btn-primary" name="btnAceptar" value="Aceptar">      
	  </div>
    </form>
    </div>
  </div>
</div>

<!-- Modal AGREGAR CLIENTE -->
<div class="modal fade" id="addClientModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    <form action="alta_cliente.html" method="post">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">AGREGAR CLIENTE</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-4">
               <label style="float: left">Apellido</label>
               <input name="apeNuevo" class="form-control" placeholder="Ingrese apellido" required pattern="[A-Za-z]{20}"/>
           </div>
           <div class="form-group col-md-4">
               <label style="float: left">Nombre</label>
               <input name="nomNuevo" class="form-control" placeholder="Ingrese nombre" required pattern="[A-Za-z]{20}"/>
           </div>
           <div class="form-group col-md-4">
               <label style="float: left">Sexo</label>
		       <select name="sexoNuevo" class="form-select">
		           <option value="M">MASCULINO</option>
		           <option value="F">FEMENINO</option>
		           <option value="N/C">OTRO</option>
		       </select>
           </div>           
       </div>
       
       <div class="row align-items-md-stretch mt-1">
           <div class="form-group col-md-4">
               <label style="float: left">DNI</label>
               <input name="DNINuevo" class="form-control" type="number" placeholder="Ingrese DNI" required min="10000000" max="99999999" >
           </div>
           <div class="form-group col-md-3">
               <label style="float: left">Fecha de Nacimiento</label>
               <input name="fechaNuevo" class="form-control" type="date" name="date" required>
           </div>
           <div class="form-group col-md-5">
               <label style="float: left">Direccion</label>
               <input name="direcNuevo" class="form-control" placeholder="Ingrese direccion" required>
           </div>
       </div>

       <hr>
       
        <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-6">
               <label style="float: left">Provincia</label>
               <select class="form-select" id="provinciaNuevo" name="provinciaNuevo" onchange="actualizarLocalidades()">
			   	<c:forEach items="${listaProvincias}" var="Provincia">
			       <option id="${Provincia.ID}" value="${Provincia.ID}">${Provincia.nombre}</option>
			   	</c:forEach>
			   </select>
           </div>
           <script type="text/javascript"> var provinciaSelect = document.getElementById('provinciaNuevo');</script>
           <div class="form-group col-md-6">
               <label style="float: left">Localidad</label>
               <select class="form-select" id="localidadNuevo" name="localidadNuevo">
			   	<c:forEach items="${listaLocalidades}" var="localidad">
			        <c:if test="${localidad.getProvincia().getID() == listaProvincias[0].ID}">
			       		<option id="${localidad.ID}" value="${localidad.ID}">${localidad.nombre}</option>
					</c:if>
				</c:forEach>
			   </select>
           </div>                
       </div>
<script type="text/javascript">
    function actualizarLocalidades() {
        // Obtiene la Informaci�n
        var provinciaSelect = document.getElementById('provinciaNuevo');
        var localidadSelect = document.getElementById('localidadNuevo');
        var provinciaId = provinciaSelect.value;
        // Borra Todo
        localidadSelect.innerHTML = '';
        // Revisa Todo
        <c:forEach items="${listaLocalidades}" var="Localidad">
            if (${Localidad.getProvincia().getID()} == provinciaId) {
                var option = document.createElement('option');
                option.id = "${Localidad.ID}";
                option.value = "${Localidad.ID}";
                option.text = "${Localidad.nombre}";
             	// Los a�ade si coinciden
                localidadSelect.appendChild(option);
            }
        </c:forEach>
    }
</script>
       <hr>
       
        <div class="row align-items-md-stretch">
     	   <div class="form-group col-md-6">
               <label style="float: left">Correo Electronico</label>
               <input name="corNuevo" class="form-control" type="email" placeholder="Ingrese correo electronico" required>
           </div>
           <div class="form-group col-md-6">
               <label style="float: left">Numero de Telefono</label>
               <input name="telNuevo" class="form-control" type="number" placeholder="Ingrese numero de telefono" required>
           </div>                   
       </div>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">CANCELAR</button>
		<input type="submit" class="btn btn-primary" name="btnAceptar" value="Aceptar">      
	  </div>
    </form>
    </div>
  </div>
</div>
	
</body>
</html>