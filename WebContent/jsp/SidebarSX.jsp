<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/PannelloSidebar.css">
	</head>
	
	<body class="sidebar">
	
		<section class="filtri">
			<form class="filtri" id="filtriForm" action="/SVcars/EffettuaRicercaServlet" method="get">
				
				<div id="labelMain">
					<h2>Ricerca avanzata</h2>
				</div>
				
				<div class="filtro">
					<label class="filtroLabel" for="barraRicerca">Cerca titolo:</label>
					<input type="text" name="barraRicerca" id="barraRicercaSidebar" placeholder="Cerca...">
				</div>
				
				<div class="filtro">
					<label class="filtroLabel" for="marcaAuto">Seleziona una marca:</label>
					<select name="marcaAuto" class="selectF">
				  		<option value="" selected>-- Seleziona --</option>
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
				
				<!-- Selezione modello -->
				<div class="filtro">
					<label class="filtroLabel" for="modelloAuto">Seleziona un modello:</label>
					<select name="modelloAuto" class="selectF">
						 <option value="" selected>-- Seleziona --</option>
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
				
				<!-- Selezione prezzo -->
				<div class="filtro">
				  <label class="filtroLabel" for="prezzoMin">Prezzo min(€):</label>
				  <select name="prezzoMin" class="selectF">
				  	<option value="" selected>-- Seleziona --</option>
				  	<option value=0>0</option>
				  	<option value=500>500</option>
				  	<option value=1000>1000</option>
				  	<option value=1500>1500</option>
				  	<option value=2000>2000</option>
				  	<option value=2500>2500</option>
				  	<option value=3000>3000</option>
				  	<option value=4000>4000</option>
				  	<option value=5000>5000</option>
				  	<option value=6000>6000</option>
				  	<option value=8000>8000</option>
				  	<option value=10000>10000</option>
				  	<option value=12500>12500</option>
				  	<option value=15000>15000</option>
				  	<option value=20000>20000</option>
				  	<option value=30000>30000</option>
				  	<option value=40000>40000</option>
				  	<option value=50000>50000</option>
				  	<option value=60000>60000</option>
				  	<option value=80000>80000</option>
				  	<option value=100000>100000</option>
				  </select>
				
				  <label class="filtroLabel" for="prezzoMax">Prezzo max(€):</label>
				  <select name="prezzoMax" class="selectF">
				  	<option value="" selected>-- Seleziona --</option>
				  	<option value=500>500</option>
				  	<option value=1000>1000</option>
				  	<option value=1500>1500</option>
				  	<option value=2000>2000</option>
				  	<option value=2500>2500</option>
				  	<option value=3000>3000</option>
				  	<option value=4000>4000</option>
				  	<option value=5000>5000</option>
				  	<option value=6000>6000</option>
				  	<option value=8000>8000</option>
				  	<option value=10000>10000</option>
				  	<option value=12500>12500</option>
				  	<option value=15000>15000</option>
				  	<option value=20000>20000</option>
				  	<option value=30000>30000</option>
				  	<option value=40000>40000</option>
				  	<option value=50000>50000</option>
				  	<option value=60000>60000</option>
				  	<option value=80000>80000</option>
				  	<option value=100000>100000</option>
				  	<option value=125000>125000</option>
				  	<option value=150000>150000</option>
				  	<option value=200000>200000</option>
				  </select>
				</div>
				
				<!-- Selezione km -->
				<div class="filtro">
				  <label class="filtroLabel" for="KmMin">Chilometri min(Km):</label>
				  <select name="KmMin" class="selectF">
				  	<option value="" selected>-- Seleziona --</option>
				  	<option value=10000>10000</option>
				  	<option value=30000>30000</option>
				  	<option value=50000>50000</option>
				  	<option value=75000>75000</option>
				  	<option value=100000>100000</option>
				  	<option value=150000>150000</option>
				  	<option value=200000>200000</option>
				  	<option value=250000>250000</option>
				  	<option value=300000>300000</option>
				  	<option value=350000>350000</option>
				  	<option value=400000>400000</option>
				  </select>
				
				  <label class="filtroLabel" for="KmMax">Chilometri max(Km):</label>
				  <select name="KmMax" class="selectF"">
				    <option value="" selected>-- Seleziona --</option>
				    <option value=10000>10000</option>
				  	<option value=30000>30000</option>
				  	<option value=50000>50000</option>
				  	<option value=75000>75000</option>
				  	<option value=100000>100000</option>
				  	<option value=150000>150000</option>
				  	<option value=200000>200000</option>
				  	<option value=250000>250000</option>
				  	<option value=300000>300000</option>
				  	<option value=350000>350000</option>
				  	<option value=400000>400000</option>
				  	<option value=500000>500000</option>
				  </select>
				</div>
				
				<!-- Selezione anno -->
				<div class="filtro">
				  <label class="filtroLabel" for="annoMin">Anno minimo:</label>
				  <select name="annoMin" class="selectF">
				    <option value="" selected>-- Seleziona --</option>
				    <option value="1980">1980</option>
				    <option value="1990">1990</option>
				    <option value="2000">2000</option>
				    <option value="2005">2005</option>
				    <option value="2010">2010</option>
				    <option value="2015">2015</option>
				    <option value="2018">2018</option>
				    <option value="2020">2020</option>
				    <option value="2022">2022</option>
				    <option value="2023">2023</option>
				    <option value="2024">2024</option>
				    <option value="2025">2025</option>
				  </select>
				
				  <label class="filtroLabel" for="annoMax">Anno massimo:</label>
				  <select name="annoMax" class="selectF">
				    <option value="" selected>-- Seleziona --</option>
				    <option value="1980">1980</option>
				    <option value="1990">1990</option>
				    <option value="2000">2000</option>
				    <option value="2005">2005</option>
				    <option value="2010">2010</option>
			    	<option value="2015">2015</option>
				    <option value="2018">2018</option>
				    <option value="2020">2020</option>
				    <option value="2022">2022</option>
				    <option value="2023">2023</option>
				    <option value="2024">2024</option>
				    <option value="2025">2025</option>
			      </select>
				</div>
				
				<div class="filtro">
				  <label class="filtroLabel" for="coloreAuto">Colore:</label>
				  <select name="coloreAuto" class="selectF">
				    <option value="" selected>-- Seleziona --</option>
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
				
				<div class="filtro">
				  <label class="filtroLabel" for="tipologiaAuto">Tipologia:</label>
				  <select name="tipologiaAuto" class="selectF">
				    <option value="" selected>-- Seleziona --</option>
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
				
				<div id="submitDiv">
					<input id="submitButton" type="submit" value="Cerca">
				</div>
				
			</form>
		</section>
		<script src="scripts/validazioneForm.js"></script>
	</body>
</html>