package Model;

public class UtenteIscritto 
{
	private String email;
	private String nome;
	private String cognome;
	private Tipo_utente tipo_utente;
	private String password;
	private String citta;
	private int cap;
	private String via;
	
	public UtenteIscritto() {}
	
	public UtenteIscritto(String email, String nome, String cognome, Tipo_utente tipo_utente, String password, String citta, int cap, String via)
	{
		this.email = email;
		this.nome = nome.toString();
		this.cognome = cognome.toString();
		this.tipo_utente = tipo_utente;
		this.password = password;
		this.citta = citta;
		this.cap = cap;
		this.via = via.toString();
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

	public void setTipo_utente(Tipo_utente tipo_utente) {
		this.tipo_utente = tipo_utente;
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
	
	// quando invocata legge tutta la tabella del db UtenteIscritto e per ogni riga ne crea un oggetto che salva in un array di oggetti che restituirà
	
	/*public UtenteIscritto[] getUtentiIscritti() 
	{
		
	}*/
	
	
	
}