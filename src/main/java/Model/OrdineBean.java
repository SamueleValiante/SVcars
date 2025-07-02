package Model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrdineBean implements Serializable
{
	// numero seriale
	private static final long serialVersionUID = 1L;
	
	// variabili d'istanza
	private String codice_ordine;
    private String indirizzo_origine;
    private String indirizzo_destinazione;
    private List<AnnuncioBean> prodotti;
    private double costo_prodotti;
    private double costo_spedizione;
    private double totale;
    private Date dataAcquisto;
    private String tempo_spedizione;
    private String codice_fattura;
    private String email_compratore;
	
    // costruttore vuoto
    public OrdineBean() {}

    // costruttore con argomenti
	public OrdineBean(String codice_ordine, String indirizzo_origine, String indirizzo_destinazione, List<AnnuncioBean> prodotti,double costo_prodotti,
			double costo_spedizione, double totale, Date dataAcquisto, String tempo_spedizione, FatturaBean fattura, UtenteIscrittoBean utenteCompratore) 
	{
		this.codice_ordine = codice_ordine;
		this.indirizzo_origine = indirizzo_origine;
		this.indirizzo_destinazione = indirizzo_destinazione;
		this.prodotti = new ArrayList<>(prodotti);
		this.costo_prodotti = costo_prodotti;
		this.costo_spedizione = costo_spedizione;
		this.totale = totale;
		this.dataAcquisto = dataAcquisto;
		this.tempo_spedizione = tempo_spedizione;
		this.codice_fattura = fattura.getCodice_fattura();
		this.email_compratore = utenteCompratore.getEmail();
	}

	// getters e setters
	
	public String getCodice_ordine() {
		return codice_ordine;
	}

	public void setCodice_ordine(String codice_ordine) {
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
	
	public List<AnnuncioBean> getProdotti() {
		List<AnnuncioBean> prodotti2 = new ArrayList<>(prodotti);
		
		return prodotti2;
	}

	public void setProdotti(List<AnnuncioBean> prodotti) {
		this.prodotti = prodotti;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}
	

	public Date getDataAcquisto() {
		return dataAcquisto;
	}

	public void setDataAcquisto(Date dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public String getTempo_spedizione() {
		return tempo_spedizione;
	}

	public void setTempo_spedizione(String tempo_spedizione) {
		this.tempo_spedizione = tempo_spedizione;
	}

	public String getCodiceFattura() {
		return codice_fattura;
	}
	
	public void setCodiceFattura(String codice_fattura)
	{
		this.codice_fattura = codice_fattura;
	}

	public String getEmail_compratore() {
		return email_compratore;
	}

	public void setEmail_compratore(String email_compratore) {
		this.email_compratore = email_compratore;
	}
	
	 
}
