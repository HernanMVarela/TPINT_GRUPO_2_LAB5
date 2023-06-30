<%@page import="frgp.utn.edu.ar.dominio.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/Header.jspf"%>
<%@ include file="../common/NavigatorContador.jspf"%>

<script type="text/javascript">
            $(document).ready(function () {
              $('#tabla_contador').DataTable();
            });
</script>

<script type="text/javascript" >
	function actualizarEstadoBoton(form) {
		var calcularButton = form.querySelector('button[type="submit"]');

		var fechaInicioInput = form.querySelector('input[name="datei"]');
		var fechaFinInput = form.querySelector('input[name="datef"]');

		if (fechaInicioInput.value === "" || fechaFinInput.value === "") {
		  calcularButton.disabled = true;
		} else {
		  calcularButton.disabled = false;
		}

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
	    	   if (user.getTipo().getNombre().equals("VENDEDOR")){ %>
	    		  <div class="col-md-12" >
	    		   <%@ include file="../common/ErrorPermisos.jspf" %>
	    		  </div>
	    	    <%} else {%>
 <div class="row align-items-md-stretch">
        <div class="col-md-3 ">
       		<%@ include file="../common/UserData.jspf" %>        
        </div>
		
       <div class="col-md-9">
        	<form action="HomeContador.html" method="post">
              <div class="p-5 bg-light border rounded-3" style="width: 100%">
                <div class="d-flex  align-content-center bd-highlight mb-3">
                  <div class="me-auto p-2 bd-highlight align-self-center">
                    <h1>VENTAS</h1>
                  </div>
                  <div class="p-2 bd-highlight align-self-center">
                  	<div class="d-flex align-items-center">
	                  	
	                  	<div style="width:200px;padding-right:20px;" >
			         		 <label style="float: left">Fecha de inicio</label>
		        		     <input onchange="actualizarEstadoBoton(this.form)" class="form-control" type="date" name="datei">
						</div>
			          
						<div style="width:200px;">
							<label style="float: left">Fecha de fin</label>
							<input onchange="actualizarEstadoBoton(this.form)" class="form-control" type="date" name="datef">
						</div>
						
	                    <button disabled="true" type="submit" class="btn btn-primary m-2">
	                      CALCULAR
	                    </button>
                    </div> 
                    <c:if test="${empty gananciaTotal}">
  						<c:set var="gananciaTotal" value="0" />
					</c:if>
                    <h4 class="m-1">GANANCIAS DEL PERIODO: $${gananciaTotal}</h4>               
                  </div>
                </div>

                <div class="row mx-2 d-flex flex-wrap align-middle justify-content-evenly">
                  <div class="col-md-auto table-responsive w-100">
                    <table id="tabla_contador" class="table table-hover text-center">
			  		<thead>
						<tr>
							<th class="text-center" scope="col"> N� de Venta </th>
							<th class="text-center" scope="col"> Fecha </th>
							<th class="text-center" scope="col"> Cliente </th> 
							<th class="text-center" scope="col"> Monto </th> 
							<th class="text-center" scope="col">  </th> 
							<th class="text-center" scope="col">  </th> 
						</tr>
					</thead>
					<tbody>
            
           			 <c:forEach items="${listaContador}" var="item">
                                             
                          <tr>
                            <td>${item.num_venta} </td>
                            <td>${item.fecha} </td>
                            <td  id="cliente_${item.num_venta}" >${item.cliente.nombre} ${item.cliente.apellido}</td>
                            <td id="total_monto_${item.num_venta}" >$ ${item.totalMonto()}</td>
							<td style="display: none;" id="td_venta_${item.num_venta}">${item.generarDetalleString()}</td>
							<td id="total_monto_${item.num_venta}" >

								<button  value="${item}" type="button" class="btn btn-success" data-bs-toggle="modal" 
						        data-bs-target="#detailSaleModal" onclick="abrirDetalleModal(${item.num_venta},${item.ganancia})">
						        DETALLE
						   		 </button>
							</td>
                          </tr>
                        </c:forEach>
					   </tbody>
                    </table>
					<script>
						          function abrirDetalleModal(numVenta,ganancia) {
									            
										var contenidoVenta = document.getElementById("td_venta_"+numVenta);
										var detalleVenta = document.getElementById("detalleVenta");
										detalleVenta.innerHTML = agregarSaltosDeLinea( contenidoVenta.innerHTML);								

										var totalMonto = document.getElementById("total_monto_"+numVenta);
										var totalVenta = document.getElementById("totalVenta");
										totalVenta.innerHTML = totalMonto.innerHTML;

										var clienteTD = document.getElementById("cliente_"+numVenta);
										var clienteModal = document.getElementById("nombreCliente");
										var gananciaDetalle = document.getElementById("gananciaDetalle");
										clienteModal.innerHTML = clienteTD.innerHTML;    
										gananciaDetalle.innerHTML = ganancia;   	   
										}

										function agregarSaltosDeLinea(texto) {
										var nuevoTexto = texto.replace(/PRODUCTO:/g, '<br/><br/> PRODUCTO:');
										return nuevoTexto;
										}
					</script>
                    
                  </div>
                </div>
              </div>
            </form>
    </div>
   </div>
    <% }}%>
 </div>

        <!-- Modal DETALLE VENTA -->
<div class="modal fade" id="detailSaleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">DETALLE VENTA</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
           <div class="modal-body">
			   
			    <div id="detalleVenta"></div>
			    <hr>
			    <label>TOTAL: <span id="totalVenta"></span></label>
			    <br>
			    <label>Cliente: <span id="nombreCliente"></span></label>
				<br>
				<h3>Ganancia : $<span id="gananciaDetalle"></span></h3>
			</div>

            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>
            
</body>
</html>