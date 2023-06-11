<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	<jsp:include page="cssStyles/StyleSheet.css"></jsp:include>
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio</title>
</head>
<body>
<div class="container-fluid">
  <form  id="myForm" method="get">
    <div class="cuadro_uno justify-content-center">
      <div class="d-flex col col-12 mh-2 justify-content-center p-2">
        <div class="row w-auto justify-content-center">
          <h1>Portal de inicio</h1>
        </div>
      </div>
      <div class="d-flex col col-12 mh-2 justify-content-center p-2">
        <div class="row w-25 justify-content-center">
          <input type="submit" class="btn btn-primary" value="Listado Articulos" name="btnRedirigir" formaction="redireccionar_listado_art.html">
        </div>
      </div>
      <div class="d-flex col col-12 mh-2 justify-content-center p-2">
        <div class="row w-25 justify-content-center">
 <input type="submit" class="btn btn-primary" value="Listado Clientes" name="btnRedirigir" formaction="redireccionar_listado_clientes.html">
        </div>
      </div>
    </div>
  </form>
</div>




</body>
</html>