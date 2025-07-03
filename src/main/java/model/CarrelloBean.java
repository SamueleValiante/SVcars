package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarrelloBean implements Serializable
{
	// numero seriale
	private static final long serialVersionUID = 1L;
	
	// variabili d'istanza
	private String codice_carrello;
	private List<AnnuncioBean> prodotti;
    private double totale;
	
    // costruttore vuoto
    public CarrelloBean() {}

 // costruttore con parametri
	public CarrelloBean(String codice_carrello, double totale, List<AnnuncioBean> prodotti) 
	{
		this.codice_carrello = codice_carrello;
		this.prodotti = new ArrayList<>(prodotti);
		this.totale = totale;
	}

	// GETTERS AND SETTERS
	
	public String getCodice_carrello() {
		return codice_carrello;
	}

	public void setCodice_carrello(String codice_carrello) {
		this.codice_carrello = codice_carrello;
	}

	public double getTotale() 
	{
		return totale;
	}
	
	public void setTotale(double totale)
	{
		this.totale = totale;
	}

	public List<AnnuncioBean> getProdotti() {
		return prodotti == null ? new ArrayList<>() : new ArrayList<>(prodotti);
	}

	public void setProdotti(List<AnnuncioBean> prodotti) {
		this.prodotti = prodotti;
	}
	
	
    
    
    
}