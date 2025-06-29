package Model;

import java.io.Serializable;

public class FatturaBean implements Serializable
{
	// numero seriale
	private static final long serialVersionUID = 1L;
	
	// variabili d'istanza
	private String codice_fattura;
	
    
	// costruttore vuoto
    public FatturaBean() {}

	
    // costruttore con campi
    public FatturaBean(String codice_fattura) 
    {
		this.codice_fattura = codice_fattura;
	}
    
    
    // getters and setters

	public String getCodice_fattura() {
		return codice_fattura;
	}

	public void setCodice_fattura(String codice_fattura) {
		this.codice_fattura = codice_fattura;
	}
}
