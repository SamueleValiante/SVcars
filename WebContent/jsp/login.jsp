<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>SVcars Login Page</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
	</head>
	
	<body>
		<jsp:include page="HeaderGuestOther.jsp"></jsp:include>
		
		
		<div class=pannelloLogin>
			<div id=quadratoLogin>
				<h2>Effettua Login</h2>
				<form id="loginForm" action="/SVcars/EffettuaLoginServlet" method="post">
					<input type="text" id="email" name="email" class="input" placeholder="Inserisci Email">
					<input type="password" id="pwd" name="pwd" class="input" placeholder="Inserisci Password">
					<div id="globalError" class="error"></div>
					<input id="submitButton" type="submit" value="Login">
				</form>
			</div>
		</div>
		
		<jsp:include page="Footer.jsp"></jsp:include>
		
		<script src="${pageContext.request.contextPath}/scripts/validaLogin.js"></script>
	</body>

</html>