package model;

import java.time.LocalTime;
import java.util.UUID;

public class GeneraCodici 
{
	// genera il codice per l'utente guest
	public String generaCodiceUtente()
	{
		String codice = new String();
		
		codice = "codice_utente"  + UUID.randomUUID();
		
		return codice;
	}
	
	// genera codice carrello utente iscritto
	public String generaCodiceCarrello(UtenteIscrittoBean utenteiscritto)
	{
		String codice = new String();
		
		codice = "codice_carrello" + utenteiscritto.getEmail() + UUID.randomUUID();
		
		return codice;
	}
	
	// genera codice carrello utente guest
	public String generaCodiceCarrello(String codice_utente_guest)
	{
		String codice = new String();
		
		codice = "codice_carrello" + codice_utente_guest;
		
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
	public String generaCodiceFattura()
	{
		return "codice_fattura" + UUID.randomUUID().toString();
	}
}
