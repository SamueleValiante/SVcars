package Model;

public class Ordine 
{
	// variabili d'istanza
	private int codice_ordine;
    private String indirizzo_origine;
    private String indirizzo_destinazione;
    private double costo_prodotti;
    private double costo_spedizione;
    private double totale;
    private String tempo_spedizione;
    private String email;
	
    // costruttore vuoto
    public Ordine() {}

    // costruttore con argomenti
	public Ordine(int codice_ordine, String indirizzo_origine, String indirizzo_destinazione, double costo_prodotti,
			double costo_spedizione, double totale, String tempo_spedizione, String email) 
	{
		this.codice_ordine = codice_ordine;
		this.indirizzo_origine = indirizzo_origine;
		this.indirizzo_destinazione = indirizzo_destinazione;
		this.costo_prodotti = costo_prodotti;
		this.costo_spedizione = costo_spedizione;
		this.totale = totale;
		this.tempo_spedizione = tempo_spedizione;
		this.email = email;
	}

	// getters e setters
	
	public int getCodice_ordine() {
		return codice_ordine;
	}

	public void setCodice_ordine(int codice_ordine) {
		this.codice_ordine = codice_ordine;
	}

	public String getIndirizzo_origine() {
		return indirizzo_origine;
	}

	public void setIndirizzo_origine(String indirizzo_origine) {
		this.indirizzo_origine = indirizzo_origine;
	}

	public String getIndirizzo_destinazione() {
		return indirizzo_destinazione;
	}

	public void setIndirizzo_destinazione(String indirizzo_destinazione) {
		this.indirizzo_destinazione = indirizzo_destinazione;
	}

	public double getCosto_prodotti() {
		return costo_prodotti;
	}

	public void setCosto_prodotti(double costo_prodotti) {
		this.costo_prodotti = costo_prodotti;
	}

	public double getCosto_spedizione() {
		return costo_spedizione;
	}

	public void setCosto_spedizione(double costo_spedizione) {
		this.costo_spedizione = costo_spedizione;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public String getTempo_spedizione() {
		return tempo_spedizione;
	}

	public void setTempo_spedizione(String tempo_spedizione) {
		this.tempo_spedizione = tempo_spedizione;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
    
    
    
    
}
