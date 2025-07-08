<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>SVcars Ricerca</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/PannelloSidebar.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/visualizzaAnnunci.css">
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
		
		<div class="contenitorePagina">
			<jsp:include page="SidebarSX.jsp" />
			
			<div id="risultati">
				<div class="pannelloCentrale">
					<% 
						List<AnnuncioBean> listaAnnunci = (List<AnnuncioBean>) request.getAttribute("annunciCercati"); 
						if (listaAnnunci != null && !listaAnnunci.isEmpty()) 
						{
					%>
					
						<ul>
							<% for (AnnuncioBean annuncio : listaAnnunci) {
								String targa = annuncio.getTarga();
							%>
								<li>
									<div class="annuncio">
										<img src="${pageContext.request.contextPath}/images/<%= targa %>.jpg" alt="Annuncio">
										<label class="annuncioTitolo"><%out.print(annuncio.getTitolo()); %></label>
										<label class="annuncioLabel"><%=" "+ annuncio.getCitta() %></label>
										<label class="annuncioPrezzo"><%= (int) annuncio.getPrezzo() %>€</label>
									</div>
								</li>
							<% } %>
						</ul>
						<% } else { 
						%>
						<h1>Nessun Annuncio trovato!</h1>
					<% } %>
				</div>
			</div>
		</div>
		
		<jsp:include page="Footer.jsp"></jsp:include>
		
		<script src="${pageContext.request.contextPath}/scripts/validazioneBarra.js"></script>
		
	</body>

</html>