<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>SVcars Creazione annuncio</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/creaAnnuncio.css">
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
			
			<div id=quadratoAnnuncio>
				<h2>Crea nuovo annuncio</h2>
				
				<form id="annuncioForm" action="/SVcars/CreaAnnuncioServlet" method="post" enctype="multipart/form-data">
  					<label id="annuncioLabel" for="immagine">Seleziona immagine annuncio:</label><br>
  					<input type="file" id="immagine" name="immagine" accept="image/*"><br><br>
				
					<input type="text" id="targa" name="targa" class="input" placeholder="Inserisci Targa">
					<input type="text" id="titolo" name="titolo" class="input" placeholder="Inserisci Titolo">
					 
					<textarea id="descrizione" name="descrizione" rows="6" cols="40" placeholder="Inserisci qui il tuo testo..."></textarea><br>
					
					<div class="select">
						<select name="marcaAuto" class="selectF">
					  		<option value="" selected disabled>-- Seleziona la marca --</option>
					  		<option value="Audi">Audi</option>
					  		<option value="Bmw">BMW</option>
					  		<option value="Fiat">Fiat</option>
					  		<option value="Ford">Ford</option>
					  		<option value="Mercedes">Mercedes</option>
					  		<option value="Peugeot">Peugeot</option>
					  		<option value="Renault">Renault</option>
					 		<option value="Toyota">Toyota</option>
					 		<option value="Volkswagen">Volkswagen</option>
						</select>
					</div>
					
					<div class="select">
						<select name="modelloAuto" class="selectF">
							 <option value="" selected disabled>-- Seleziona un modello --</option>
							 <option value="A1">A1</option>
							 <option value="A3">A3</option>
							 <option value="A4">A4</option>
							 <option value="A6">A6</option>
							 <option value="Q2">Q2</option>
							 <option value="Q5">Q5</option>
							 <option value="Q7">Q7</option>
							 <option value="TT">TT</option>
							 <option value="E-Tron">e-tron</option>
						</select>
					</div>
					
					<input type="text" id="prezzo" name="prezzo" class="input" placeholder="Inserisci Prezzo...">
					
					<input type="text" id="km" name="km" class="input" placeholder="Inserisci Km...">
					
					<input type="text" id="anno" name="anno" class="input" placeholder="Inserisci anno...">
					
					<div class="select">
						<select name="carburante" class="selectF">
					  		<option value="" selected disabled>-- Seleziona Carburante --</option>
					  		<option value="Benzina">Benzina</option>
					  		<option value="Diesel">Diesel</option>
					  		<option value="Ibrida">Ibrida</option>
					  		<option value="Elettrica">Elettrica</option>
					  		<option value="Gpl">GPL</option>
						</select>
					</div>
					
					<div class="select">
					  <select name="tipologiaAuto" class="selectF">
					    <option value="" selected>-- Seleziona Tipologia --</option>
					    <option value="SUV">SUV</option>
					    <option value="Berlina">Berlina</option>
					    <option value="CityCar">City Car</option>
					    <option value="StationWagon">Station Wagon</option>
					    <option value="Cabrio">Cabrio</option>
					    <option value="Coupe">Coupé</option>
					    <option value="Monovolume">Monovolume</option>
					    <option value="Pick-up">Pick-up</option>
					  </select>
					</div>
					
					<div class="select">
					  <select name="coloreAuto" class="selectF">
					    <option value="" selected disabled>-- Seleziona Colore --</option>
					    <option value="Nero">Nero</option>
					    <option value="Bianco">Bianco</option>
					    <option value="Grigio">Grigio</option>
					    <option value="Argento">Argento</option>
					    <option value="Blu">Blu</option>
					    <option value="Rosso">Rosso</option>
					    <option value="Verde">Verde</option>
					    <option value="Giallo">Giallo</option>
					    <option value="Marrone">Marrone</option>
					    <option value="Arancione">Arancione</option>
					    <option value="Oro">Oro</option>
					    <option value="Viola">Viola</option>
					  </select>
					</div>
					
					<input type="text" id="cilindrata" name="cilindrata" class="input" placeholder="Inserisci Cilindrata...">
					
					<div class="select">
					  <select name="n_porte" class="selectF">
					    <option value="" selected disabled>-- Seleziona numero porte --</option>
					    <option value=2>Due</option>
					    <option value=3>Tre</option>
					    <option value=4>Quattro</option>
					    <option value=5>Cinque</option>
					  </select>
					</div>
					
					<input type="text" id="citta" name="citta" class="input" placeholder="Inserisci Città">
					
					<input id="submitButton" type="submit" value="Crea annuncio">
				</form>
				
				<div id="erroreForm" style="color: red; margin-top: 15px; font-weight: bold;"></div>
			</div>
		
		<jsp:include page="Footer.jsp"></jsp:include>
		
		<script src="${pageContext.request.contextPath}/scripts/modelliDinamicamenteAnnuncio.js"></script>
		<script src="${pageContext.request.contextPath}/scripts/validaAnnuncio.js"></script>
		<script src="${pageContext.request.contextPath}/scripts/validazioneBarra.js"></script>
	</body>

</html>