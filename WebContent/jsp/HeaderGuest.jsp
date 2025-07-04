<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/Header.css">
	</head>

	<body>
		<header>
			<p id="logo"> SVcars </p>
			<img id="menu" src="images/opzioni.jpg" width=40 height=40 alt="Menu" align="right">
			
			<img id="carrello" src="images/carrello.png" width=50 height=50 alt="Carrello" align="left">
			
			<form action="control/EffettuaRicerca">
				
				<input type="text" name="barraRicerca" value="" placeholder="Cerca...">
				<img id="cerca" src="images/cerca.png" width=40 height=30 alt="Cerca">
			</form>
		
		</header>
	</body>

</html>