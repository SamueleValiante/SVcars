<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.AnnuncioBean" %>
<%@ page import="java.util.List" %>
<div class="pannelloCentrale">
	<% 
		List<AnnuncioBean> listaAnnunci = (List<AnnuncioBean>) request.getAttribute("annunciDB"); 
		if (listaAnnunci != null && !listaAnnunci.isEmpty()) {
	%>
		<!-- Mostra annuncio centrale dell elenco -->
		<h1>Offerta del giorno!</h1>
		<img src="images/<%= listaAnnunci.get(listaAnnunci.size() / 2) %>.png" alt="Annuncio">
		<label><%listaAnnunci.get(listaAnnunci.size() / 2).getTitolo(); %></label>
		<label><%listaAnnunci.get(listaAnnunci.size() / 2).getPrezzo(); %></label>
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
		<h1>Nessun Annuncio trovato!</h1>
	<% } %>
</div>