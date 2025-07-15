document.addEventListener("DOMContentLoaded", function () {
    const marcaSelect = document.querySelector("select[name='marcaAuto']");
    const modelloSelect = document.querySelector("select[name='modelloAuto']");

	const modelliPerMarca = {
	        audi: ["A1", "A2", "A3", "A4", "A5", "A6", "Q2", "Q3", "Q4", "Q5", "Q7", "TT", "e-tron"],
	        bmw: ["Serie 1", "Serie 3", "Serie 5", "X1", "X3", "X5", "i3"],
	        fiat: ["Panda", "500", "Tipo", "Punto", "Uno"],
	        ford: ["Fiesta", "Focus", "Kuga", "Mondeo"],
	        mercedes: ["Classe A", "Classe C", "Classe E", "GLA", "GLE", "EQC"],
	        peugeot: ["208", "308", "3008", "5008"],
	        renault: ["Clio", "Captur", "Megane", "Kadjar", "Quattro", "Cinque"],
	        toyota: ["Yaris", "Corolla", "RAV4", "C-HR"],
	        volkswagen: ["Golf", "Polo", "Passat", "Tiguan", "T-Roc"]
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

        modelli.forEach(modello => {
            const option = document.createElement("option");
            option.value = modello;
            option.textContent = modello;
            modelloSelect.appendChild(option);
        });
    });
});
