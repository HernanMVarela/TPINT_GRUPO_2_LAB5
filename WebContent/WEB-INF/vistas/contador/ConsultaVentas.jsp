<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common/Header.jspf"></jsp:include>
</head>

<body>
<jsp:include page="../common/NavigatorContador.jspf"></jsp:include>

<div class="container-fluid">
  <form  id="myForm" method="get">
    <div class="cuadro_uno justify-content-center">
      <div class="d-flex col col-12 mh-2 justify-content-center p-2">
        <div class="row w-auto justify-content-center">
          <h1>Consulta Ventas</h1>
          <div >
          <div  style="display:flex;">
          
			<div style="width:200px;padding-right:20px;" >
         		 <label style="float: left">Fecha de inicio</label>
        		     <input class="form-control" type="date" name="date">
			</div>
          
			<div style="width:200px;">
				<label style="float: left">Fecha de fin</label>
					<input class="form-control" type="date" name="date">
			</div>

          </div>
          
          <table id="tabla_clientes" class="table table-hover text-center">
			  		<thead>
						<tr>
						    <th class="text-center" scope="col"> Fecha </th>
						    <th class="text-center" scope="col"> N° venta </th>
							<th class="text-center" scope="col"> ID Cliente </th>
							<th class="text-center" scope="col"> Ganancia </th>
							
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
								
							</tr>
						<%}%>  					
					</tbody>
					</table></div>
					
					<div>
					<h3>Ganancia total obtenida en rango de fechas:</h3></div>
        </div>
      </div>      
</div>




</body>
</html>