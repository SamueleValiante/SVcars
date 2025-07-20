<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>SVcars Creazione ordine</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/effettuaOrdine.css">
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
			
			<div id=quadratoOrdine>
				<h2>Crea nuovo ordine</h2>
				
				<form id="annuncioForm" action="${pageContext.request.contextPath}/EffettuaOrdineServlet" method="post">
					<div class="rigaDettaglio"><label class="Label2">Indirizzo Sorgente: </label><label class="Label">Viale del bosco, 5, Milano</label></div> 
				
					<div class="rigaDettaglio"><label class="Label2">Indirizzo Destinazione: </label></div>
					<input type="text" id="indirizzoDe" name="indirizzo" class="input" placeholder="Inserisci Indirizzo">
					
					<div class="rigaDettaglio"><label class="Label2">Titolare carta: </label></div>
					<input type="text" id="nomeCognome" name="nome" class="input" placeholder="Inserisci Nome e cognome">
					
					<div class="rigaDettaglio"><label class="Label2">Numero carta: </label></div>
					<input type="text" id="Ncarta" name="Ncarta" class="input" placeholder="Inserisci Numero carta">
					
					<div class="rigaDettaglio"><label class="Label2">Scadenza carta: </label></div>
					<input type="date" id="" name="dataScad" class="input">
					
					<% Double totaleAttr = (Double) request.getAttribute("totale");
  					   double totaleOrdine = totaleAttr != null ? totaleAttr + 100 : 0;
					%>
					<input type="hidden" name="totale" value="<%= totaleOrdine %>">
					
					<div class="rigaDettaglio"><label class="Label2">CVV carta: </label></div>
					<input type="text" id="CVV" name="CVV" class="input" placeholder="Inserisci CVV">
					
					<div class="rigaDettaglio"><label class="Label2">Data acquisto: </label><label class="Label"><%=LocalDate.now() %></label></div> 
					
					<div class="rigaDettaglio"><label class="Label2">Data arrivo stimata: </label><label class="Label"><%=LocalDate.now().plus(7, ChronoUnit.DAYS) %></label></div> 
					
					<div class="rigaDettaglio"><label class="Label2">Costo prodotti: </label><label class="Label"><%=Integer.valueOf(((Double)request.getAttribute("totale")).intValue()) %>€</label></div> 
					
					<div class="rigaDettaglio"><label class="Label2">Costo spedizione: </label><label class="Label">100 €</label></div> 
					
					<div class="rigaDettaglio"><label class="totaleLabel">Totale: </label><label class="Labeltotale"> <%= Integer.valueOf(((Double)request.getAttribute("totale")).intValue()) + 100%> €</label></div>
				
					<% request.setAttribute("totale", Integer.valueOf(((Double)request.getAttribute("totale")).intValue()) + 100); %>
					
					<input id="submitButton" type="submit" value="Effettua ordine">
				</form>
				
				<div id="erroreForm" style="color: red; margin-top: 15px; font-weight: bold;"></div>
			</div>
		
		<jsp:include page="Footer.jsp"></jsp:include>
		
		<script src="${pageContext.request.contextPath}/scripts/validaOrdine.js"></script>
	</body>

</html>