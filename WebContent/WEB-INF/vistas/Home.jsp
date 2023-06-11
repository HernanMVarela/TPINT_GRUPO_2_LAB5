<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="common/Header.jspf"></jsp:include>
</head>

<body>
<jsp:include page="common/NavigatorCommon.jspf"></jsp:include>
<div class="container-fluid">
  <form  id="myForm" method="post">
    <div class="cuadro_uno justify-content-center">
      <div class="d-flex col col-12 mh-2 justify-content-center p-2">
        <div class="row w-auto justify-content-center">
          <h1>Portal de inicio</h1>
        </div>
      </div>
      <div class="d-flex col col-12 mh-2 justify-content-center p-2">
			<input type="submit" class="btn btn-primary mr-1" value="Login Administrador" name="btnRedirigir" formaction="admin.html">
			<input type="submit" class="btn btn-primary mr-1" value="Login Vendedor" name="btnRedirigir" formaction="vendedor.html">
			<input type="submit" class="btn btn-primary mr-1" value="Login Contador" name="btnRedirigir" formaction="contador.html">
	  </div> 
    </div>    
  </form>   
</div>
</body>
</html>