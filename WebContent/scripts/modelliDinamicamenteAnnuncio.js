document.addEventListener("DOMContentLoaded", function () {
    const marcaSelect = document.querySelector("select[name='marcaAuto']");
    const modelloSelect = document.querySelector("select[name='modelloAuto']");

	const modelliPerMarca = {
	        Audi: ["A1", "A2", "A3", "A4", "A5", "A6", "Q2", "Q3", "Q4", "Q5", "Q7", "TT", "e-tron"],
	        Bmw: ["Serie 1", "Serie 3", "Serie 5", "X1", "X3", "X5", "i3"],
	        Fiat: ["Panda", "500", "Tipo", "Punto", "Uno"],
	        Ford: ["Fiesta", "Focus", "Kuga", "Mondeo"],
	        Mercedes: ["Classe A", "Classe C", "Classe E", "GLA", "GLE", "EQC"],
	        Peugeot: ["208", "308", "3008", "5008"],
	        Renault: ["Clio", "Captur", "Megane", "Kadjar", "Quattro", "Cinque"],
	        Toyota: ["Yaris", "Corolla", "RAV4", "C-HR"],
	        Volkswagen: ["Golf", "Polo", "Passat", "Tiguan", "T-Roc"]
	    };

    marcaSelect.addEventListener("change", function () {
        const marca = this.value;
        const modelli = modelliPerMarca[marca] || [];

        modelloSelect.innerHTML = "";

        const defaultOption = document.createElement("option");
        defaultOption.value = "";
        defaultOption.textContent = "-- Seleziona un modello --";
        defaultOption.disabled = true;
        defaultOption.selected = true;
        modelloSelect.appendChild(defaultOption);
		
		console.log("Marca selezionata:", marca);

        modelli.forEach(modello => {
            const option = document.createElement("option");
            option.value = modello;
            option.textContent = modello;
            modelloSelect.appendChild(option);
        });
    });
});
