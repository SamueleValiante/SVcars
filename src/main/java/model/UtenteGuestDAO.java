package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UtenteGuestDAO implements InterfaceDataAccessObject<UtenteGuestBean>
{
	private static final String TABLE_NAME = "UtenteGuest";
	private final String db = "svcars_db";
	private final String username = "samuele";
	private final String password = "samuele99";


	@Override
	public synchronized void doSave(UtenteGuestBean utenteGuest) throws SQLException {
		
		System.out.print("sto eseguendo il doSave di guest!!!");
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UtenteGuestDAO.TABLE_NAME
				+ " (codice_utente, codice_carrello) VALUES (?, ?)";
		
		// salvo il suo carrello nel database
		CarrelloDAO dao = new CarrelloDAO();
		dao.doSave(new CarrelloBean(utenteGuest.getCodice_carrello(), 0, new ArrayList<AnnuncioBean>()));
		

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utenteGuest.getCodice_utente());
			preparedStatement.setString(2, utenteGuest.getCodice_carrello());

			// salvo l'utente nel database
			preparedStatement.executeUpdate();
			

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
	public synchronized UtenteGuestBean doRetrieveByKey(String codice_utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UtenteGuestBean bean = new UtenteGuestBean();

		String selectSQL = "SELECT * FROM " + UtenteGuestDAO.TABLE_NAME + " WHERE codice_utente = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codice_utente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice_utente(rs.getString("codice_utente"));
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
	public synchronized boolean doDelete(String codice_utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + UtenteGuestDAO.TABLE_NAME + " WHERE codice_utente = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, codice_utente);
			
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
	public synchronized List<UtenteGuestBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<UtenteGuestBean> utentiGuest = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + UtenteGuestDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UtenteGuestBean bean = new UtenteGuestBean();

				bean.setCodice_utente(rs.getString("codice_utente"));
				bean.setCodice_carrello(rs.getString("codice_carrello"));
				utentiGuest.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return utentiGuest;
	}
	
	// passa a utente iscritto
	public void daGuestAiscritto(String codice_utente, UtenteIscrittoBean utente) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		
		// cancella record utente guest da db
		String deleteSQL = "DELETE FROM " + UtenteGuestDAO.TABLE_NAME + " WHERE codice_utente = ?";
		
		// associa il carrello all'utente iscritto
		String updateSQL = "UPDATE UtenteIscritto SET codice_carrello = ? WHERE e_mail = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement2 = connection.prepareStatement(updateSQL);
			
			// Ottiene il codice carrello del guest prima di eliminarlo
			String codice_carrello = doRetrieveByKey(codice_utente).getCodice_carrello();
			
			preparedStatement.setString(1, codice_utente);
			
			preparedStatement2.setString(1, codice_carrello);
			preparedStatement2.setString(2, utente.getEmail());
			
			preparedStatement2.executeUpdate();
			preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
}

