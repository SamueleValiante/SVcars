<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/carrello.css">
	</head>

	<body>
		<div class="pannelloCarrello">
			<% 
				List<AnnuncioBean> listaAnnunci = (List<AnnuncioBean>) request.getAttribute("annunciCarrello"); 
			    double totale = 0;
			    HttpSession session2 = request.getSession();
			    UtenteIscrittoBean user = (UtenteIscrittoBean) session2.getAttribute("utente");
				if (listaAnnunci != null && !listaAnnunci.isEmpty()) {
			%>
				<h2>I tuoi preferiti</h2>
				
				<%if(user != null) {%>
					<%request.getSession().setAttribute("annunciCarrello", listaAnnunci); %>
				
					<form action="/SVcars/EffettuaAcquistoServlet" method="get">
						<br> <input class="ordina" type="submit" value="Acquista tutto">
					</form>
				<%} %>
				<ul>
					<% for (AnnuncioBean annuncio : listaAnnunci) {
						String targa = annuncio.getTarga();
						totale += annuncio.getPrezzo();
					%>
						<li>
									
							<div class="annuncio">
								<img src="${pageContext.request.contextPath}/images/<%= targa %>.jpg" alt="Annuncio">
								<a href="/SVcars/VisualizzaAnnuncioCarrelloServlet?targa=<%= annuncio.getTarga() %>" class="annuncio-link">
									<label class="annuncioTitolo"><%= annuncio.getTitolo() %></label>
								   	<label class="annuncioLabel"><%= annuncio.getCitta() %></label>
								    <label class="annuncioPrezzo"><%= (int) annuncio.getPrezzo() %>€</label>
							    </a>
							</div>
						</li>
		
					<% } %>
				
			<% } else { %>
				<h1>Il tuo carrello è vuoto</h1>
			<% } %>
		</div>
	</body>
</html>