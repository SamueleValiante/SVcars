<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>SVcars Carrello</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	
	<body>
		<% String tipoUtente = request.getAttribute("tipoUtente").toString(); %>
	
		<% if(tipoUtente.equals("Amministratore")) {%>
			<jsp:include page="HeaderAmministratoreOther.jsp"></jsp:include>
			
		<%} else if(tipoUtente.equals("Utente_normale")) {%>
			<jsp:include page="HeaderRegistratoOther.jsp"></jsp:include>
			
		<% }else {%>
			<jsp:include page="HeaderGuestOther.jsp"></jsp:include>
		<% } %>
		
		<jsp:include page="carrello.jsp"></jsp:include>
		
		<jsp:include page="Footer.jsp"></jsp:include>
		
		<script src="${pageContext.request.contextPath}/scripts/validazioneBarra.js"></script>
		
	</body>

</html>