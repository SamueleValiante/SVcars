<%@page import="model.UtenteIscrittoBean"%>
<%@page import="model.AnnuncioBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>SVcars Annuncio</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mostraAnnuncio.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/PannelloSidebar.css">
	</head>
	
	<body>
		<% String tipoUtente = request.getAttribute("tipoUtente").toString(); 
		   UtenteIscrittoBean user = session.
		%>
	
		<% if(tipoUtente.equals("Amministratore")) {%>
			<jsp:include page="HeaderAmministratoreOther.jsp"></jsp:include>
			
		<%} else if(tipoUtente.equals("Utente_normale")) {%>
			<jsp:include page="HeaderRegistratoOther.jsp"></jsp:include>
			
		<% }else {%>
			<jsp:include page="HeaderGuestOther.jsp"></jsp:include>
		<% } %>
	
		<div class=pannelloVerde>
			<% AnnuncioBean annuncio = (AnnuncioBean) request.getAttribute("annuncio"); %>
			
			<img id="immagineAnnuncio" src="images/<%= annuncio.getTarga() %>.jpg" alt="Annuncio">
			<h1><%= annuncio.getTitolo() %></h1>
			<div id="marca">
				<label class="Label2">Marca:</label><label class="Label"> <%= annuncio.getMarca() %></label> <br>
				<label class="Label2">Modello:</label><label class="Label"> <%=" "+ annuncio.getModello() %></label>
			</div>
			
			<h2>Descrizione:</h2>
			<div id="descrizione">
				<label class="Label"><%=" "+ annuncio.getDescrizione() %></label>
			</div>
			
			<h2>Dettagli tecnici: </h2>
			<div id="dettagli">
				 <div class="rigaDettaglio"><label class="Label2">Km percorsi:</label><label class="Label"> <%=" "+ annuncio.getKm() %>Km</label></div> 
				 <div class="rigaDettaglio"><label class="Label2">Cilindrata:</label><label class="Label"> <%=" "+ annuncio.getCilindrata() %></label></div> 
				 <div class="rigaDettaglio"><label class="Label2">Anno immatricolazione:</label><label class="Label"> <%=" "+ annuncio.getAnno() %></label></div> 
				 <div class="rigaDettaglio"><label class="Label2">Tipo carburante:</label><label class="Label"> <%=" "+ annuncio.getCarburante() %></label></div> 
				 <div class="rigaDettaglio"><label class="Label2">Colore:</label><label class="Label"> <%=" "+ annuncio.getColore() %></label></div> 
				 <div class="rigaDettaglio"><label class="Label2">Tipologia:</label><label class="Label"> <%=" "+ annuncio.getTipologia() %></label></div>
				 <div class="rigaDettaglio"><label class="Label2">Città:</label><label class="Label"> <%=" "+ annuncio.getCitta() %></label></div>
				 <div class="rigaDettaglio"><label class="Label2">Numero porte:</label><label class="Label"> <%=" "+ annuncio.getN_porte() %></label></div>
			</div>
			
			<div id="azioni">
				<form action="/SVcars/AggiungiAnnuncioCarrelloServlet?targa=<%= annuncio.getTarga() %>"" method="get">
					<input class="in" type="submit" value="Aggiungi al carrello">
				</form>
				
				<form action="" method="get">
					<input class="in" type="submit" value="Acquista Ora">
				</form>
			</div>
			
		</div>
		
		<jsp:include page="Footer.jsp"></jsp:include>
	</body>

</html>