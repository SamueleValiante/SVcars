package model;

import java.io.Serializable;

public class UtenteIscrittoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String email;
	private String nome;
	private String cognome;
	private Tipo_utente tipo_utente;
	private String password;
	private String citta;
	private int cap;
	private String via;
	private String codice_carrello;
	
	public UtenteIscrittoBean() {}
	
	public UtenteIscrittoBean(String email, String nome, String cognome, Tipo_utente tipo_utente, String password, String citta, int cap, String via, String codice_carrello)
	{
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.tipo_utente = tipo_utente;
		this.password = password;
		this.citta = citta;
		this.cap = cap;
		this.via = via;
		this.codice_carrello = codice_carrello;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Tipo_utente getTipo_utente() {
		return tipo_utente;
	}

	public void setTipo_utente(String string) {
		this.tipo_utente = Tipo_utente.valueOf(string);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCodice_carrello() {
		return codice_carrello;
	}

	public void setCodice_carrello(String codice_carrello) {
		this.codice_carrello = codice_carrello;
	}
	
	
	
	
	
}