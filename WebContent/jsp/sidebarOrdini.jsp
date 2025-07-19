<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/PannelloSidebar.css">
	</head>
	
	<body class="sidebar">
	
		<section class="filtri">
			<form class="filtri" id="filtriForm" action="/SVcars/EffettuaRicercaOrdiniServlet" method="get">
				
				<div id="labelMain">
					<h2>Ricerca Ordini</h2>
				</div>
				
				<div class="filtro">
					<label class="filtroLabel" for="barraRicerca">Cerca per email:</label>
					<input type="text" name="barraRicercaFiltro" id="barraRicercaSidebar" placeholder="Cerca...">
				</div>
				
				
				<div class="filtro">
				  <label class="filtroLabel" for="DataMin">Data da:</label>
				  <input type="date" name="dataMin">
				
				  <label class="filtroLabel" for="DataMax">Data fino a:</label>
				  <input type="date" name="dataMax">
				  
				</div>
				
				<div id="submitDiv">
					<input id="submitButton" type="submit" value="Cerca">
				</div>
				
			</form>
		</section>
		<script src="${pageContext.request.contextPath}/scripts/validazioneFormOrdini.js"></script>
	</body>
</html>