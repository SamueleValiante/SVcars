<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>SVcars HomePage</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/visualizzaAnnunci.css">
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
		
		
		<div id="risultati" class="pannelloCentrale">

			<% 
				List<AnnuncioBean> listaAnnunci = new OrdineDAO().getAnnunciOrdine(request.getParameter("codiceOrdine")); 
				if (listaAnnunci != null && !listaAnnunci.isEmpty()) 
				{
			%>
				
				<h2>Prodotti ordinati</h2>
				
				<ul>
					<% for (AnnuncioBean annuncio : listaAnnunci) {
						String targa = annuncio.getTarga();
					%>
						<li>
							
							<div class="annuncio">
								<img src="${pageContext.request.contextPath}/images/<%= targa %>.jpg" alt="Annuncio">
								<a href="/SVcars/VisualizzaAnnuncioServletOrdine?targa=<%= annuncio.getTarga() %>" class="annuncio-link">
						            <label class="annuncioTitolo"><%= annuncio.getTitolo() %></label>
						            <label class="annuncioLabel"><%= annuncio.getCitta() %></label>
						            <label class="annuncioPrezzo"><%= (int) annuncio.getPrezzo() %>€</label>
						        </a>
						    </div>
						</li>

					<% } %>
				</div>
			<% } else { 
			%>
				<h1>Nessun Annuncio trovato!</h1>
			<% } %>
		</div>
		
		
		<jsp:include page="Footer.jsp"></jsp:include>
		
		<script src="${pageContext.request.contextPath}/scripts/validazioneBarra.js"></script>
		
	</body>

</html>