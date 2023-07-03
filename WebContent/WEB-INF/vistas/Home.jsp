<%@page import="frgp.utn.edu.ar.dominio.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="common/Header.jspf" %>
</head>

<script type="text/javascript">
function admin() { 
	var user = document.getElementById("usernameLogin");
	var pass = document.getElementById("passLogin");
	user.value = 'admin';   
	pass.value = '123456'; 
}

function vendedor() { 
	var user = document.getElementById("usernameLogin");
	var pass = document.getElementById("passLogin");
	user.value = 'vendedor';   
	pass.value = '123456'
}

function contador() { 
	var user = document.getElementById("usernameLogin");
	var pass = document.getElementById("passLogin");
	user.value = 'contador';   
	pass.value = '123456'
}
</script>

<body>
<%@ include file="common/NavigatorCommon.jspf" %>
	<div class="container-fluid">
		<% Usuario user = null; %>
 		<% user = (Usuario)request.getAttribute("userLogin"); %>
 		
 		 <div class="row align-items-md-stretch">
             <div class="d-flex justify-content-center" style="text-align: center;">
                	<h3 class="w-auto"><% if (user != null){%> Bienvenido <%= user.getNombreU() %> <% }else{%> NO HAY USUARIO LOGUEADO <%}  %></h3>	
             </div>
             </div>
 		
			<form action="login.html" method="post">
				<div class="container">
					<div class="p-2 mb-2 bg-light border rounded-3" style="width: 100%">
						<div class="container-fluid py-1">
							<div class="row align-items-md-stretch">
								<% if (user == null){%>
								<div class="col-md-4" style="text-align: center;">
									<img class="img-responsive p-3" style="width: 80%;" src="<c:url value="/assets/logo.png"/>"/>
									<h1>¡Hola!</h1>
									<p>
										Si aun no tienes cuenta, puedes registrarte haciendo click en el boton de abajo.
									</p>
									<input type="submit" class="btn btn-success mr-1" value="REGISTRARSE" name="btnRedirigir" formaction="registro-usuario.html">
								</div>
								<% } else {%>
								<div class="col-md-4 ">
						       		<%@ include file="common/UserData.jspf" %>        
						        </div>
								<% } %>
								<div class="col-md-8" style="text-align: center;">
									<div class="d-flex col col-12 mh-2 justify-content-center p-2">
										<div class="row w-auto justify-content-center">
											<img class="img-responsive p-2" style="width: 90%;" src="<c:url value="/assets/banner.jpg"/>"/>

											<% if (user == null){%>
												<input class="form-control mt-2" id="usernameLogin" name="usernameLogin" value="" placeholder="Ingrese usuario" >									
												<input class="form-control mt-2" id="passLogin" name="passLogin" type="password" value="" placeholder="Ingrese contraseña">
												<input type="submit" class="btn btn-primary mt-2" value="INGRESAR" name="btnRedirigir">
											<% } else {%>
												<input class="form-control mt-2" id="usernameLogin" name="usernameLogin" disabled>									
												<input class="form-control mt-2" id="passLogin" name="passLogin" type="password" disabled>
												<input type="submit" class="btn btn-danger mt-2" value="CERRAR SESION" name="btnCerrarSession" formaction="Logout.html">	
											<% } %>
											
										</div>

									</div>

									<hr>
									<div class="d-flex col col-12 mh-2 justify-content-center p-2">
										<button type="button" onclick="admin()" class="btn btn-danger m-1" value='ADMINISTRADOR'>ADMINISTRADOR</button>
  										<button type="button" onclick="vendedor()" class="btn btn-warning m-1" value='VENDEDOR'>VENDEDOR</button>
										<button type="button" onclick="contador()" class="btn btn-info m-1" value='CONTADOR'>CONTADOR</button>									
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</form>
		</div>
	</body>

	</html>