package model;

import java.time.LocalTime;

public class GeneraCodici 
{
	// genera il codice per l'utente guest
	public String generaCodiceUtente()
	{
		String codice = new String();
		
		codice = "codice_utente"  + LocalTime.now(); // da rivedere
		
		return codice;
	}
	
	// genera codice carrello utente iscritto
	public String generaCodiceCarrello(UtenteIscrittoBean utenteiscritto)
	{
		String codice = new String();
		
		codice = "codice_carrello" + utenteiscritto.getEmail() + LocalTime.now();
		
		return codice;
	}
	
	// genera codice carrello utente guest
	public String generaCodiceCarrello(String codice_utente_guest)
	{
		String codice = new String();
		
		codice = "codice_carrello" + codice_utente_guest + LocalTime.now();
		
		return codice;
	}
	
	// genera codice ordine
	public String generaCodiceOrdine(UtenteIscrittoBean utenteiscritto)
	{
		String codice = new String();
		
		codice = "codice_ordine" + utenteiscritto.getEmail() + LocalTime.now();
		
		return codice;
	}
	
	// genera codice fattura
	public String generaCodiceFattura(String codice_ordine)
	{
		String codice = new String();
		
		codice = "codice_fattura" + codice_ordine;
		
		return codice;
	}
}
