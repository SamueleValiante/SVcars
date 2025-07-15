package model;

import java.io.Serializable;

public class UtenteGuestBean implements Serializable
{
	// numero seriale
	private static final long serialVersionUID = 1L;

	// variabili d'istanza
	private String codice_utente;
	private String codice_carrello;

	// costruttore vuoto
	public UtenteGuestBean() {}

	// costruttore con argomenti
	public UtenteGuestBean(String codice_utente, String codice_carrello) 
	{
		this.codice_utente = codice_utente;
		this.codice_carrello = codice_carrello;
	}

	
	// getters and setters
	
	public String getCodice_utente() {
		return codice_utente;
	}

	public void setCodice_utente(String codice_utente) {
		this.codice_utente = codice_utente;
	}

	public String getCodice_carrello() {
		return codice_carrello;
	}

	public void setCodice_carrello(String codice_carrello) {
		this.codice_carrello = codice_carrello;
	}

	@Override
	public String toString() {
		return "UtenteGuestBean [codice_utente=" + codice_utente + ", codice_carrello=" + codice_carrello + "]";
	}
	
	
	
}
