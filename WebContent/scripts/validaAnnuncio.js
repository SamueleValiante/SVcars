document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("annuncioForm");
  const erroreForm = document.getElementById("erroreForm");

  form.addEventListener("submit", function (e) {
    erroreForm.textContent = ""; // Pulisce errori precedenti
    let errori = [];

    // Campi da verificare
    const campiObbligatori = [
      "immagine", "targa", "titolo", "descrizione", "prezzo", "km", "anno", "cilindrata", "citta"
    ];

    campiObbligatori.forEach(id => {
      const campo = document.getElementById(id);
      if (!campo || campo.value.trim() === "") {
        errori.push(`Il campo "${campo.placeholder || id}" è obbligatorio.`);
      }
    });

    // Validazione numerica
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
      e.preventDefault(); // Blocca invio
      erroreForm.innerHTML = errori.join("<br>");
    }
  });
});
