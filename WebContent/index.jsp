<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>SVcars HomePage</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/PannelloSidebar.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/visualizzaAnnunci.css">
	</head>
	
	<body>
		<% String tipoUtente = request.getAttribute("tipoUtente").toString(); %>
	
		<% if(tipoUtente.equals("Amministratore")) {%>
			<jsp:include page="jsp/HeaderAmministratore.jsp"></jsp:include>
			
		<%} else if(tipoUtente.equals("Utente_normale")) {%>
			<jsp:include page="jsp/HeaderRegistrato.jsp"></jsp:include>
			
		<% }else {%>
			<jsp:include page="jsp/HeaderGuest.jsp"></jsp:include>
		<% } %>
		
		
			<div class="layout-container">
	        	<jsp:include page="jsp/SidebarSX.jsp" />
	        	<jsp:include page="jsp/VisualizzaAnnunci.jsp" />
	    	</div>
		
		<jsp:include page="jsp/Footer.jsp"></jsp:include>
		
		
		<script>const contextPath = "${pageContext.request.contextPath}";</script>
		
		<script src="${pageContext.request.contextPath}/scripts/validazioneBarra.js"></script>
	</body>

</html>