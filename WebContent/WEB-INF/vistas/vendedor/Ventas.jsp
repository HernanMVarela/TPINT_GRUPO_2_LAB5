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

                  <div id="articulos-container"  style="padding-bottom: 20px; display: flex; flex-wrap: wrap; justify-content: center; " ></div>


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
                            var nuevoArticulo = document.createElement("div");
                            nuevoArticulo.classList.add("form-group", "col-md-10", "row", "pt-5");

                            var selectElement = document.createElement("select");
                            selectElement.classList.add("form-select");
                            selectElement.setAttribute("name", "articulo");
                            selectElement.setAttribute("id", `articulo${itemCounter}`);
                            selectElement.setAttribute("required", true);
                            selectElement.setAttribute("onchange", "actualizarPrecio(this)");

                            var defaultOption = document.createElement("option");
                            defaultOption.setAttribute("value", "");
                            defaultOption.setAttribute("selected", true);
                            defaultOption.setAttribute("disabled", true);
                            defaultOption.setAttribute("hidden", true);
                            defaultOption.textContent = "Seleccione un articulo";
                            selectElement.appendChild(defaultOption);

                            listaArticulos.forEach(function (articulo) {
                              var option = document.createElement("option");
                              option.setAttribute("id", articulo.nombre);
                              option.setAttribute("value", articulo.nombre);
                              option.textContent = articulo.nombre + " - " + articulo.marca;
                              selectElement.appendChild(option);
                            });

                            nuevoArticulo.appendChild(selectElement);

                            var cantidadInput = document.createElement("input");
                            cantidadInput.setAttribute("type", "number");
                            cantidadInput.setAttribute("name", "cantidad");
                            cantidadInput.classList.add("form-control");
                            cantidadInput.setAttribute("required", true);
                            cantidadInput.setAttribute("min", "1");
                            cantidadInput.setAttribute("id", `cantidad${itemCounter}`);
                            cantidadInput.setAttribute("oninput", "calcularMontoTotal()");

                            nuevoArticulo.appendChild(cantidadInput);

                            var precioInput = document.createElement("input");
                            precioInput.setAttribute("name", "precio_venta");
                            precioInput.setAttribute("type", "number");
                            precioInput.classList.add("form-control");
                            precioInput.setAttribute("required", true);
                            precioInput.setAttribute("min", "1");
                            precioInput.setAttribute("readonly", true);
                            precioInput.setAttribute("id", `precio_venta${itemCounter}`);

                            nuevoArticulo.appendChild(precioInput);

                            var eliminarButton = document.createElement("button");
                            eliminarButton.setAttribute("type", "button");
                            eliminarButton.classList.add("btn", "btn-danger");
                            eliminarButton.textContent = "Eliminar";
                            eliminarButton.setAttribute("onclick", "eliminarArticulo(this)");

                            nuevoArticulo.appendChild(eliminarButton);

                            articulosContainer.appendChild(nuevoArticulo);
                            itemCounter++;
                          }

                          function eliminarArticulo(button) {
                            if (itemCounter > 1) {
                              var fila = button.parentNode;
                              fila.parentNode.removeChild(fila);
                              itemCounter--;
                            }
                          }

                          function actualizarPrecio(selectElement) {
                          var selectedOption = selectElement.options[selectElement.selectedIndex];
                          var precioInput = selectElement.parentNode.querySelector('input[name="precio_venta"]');
                          
                          if (selectedOption && precioInput) {
                            var articulo = listaArticulos.find(function (articulo) {
                              return articulo.nombre === selectedOption.value;
                            });
                          
                            if (articulo) {
                              precioInput.value = articulo.precio.toString();
                              calcularMontoTotal();
                            }
                          }
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

    if ( contenedores[0]?.children?.length === 0)
    {
      alert("Necesita cargar al menos un articulo para guardar el carrito.");
    }

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
      alert("Por favor, complete todos los campos antes de guardar el carrito.");
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
                            var filas = document.querySelectorAll("#articulos-container .form-group");

                            var total = 0;
                            filas.forEach(function (fila) {
                              var cantidad = parseInt(fila.querySelector('input[name="cantidad"]').value);
                              var precio = parseFloat(fila.querySelector('input[name="precio_venta"]').value);
                              var subtotal = cantidad * precio;

                              if (!isNaN(subtotal)) {
                                total += subtotal;
                              }
                            });

                            var montoTotalInput = document.getElementById("precio_compra");
                            if (montoTotalInput) {
                              montoTotalInput.value = total.toFixed(2);
                            }
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