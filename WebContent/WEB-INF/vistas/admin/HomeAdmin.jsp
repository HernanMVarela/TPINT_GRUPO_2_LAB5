<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<%@ include file="../common/Header.jspf" %>
		<%@ include file="../common/NavigatorAdmin.jspf" %>
		
		<script type="text/javascript">
            $(document).ready(function () {
              $('#tabla_empleados').DataTable();
            });
            
            function abrirEliminarModal(id, nombre, apellido, nombreU, tipo) {

                $('#idEliminar').val(id);
                $('#nombreEliminar').val(nombre);
                $('#apellidoEliminar').val(apellido);
                $('#nombreUEliminar').val(nombreU);
                $('#tipoEliminar').val(tipo);
            }
            
        </script>
	</head>
<body>
<div class="container-fluid">
	<div class="row align-items-md-stretch">
        <div class="col-md-3 ">
        	<%@ include file="../common/UserData.jspf" %>       
        </div>
        
        <div class="col-md-9 text-center">
            <div class="h-100 p-5 bg-light border rounded-3">
				<h1>PLANTILLA DE EMPLEADOS</h1>
          		<hr>
              	<table id="tabla_empleados" class="table table-hover text-center">
                  <thead>
                      <tr>                         
                          <th scope="col">NOMBRE</th>
                          <th scope="col">APELLIDO</th>
                          <th scope="col">USUARIO</th>
                          <th scope="col">ROL</th>
                          <th scope="col">HABILITAR</th>
                      </tr>
                  </thead>
                  <tbody>
                      <c:forEach items="${listaUsuarios}" var="item">
                          <c:if test="${item.estado.ID == 1}">                        
                          <tr>
                            <td>${item.nombre} </td>
                            <td>${item.apellido} </td>
                            <td>${item.nombreU}</td>
                            <td>${item.tipo.getNombre()}</td>                            
                            <td>
                              <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                                data-bs-target="#deleteUserModal" onclick="abrirEliminarModal('${item.id}',
                                															  '${item.nombre}',
                                                                                              '${item.apellido}',
                                                                                              '${item.nombreU}',
                                                                                              '${item.tipo.getNombre()}')">
                                INHABILITAR
                              </button>
                            </td>
                          </c:if>
                        </c:forEach>
                  </tbody>
              </table>
          </div>
    </div>
	
</div>

</body>

<!-- Modal ELIMINAR ARTICULO -->
          <div class="modal fade" id="deleteUserModal" tabindex="-1" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog modal-lg">
              <div class="modal-content">
                <form action="eliminar_usuario.html" method="post">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">¿DESEA ELIMINAR EL SIGUIENTE USUARIO?</h5>
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
	                        <label>USUARIO:</label>
	                      </div>
	                      <div class="col-md-8">
	                        <input id="nombreUEliminar" name="nombreUEliminar" class="form-control mt-1" id="textAreaExample1" readonly>
	                      </div>
	                      <div class="col-md-4">
	                        <label>ROL:</label>
	                      </div>
	                      <div class="col-md-8">
   	                        <input id="tipoEliminar" name="tipoEliminar" class="form-control mt-1" id="textAreaExample1" readonly>
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

</html>