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
		<% String tipoUtente = request.getAttribute("tipoUtente").toString(); %>
	
		<% if(tipoUtente.equals("Amministratore")) {%>
			<jsp:include page="HeaderAmministratore.jsp"></jsp:include>
			
		<%} else if(tipoUtente.equals("Utente_normale")) {%>
			<jsp:include page="HeaderRegistrato.jsp"></jsp:include>
			
		<% }else {%>
			<jsp:include page="HeaderGuest.jsp"></jsp:include>
		<% } %>
		
		<jsp:include page="PannelloSidebar.jsp"></jsp:include>
		
		<jsp:include page="Footer.jsp"></jsp:include>
	</body>

</html>