document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("barraForm");
  const input = document.getElementById("barraRicercaInput");
  const risultatiDiv = document.getElementById("risultatiDiv");

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

  // Funzione che invia la richiesta AJAX
  // Valida e invia la richiesta AJAX
	function inviaRicercaAjax() 
   	{
   		const valore = input.value.trim();
    	const regex = /^[a-zA-Z0-9\s]{2,}$/;

     	if (valore.length < 2 && valore.lenght > 0) || !regex.test(valore)) {
        	mostraErrore("Inserisci almeno 2 caratteri validi");
        	return;
     	}

     	rimuoviErrore();

     	const queryString = new URLSearchParams(new FormData(form)).toString();

     	fetch(form.action + "?" + queryString, {
        	method: "GET",
       		headers: {
         		"X-Requested-With": "XMLHttpRequest"
       		}
     	})
       
		.then(response => {
         	if (!response.ok) throw new Error("Errore nella risposta");
         		return response.text();
       	 })
       	.then(html => {
         	risultatiDiv.innerHTML = html;
       	 })
       	.catch(error => {
         	console.error("Errore AJAX:", error);
         	risultatiDiv.innerHTML = "<p>Errore nella ricerca.</p>";
         });
   }
	
   // Trigger sugli eventi
     form.addEventListener("submit", function (e) {
       e.preventDefault();
       inviaRicercaAjax();
     });

     input.addEventListener("input", function () {
       rimuoviErrore();
     });
});

