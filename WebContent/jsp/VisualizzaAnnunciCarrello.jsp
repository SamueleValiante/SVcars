<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>SVcars HomePage</title>
		<link rel="stylesheet" href="css/carrello.css">
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
		
		<div class="pannelloCarrello">
			<% 
				List<AnnuncioBean> listaAnnunci = (List<AnnuncioBean>) request.getAttribute("annunciCarrello"); 
				if (listaAnnunci != null && !listaAnnunci.isEmpty()) {
			%>
				<ul>
					<% for (AnnuncioBean annuncio : listaAnnunci) {
						String targa = annuncio.getTarga();
					%>
						<li>
							<img src="images/<%= targa %>.png" alt="Annuncio">
							<label><%annuncio.getTitolo(); %></label>
							<label><%annuncio.getPrezzo(); %></label>
						</li>
					<% } %>
				</ul>
			<% } else { %>
				<h1>Il tuo carrello è vuoto</h1>
			<% } %>
		</div>
		
		<jsp:include page="Footer.jsp"></jsp:include>
		
		<script src="${pageContext.request.contextPath}/scripts/validazioneBarra.js"></script>
		
	</body>

</html>