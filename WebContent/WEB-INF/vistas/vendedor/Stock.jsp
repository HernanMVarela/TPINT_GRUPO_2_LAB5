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
              $('#tabla_stock').DataTable();
            });
        </script>
      </head>
<body>
          <div class="container-fluid">
            <form action="stock.html" method="post">
              <div class="p-5 bg-light border rounded-3" style="width: 100%">
                <div class="d-flex  align-content-center bd-highlight mb-3">
                  <div class="me-auto p-2 bd-highlight align-self-center">
                    <h1>STOCK</h1>
                  </div>                  
                </div>

                <div class="row mx-2 d-flex flex-wrap align-middle justify-content-evenly">
                  <div class="col-md-auto table-responsive w-100">
                    <table id="tabla_stock" class="table table-hover text-center">
                      <thead>
                        <tr>
                          <th class="text-center" scope="col"> Articulo </th>
                          <th class="text-center" scope="col"> Marca </th>
                          <th class="text-center" scope="col"> Tipo </th>
                          <th class="text-center" scope="col"> Descripcion </th>
                          <th class="text-center" scope="col"> Cantidad </th>
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
                            <td>${item.descripcion}</td>
                            <td></td>
                            <td></td>
                          </c:if>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addStockModal">
                      				Nuevo Ingreso
                    			</button>
                </div>
              </div>
            </form>
          </div>

<!-- Modal AGREGAR Stock -->
<div class="modal fade" id="addStockModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
     <form action="ingreso_stock.html" method="post">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Agregar Stock</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       <div class="row align-items-md-stretch">
           <div class="form-group col-md-6">
                        <label style="float: left">Articulo</label>
                        <select class="form-select" name="art">
						    <c:forEach items="${listaArticulos}" var="Articulo">
						        <option id="${Articulo.nombre}" value="${Articulo.nombre}">${Articulo.nombre}</option>
						    </c:forEach>
						  </select>
                      </div>
           <div class="form-group col-md-6">
             <label style="float: left">Fecha de Ingreso</label>
               <input class="form-control" type="date" name="date">
           </div>
         </div>        

         <div class="row align-items-md-stretch mt-1">
           <div class="form-group col-md-6">
             <label style="float: left">Precio de Compra</label>
             <input id="precio_compra" type="number" name="precio_compra" class="form-control">
           </div>
           <div class="form-group col-md-6">
             <label style="float: left">Cantidad</label>
             <input id="cantidad" name="cantidad" type="number" class="form-control">
           </div>
         </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
         <input type="submit" class="btn btn-primary" name="btnAceptar" value="Aceptar">
      </div>
     </form>
    </div>
  </div>
</div>      

</body>
</html>