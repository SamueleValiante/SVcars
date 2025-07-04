<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.AnnuncioBean" %>
<%@ page import="java.util.List" %>
<div class="pannelloCentrale">
	<% 
		List<AnnuncioBean> listaAnnunci = (List<AnnuncioBean>) request.getAttribute("annunciDB"); 
		if (listaAnnunci != null && !listaAnnunci.isEmpty()) {
	%>
		<ul>
			<% for (AnnuncioBean annuncio : listaAnnunci) {
				String targa = annuncio.getTarga();
			%>
				<li><img src="images/<%= targa %>.png" alt="Annuncio"></li>
			<% } %>
		</ul>
	<% } else { %>
		<h1>Nessun Annuncio disponibile!</h1>
	<% } %>
</div>