<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="common/Header.jspf"%>
</head>

<body>
	<div class="container-fluid">
		<div class="container">
			<div class="p-2 mb-2 bg-light border rounded-3 mt-2 d-flex aligns-items-center justify-content-center" style="width: 100%">
			<div class="col-md-12" style="text-align: center;">			
				<img class="img-fluid" src="<c:url value="/assets/error.png"/>" />				
				<label name="error" id="error" value="${Mensaje}" >${Mensaje}</label>
			</div>
			</div>			
		</div>
	</div>
</body>
</html>