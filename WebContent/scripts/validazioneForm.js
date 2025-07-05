document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("filtriForm");

    const regole = {
        barraRicerca: /^[a-zA-Z0-9\s]{2,}$/, // esempio: almeno 2 caratteri alfanumerici
        prezzoMin: /^\d+$/,
        prezzoMax: /^\d+$/,
        annoMin: /^\d{4}$/,
        annoMax: /^\d{4}$/,
    };

    form.addEventListener("submit", function (event) {
        let valid = true;
		// Verifica se tutti i campi della form sono vuoti
		const formData = new FormData(form);
		let almenoUnCampoCompilato = false;

		for (let value of formData.values()) {
		    if (value.trim() !== "") {
		        almenoUnCampoCompilato = true;
		        break;
		    }
		}

		if (!almenoUnCampoCompilato) {
		    // Mostra messaggio generico (es. sotto al bottone)
		    const submitDiv = document.getElementById("submitDiv");
		   
		    event.preventDefault(); // blocca l'invio
		    return;
		}

        // Rimuovi messaggi di errore precedenti
        form.querySelectorAll(".errore-msg").forEach(e => e.remove());

        // Loop su tutti gli input da validare
        Object.keys(regole).forEach(id => {
            const input = form.querySelector(`[name="${id}"]`);
            if (!input) return;
            const value = input.value.trim();
            const regex = regole[id];

            if (value !== "" && !regex.test(value)) {
                mostraErrore(input, "Formato non valido");
                valid = false;
            }
        });

        // Controllo logico: prezzoMin ≤ prezzoMax
		const minPrezzo = parseInt(form.prezzoMin.value);
		const maxPrezzo = parseInt(form.prezzoMax.value);
		if (!isNaN(minPrezzo) && !isNaN(maxPrezzo) && minPrezzo > maxPrezzo) {
		    mostraErrore(form.prezzoMin, "Input invalido");
		    valid = false;
		}

		const minKm = parseInt(form.KmMin.value);
		const maxKm = parseInt(form.KmMax.value);
		if (!isNaN(minKm) && !isNaN(maxKm) && minKm > maxKm) {
		    mostraErrore(form.KmMin, "Input invalido");
		    valid = false;
		}

		const minAnno = parseInt(form.annoMin.value);
		const maxAnno = parseInt(form.annoMax.value);
		if (!isNaN(minAnno) && !isNaN(maxAnno) && minAnno > maxAnno) {
		    mostraErrore(form.annoMin,"Input invalido");
		    valid = false;
		}


        if (!valid) event.preventDefault();
    });

    // Mostra errore in DOM
    function mostraErrore(input, messaggio) {
        const errore = document.createElement("div");
        errore.className = "errore-msg";
        errore.style.color = "red";
        errore.textContent = messaggio;
        input.parentElement.appendChild(errore);
    }

    // Evento "change" su ogni campo
    Object.keys(regole).forEach(id => {
        const input = form.querySelector(`[name="${id}"]`);
        if (!input) return;
        input.addEventListener("change", () => {
            form.querySelectorAll(".errore-msg").forEach(e => e.remove());
            if (input.value.trim() !== "" && !regole[id].test(input.value.trim())) {
                mostraErrore(input, "Formato non valido");
            }
        });
    });
});
