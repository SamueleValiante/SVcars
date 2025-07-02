package model;

import java.io.Serializable;

public class AnnuncioBean implements Serializable
{
	// numero seriale
	private static final long serialVersionUID = 1L;
	
	// variabili d'istanza
	private String targa;
	private Boolean visibilita;
    private String titolo;
    private String descrizione;
    private double prezzo;
    private String tipologia;
    private String colore;
    private int km;
    private int anno;
    private String carburante;
    private String marca;
    private String modello;
    private int cilindrata;
    private int n_porte;
    private String citta;
    private String email;
    
    // prova
	
    // costruttore vuoto
    public AnnuncioBean() {}

    // costruttore con parametri
	public AnnuncioBean(String targa, Boolean visibilita, String titolo, String descrizione, double prezzo, String tipologia, String colore, int km, int anno,
			String carburante, String marca, String modello, int cilindrata, int n_porte, String citta, UtenteIscrittoBean utente) 
	{
		this.targa = targa;
		this.visibilita = visibilita;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.tipologia = tipologia;
		this.colore = colore;
		this.km = km;
		this.anno = anno;
		this.carburante = carburante;
		this.marca = marca;
		this.modello = modello;
		this.cilindrata = cilindrata;
		this.n_porte = n_porte;
		this.citta = citta;
		this.email = utente.getEmail();
	}

	
	// GETTERS AND SETTERS
	
	public String getTarga() {
		return targa;
	}


	public void setTarga(String targa) {
		this.targa = targa;
	}

	
	public Boolean isVisible()
	{
		return this.visibilita;
	}
	
	public void setVisibilita(Boolean visibilita)
	{
		this.visibilita = visibilita;
	}
	

	public String getTitolo() {
		return titolo;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public double getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	public String getTipologia() {
		return tipologia;
	}
	
	public String getColore() {
		return colore;
	}
	
	public void setColore(String colore)
	{
		this.colore = colore;
	}


	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}


	public int getKm() {
		return km;
	}


	public void setKm(int km) {
		this.km = km;
	}


	public int getAnno() {
		return anno;
	}


	public void setAnno(int anno) {
		this.anno = anno;
	}


	public String getCarburante() {
		return carburante;
	}


	public void setCarburante(String carburante) {
		this.carburante = carburante;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModello() {
		return modello;
	}


	public void setModello(String modello) {
		this.modello = modello;
	}


	public int getCilindrata() {
		return cilindrata;
	}


	public void setCilindrata(int cilindrata) {
		this.cilindrata = cilindrata;
	}


	public int getN_porte() {
		return n_porte;
	}


	public void setN_porte(int n_porte) {
		this.n_porte = n_porte;
	}


	public String getCitta() {
		return citta;
	}


	public void setCitta(String citta) {
		this.citta = citta;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	
}
