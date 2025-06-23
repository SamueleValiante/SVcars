package Model;

public class UtenteGuest
{
	private int codice_utente;

	public UtenteGuest() {}

	public UtenteGuest(int codice_utente) 
	{
		this.codice_utente = codice_utente;
	}

	public int getCodice_utente() {
		return codice_utente;
	}

	public void setCodice_utente(int codice_utente) {
		this.codice_utente = codice_utente;
	}
	
}
