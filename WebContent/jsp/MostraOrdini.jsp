<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>I tuoi ordini</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mostraOrdini.css">
</head>
<body>
    <div class="containerOrdini">
        <h2>Storico Ordini</h2>

        <%
            HttpSession sessione = request.getSession();
            UtenteIscrittoBean utente = (UtenteIscrittoBean) sessione.getAttribute("utente");
            OrdineDAO dao = new OrdineDAO();
            List<String> codiciOrdine = dao.getCodiciTuttiOrdini(utente.getEmail());

            if (codiciOrdine != null && !codiciOrdine.isEmpty()) {
                for (String codice : codiciOrdine) {
                    OrdineBean ordine = dao.doRetrieveByKey(codice);
        %>
                    <div class="ordineCard">
                        <p><strong>Codice Ordine:</strong> <%= ordine.getCodice_ordine() %></p>
                        <p><strong>Codice Fattura:</strong> <%= ordine.getCodiceFattura() %></p>
                        <p><strong>Email Compratore:</strong> <%= ordine.getEmail_compratore() %></p>
                        <p><strong>Indirizzo Sorgente:</strong> <%= ordine.getIndirizzo_origine() %></p>
                        <p><strong>Indirizzo Destinazione:</strong> <%= ordine.getIndirizzo_destinazione() %></p>
                        <p><strong>Data Acquisto:</strong> <%= ordine.getDataAcquisto() %></p>
                        <p><strong>Tempo Spedizione:</strong> <%= ordine.getTempo_spedizione() %></p>
                        <p><strong>Costo Prodotti:</strong> <%= ordine.getCosto_prodotti() %> €</p>
                        <p><strong>Costo Spedizione:</strong> <%= ordine.getCosto_spedizione() %> €</p>
                        <p><strong>Totale:</strong> <%= ordine.getTotale() %> €</p>

                        <form action="${pageContext.request.contextPath}/VisualizzaProdottiOrdineServlet" method="get">
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
</body>
</html>