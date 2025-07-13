document.addEventListener("DOMContentLoaded", function () {
  	const form = document.getElementById("barraForm");
  	const input = document.getElementById("barraRicercaInput");

  	form.addEventListener("submit", function (event) 
	{
   		event.preventDefault(); // blocca sempre il submit
    	rimuoviErrore();

    	const valore = input.value.trim();
    	const regex = /^[a-zA-Z0-9\s]{2,}$/;

    	if (valore === "" || !regex.test(valore)) {
      		mostraErrore("Inserisci almeno 2 caratteri validi");
      		return;
    	}
  	});

  	input.addEventListener("change", function () 
	{
    	rimuoviErrore();
    	const valore = input.value.trim();
    	const regex = /^[a-zA-Z0-9\s]{2,}$/;

    	if (valore !== "" && !regex.test(valore)) {
      		mostraErrore("Formato non valido");
    	}
  	});

  	function mostraErrore(messaggio) 
	{
    	const errore = document.createElement("div");
    	errore.className = "errore-msg";
    	errore.style.color = "red";
    	errore.style.fontSize = "0.8em";
    	errore.style.marginTop = "4px";
    	errore.textContent = messaggio;
    	input.parentElement.appendChild(errore);
  	}

  	function rimuoviErrore() 	
	{
    	const vecchioErrore = form.querySelector(".errore-msg");
    	if (vecchioErrore) 
			vecchioErrore.remove();
  	}
	
	function ricercaAnnunci() {
	    const barra = document.getElementById("barraRicercaInput").value;

	    const xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function () {
	        if (this.readyState === 4 && this.status === 200) {
	            const risultati = JSON.parse(this.responseText);
	            mostraRisultati(risultati);
	        }
	    };

	    xhr.open("GET", "/SVcars/EffettuaRicercaServlet?barraRicerca=" + encodeURIComponent(barra), true);
	    xhr.send();
	}

	function mostraRisultati(lista) 
	{
	    const container = document.getElementById("risultati");
	    container.innerHTML = ""; // pulisci tutto

	    if (lista.length == 0) {
	        container.innerHTML = "<h1>Nessun annuncio trovato.</h1>";
	        return;
	    }
		
		const titolo = document.createElement("h2");
		titolo.textContent = "Risultati della tua ricerca:";
		container.appendChild(titolo);

	    const ul = document.createElement("ul");

	    lista.forEach(annuncio => {
	        const li = document.createElement("li");
	        li.innerHTML = `
	            <div class="annuncio">
	                <img src="${contextPath}/images/${annuncio.targa}.jpg" alt="Annuncio">
	                <label class="annuncioTitolo">${annuncio.titolo}</label>
	                <label class="annuncioLabel">${annuncio.citta}</label>
	                <label class="annuncioPrezzo">${annuncio.prezzo}€</label>
	            </div>
	        `;
	        ul.appendChild(li);
	    });

	    container.appendChild(ul);
	}

	
	input.addEventListener("input", ricercaAnnunci);

	form.addEventListener("submit", function (event) {
		event.preventDefault();
		ricercaAnnunci();
	});
});