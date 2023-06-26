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
            
            <c:forEach items="${listaVentas}" var="item">
                                             
                          <tr>
                            <td>${item.num_venta} </td>
                            <td>${item.fecha} </td>
                            <td>${item.cliente.ID} </td>
                            <td> </td>
                            
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
                            
                          </tr>
                        </c:forEach>
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
              <form action="ingreso_venta.html" method="post" id="ventaForm">            
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

                    <div  style="padding-left: 20px;" class="form-group col-md-4">
                      <label style="float: left">Cliente</label>
                      <select id="cliente" name="cliente" class="form-select">
                        <c:forEach items="${listaClientes}" var="item">
                          <c:if test="${item.estado.ID == 1}">     
                             <option value="${item.ID}">${item.ID} - ${item.nombre},${item.apellido}</option>
                          </c:if>
                      </c:forEach>
                      </select>
                    </div>
                  </div>


                  
                  <hr />

                  <button type="button" class="btn btn-success" onclick="agregarArticulo()">Agregar articulo</button>

                  <div style="padding-bottom: 20px ; display: flex;" id="articulos-container">
                    <div class="form-group col-md-4">
                      <label style="float: left">Articulo</label>
                      <select class="form-select" name="articulo" id="articulo" class="form-select" required onchange="actualizarPrecio(this)">
                        <option value="" selected disabled hidden>Seleccione un articulo</option>
                        <c:forEach items="${listaArticulos}" var="item">
                          <option id="${item.nombre}" value="${item.nombre}">${item.nombre} - ${item.marca.nombre}</option>
                        </c:forEach>
                      </select>
                    </div>

                    <div style="float: left; padding-left: 20px; width: 100px;">
                      <label style="float: left">Cantidad</label>
                      <input type="number" name="cantidad" class="form-control" required min="1" oninput="calcularMontoTotal()">
                    </div>

                    <div style="float: left; padding-left: 20px;">
                      <label style="float: left">Precio</label>
                      <input name="precio_venta" type="number" class="form-control" required min="1" readonly>
                    </div>

                    <div style="float: left; padding-left: 20px; padding-top: 20px; place-self: center;">
                      <button type="button" class="btn btn-danger" onclick="eliminarArticulo(this)">Eliminar</button>
                    </div>
                  </div>

                  <script>
                    var articulosContainer = document.getElementById("articulos-container");
                    var listaArticulos = [
                      <c:forEach items="${listaArticulos}" var="item" varStatus="status">
                        {
                          nombre: '${item.nombre}',
                          marca: '${item.marca.nombre}',
                          precio: ${item.precio_venta}
                        }<c:if test="${!status.last}">,</c:if>
                      </c:forEach>
                    ];
                    var itemCounter = 1;

                    function agregarArticulo() {
                      var nuevoArticulo = articulosContainer.cloneNode(true);
                      var selectElement = nuevoArticulo.querySelector(`select[name='articulo']`);
                      var cantidadInput = nuevoArticulo.querySelector(`input[name='cantidad']`);
                      var precioInput = nuevoArticulo.querySelector(`input[name='precio_venta']`);
                      var eliminarButton = nuevoArticulo.querySelector("button");

                      // Asignar ID único a los elementos de artículo
                      console.log("agregar")
                      selectElement.setAttribute("id", `articulo${itemCounter}`);
                      cantidadInput.setAttribute("id", `cantidad${itemCounter}`);
                      precioInput.setAttribute("id", `precio_venta${itemCounter}`);

                      selectElement.selectedIndex = 0;
                      cantidadInput.value = "";
                      precioInput.value = "";
                      eliminarButton.style.display = "inline-block";

                      articulosContainer.parentElement.appendChild(nuevoArticulo);
                      itemCounter++;
                    }

                    function eliminarArticulo(button) {
                      if (itemCounter > 1) {
                        var fila = button.parentNode.parentNode;
                        fila.parentNode.removeChild(fila);
                        itemCounter--;
                      }
                    }

                    function actualizarPrecio(selectElement) {
                      var articulo = selectElement.value;
                      var precioInput = selectElement.parentElement.nextElementSibling.nextElementSibling.querySelector("input[name='precio_venta']");

                      var articuloEncontrado = listaArticulos.find(function (item) {
                        return item.nombre === articulo;
                      });

                      precioInput.value = articuloEncontrado ? articuloEncontrado.precio : "";
                      calcularMontoTotal();
                    }

                    function enviarFormulario() {
  var contenedores = document.querySelectorAll("#articulos-container");

  var articulos = [];

  contenedores.forEach(function (contenedor) {
    var selectElement = contenedor.querySelector("select[name='articulo']");
    var cantidadInput = contenedor.querySelector("input[name='cantidad']");
    var precioInput = contenedor.querySelector("input[name='precio_venta']");

    var articulo = {
      nombre: selectElement.value,
      cantidad: cantidadInput.value,
      precio: precioInput.value
    };



    articulos.push(articulo);
  });

  var articulosInput = document.getElementById("articulosLista");
  articulosInput.value = JSON.stringify(articulos);

  document.getElementById("ventaForm").submit();

}

function cerrarCarrito() {
    var contenedores = document.querySelectorAll("#articulos-container");
    var fechaInput = document.querySelector("input[name='fechaNuevo']");
    var camposIncompletos = false;

    contenedores.forEach(function (contenedor) {
      var selectElement = contenedor.querySelector("select[name='articulo']");
      var cantidadInput = contenedor.querySelector("input[name='cantidad']");
     

      if (selectElement.value === "" || cantidadInput.value === "") {
        camposIncompletos = true;
      }
    });
    if (fechaInput.value === "") {
      camposIncompletos = true;
    }

    if (camposIncompletos) {
      alert("Por favor, complete todos los campos antes de cerrar el carrito.");
    } else {
      enviarFormulario();
    }
  }
                  </script>




                      </div>
                      <div style="padding-bottom: 20px;" class="form-group col-md-6">
                        <label style="float: left">Monto total</label>
                        <input id="precio_compra" type="number" name="precio_compra" class="form-control" readonly>
                      </div>

                      <script>
                         function calcularMontoTotal() {
                            var contenedores = document.querySelectorAll("#articulos-container");
                            var montoTotal = 0;

                            contenedores.forEach(function (contenedor) {
                              var cantidadInput = contenedor.querySelector("input[name='cantidad']");
                              var precioInput = contenedor.querySelector("input[name='precio_venta']");

                              var cantidad = parseInt(cantidadInput.value);
                              var precio = parseFloat(precioInput.value);

                              if (!isNaN(cantidad) && !isNaN(precio)) {
                                montoTotal += cantidad * precio;
                              }
                            });

                            var precioCompraInput = document.getElementById("precio_compra");
                            precioCompraInput.value = montoTotal.toFixed(2);
                          }
                      </script>

                    </div>
                   
                    <input   id="articulosLista" type="hidden" name="articulosLista" class="form-control" required min="1">

                    <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">CANCELAR</button>
                    <button type="button" class="btn btn-success" onclick="cerrarCarrito()" >Guardar venta</button>
                    <input style="display: none;" type="submit" class="btn btn-primary" name="btnAceptar" value="Aceptar">
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