<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
<%@ include file="../common/Header.jspf" %>
</head>

<body>
<%@ include file="../common/NavigatorVendedor.jspf" %>
		<div class="container-fluid">
			<form id="myForm" method="get">
				<div class="cuadro_uno justify-content-center">
					<div class="d-flex col col-12 mh-2 justify-content-center p-2">
						<div class="row w-auto justify-content-center">
							<div class="d-flex col col-12 mh-2 justify-content-center p-2">
								<h1>Portal Administrador</h1>
							</div>

							<div class="d-flex col col-12 mh-2 justify-content-center p-2">
								<div class="row w-25 justify-content-center">
									<input type="submit" class="btn btn-primary" value="Alta de Usuario"
										name="btnRedirigir" formaction="alta-usuario.html">
								</div>
							</div>
							<div class="d-flex col col-12 mh-2 justify-content-center p-2">
								<div class="row w-25 justify-content-center">
									<input type="submit" class="btn btn-primary" value="Listado de Usuarios"
										name="btnRedirigir" formaction="listar-usuarios.html">
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>

	</html>