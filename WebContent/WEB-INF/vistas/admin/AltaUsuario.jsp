<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

   <head>
	<%@ include file="../common/Header.jspf"%>
</head>

<body>
	<%@ include file="../common/NavigatorAdmin.jspf"%>
        <div class="container-fluid">
                <div class="container">
                    <div class="p-2 mb-2 bg-light border rounded-3" style="width: 100%">
                        <div class="container-fluid py-1">
                            <div>
                                <br>
                                <h1 style="text-align: center;">REGISTRO DE NUEVOS USUARIOS</h1>
                                <hr>
                                <div class="row align-items-md-stretch">

                                    <div class="col-md-6">
							    	<form id="altaForm" method="post">
										<div class="row align-items-md-stretch">
											<div class="form-group col-md-6">
												<label style="float: left">Nombre</label> 
												<input class="form-control" placeholder="Ingrese nombre" required>
											</div>
											<div class="form-group col-md-6">
												<label style="float: left">Apellido</label>
												<input class="form-control" placeholder="Ingrese apellido" required>
											</div>
										</div>
	
										<br>
	
										<div class="row align-items-md-stretch">
											<div class="form-group col-md-9">
												<label style="float: left">DNI</label> 
												<input class="form-control" type="number" placeholder="Ingrese DNI" required min="1" max="99999999" >
											</div>
										</div>
	
										<hr>
	
										<div class="form-group col-md-8">
											<label style="float: left">Usuario</label> 
											<input class="form-control" placeholder="Ingrese usuario" required> 
											
											<br>
	
											<label style="float: left">Contraseña</label>
											<input class="form-control" type="password" placeholder="Ingrese contraseña" required min="6"> 
											
											<br> 
											
											<label style="float: left">Repita la contraseña</label> 
											<input class="form-control" type="password" placeholder="Re-Ingrese su contraseña" required min="6">
										</div>
	
										<hr>
	
										<label style="float: left">Seleccione rol</label>
										<select class="form-select">
											<option value="administrador">ADMINISTRADOR</option>											
											<option value="vendedor">VENDEDOR</option>
											<option value="contador">CONTADOR</option>
										</select>
	
										<hr>
	
										<div class="d-flex justify-content-center">
											<input type="submit" class="btn btn-success mr-1" value="REGISTRARSE" name="btnRedirigir" formaction="home.html">
										</div>
								    </form>
									</div>
								
                                    <div class="col-md-6" style="text-align: center;">
                                    	<img class="img-responsive p-3" style="width: 80%;" src="<c:url value="/assets/userLogo.png"/>"/>

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </body>

    </html>