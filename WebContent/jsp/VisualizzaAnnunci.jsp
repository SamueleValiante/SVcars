<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.AnnuncioBean" %>
<%@ page import="java.util.List" %>

<html>		
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/visualizzaAnnunci.css">
	</head>
	
	<body>
		<div id="risultati" class="pannelloCentrale">

			<% 
				List<AnnuncioBean> listaAnnunci = (List<AnnuncioBean>) request.getAttribute("annunciDB"); 
				if (listaAnnunci != null && !listaAnnunci.isEmpty()) 
				{
			%>
				<!-- Mostra annuncio dell elenco -->
				<h1>Offerta del giorno!</h1>
				<a href="/SVcars/VisualizzaAnnuncioServlet?targa=<%= listaAnnunci.get(listaAnnunci.size()-1).getTarga() %>" class="annuncio-link">
					<div class="offerta">
						<img class="offerta" src="images/<%= listaAnnunci.get(listaAnnunci.size()-1).getTarga() %>.jpg" alt="Annuncio">
						<div class="dati-offerta">
						    <label class="offertaTitolo"><%= listaAnnunci.get(listaAnnunci.size()-1).getTitolo() %></label>
						    <label class="offertaLabel">Marca: <%=" "+ listaAnnunci.get(listaAnnunci.size()-1).getMarca() %></label>
						    <label class="offertaLabel">Modello: <%=" "+ listaAnnunci.get(listaAnnunci.size()-1).getModello() %></label>
						    <label class="offertaLabel">Km percorsi: <%=" "+ listaAnnunci.get(listaAnnunci.size()-1).getKm() %> Km</label>
						    <label class="offertaLabel">Città: <%=" "+ listaAnnunci.get(listaAnnunci.size()-1).getCitta() %></label>
						    <label class="offertaPrezzo"><%= (int) listaAnnunci.get(listaAnnunci.size()-1).getPrezzo() %>€</label>
						</div>
					</div>
				</a>
				<h2>Annunci che ti potrebbero interessare</h2>
				
				<ul>
					<% for (AnnuncioBean annuncio : listaAnnunci) {
						String targa = annuncio.getTarga();
					%>
						<li>
							
							<div class="annuncio">
								<img src="${pageContext.request.contextPath}/images/<%= targa %>.jpg" alt="Annuncio">
								<a href="/SVcars/VisualizzaAnnuncioServlet?targa=<%= annuncio.getTarga() %>" class="annuncio-link">
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
	</body>
</html>
		