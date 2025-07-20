<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>SVcars Registration Page</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registrazione.css">
	</head>
	
	<body>
		<jsp:include page="HeaderGuestOther.jsp"></jsp:include>
		
		
		<div class=pannelloLogin>
			<div id=quadratoLogin>
				<h2>Registrati</h2>
				<form id="loginForm" action="/SVcars/CreaNuovoUtenteServlet" method="post">
					<input type="text" id="email" name="email" class="input" placeholder="Inserisci Email">
					<input type="text" id="nome" name="nome" class="input" placeholder="Inserisci Nome">
					<input type="text" id="cognome" name="cognome" class="input" placeholder="Inserisci Cognome">
					<input type="text" id="citta" name="citta" class="input" placeholder="Inserisci Città">
					<input type="text" id="via" name="via" class="input" placeholder="Inserisci Via">
					<input type="text" id="cap" name="cap" class="input" placeholder="Inserisci Cap">
					<input type="password" id="pwd" name="pwd" class="input" placeholder="Inserisci Password">
					<div id="globalError" class="error"></div>
					<input id="submitButton" type="submit" value="Registrati">
				</form>
			</div>
		</div>
		
		<jsp:include page="Footer.jsp"></jsp:include>
		
		<script src="${pageContext.request.contextPath}/scripts/validaReigistrazione.js"></script>
	</body>

</html>