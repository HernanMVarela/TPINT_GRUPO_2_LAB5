<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <jsp:include page="../common/Header.jspf"></jsp:include>
    </head>

    <body>
        <jsp:include page="../common/NavigatorAdmin.jspf"></jsp:include>
        <div class="container-fluid">
            <form id="altaForm" method="post">
                <div class="container">
                    <div class="p-2 mb-2 bg-light border rounded-3" style="width: 100%">
                        <div class="container-fluid py-1">
                            <div>
                                <br>
                                <h1 style="text-align: center;">REGISTRO DE NUEVOS USUARIOS</h1>
                                <hr>
                                <div class="row align-items-md-stretch">

                                    <div class="col-md-6">
                                        <div class="row align-items-md-stretch">
                                            <div class="form-group col-md-6">
                                                <label style="float: left">Nombre</label>
                                                <input class="form-control" placeholder="Ingrese nombre">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label style="float: left">Apellido</label>
                                                <input class="form-control" placeholder="Ingrese apellido">
                                            </div>
                                        </div>

                                        <br>

                                        <div class="row align-items-md-stretch">
                                            <div class="form-group col-md-9">
                                                <label style="float: left">DNI</label>
                                                <input class="form-control" type="number" placeholder="Ingrese DNI">
                                            </div>
                                        </div>

                                        <hr>

                                        <div class="form-group col-md-8">
                                            <label style="float: left">Usuario</label>
                                            <input class="form-control" placeholder="Ingrese usuario">

                                            <br>

                                            <label style="float: left">Contraseņa</label>
                                            <input class="form-control" type="password"
                                                placeholder="Ingrese contraseņa">

                                            <br>

                                            <label style="float: left">Repita la contraseņa</label>
                                            <input class="form-control" type="password"
                                                placeholder="Re-Ingrese su contraseņa">
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
                                            <button class="btn btn-primary mr-1">Registrar</button>
                                        </div>
                                    </div>

                                    <div class="col-md-6" style="text-align: center;">
                                        <img src="${pageContext.request.contextPath}/assets/userLogo.png"
                                            class="img-responsive" style="width: 80%;">
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