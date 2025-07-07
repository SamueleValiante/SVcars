document.addEventListener("DOMContentLoaded", function () {
	const form = document.getElementById("annuncioForm");
	const erroreForm = document.getElementById("erroreForm");
	const targaInput = document.getElementById("targa");

	function targaVisibilitaCorrette(targa, callback) {
		const xhr = new XMLHttpRequest();
		const url = `/SVcars/verificaTarga`;

		const body = `targa=${encodeURIComponent(targa.trim())}`;
		
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					try {
						const data = JSON.parse(xhr.responseText);
						callback(data.valide);
					} catch (e) {
						console.error("Errore parsing JSON:", e);
						callback(false);
					}
				} else {
					console.error("Errore HTTP:", xhr.status);
					callback(false);
				}
			}
		};

		xhr.send(body);
	}

	form.addEventListener("submit", function (e) {
		e.preventDefault(); // Fermiamo SEMPRE l'invio, gestito solo se tutto è valido
		erroreForm.textContent = "";
		let errori = [];

		const campiObbligatori = [
			"immagine", "targa", "titolo", "descrizione", "prezzo", "km", "anno", "cilindrata", "citta"
		];

		campiObbligatori.forEach(id => {
			const campo = document.getElementById(id);
			if (!campo || campo.value.trim() === "") {
				errori.push(`Il campo "${campo.placeholder || id}" è obbligatorio.`);
			}
		});

		const campiNumerici = [
			{ id: "prezzo", nome: "Prezzo" },
			{ id: "km", nome: "Km" },
			{ id: "anno", nome: "Anno" },
			{ id: "cilindrata", nome: "Cilindrata" }
		];

		campiNumerici.forEach(campo => {
			const valore = document.getElementById(campo.id)?.value.trim();
			if (valore && isNaN(valore)) {
				errori.push(`Il campo "${campo.nome}" deve contenere solo numeri.`);
			}
		});

		if (errori.length > 0) {
			erroreForm.innerHTML = errori.join("<br>");
			return; // Blocca l'invio se ci sono errori
		}

		// Controllo asincrono solo dopo che tutti gli altri controlli sono andati bene
		targaVisibilitaCorrette(targaInput.value.trim(), function (valida) {
			if (valida) {
				erroreForm.textContent = "Annuncio già esistente";
				targaInput.value = "";
			} else {
				form.submit(); // Invia solo se tutto è valido
			}
		});
	});
});
