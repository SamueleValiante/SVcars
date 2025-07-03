<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>SVcars HomePage</title>
	</head>
	
	<body>
		<% if(request.getAttribute("tipoUtente").equals("Amministratore")) {%>
			<jsp:include page="jsp/HeaderAmministratore.jsp"></jsp:include>
			
		<%} else if(request.getAttribute("tipoUtente").equals("Utente_normale")) {%>
			<jsp:include page="jsp/HeaderRegistrato.jsp"></jsp:include>
			
		<% }else {%>
			<jsp:include page="jsp/HeaderGuest.jsp"></jsp:include>
		<% } %>
	</body>

</html>