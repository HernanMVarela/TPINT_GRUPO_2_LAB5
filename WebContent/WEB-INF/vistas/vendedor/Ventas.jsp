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
                $('#tabla_ventas').DataTable();
              });
            </script>
      </head>

      <body>
        <div class="container-fluid">
            <form action="ventas.html" method="post">
              <div class="p-5 bg-light border rounded-3" style="width: 100%">
                <div class="d-flex  align-content-center bd-highlight mb-3">
                  <div class="me-auto p-2 bd-highlight align-self-center">
					<h1 >VENTAS</h1>
				</div>
				<div class="p-2 bd-highlight align-self-center">
					<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addSaleModal">
			  			CARGAR VENTA
					</button>
				</div>	                  
                </div>

                <div class="row mx-2 d-flex flex-wrap align-middle justify-content-evenly">
                  <div class="col-md-auto table-responsive w-100">
                    <table id="tabla_ventas" class="table table-hover text-center">
			  		<thead>
						<tr>
							<th class="text-center" scope="col"> N� de Venta </th>
							<th class="text-center" scope="col"> Fecha </th>
							<th class="text-center" scope="col"> Cliente </th> 
							<th class="text-center" scope="col"> Monto </th> 
							<th></th>
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
								<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#detailSaleModal">
			                      DETALLE
			                    </button>
							</td>
							<td> 
								<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteSaleModal">
			                      ANULAR VENTA
			                    </button>
							</td>
						<%}%>  					
					   </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </form>
          </div>

      <!-- Modal AGREGAR VENTA -->
        <div class="modal fade" id="addSaleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
              <form action="ingreso_venta.html" method="post">            
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">AGREGAR VENTA</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <div class="row align-items-md-stretch">

                  <div style="padding-bottom: 20px; display: flex;">
                    <div>
                      <label style="float: left">Fecha</label>
                      <input id="fechaNuevo" name="fechaNuevo" class="form-control" type="date" name="date" required>
                    </div>

                    <div style="float: left; padding-left: 20px;">
                      <label style="float: left">Cliente</label><br />
                      <div style="display: flex; ">
                        <input style="width: 200px;" class="form-control" placeholder="Ingrese nombre" required>
                        <button type="button" class="btn btn-danger m-1" data-bs-dismiss="modal">Buscar</button>
                       </div>
                    </div>
                  </div>


                  <div style="padding-bottom: 20px;" class="form-group col-md-6">
                    <label style="float: left">Monto total</label>
                    <input id="precio_compra" type="number" name="precio_compra" class="form-control" required min="1">
                  </div>
                  <hr />

                  <div style="padding-bottom: 20px ; display: flex;">
                    <div class="form-group col-md-4">
                      <label style="float: left">Articulo</label>
                      <select class="form-select">
                        <option value="administrador">Articulo1</option>
                        <option value="administrador">Articulo2</option>
                        <option value="administrador">Articulo3</option>
                      </select>
                    </div>

                    <div style="float: left; padding-left: 20px; width: 100px;">
                      <label style="float: left">Cantidad</label>
                      <input id="" type="number" name="precio_compra" class="form-control" required min="1">
                    </div>

                    <div style="float: left; padding-left: 20px;">
                      <label style="float: left">Precio</label>
                      <input id="" type="number" name="precio_compra" class="form-control" required min="1">
                    </div>

                  </div>
                  <br />

                  <button type="button" class="btn btn-success" data-bs-dismiss="modal">Agregar articulo</button>

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
        
        <!-- Modal DETALLE VENTA -->
        <div class="modal fade" id="detailSaleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
          aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">DETALLE VENTA</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                 <table id="tabla_ventas" class="table table-hover text-center">
			  		<thead>
						<tr>
							<th class="text-center" scope="col"> PRODUCTO </th>
							<th class="text-center" scope="col"> PRECIO U. </th>
							<th class="text-center" scope="col"> CANTIDAD </th> 
							<th class="text-center" scope="col"> SUBTOTAL </th> 
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>COCA COLA</td>
							<td>$ 500</td>
							<td>2</td>
							<td>$ 1000</td>
						</tr>
						<tr>
							<td>FERNET</td>
							<td>$ 1500</td>
							<td>1</td>
							<td>$ 1500</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<label>TOTAL: $2500</label>							
              </div>
              <div class="modal-footer">               
              </div>
            </div>
          </div>
        </div>

        <!-- Modal ELIMINAR VENTA -->
        <div class="modal fade" id="deleteSaleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
          aria-hidden="true">
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