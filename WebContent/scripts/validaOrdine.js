document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("annuncioForm");

  form.addEventListener("submit", function (event) {
    event.preventDefault();

    // Pulizia errori precedenti
    document.querySelectorAll(".messaggioErrore").forEach(e => e.remove());

    let valido = true;

    // Riferimenti agli input
    const nomeCognome = document.getElementById("nomeCognome");
    const ncarta = document.getElementById("Ncarta");
    const cvv = document.getElementById("CVV");
    const indirizzoDe = document.getElementById("indirizzoDe");
    const scadenza = document.querySelector('input[type="date"]');

    // Helper per mostrare errore
    const mostraErrore = (input, messaggio) => {
      const span = document.createElement("span");
      span.className = "messaggioErrore";
      span.textContent = messaggio;
      span.style.color = "red";
      span.style.fontSize = "0.9em";
      input.value = ""; // azzera campo
      input.parentNode.appendChild(span);
    };

    // Verifiche
    if (!nomeCognome.value.trim()) {
      mostraErrore(nomeCognome, "Inserisci nome e cognome.\n");
      valido = false;
    }

    if (!/^\d{16}$/.test(ncarta.value)) {
      mostraErrore(ncarta, "Il numero carta deve contenere 16 cifre.");
      valido = false;
    }

    if (!/^\d{3}$/.test(cvv.value)) {
      mostraErrore(cvv, "Il CVV deve contenere 3 cifre.");
      valido = false;
    }

    if (!indirizzoDe.value.trim()) {
      mostraErrore(indirizzoDe, "Inserisci indirizzo di destinazione.");
      valido = false;
    }

    const oggi = new Date();
    const dataScadenza = new Date(scadenza.value);
    if (isNaN(dataScadenza.getTime()) || dataScadenza <= oggi) {
      mostraErrore(scadenza, "La data di scadenza deve essere futura.");
      valido = false;
    }

    // Se tutto ok, invia il form
    if (valido) {
      form.submit();
    }
  });
});
