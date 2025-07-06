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
			<jsp:include page="HeaderAmministratore.jsp"></jsp:include>
			
		<%} else if(tipoUtente.equals("Utente_normale")) {%>
			<jsp:include page="HeaderRegistrato.jsp"></jsp:include>
			
		<% }else {%>
			<jsp:include page="HeaderGuest.jsp"></jsp:include>
		<% } %>
			
			<div id=quadratoAnnuncio>
				<h2>Crea nuovo annuncio</h2>
				
				<form id="annuncioForm" action="/SVcars/CreaAnnuncioServlet" method="get" enctype="multipart/form-data">
  					<label id="annuncioLabel" for="immagine">Seleziona immagine annuncio:</label><br>
  					<input type="file" id="immagine" name="immagine" accept="image/*"><br><br>
				
					<input type="text" id="targa" name="targa" class="input" placeholder="Inserisci Targa">
					<input type="text" id="titolo" name="titolo" class="input" placeholder="Inserisci Titolo">
					 
					<textarea id="descrizione" name="descrizione" rows="6" cols="40" placeholder="Inserisci qui il tuo testo..."></textarea><br>
					
					<div class="select">
						<select name="marcaAuto" class="selectF">
					  		<option value="" selected disabled>-- Seleziona la marca --</option>
					  		<option value="audi">Audi</option>
					  		<option value="bmw">BMW</option>
					  		<option value="fiat">Fiat</option>
					  		<option value="ford">Ford</option>
					  		<option value="mercedes">Mercedes</option>
					  		<option value="peugeot">Peugeot</option>
					  		<option value="renault">Renault</option>
					 		<option value="toyota">Toyota</option>
					 		<option value="volkswagen">Volkswagen</option>
						</select>
					</div>
					
					<div class="select">
						<select name="modelloAuto" class="selectF">
							 <option value="" selected disabled>-- Seleziona un modello --</option>
							 <option value="a1">A1</option>
							 <option value="a3">A3</option>
							 <option value="a4">A4</option>
							 <option value="a6">A6</option>
							 <option value="q2">Q2</option>
							 <option value="q5">Q5</option>
							 <option value="q7">Q7</option>
							 <option value="tt">TT</option>
							 <option value="e-tron">e-tron</option>
						</select>
					</div>
					
					<input type="text" id="prezzo" name="prezzo" class="input" placeholder="Inserisci Prezzo...">
					
					<input type="text" id="km" name="km" class="input" placeholder="Inserisci Km...">
					
					<input type="text" id="anno" name="anno" class="input" placeholder="Inserisci anno...">
					
					<div class="select">
						<select name="carburante" class="selectF">
					  		<option value="" selected disabled>-- Seleziona Carburante --</option>
					  		<option value="benzina">Benzina</option>
					  		<option value="diesel">Diesel</option>
					  		<option value="ibrida">Ibrida</option>
					  		<option value="elettrica">Elettrica</option>
					  		<option value="gpl">GPL</option>
						</select>
					</div>
					
					<div class="select">
					  <select name="tipologiaAuto" class="selectF">
					    <option value="" selected>-- Seleziona Tipologia --</option>
					    <option value="suv">SUV</option>
					    <option value="berlina">Berlina</option>
					    <option value="citycar">City Car</option>
					    <option value="stationwagon">Station Wagon</option>
					    <option value="cabrio">Cabrio</option>
					    <option value="coupe">Coupé</option>
					    <option value="monovolume">Monovolume</option>
					    <option value="pickup">Pick-up</option>
					  </select>
					</div>
					
					<div class="select">
					  <select name="coloreAuto" class="selectF">
					    <option value="" selected disabled>-- Seleziona Colore --</option>
					    <option value="nero">Nero</option>
					    <option value="bianco">Bianco</option>
					    <option value="grigio">Grigio</option>
					    <option value="argento">Argento</option>
					    <option value="blu">Blu</option>
					    <option value="rosso">Rosso</option>
					    <option value="verde">Verde</option>
					    <option value="giallo">Giallo</option>
					    <option value="marrone">Marrone</option>
					    <option value="arancione">Arancione</option>
					    <option value="oro">Oro</option>
					    <option value="viola">Viola</option>
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
		
		<script src="${pageContext.request.contextPath}/scripts/validaAnnuncio.js"></script>
	</body>

</html>