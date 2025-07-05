document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("barraForm");
  const input = document.getElementById("barraRicercaInput");

  form.addEventListener("submit", function (event) {
    event.preventDefault(); // blocca sempre il submit
    rimuoviErrore();

    const valore = input.value.trim();
    const regex = /^[a-zA-Z0-9\s]{2,}$/;

    if (valore === "" || !regex.test(valore)) {
      mostraErrore("Inserisci almeno 2 caratteri validi");
      return;
    }

    // Se il valore è valido, invia via AJAX
    //inviaAjax(valore);
  });

  input.addEventListener("change", function () {
    rimuoviErrore();
    const valore = input.value.trim();
    const regex = /^[a-zA-Z0-9\s]{2,}$/;

    if (valore !== "" && !regex.test(valore)) {
      mostraErrore("Formato non valido");
    }
  });

  function mostraErrore(messaggio) {
    const errore = document.createElement("div");
    errore.className = "errore-msg";
    errore.style.color = "red";
    errore.style.fontSize = "0.8em";
    errore.style.marginTop = "4px";
    errore.textContent = messaggio;
    input.parentElement.appendChild(errore);
  }

  function rimuoviErrore() {
    const vecchioErrore = form.querySelector(".errore-msg");
    if (vecchioErrore) vecchioErrore.remove();
  }

  function inviaAjax(valore) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/SVcars/EffettuaRicercaServlet?barraRicerca=" + encodeURIComponent(valore), true);
    xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");

    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
        const risultatiDiv = document.getElementById("risultatiAnnunci");
        if (risultatiDiv) {
          risultatiDiv.innerHTML = xhr.responseText;
        } else {
          console.warn("Div #risultatiAnnunci non trovato nella pagina");
        }
      }
    };

    xhr.send();
  }
});

