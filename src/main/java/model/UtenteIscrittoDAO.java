package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteIscrittoDAO implements InterfaceDataAccessObject<UtenteIscrittoBean> {

	private static final String TABLE_NAME = "UtenteIscritto";
	private final String db = "svcars_db";
	private final String username = "samuele";
	private final String password = "samuele99";

	@Override
	public synchronized void doSave(UtenteIscrittoBean utenteIscritto) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UtenteIscrittoDAO.TABLE_NAME
				+ " (email, nome, cognome, tipo_utente, password, citta, cap, via, codice_carrello) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utenteIscritto.getEmail());
			preparedStatement.setString(2, utenteIscritto.getNome());
			preparedStatement.setString(3, utenteIscritto.getCognome());
			preparedStatement.setString(4, utenteIscritto.getTipo_utente().name());
			preparedStatement.setString(5, sha256(utenteIscritto.getPassword())); 
			preparedStatement.setString(6, utenteIscritto.getCitta());
			preparedStatement.setInt(7, utenteIscritto.getCap());
			preparedStatement.setString(8, utenteIscritto.getVia());
			preparedStatement.setString(9, utenteIscritto.getCodice_carrello());

			// salvo l'utente nel database
			preparedStatement.executeUpdate();
						
			// salvo il suo carrello nel database
			CarrelloDAO dao = new CarrelloDAO();
			dao.doSave(new CarrelloBean(utenteIscritto.getCodice_carrello(), 0, null));
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public synchronized UtenteIscrittoBean doRetrieveByKey(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UtenteIscrittoBean bean = new UtenteIscrittoBean();

		String selectSQL = "SELECT * FROM " + UtenteIscrittoDAO.TABLE_NAME + " WHERE email = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setTipo_utente(rs.getString("tipo_utente"));
				bean.setPassword(rs.getString("password")); 
				bean.setCitta(rs.getString("citta"));
				bean.setCap(rs.getInt("cap"));
				bean.setVia(rs.getString("via"));
				bean.setCodice_carrello(rs.getString("codice_carrello"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}

	@Override
	public synchronized boolean doDelete(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + UtenteIscrittoDAO.TABLE_NAME + " WHERE email = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, email);

			// Cancella il carrello all'utente associato
			CarrelloDAO dao = new CarrelloDAO();
			dao.doDelete(this.doRetrieveByKey(email).getCodice_carrello());
			
			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized List<UtenteIscrittoBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<UtenteIscrittoBean> utentiIscritti = new ArrayList<UtenteIscrittoBean>();

		String selectSQL = "SELECT * FROM " + UtenteIscrittoDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UtenteIscrittoBean bean = new UtenteIscrittoBean();

				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setTipo_utente(rs.getString("tipo_utente"));
				bean.setPassword(rs.getString("password")); 
				bean.setCitta(rs.getString("citta"));
				bean.setCap(rs.getInt("cap"));
				bean.setVia(rs.getString("via"));
				bean.setCodice_carrello(rs.getString("codice_carrello"));
				
				utentiIscritti.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return utentiIscritti;
	}
	
	// funzione per generare stringa codificata sha-256
	public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));


            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b)); // converte byte in esadecimale
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Errore durante la generazione dell'hash SHA-256", e);
        }
    }
	
	// cancella riferimenti all ordine
	public int cancellaRiferimenti(String email) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
					
		int result = 0;

		// cancella tutte le tuple dalle tabelle che coincidono con l'email
		String deleteSQL1 = "SELECT codice_ordine FROM Ordine WHERE email = ?";
		String deleteSQL2 = "SELECT targa FROM Annuncio WHERE email = ?";


		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(deleteSQL1);
			preparedStatement2 = connection.prepareStatement(deleteSQL2);
						
			preparedStatement.setString(1, email);
					
			ResultSet rs1 = preparedStatement.executeQuery();

			OrdineDAO dao = new OrdineDAO();
			
			// ottiene tutte le tuple che coincidono con l email
			while (rs1.next()) 
			{
				// ottiene il codice ordine 
				String codice_ordine = rs1.getString("codice_ordine");
			
				// cancella ordine
				dao.doDelete(codice_ordine);
			}
			
			preparedStatement2.setString(1, email);
			
			ResultSet rs2 = preparedStatement2.executeQuery();

			// ottiene tutte le tuple che coincidono con l email
			while (rs2.next()) 
			{
				// ottiene il codice ordine 
				String targa = rs2.getString("targa");
			
				// cancella annuncio
				AnnuncioDAO dao2 = new AnnuncioDAO();
				dao2.doDelete(targa);
			}
			
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (preparedStatement2 != null)
				    preparedStatement2.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
					
		return result;
	}
	
	// effettua ordine da singolo annuncio
	public void effettuaOrdineSingolo(OrdineBean ordine, AnnuncioBean annuncio) throws SQLException
	{
		// ordine.prodotti verra passato vuoto a questa funzione, quindi gli inseriremo l'annuncio
		List<AnnuncioBean> prodotti = ordine.getProdotti();
		
		prodotti.add(annuncio);
		
		ordine.setProdotti(prodotti);
		
		// salviamo l'ordine, la fattura e le corrispondenze acquistate nel db
		OrdineDAO dao = new OrdineDAO();
		dao.doSave(ordine);
	}
	
	// effettua ordine da carrello
	public void effettuaOrdineCarrello(OrdineBean ordine) throws SQLException
	{
		// parametri da definire: prodotti, costoProdotti, totale
		
		// ordine.prodotti verra passato vuoto a questa funzione, quindi gli inseriremo gli annunci

		// otteniamo l'utente che effettua l'ordine
		UtenteIscrittoDAO daoutente = new UtenteIscrittoDAO();
		
		UtenteIscrittoBean utente = daoutente.doRetrieveByKey(ordine.getEmail_compratore());
		
		// ottiene codice carrello utente che effettua ordine
		String codice_carrello = utente.getCodice_carrello();
		
		// ottiene prodotti nel carrello e li salva nell'ordine
		CarrelloDAO daocarrello = new CarrelloDAO();
		List<AnnuncioBean> prodotti = daocarrello.getAnnunciCarrello(codice_carrello);
		ordine.setProdotti(prodotti);
		
		// salviamo l'ordine, la fattura e le corrispondenze acquistate nel db
		OrdineDAO dao = new OrdineDAO();
		dao.doSave(ordine);
		
		// svuota il carrello
		daocarrello.svuotaCarrello(codice_carrello);
		
		// elimina annunci acquistati e calcola il totale
		for(AnnuncioBean annuncio : prodotti)
		{
			String targa = annuncio.getTarga();
			ordine.setCosto_prodotti(ordine.getCosto_prodotti() + annuncio.getPrezzo());
		
			AnnuncioDAO daoannuncio = new AnnuncioDAO();
			daoannuncio.doDelete(targa);
		}
	}
	
	// annulla ordine
	public void annullaOrdine(String codice_ordine) throws SQLException
	{
		// ottiene l'ordine dal db
		OrdineDAO daoOrdine = new OrdineDAO();
		OrdineBean ordine = daoOrdine.doRetrieveByKey(codice_ordine);
		
		// ripristina annunci ordine
		for(AnnuncioBean annuncio: ordine.getProdotti())
		{
			AnnuncioDAO dao = new AnnuncioDAO();
			dao.restoreAnnuncio(annuncio.getTarga());
		}
		
		// cancella ordine e riferimenti ad esso
		daoOrdine.doDelete(codice_ordine);
	}
	
	
	// crea annuncio
	public void creaAnnuncio(AnnuncioBean annuncio) throws SQLException
	{
		annuncio.setVisibilita(true);
		
		// aggiungiamo annuncio al db
		AnnuncioDAO dao = new AnnuncioDAO();
		dao.doSave(annuncio);
	}
	
	// modifica annuncio
	public void modificaAnnuncio(AnnuncioBean annuncio) throws SQLException
	{
		Connection connection = null;
	    PreparedStatement ps = null;

	    String updateSQL = "UPDATE Annuncio SET titolo = ?, descrizione = ?, prezzo = ?, tipologia = ?, colore = ?, " +
	                       "km = ?, anno = ?, carburante = ?, marca = ?, modello = ?, cilindrata = ?, n_porte = ?, " +
	                       "citta = ?, email = ?, visibilita = ? WHERE targa = ?";

	    try {
	        connection = DriverManagerConnectionPool.getConnection(db, username, password);
	        ps = connection.prepareStatement(updateSQL);
	        ps.setString(1, annuncio.getTitolo());
	        ps.setString(2, annuncio.getDescrizione());
	        ps.setDouble(3, annuncio.getPrezzo());
	        ps.setString(4, annuncio.getTipologia());
	        ps.setString(5, annuncio.getColore());
	        ps.setInt(6, annuncio.getKm());
	        ps.setInt(7, annuncio.getAnno());
	        ps.setString(8, annuncio.getCarburante());
	        ps.setString(9, annuncio.getMarca());
	        ps.setString(10, annuncio.getModello());
	        ps.setInt(11, annuncio.getCilindrata());
	        ps.setInt(12, annuncio.getN_porte());
	        ps.setString(13, annuncio.getCitta());
	        ps.setString(14, annuncio.getEmail());
	        ps.setBoolean(15, annuncio.isVisible());
	        ps.setString(16, annuncio.getTarga());

	        ps.executeUpdate();
	        
	    } finally {
	        if (ps != null) ps.close();
	        DriverManagerConnectionPool.releaseConnection(connection);
	    }
	}
	
	// aggiungi annuncio a carrello
	public String aggiungiAnnuncioCarrello(AnnuncioBean annuncio, String codice_carrello) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		// ottiene i riferimenti di ogni annuncio in base al codice_carrello
		String selectSQL = "SELECT * FROM Contiene WHERE codice_carrello = ?";

		// verifica se l'annuncio è gia presente nel carrello
		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, codice_carrello);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				if(rs.getString("targa").equals(annuncio.getTarga()))
					return "Annuncio già salvato";
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		// aggiunge annuncio a carrello
		Connection connection2 = null;
		PreparedStatement preparedStatement2 = null;

		String insertSQL = "INSERT INTO Contiene (codice_carrello, targa) VALUES (?, ?)";
		

		try {
			connection2 = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement2 = connection2.prepareStatement(insertSQL);
			preparedStatement2.setString(1, codice_carrello);
			preparedStatement2.setString(2, annuncio.getTarga());

			preparedStatement2.executeUpdate();

		} finally {
			try {
				if (preparedStatement2 != null)
					preparedStatement2.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection2);
			}
		}
		
		// incrementa totale carrello
		
		// ottiene carrello che conteneva l'annuncio
		CarrelloDAO carrellodao = new CarrelloDAO();
		CarrelloBean carrello = carrellodao.doRetrieveByKey(codice_carrello);
				
		Connection connection3 = null;
		PreparedStatement preparedStatement3 = null;

		// modifica totale nel carrello
		String modificaSQL = "UPDATE Carrello SET totale = ? WHERE codice_carrello = ?";

		try {
			connection3 = DriverManagerConnectionPool.getConnection(db, username, password);

			preparedStatement3 = connection3.prepareStatement(modificaSQL);
					
			preparedStatement3.setDouble(1, carrello.getTotale() + annuncio.getPrezzo());
			preparedStatement3.setString(2, codice_carrello);
					
			// aggiorna totale carrello
			preparedStatement3.executeUpdate();

		} finally {
			try {
				if (preparedStatement3 != null)
					preparedStatement3.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection3);
			}
		}
		
		return "Successo";
	}
	
	// rimuovi annuncio da carrello
	public String rimuoviAnnuncioCarrello(AnnuncioBean annuncio, String codice_carrello) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM Contiene WHERE codice_carrello = ? AND targa = ?";


		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(deleteSQL);
				
			preparedStatement.setString(1, codice_carrello);
			preparedStatement.setString(2, annuncio.getTarga());

			preparedStatement.executeUpdate();

		} 
		
		catch(Exception e){
			System.out.println(e);
			return "errore";
		}
		finally 
		{
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		// decremento totale del carrello
		
		// ottiene carrello che conteneva l'annuncio
		CarrelloDAO carrellodao = new CarrelloDAO();
		CarrelloBean carrello = carrellodao.doRetrieveByKey(codice_carrello);
		
		Connection connection2 = null;
		PreparedStatement preparedStatement2 = null;

		// modifica totale nel carrello
		String modificaSQL = "UPDATE Carrello SET totale = ? WHERE codice_carrello = ?";

		try {
			connection2 = DriverManagerConnectionPool.getConnection(db, username, password);

			preparedStatement2 = connection2.prepareStatement(modificaSQL);
			
			preparedStatement2.setDouble(1, carrello.getTotale() - annuncio.getPrezzo());
			preparedStatement2.setString(2, codice_carrello);
			
			// aggiorna totale carrello
			preparedStatement2.executeUpdate();

		} finally {
			try {
				if (preparedStatement2 != null)
					preparedStatement2.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection2);
			}
		}
			
		return "Successo";
	}
	
	// verifica se l'utente è amministratore
	public boolean isAmministratore(String email) throws SQLException
	{
		return doRetrieveByKey(email).getTipo_utente().equals(Tipo_utente.Amministratore);
	}
	

}