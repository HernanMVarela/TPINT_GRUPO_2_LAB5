<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<%@ include file="../common/Header.jspf" %>
	</head>
	
	<script type="text/javascript">
		function actualizarLocalidades() {
			// Obtiene la Informaci�n
			var provinciaSelect = document.getElementById('provincia');
			var localidadSelect = document.getElementById('localidad');
			var provinciaId = provinciaSelect.value;
			// Borra Todo
			localidadSelect.innerHTML = '';
			// Revisa Todo
			<c:forEach items="${listaLocalidades}" var="Localidad">
				if (${Localidad.getProvincia().getID()} == provinciaId) { var option = document.createElement('option');
				option.id = "${Localidad.ID}";
				option.value = "${Localidad.ID}";
				option.text = "${Localidad.nombre}";
				// Los a�ade si coinciden
				localidadSelect.appendChild(option); }
			</c:forEach>
		}
	</script>

	<script type="text/javascript">
		type="text/javascript"> var provinciaSelect = document.getElementById('provinciaNuevo');
	</script>
	

	<body>
		<%@ include file="../common/NavigatorCommon.jspf" %>
			<div class="container-fluid">
				<div class="container">
					<div class="p-2 mb-2 bg-light border rounded-3" style="width: 100%">
						<div class="container-fluid py-1">
							<div>
								<br>
								<h1 style="text-align: center;">CREACION DE USUARIO</h1>
								<hr>
								<div class="row align-items-md-stretch">

									<div class="col-md-8">
										<form action="alta_usuario.html" id="alta_usuario.html" method="post">
											<div class="row align-items-md-stretch">
												<div class="form-group col-md-6">
													<label style="float: left">Nombre</label>
													<input class="form-control" name="nombre"
														placeholder="Ingrese nombre" required>
												</div>
												<div class="form-group col-md-6">
													<label style="float: left">Apellido</label>
													<input class="form-control" name="apellido"
														placeholder="Ingrese apellido" required>
												</div>
											</div>

											<br>

											<div class="row align-items-md-stretch">
												<div class="form-group col-md-4">
													<label style="float: left">DNI</label>
													<input class="form-control" type="number" name="dni"
														placeholder="Ingrese DNI" required min="1" max="99999999">
												</div>
												<div class="form-group col-md-3">
													<label style="float: left">Fecha de Nacimiento</label>
													<input class="form-control" type="date" name="fechaNacimiento"
														required>
												</div>
												<div class="form-group col-md-5">
													<label style="float: left">Sexo</label>
													<select name="sexo" class="form-select">
														<option value="M">MASCULINO</option>
														<option value="F">FEMENINO</option>
														<option value="N/C">OTRO</option>
													</select>
												</div>
											</div>

											<br>
											
											<div class="row align-items-md-stretch mt-1">
									           <div class="form-group col-md-12">
									               <label style="float: left">Direccion</label>
									               <input name="direccion" class="form-control" placeholder="Ingrese direccion" required>
									           </div>
									       </div>
									       
									       <br>

											<div class="row align-items-md-stretch">
												<div class="form-group col-md-6">
													<label style="float: left">Provincia</label>
													<select class="form-select" id="provincia" name="provincia" onchange="actualizarLocalidades()">
														<c:forEach items="${listaProvincias}" var="Provincia">
															<option id="${Provincia.ID}" value="${Provincia.ID}">
																${Provincia.nombre}</option>
														</c:forEach>
													</select>
												</div>
												
												<div class="form-group col-md-6">
													<label style="float: left">Localidad</label>
													<select class="form-select" id="localidad"	name="localidad">
														<c:forEach items="${listaLocalidades}" var="localidad">
															<c:if
																test="${localidad.getProvincia().getID() == listaProvincias[0].ID}">
																<option id="${localidad.ID}" value="${localidad.ID}">
																	${localidad.nombre}</option>
															</c:if>
														</c:forEach>
													</select>
												</div>
											</div>
											
											
											<br>

											<div class="row align-items-md-stretch">
												<div class="form-group col-md-6">
													<label style="float: left">Correo Electronico</label>
													<input class="form-control" type="email" name="correo" placeholder="Ingrese correo electronico" required>
												</div>
												<div class="form-group col-md-6">
													<label style="float: left">Numero de Telefono</label>
													<input class="form-control" type="number" name="telefono" placeholder="Ingrese numero de telefono" required>
												</div>
											</div>

											<hr>

											<div class="form-group col-md-8">
												<label style="float: left">Usuario</label>
												<input class="form-control" name="user" placeholder="Ingrese usuario" required>

												<br>

												<label style="float: left">Contraseña</label>
												<input class="form-control" type="password" name="pass" placeholder="Ingrese contraseña" required min="6">

												<br>

												<label style="float: left">Repita la contraseña</label>
												<input class="form-control" type="password" placeholder="Re-Ingrese su contraseña" required min="6">
											</div>

											<hr>

											<label style="float: left">Seleccione rol</label>
											<select class="form-select" name="rol">
												<option value=2>VENDEDOR</option>
												<option value=3>CONTADOR</option>
											</select>

											<hr>

											<div class="d-flex justify-content-center">
												<input type="submit" class="btn btn-primary" name="btnAceptar"
													value="Aceptar">
											</div>
										</form>
									</div>

									<div class="col-md-4" style="text-align: center;">
										<form id="volverForm" method="post">
											<img class="img-responsive p-3" style="width: 50%;" src="<c:url value="/assets/register.png" />"/>
											<h1>¡Oye!</h1>
											<p>¿Ya tienes una cuenta? Haz click en el boton de abajo
												para iniciar sesión.</p>
											<input type="submit" class="btn btn-success mr-1" value="INICIAR SESION"
												name="btnRedirigir" formaction="home.html">
										</form>
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