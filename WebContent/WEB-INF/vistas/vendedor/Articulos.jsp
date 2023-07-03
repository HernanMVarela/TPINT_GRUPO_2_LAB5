<%@page import="frgp.utn.edu.ar.dominio.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <html>

      <head>
        <%@ include file="../common/Header.jspf" %>
        <%@ include file="../common/NavigatorVendedor.jspf" %>

          <script type="text/javascript">
            $(document).ready(function () {
              $('#tabla_articulos').DataTable();
            });

            function abrirModificarModal(nombre, marca, descripcion, tipo, precio_venta) {

              $('#nombre').val(nombre);
              $('#marca').val(marca);
              $('#descripcion').val(descripcion);
              $('#tipo').val(tipo);
              $('#precio_venta').val(precio_venta);

            }

            function abrirEliminarModal(nombre, marca, descripcion, tipo, precio_venta) {

              $('#nombreEliminar').val(nombre);
              $('#marcaEliminar').val(marca);
              $('#descripcionEliminar').val(descripcion);
              $('#tipoEliminar').val(tipo);
              $('#precio_ventaEliminar').val(precio_venta);
            }

          </script>
      </head>

      <body>
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

            <form action="articulos.html" method="post">
              <div class="p-5 bg-light border rounded-3" style="width: 100%">
              <div class="row align-items-md-stretch">
		            <div class="d-flex justify-content-center" style="text-align: center;">
		                <h3 class="w-auto">${Mensaje}</h3>	
		            </div>
	           </div>
                <div class="d-flex  align-content-center bd-highlight mb-3">
	                
                  <div class="me-auto p-2 bd-highlight align-self-center">
                    <h1>ARTICULOS</h1>
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
                          <th></th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${listaArticulos}" var="item">
                          <c:if test="${item.estado eq true}">                        
                          <tr>
                            <td>${item.nombre} </td>
                            <td>${item.marca}</td>
                            <td>${item.tipo}</td>
                            <td>${item.precio_venta}</td>
                            <td>${item.descripcion}</td>
                            <td>
                              <button type="button" class="btn btn-success" data-bs-toggle="modal"
                                data-bs-target="#modifyItemModal" 
                                onclick="abrirModificarModal('${item.nombre}',
                                                             '${item.marca}',
                                                             '${item.descripcion}',
                                                             '${item.tipo}',
                                                             '${item.precio_venta}',
                                                             '${item.estado}')">
                                MODIFICAR
                              </button>
                            </td>
                            <td>
                              <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                                data-bs-target="#deleteItemModal" onclick="abrirEliminarModal('${item.nombre}',
                                                                                              '${item.marca}',
                                                                                              '${item.descripcion}',
                                                                                              '${item.tipo}',
                                                                                              '${item.precio_venta}',
                                                                                              '${item.estado}')">
                                ELIMINAR
                              </button>
                            </td>
                          </c:if>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </form>
           
             <% }}%>
          </div>

          <!-- Modal ELIMINAR ARTICULO -->
          <div class="modal fade" id="deleteItemModal" tabindex="-1" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog modal-lg">
              <div class="modal-content">
                <form action="eliminar_articulo.html" method="post">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">¿DESEA ELIMINAR EL SIGUIENTE ARTICULO?</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">                  
                  	 <div class="row d-flex align-items-center">
	                      <div class="col-md-4">
	                        <label>NOMBRE:</label>
	                      </div>
	                      <div class="col-md-8">
	                        <input id="nombreEliminar" name="nombreEliminar" class="form-control mt-1" readonly>
	                      </div>
	                      <div class="col-md-4">  
	                        <label>MARCA:</label>
	                      </div>
	                      <div class="col-md-8">		                      
	                          <input id="marcaEliminar" name="marcaEliminar" class="form-control mt-1" readonly>
	                      </div>
	                      <div class="col-md-4">
	                        <label>DESCRIPCION:</label>
	                      </div>
	                      <div class="col-md-8">
	                        <input id="descripcionEliminar" name="descripcionEliminar" class="form-control mt-1" id="textAreaExample1" readonly>
	                      </div>
	                      <div class="col-md-4">
	                        <label>TIPO:</label>
	                      </div>
	                      <div class="col-md-8">
   	                        <input id="tipoEliminar" name="tipoEliminar" class="form-control mt-1" id="textAreaExample1" readonly>
	                      </div>
	                      <div class="col-md-4">
	                        <label>PRECIO:</label>
	                      </div>
	                      <div class="col-md-8">                     
	                        <input id="precio_ventaEliminar" type="number" name="precio_ventaEliminar" class="form-control mt-1" readonly>
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

          <!-- Modal MODIFICAR ARTICULO -->
          <div class="modal fade" id="modifyItemModal" tabindex="-1" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
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
                        <input id="nombre" name="nombre" class="form-control" readonly>
                      </div>
                      <div class="form-group col-md-6">
                        <label style="float: left">Marca</label>
                        <select class="form-select" name="marca">
						    <c:forEach items="${listaMarcas}" var="Marca">
						        <option id="${Marca.ID}" value="${Marca.ID}">${Marca.nombre}</option>
						    </c:forEach>
						  </select>
                      </div>
                    </div>

                    <div class="row align-items-md-stretch mt-1">
                      <div class="form-group col-md-12">
                        <label style="float: left">Descripcion</label>
                        <textarea id="descripcion" name="descripcion" class="form-control" id="textAreaExample1"
                          rows="4" required></textarea>
                      </div>
                    </div>

                    <div class="row align-items-md-stretch mt-1">
                      <div class="form-group col-md-6">
                        <label style="float: left">Tipo</label>
                         <select class="form-select" name="tipo">
						    <c:forEach items="${listaTipoArticulos}" var="TipoArticulos">
						        <option id="${TipoArticulos.ID}" value="${TipoArticulos.ID}">${TipoArticulos.nombre}</option>
						    </c:forEach>
						</select>
                      </div>
                      <div class="form-group col-md-6">
                        <label style="float: left">Precio de venta</label>
                        <input id="precio_venta" type="number" name="precio_venta" class="form-control" required min="1">
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

          <!-- Modal AGREGAR ARTICULO -->
          <div class="modal fade" id="addItemModal" tabindex="-1" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog modal-lg">
              <div class="modal-content">
                <form action="alta_articulo.html" method="post">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">AGREGAR ARTICULO</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <div class="row align-items-md-stretch">
                      <div class="form-group col-md-6">
                        <label style="float: left">Nombre</label>
                        <input name="nombre" class="form-control" placeholder="Ingrese nombre" required>
                      </div>
                      <div class="form-group col-md-6">
                        <label style="float: left">Marca</label>
                        <select class="form-select" name="marca">
						    <c:forEach items="${listaMarcas}" var="Marca">
						        <option id="${Marca.ID}" value="${Marca.ID}">${Marca.nombre}</option>
						    </c:forEach>
						  </select>
                      </div>
                    </div>

                    <div class="row align-items-md-stretch mt-1">
                      <div class="form-group col-md-12">
                        <label style="float: left">Descripcion</label>
                        <textarea name="descripcion" class="form-control" id="textAreaExample1" rows="4"
                          placeholder="Ingrese descripcion" required></textarea>
                      </div>
                    </div>

                    <div class="row align-items-md-stretch mt-1">
                      <div class="form-group col-md-6">
                        <label style="float: left">Tipo</label>
                        <select class="form-select" name="tipo">
						    <c:forEach items="${listaTipoArticulos}" var="TipoArticulos">
						        <option id="${TipoArticulos.ID}" value="${TipoArticulos.ID}">${TipoArticulos.nombre}</option>
						    </c:forEach>
						</select>
                      </div>
                      <div class="form-group col-md-6">
                        <label style="float: left">Precio de venta</label>
                        <input type="number" name="precio_venta" class="form-control"
                          placeholder="Ingrese precio de compra" required min="1">
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