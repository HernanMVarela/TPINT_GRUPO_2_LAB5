<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/Header.jspf" %>
</head>

<body>
<%@ include file="../common/NavigatorVendedor.jspf" %>
<div class="container-fluid">
  <form  id="myForm" method="get">
    <div class="cuadro_uno justify-content-center">
      <div class="p-5 bg-light border rounded-3" style="width: 100%">
        <div class="row w-auto justify-content-center">
          <h1>PLANTILLA DE EMPLEADOS</h1>
          <hr>
              <table class="table table-hover text-center">
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
                      <tr>
                        <td class="align-middle">
                            Gonzalo
                        </td>
                        <td class="align-middle">
                            Sinnott Segura
                        </td>
                        <td class="align-middle">
                            gsinnott
                        </td>
                        <td class="align-middle">
                            VENDEDOR
                        </td>
                        <td class="align-middle">
							<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#disableUserModal">
							  INHABILITAR
							</button>  
	                    </td>                          
                      </tr>
                      <tr>
                        <td class="align-middle">
                            Leonardo
                        </td>
                        <td class="align-middle">
                            Fernandez
                        </td>
                        <td class="align-middle">
                            lfernandez
                        </td>
                        <td class="align-middle">
                            VENDEDOR
                        </td>
                        <td class="align-middle">
                        	<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#disableUserModal">
							  INHABILITAR
							</button>  
                        </td>                          
                      </tr>
                      <tr>
                        <td class="align-middle">
                            Hernan
                        </td>
                        <td class="align-middle">
                            Varela
                        </td>
                        <td class="align-middle">
                            hvarela
                        </td>
                        <td class="align-middle">
                            CONTADOR
                        </td>
                        <td class="align-middle">
                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#disableUserModal">
							  INHABILITAR
							</button>  
                        </td>                          
                      </tr>
                      <tr>
                        <td class="align-middle">
                            Santiago
                        </td>
                        <td class="align-middle">
                            Barzola
                        </td>
                        <td class="align-middle">
                            sbarzola
                        </td>
                        <td class="align-middle">
                            CONTADOR
                        </td>
                        <td class="align-middle">
                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#disableUserModal">
							  INHABILITAR
							</button>  
                        </td>                          
                      </tr>
                  </tbody>
              </table>
        </div>
      </div>      
</div>

<!-- Modal INHABILITAR USUARIO -->
<div class="modal fade" id="disableUserModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">INHABILITAR USUARIO</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ¿DESEA INHABILITAR EL USUARIO SELECCIONADO?
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