package Model;

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
            byte[] hashedBytes = md.digest(input.getBytes());

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

			// ottiene tutte le tuple che coincidono con l email
			while (rs1.next()) 
			{
				// ottiene il codice ordine 
				String codice_ordine = rs1.getString("codice_ordine");
			
				// cancella ordine
				OrdineDAO dao = new OrdineDAO();
				dao.doDelete(codice_ordine);
			}
			
			ResultSet rs2 = preparedStatement2.executeQuery();

			// ottiene tutte le tuple che coincidono con l email
			while (rs2.next()) 
			{
				// ottiene il codice ordine 
				String targa = rs2.getString("targa");
			
				// cancella annuncio
				AnnuncioDAO dao = new AnnuncioDAO();
				dao.doDelete(targa);
			}
			
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
					
		return result;
	}
	
	// effettua ordine
	
	// effettua ordine da carrello
	
	// annulla ordine
	
	// crea annuncio
	
	// modifica annuncio
	
	// elimina annuncio
	
	// aggiungi annuncio a carrello
	
	// rimuovi annuncio da carrello
	
	
	
	

}