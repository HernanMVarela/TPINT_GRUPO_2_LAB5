<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<%@ include file="../common/Header.jspf" %>
		<%@ include file="../common/NavigatorAdmin.jspf" %>		
    </head>

 <body>
<div class="container-fluid">
  <form  id="myForm" method="get">
      <div class="p-5 bg-light border rounded-3" style="width: 100%">
        <div class="row w-auto justify-content-center">
          <h1>REPORTES</h1>
          <hr>
          <div class="row align-items-md-stretch">
	          <div class="col-md-4">
	          <h4>CANTIDAD DE USUARIOS POR ROL</h4>
	          <hr>
	          <table id="tabla_empleados" class="table table-hover text-center">
	                  <thead>
	                      <tr>                         
	                          <th scope="col">ROL</th>
	                          <th scope="col">CANTIDAD</th>                          
	                      </tr>
	                  </thead>
	                  <tbody>
	                      <c:forEach var="entry" items="${listaRoles}">
	                      <tr>
	                            <td>${entry.key}</td>
	                            <td>${entry.value} </td>
	                      </tr>
						  </c:forEach>
	                  </tbody>
	              </table>
	           	</div>
      		</div>              
        </div>
      </div>
      </form>      
</div>  
</body>
</html>