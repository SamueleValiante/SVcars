<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Annunci creati</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/carrello.css">
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
		
		<div class="pannelloCarrello">
			<% 
				List<AnnuncioBean> listaAnnunci = new AnnuncioDAO().doRetrieveAll("targa");
				List<AnnuncioBean> listaAnnunci2 = new ArrayList<>();
			
			    HttpSession session2 = request.getSession();
			    UtenteIscrittoBean user = (UtenteIscrittoBean) session2.getAttribute("utente");
			    String email = user.getEmail();
			    
			    for(AnnuncioBean annuncio: listaAnnunci)
			    {
			    	if(annuncio.getEmail().equals(email))
			    		listaAnnunci2.add(annuncio);
			    }
			  
				
			    if (listaAnnunci2 != null && !listaAnnunci2.isEmpty()) {
			%>
				<h2>I tuoi annunci creati</h2>
				
				<ul>
					<% for (AnnuncioBean annuncio : listaAnnunci2) {
						
							String targa = annuncio.getTarga();
					%>
							<li>
										
								<div class="annuncio">
									<img src="${pageContext.request.contextPath}/images/<%= targa %>.jpg" alt="Annuncio">
									<a href="/SVcars/VisualizzaAnnuncioCreatoServlet?targa=<%= annuncio.getTarga() %>" class="annuncio-link">
										<label class="annuncioTitolo"><%= annuncio.getTitolo() %></label>
									   	<label class="annuncioLabel"><%= annuncio.getCitta() %></label>
									    <label class="annuncioPrezzo"><%= (int) annuncio.getPrezzo() %>€</label>
								    </a>
								</div>
							</li>
		
						<% } %>
				
			<% } else { %>
				<h1>Non hai creato annunci finora!</h1>
			<% } %>
		</div>
		
		<jsp:include page="Footer.jsp"></jsp:include>
		
		<script src="${pageContext.request.contextPath}/scripts/validazioneBarra.js"></script>
		
	</body>

</html>