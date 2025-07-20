<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%
    AnnuncioBean annuncio = (AnnuncioBean) new AnnuncioDAO().doRetrieveByKey(request.getParameter("targa"));
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>SVcars Modifica Annuncio</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/creaAnnuncio.css">
  </head>

  <body>
    <div id="quadratoAnnuncio">
      <h2>Modifica annuncio</h2>

      <form id="annuncioForm" action="${pageContext.request.contextPath}/ModificaAnnuncioServlet" method="post">

        <input type="text" name="targa" value="<%= annuncio.getTarga() %>" class="input" readonly>
        <input type="text" name="titolo" value="<%= annuncio.getTitolo() %>" class="input">

        <textarea name="descrizione" rows="6" cols="40"><%= annuncio.getDescrizione() %></textarea><br>

        <select name="marcaAuto" class="selectF">
		  <option value="" disabled>-- Seleziona la marca --</option>
		  <% String marca = annuncio.getMarca(); %>
		  <option value="Audi" <%= "Audi".equalsIgnoreCase(marca) ? "selected" : "" %>>Audi</option>
		  <option value="Bmw" <%= "Bmw".equalsIgnoreCase(marca) ? "selected" : "" %>>BMW</option>
		  <option value="Fiat" <%= "Fiat".equalsIgnoreCase(marca) ? "selected" : "" %>>Fiat</option>
		  <option value="Ford" <%= "Ford".equalsIgnoreCase(marca) ? "selected" : "" %>>Ford</option>
		  <option value="Mercedes" <%= "Mercedes".equalsIgnoreCase(marca) ? "selected" : "" %>>Mercedes</option>
		  <option value="Peugeot" <%= "Peugeot".equalsIgnoreCase(marca) ? "selected" : "" %>>Peugeot</option>
		  <option value="Renault" <%= "Renault".equalsIgnoreCase(marca) ? "selected" : "" %>>Renault</option>
		  <option value="Toyota" <%= "Toyota".equalsIgnoreCase(marca) ? "selected" : "" %>>Toyota</option>
		  <option value="Volkswagen" <%= "Volkswagen".equalsIgnoreCase(marca) ? "selected" : "" %>>Volkswagen</option>
		</select>

        <select name="modelloAuto" class="selectF">
			  <option value="" disabled>-- Seleziona un modello --</option>
			  <% 
			    String modello = annuncio.getModello();
			  %>
			  <!-- Audi -->
			  <option value="A1" <%= "A1".equalsIgnoreCase(modello) ? "selected" : "" %>>A1</option>
			  <option value="A2" <%= "A2".equalsIgnoreCase(modello) ? "selected" : "" %>>A2</option>
			  <option value="A3" <%= "A3".equalsIgnoreCase(modello) ? "selected" : "" %>>A3</option>
			  <option value="A4" <%= "A4".equalsIgnoreCase(modello) ? "selected" : "" %>>A4</option>
			  <option value="A5" <%= "A5".equalsIgnoreCase(modello) ? "selected" : "" %>>A5</option>
			  <option value="A6" <%= "A6".equalsIgnoreCase(modello) ? "selected" : "" %>>A6</option>
			  <option value="Q2" <%= "Q2".equalsIgnoreCase(modello) ? "selected" : "" %>>Q2</option>
			  <option value="Q3" <%= "Q3".equalsIgnoreCase(modello) ? "selected" : "" %>>Q3</option>
			  <option value="Q4" <%= "Q4".equalsIgnoreCase(modello) ? "selected" : "" %>>Q4</option>
			  <option value="Q5" <%= "Q5".equalsIgnoreCase(modello) ? "selected" : "" %>>Q5</option>
			  <option value="Q7" <%= "Q7".equalsIgnoreCase(modello) ? "selected" : "" %>>Q7</option>
			  <option value="TT" <%= "TT".equalsIgnoreCase(modello) ? "selected" : "" %>>TT</option>
			  <option value="e-tron" <%= "e-tron".equalsIgnoreCase(modello) ? "selected" : "" %>>e-tron</option>
			
			  <!-- BMW -->
			  <option value="Serie 1" <%= "Serie 1".equalsIgnoreCase(modello) ? "selected" : "" %>>Serie 1</option>
			  <option value="Serie 3" <%= "Serie 3".equalsIgnoreCase(modello) ? "selected" : "" %>>Serie 3</option>
			  <option value="Serie 5" <%= "Serie 5".equalsIgnoreCase(modello) ? "selected" : "" %>>Serie 5</option>
			  <option value="X1" <%= "X1".equalsIgnoreCase(modello) ? "selected" : "" %>>X1</option>
			  <option value="X3" <%= "X3".equalsIgnoreCase(modello) ? "selected" : "" %>>X3</option>
			  <option value="X5" <%= "X5".equalsIgnoreCase(modello) ? "selected" : "" %>>X5</option>
			  <option value="i3" <%= "i3".equalsIgnoreCase(modello) ? "selected" : "" %>>i3</option>
			
			  <!-- Fiat -->
			  <option value="Panda" <%= "Panda".equalsIgnoreCase(modello) ? "selected" : "" %>>Panda</option>
			  <option value="500" <%= "500".equalsIgnoreCase(modello) ? "selected" : "" %>>500</option>
			  <option value="Tipo" <%= "Tipo".equalsIgnoreCase(modello) ? "selected" : "" %>>Tipo</option>
			  <option value="Punto" <%= "Punto".equalsIgnoreCase(modello) ? "selected" : "" %>>Punto</option>
			  <option value="Uno" <%= "Uno".equalsIgnoreCase(modello) ? "selected" : "" %>>Uno</option>
			
			  <!-- Ford -->
			  <option value="Fiesta" <%= "Fiesta".equalsIgnoreCase(modello) ? "selected" : "" %>>Fiesta</option>
			  <option value="Focus" <%= "Focus".equalsIgnoreCase(modello) ? "selected" : "" %>>Focus</option>
			  <option value="Kuga" <%= "Kuga".equalsIgnoreCase(modello) ? "selected" : "" %>>Kuga</option>
			  <option value="Mondeo" <%= "Mondeo".equalsIgnoreCase(modello) ? "selected" : "" %>>Mondeo</option>
			
			  <!-- Mercedes -->
			  <option value="Classe A" <%= "Classe A".equalsIgnoreCase(modello) ? "selected" : "" %>>Classe A</option>
			  <option value="Classe C" <%= "Classe C".equalsIgnoreCase(modello) ? "selected" : "" %>>Classe C</option>
			  <option value="Classe E" <%= "Classe E".equalsIgnoreCase(modello) ? "selected" : "" %>>Classe E</option>
			  <option value="GLA" <%= "GLA".equalsIgnoreCase(modello) ? "selected" : "" %>>GLA</option>
			  <option value="GLE" <%= "GLE".equalsIgnoreCase(modello) ? "selected" : "" %>>GLE</option>
			  <option value="EQC" <%= "EQC".equalsIgnoreCase(modello) ? "selected" : "" %>>EQC</option>
			
			  <!-- Peugeot -->
			  <option value="208" <%= "208".equalsIgnoreCase(modello) ? "selected" : "" %>>208</option>
			  <option value="308" <%= "308".equalsIgnoreCase(modello) ? "selected" : "" %>>308</option>
			  <option value="3008" <%= "3008".equalsIgnoreCase(modello) ? "selected" : "" %>>3008</option>
			  <option value="5008" <%= "5008".equalsIgnoreCase(modello) ? "selected" : "" %>>5008</option>
			
			  <!-- Renault -->
			  <option value="Clio" <%= "Clio".equalsIgnoreCase(modello) ? "selected" : "" %>>Clio</option>
			  <option value="Captur" <%= "Captur".equalsIgnoreCase(modello) ? "selected" : "" %>>Captur</option>
			  <option value="Megane" <%= "Megane".equalsIgnoreCase(modello) ? "selected" : "" %>>Megane</option>
			  <option value="Kadjar" <%= "Kadjar".equalsIgnoreCase(modello) ? "selected" : "" %>>Kadjar</option>
			  <option value="Quattro" <%= "Quattro".equalsIgnoreCase(modello) ? "selected" : "" %>>Quattro</option>
			  <option value="Cinque" <%= "Cinque".equalsIgnoreCase(modello) ? "selected" : "" %>>Cinque</option>
			
			  <!-- Toyota -->
			  <option value="Yaris" <%= "Yaris".equalsIgnoreCase(modello) ? "selected" : "" %>>Yaris</option>
			  <option value="Corolla" <%= "Corolla".equalsIgnoreCase(modello) ? "selected" : "" %>>Corolla</option>
			  <option value="RAV4" <%= "RAV4".equalsIgnoreCase(modello) ? "selected" : "" %>>RAV4</option>
			  <option value="C-HR" <%= "C-HR".equalsIgnoreCase(modello) ? "selected" : "" %>>C-HR</option>
			
			  <!-- Volkswagen -->
			  <option value="Golf" <%= "Golf".equalsIgnoreCase(modello) ? "selected" : "" %>>Golf</option>
			  <option value="Polo" <%= "Polo".equalsIgnoreCase(modello) ? "selected" : "" %>>Polo</option>
			  <option value="Passat" <%= "Passat".equalsIgnoreCase(modello) ? "selected" : "" %>>Passat</option>
			  <option value="Tiguan" <%= "Tiguan".equalsIgnoreCase(modello) ? "selected" : "" %>>Tiguan</option>
			  <option value="T-Roc" <%= "T-Roc".equalsIgnoreCase(modello) ? "selected" : "" %>>T-Roc</option>
		</select>


        <input type="text" name="prezzo" value="<%= annuncio.getPrezzo() %>" class="input">
        <input type="text" name="km" value="<%= annuncio.getKm() %>" class="input">
        <input type="text" name="anno" value="<%= annuncio.getAnno() %>" class="input">

        <select name="carburante" class="selectF">
		  <% String carb = annuncio.getCarburante(); %>
		  <option value="" disabled>-- Seleziona Carburante --</option>
		  <option value="Benzina" <%= "Benzina".equalsIgnoreCase(carb) ? "selected" : "" %>>Benzina</option>
		  <option value="Diesel" <%= "Diesel".equalsIgnoreCase(carb) ? "selected" : "" %>>Diesel</option>
		  <option value="Ibrida" <%= "Ibrida".equalsIgnoreCase(carb) ? "selected" : "" %>>Ibrida</option>
		  <option value="Elettrica" <%= "Elettrica".equalsIgnoreCase(carb) ? "selected" : "" %>>Elettrica</option>
		  <option value="Gpl" <%= "Gpl".equalsIgnoreCase(carb) ? "selected" : "" %>>GPL</option>
		</select>


        <select name="coloreAuto" class="selectF">
		  <% String colore = annuncio.getColore(); %>
		  <option value="" disabled>-- Seleziona Colore --</option>
		  <option value="Nero" <%= "Nero".equalsIgnoreCase(colore) ? "selected" : "" %>>Nero</option>
		  <option value="Bianco" <%= "Bianco".equalsIgnoreCase(colore) ? "selected" : "" %>>Bianco</option>
		  <option value="Grigio" <%= "Grigio".equalsIgnoreCase(colore) ? "selected" : "" %>>Grigio</option>
		  <option value="Argento" <%= "Argento".equalsIgnoreCase(colore) ? "selected" : "" %>>Argento</option>
		  <option value="Blu" <%= "Blu".equalsIgnoreCase(colore) ? "selected" : "" %>>Blu</option>
		  <option value="Rosso" <%= "Rosso".equalsIgnoreCase(colore) ? "selected" : "" %>>Rosso</option>
		  <option value="Verde" <%= "Verde".equalsIgnoreCase(colore) ? "selected" : "" %>>Verde</option>
		  <option value="Giallo" <%= "Giallo".equalsIgnoreCase(colore) ? "selected" : "" %>>Giallo</option>
		  <option value="Marrone" <%= "Marrone".equalsIgnoreCase(colore) ? "selected" : "" %>>Marrone</option>
		  <option value="Arancione" <%= "Arancione".equalsIgnoreCase(colore) ? "selected" : "" %>>Arancione</option>
		  <option value="Oro" <%= "Oro".equalsIgnoreCase(colore) ? "selected" : "" %>>Oro</option>
		  <option value="Viola" <%= "Viola".equalsIgnoreCase(colore) ? "selected" : "" %>>Viola</option>
		</select>

        <input type="text" name="cilindrata" value="<%= annuncio.getCilindrata() %>" class="input">
        
        <select name="n_porte" class="selectF">
          <% int porte = annuncio.getN_porte(); %>
          <option value="2" <%= porte == 2 ? "selected" : "" %>>Due</option>
          <option value="3" <%= porte == 3 ? "selected" : "" %>>Tre</option>
          <option value="4" <%= porte == 4 ? "selected" : "" %>>Quattro</option>
          <option value="5" <%= porte == 5 ? "selected" : "" %>>Cinque</option>
        </select>

        <input type="text" name="citta" value="<%= annuncio.getCitta() %>" class="input">

        <input id="submitButton" type="submit" value="Modifica annuncio">
      </form>
    </div>

    <script src="${pageContext.request.contextPath}/scripts/modelliDinamicamenteAnnuncio.js"></script>
  </body>
</html>