package Model;

public class Fattura 
{
	private int numero_fattura;
    private int codice_ordine;
	
    // costruttore vuoto
    public Fattura() {}

	// costruttore con campi
    public Fattura(int numero_fattura, int codice_ordine) 
    {
		this.numero_fattura = numero_fattura;
		this.codice_ordine = codice_ordine;
	}
    
    // getters and setters

	public int getNumero_fattura() {
		return numero_fattura;
	}

	public void setNumero_fattura(int numero_fattura) {
		this.numero_fattura = numero_fattura;
	}

	public int getCodice_ordine() {
		return codice_ordine;
	}

	public void setCodice_ordine(int codice_ordine) {
		this.codice_ordine = codice_ordine;
	}
    
	// creato il db 
	
		// elimina questa classe e array di stringhe a classe annuncio

		// il database è svcars_db ci accedi con password

		// scarica driver jdbc

		// crea classe per connection pool e da li crei le varie funzioni di accesso al db gestendo token di sessione e altre cose relate
    
    

}
