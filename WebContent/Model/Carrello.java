package Model;

public class Carrello 
{
	private int codice_carrello;
	private String[] prodotti; // array di targhe di annunci
    private double totale;
    private String e_mail;
	
    public Carrello() {}

	public Carrello(int codice_carrello, double totale, UtenteIscritto utente, String[] prodotti) 
	{
		this.codice_carrello = codice_carrello;
		this.prodotti = prodotti.clone();
		this.totale = totale;
		this.e_mail = utente.getEmail();
	}

	public int getCodice_carrello() {
		return codice_carrello;
	}

	public void setCodice_carrello(int codice_carrello) {
		this.codice_carrello = codice_carrello;
	}

	public double getTotale() 
	{
		return totale;
	}

	public String[] getProdotti() {
		return prodotti;
	}

	public void setProdotti(String[] prodotti) {
		this.prodotti = prodotti;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	
	
    
    
    
}