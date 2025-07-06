package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrdineDAO implements InterfaceDataAccessObject<OrdineBean>
{
	private static final String TABLE_NAME = "Ordine";
	private final String db = "svcars_db";
	private final String username = "samuele";
	private final String password = "samuele99";


	@Override
	public synchronized void doSave(OrdineBean ordine) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		
		String insertSQL2 = "INSERT INTO Acquista (codice_ordine, targa) VALUES (?, ?)"; 

		String insertSQL = "INSERT INTO " + OrdineDAO.TABLE_NAME
				+ " (codice_ordine, "
				+ "indirizzo_origine, "
				+ "indirizzo_destinazione, "
				+ "costo_prodotti, "
				+ "costo_spedizione, "
				+ "totale, "
				+ "data_acquisto, "
				+ "tempo_spedizione, "
				+ "codice_fattura, "
				+ "e_mail) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, ordine.getCodice_ordine());
			preparedStatement.setString(2, ordine.getIndirizzo_origine());
			preparedStatement.setString(3, ordine.getIndirizzo_destinazione());
			preparedStatement.setDouble(4, ordine.getCosto_prodotti());
			preparedStatement.setDouble(5, ordine.getCosto_spedizione());
			preparedStatement.setDouble(6, ordine.getTotale());
			preparedStatement.setDate(7, ordine.getDataAcquisto());
			preparedStatement.setString(8, ordine.getTempo_spedizione());
			preparedStatement.setString(9, ordine.getCodiceFattura());
			preparedStatement.setString(10, ordine.getEmail_compratore());
			
			preparedStatement2 = connection.prepareStatement(insertSQL2);
			
			preparedStatement.executeUpdate();
			
			// aggiunge ogni annuncio alla tabella Acquista
			for(AnnuncioBean annuncio : ordine.getProdotti())
			{
				preparedStatement2.setString(1, ordine.getCodice_ordine());
				preparedStatement2.setString(2, annuncio.getTarga());
				
				preparedStatement2.executeUpdate();
			}
			
			// salva la fattura relativa all'ordine all'interno della tabella Fattura
			FatturaDAO daofattura = new FatturaDAO();
			daofattura.doSave(new FatturaBean(ordine.getCodiceFattura()));

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
	}

	@Override
	public synchronized OrdineBean doRetrieveByKey(String codice_ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrdineBean bean = new OrdineBean();

		String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " WHERE codice_ordine = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codice_ordine);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice_ordine(rs.getString("codice_ordine"));
				bean.setIndirizzo_origine(rs.getString("indirizzo_origine"));
				bean.setIndirizzo_destinazione(rs.getString("indirizzo_destinazione"));
				bean.setProdotti(getAnnunciOrdine(codice_ordine));
				bean.setCosto_prodotti(rs.getDouble("costo_prodotti"));
				bean.setTotale(rs.getDouble("totale"));
				bean.setDataAcquisto(rs.getDate("data_acquisto"));
				bean.setTempo_spedizione(rs.getString("tempo_spedizione"));
				bean.setCodiceFattura(rs.getString("codice_fattura"));
				bean.setEmail_compratore(rs.getString("e_mail"));     
				
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
	public synchronized boolean doDelete(String codice_ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OrdineDAO.TABLE_NAME + " WHERE codice_ordine = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(deleteSQL);
			FatturaDAO dao = new FatturaDAO();
			preparedStatement.setString(1, codice_ordine);

			
			// elimina il riferimento all ordine e la fattura ad esso associata
			String codiceFattura = doRetrieveByKey(codice_ordine).getCodiceFattura();
			result = preparedStatement.executeUpdate(); 
			dao.doDelete(codiceFattura);

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
	public synchronized List<OrdineBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<OrdineBean> ordini = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();

				bean.setCodice_ordine(rs.getString("codice_ordine"));
				bean.setIndirizzo_origine(rs.getString("indirizzo_origine"));
				bean.setIndirizzo_destinazione(rs.getString("indirizzo_destinazione"));
				bean.setProdotti(getAnnunciOrdine(rs.getString("codice_ordine")));
				bean.setCosto_prodotti(rs.getDouble("costo_prodotti"));
				bean.setTotale(rs.getDouble("totale"));
				bean.setDataAcquisto(rs.getDate("data_acquisto"));
				bean.setTempo_spedizione(rs.getString("tempo_spedizione"));
				bean.setCodiceFattura(rs.getString("codice_fattura"));
				bean.setEmail_compratore(rs.getString("e_mail"));
				
				ordini.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;
	}
	
	// ottiene tutti gli annunci che contiene l'ordine
	public List<AnnuncioBean> getAnnunciOrdine(String codice_ordine) throws SQLException
	{
		List<AnnuncioBean> annunci = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		// ottiene la targa di ogni riferimento al codice_carrello
		String selectSQL = "SELECT targa FROM Acquista WHERE codice_ordine = ?";


		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
				
			preparedStatement.setString(1, codice_ordine);

			ResultSet rs = preparedStatement.executeQuery();

			// genera il DAO
			AnnuncioDAO dao = new AnnuncioDAO();
				
			while (rs.next()) {
				annunci.add(dao.doRetrieveByKey(rs.getString("targa")));
			}

		} 
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
			
		return annunci;
	}
	
	// cancella riferimenti all ordine
	public int cancellaRiferimenti(String codice_ordine) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
				
		int result = 0;

		// cancella tutte le tuple che coincidono con il codice ordine
		String selectSQL = "SELECT targa FROM Acquista WHERE codice_ordine = ?";
		String deleteSQL = "DELETE FROM Acquista WHERE codice_ordine = ? AND targa = ?";


		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement2 = connection.prepareStatement(deleteSQL);		
			
			preparedStatement.setString(1, codice_ordine);
			preparedStatement2.setString(1, codice_ordine);
			
			ResultSet rs = preparedStatement.executeQuery();

			// ottiene tutte le tuple che coincidono con il codice_ordine
			while (rs.next()) 
			{
				// cancella il riferimento da acquista
				preparedStatement2.setString(2, rs.getString("targa"));
				result += preparedStatement2.executeUpdate();	
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
	
	
	// calcola il totale dell'ordine
	public double getTotaleProdotti(String codice_ordine) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		double totale = 0;

		String selectSQL = "SELECT targa FROM Acquista WHERE codice_ordine = ? ";


		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, codice_ordine);

			ResultSet rs = preparedStatement.executeQuery();
			
			

			while (rs.next()) 
			{
				// ottiene la targa corrispondente al codice_ordine
				String targa = rs.getString("targa");
				
				// ottiene annuncio riferito alla targa
				AnnuncioDAO dao = new AnnuncioDAO();
				
				// somma totale
				totale += dao.doRetrieveByKey(targa).getPrezzo();
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return totale;
	}
	
	
}


