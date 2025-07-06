<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Header.css">
	</head>

	<body>
		<header>
			<form id="carrello" action="${pageContext.request.contextPath}/index.jsp" method="get">
				<input id="carrelloIn" type="image" src="${pageContext.request.contextPath}/images/home.png" width=50 height=50 alt="Carrello" align="left"/>
			</form>
			
			<form id="barraForm" action="/SVcars/EffettuaRicercaServlet" method="get">
				<p id="logo"> SVcars </p>
				<div id="barraecerca">
  					<input type="text" name="barraRicerca" id="barraRicercaInput" placeholder="Cerca...">
  					<input id="cerca" type="image" src="${pageContext.request.contextPath}/images/cerca.png" width=40 height=30 alt="Invia" />
				</div>
			</form>
			
			<form id="menu" action="">
				<div id="menu-container">
					<input id="menuIn" type="image" src="${pageContext.request.contextPath}/images/opzioni.jpg" width="40" height="40" alt="Opzioni" />
				
					<ul id="dropdown-menu" class="hidden">
				  		<li><a href="${pageContext.request.contextPath}/jsp/login.jsp">Login</a></li>
				    	<li><a href="${pageContext.request.contextPath}/jsp/Registrazione.jsp">Registrazione</a></li>
			
					</ul>
				</div>
			</form>
		
			<script src="${pageContext.request.contextPath}/scripts/menuTendina.js" defer></script>
		
		</header>
	</body>

</html>