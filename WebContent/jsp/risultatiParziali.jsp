<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
	<div id="risultatiDiv">
		<div class="pannelloCentrale">
			<% 
				List<AnnuncioBean> listaAnnunci = (List<AnnuncioBean>) request.getAttribute("annunciCercati"); 
				if (listaAnnunci != null && !listaAnnunci.isEmpty()) 
				{
			%>
				
				<ul>
					<% for (AnnuncioBean annuncio : listaAnnunci) {
							String targa = annuncio.getTarga();
					%>
						<li>
							<div class="annuncio">
								<img src="${pageContext.request.contextPath}/images/<%= targa %>.jpg" alt="Annuncio">
								<label class="annuncioTitolo"><%out.print(annuncio.getTitolo()); %></label>
								<label class="annuncioLabel"><%=" "+ annuncio.getCitta() %></label>
								<label class="annuncioPrezzo"><%= (int) annuncio.getPrezzo() %>€</label>
							</div>
						</li>
					<% } %>
				</ul>
			<% } else { 
			%>
				<h1>Nessun Annuncio trovato!</h1>
			<% } %>
		</div>
	</div>
	
	<script src="${pageContext.request.contextPath}/scripts/validazioneBarra.js"></script>
</html>