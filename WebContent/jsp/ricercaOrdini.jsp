<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>SVcars HomePage</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/PannelloSidebar.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mostraOrdini.css">
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
		
		
			<div class="layout-container">
	        	<jsp:include page="sidebarOrdini.jsp" />
	        	
	        	<div class="containerOrdini">
			        <h2>Pannello gestione ordini</h2>
			
			        <%
			            HttpSession sessione = request.getSession();
			            UtenteIscrittoBean utente = (UtenteIscrittoBean) sessione.getAttribute("utente");
			            OrdineDAO dao = new OrdineDAO();
			            List<OrdineBean> ordini = (List<OrdineBean>) request.getAttribute("ordiniCercati");
			
			            if (ordini != null && !ordini.isEmpty()) {
			                for (OrdineBean ordine : ordini) {
			        %>
			                    <div class="ordineCard">
			                        <p><strong>Email Compratore:</strong> <%= ordine.getEmail_compratore() %></p>
			                        <p><strong>Indirizzo Sorgente:</strong> <%= ordine.getIndirizzo_origine() %></p>
			                        <p><strong>Indirizzo Destinazione:</strong> <%= ordine.getIndirizzo_destinazione() %></p>
			                        <p><strong>Data Acquisto:</strong> <%= ordine.getDataAcquisto() %></p>
			                        <p><strong>Tempo Spedizione:</strong> <%= ordine.getTempo_spedizione() %></p>
			                        <p><strong>Costo Prodotti:</strong> <%= (int)ordine.getCosto_prodotti() %> €</p>
			                        <p><strong>Costo Spedizione:</strong> 100 €</p>
			                        <p><strong>Totale:</strong> <%= (int)ordine.getTotale() %> €</p>
			
			                        <form action="${pageContext.request.contextPath}/jsp/VisualizzaProdottiOrdine.jsp" method="get">
			                            <input type="hidden" name="codiceOrdine" value="<%= ordine.getCodice_ordine() %>">
			                            <button class="visualizzaBtn" type="submit">Visualizza Prodotti</button>
			                        </form>
			                    </div>
			        <%
			                }
			            } else {
			        %>
			            <p>Nessun ordine disponibile.</p>
			        <%
			            }
			        %>
			    </div>
			    
	    	</div>
		
		<jsp:include page="Footer.jsp"></jsp:include>
		
		
		<script>const contextPath = "${pageContext.request.contextPath}";</script>
		
		<script src="${pageContext.request.contextPath}/scripts/validazioneBarra.js"></script>
	</body>

</html>