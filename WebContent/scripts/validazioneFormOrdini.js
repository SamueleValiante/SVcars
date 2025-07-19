document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("filtriForm");
  const emailInput = document.getElementById("barraRicercaSidebar");
  const dataMinInput = form.querySelector("input[name='dataMin']");
  const dataMaxInput = form.querySelector("input[name='dataMax']");

  form.addEventListener("submit", function (event) {
    const email = emailInput.value.trim();
    const dataMin = dataMinInput.value;
    const dataMax = dataMaxInput.value;

    let messaggioErrore = "";

    // Email pattern (very basic)
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    // Validate email if present
    if (email.length > 0 && !emailPattern.test(email)) {
      messaggioErrore += " Email non valida.<br>";
    }
	
	if (email === "" && dataMin === "" && dataMax === "") {
	      messaggioErrore += "Inserisci almeno un criterio di ricerca.<br>";
	    }

    // Validate date range
    if (dataMin && dataMax && new Date(dataMin) > new Date(dataMax)) {
      messaggioErrore += "La data iniziale non può essere successiva alla data finale.<br>";
    }

    if (messaggioErrore !== "") {
      event.preventDefault();

      // Mostra messaggio errore nel DOM
      let erroreDiv = document.getElementById("erroreForm");
      if (!erroreDiv) {
        erroreDiv = document.createElement("div");
        erroreDiv.id = "erroreForm";
        erroreDiv.style.color = "red";
        erroreDiv.style.fontWeight = "bold";
        erroreDiv.style.marginTop = "10px";
        form.appendChild(erroreDiv);
      }

      erroreDiv.innerHTML = messaggioErrore;
    }
  });
});