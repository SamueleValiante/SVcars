package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AnnuncioDAO implements InterfaceDataAccessObject<AnnuncioBean>
{
	private static final String TABLE_NAME = "Annuncio";
	
	private final String db = "svcars_db";
	private final String username = "samuele";
	private final String password = "samuele99";


	// funzione che salva un nuovo annuncio creato nel database
	@Override
	public synchronized void doSave(AnnuncioBean annuncio) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		// salva annuncio nell apposita tabella
		String insertSQL = "INSERT INTO " + AnnuncioDAO.TABLE_NAME
				+ " (targa, titolo, prezzo, tipologia, colore, km, anno, carburante, marca, modello, cilindrata, n_porte, citta, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		
		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, annuncio.getTarga());
			preparedStatement.setString(2, annuncio.getTitolo());
			preparedStatement.setDouble(3, annuncio.getPrezzo());
			preparedStatement.setString(4, annuncio.getTipologia());
			preparedStatement.setString(5, annuncio.getColore());
			preparedStatement.setInt(6, annuncio.getKm());
			preparedStatement.setInt(7, annuncio.getAnno());
			preparedStatement.setString(8, annuncio.getCarburante());
			preparedStatement.setString(8, annuncio.getMarca());
			preparedStatement.setString(9, annuncio.getModello());
			preparedStatement.setInt(10, annuncio.getCilindrata());
			preparedStatement.setInt(11, annuncio.getN_porte());
			preparedStatement.setString(12, annuncio.getCitta());
			preparedStatement.setString(13, annuncio.getEmail());

			preparedStatement.executeUpdate();

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
		
	}


	@Override
	public synchronized AnnuncioBean doRetrieveByKey(String targa) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		AnnuncioBean bean = new AnnuncioBean();

		String selectSQL = "SELECT * FROM " + AnnuncioDAO.TABLE_NAME + " WHERE targa = ? AND visibilita = true;";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, targa);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setTarga(rs.getString("targa"));
				bean.setTitolo(rs.getString("titolo"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setTipologia(rs.getString("tipologia"));
				bean.setColore(rs.getString("colore"));
				bean.setKm(rs.getInt("km"));
				bean.setAnno(rs.getInt("anno"));
				bean.setCarburante(rs.getString("carburante"));
				bean.setMarca(rs.getString("marca"));
				bean.setModello(rs.getString("modello"));
				bean.setCilindrata(rs.getInt("cilindrata"));
				bean.setN_porte(rs.getInt("n_porte"));
				bean.setCitta(rs.getString("citta"));
				bean.setEmail(rs.getString("email"));
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

	// elimina un annuncio
	@Override
	public synchronized boolean doDelete(String targa) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;

		// modifica la visibilità dell annuncio
		String modificaSQL = "UPDATE Annunci SET visibilita = false WHERE targa = " + targa;

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);

			preparedStatement = connection.prepareStatement(modificaSQL);
			
			// rende non visibile l'annuncio e ne cancella i riferimenti da tutti i carrelli che lo contengono
			result = preparedStatement.executeUpdate() + cancellaRiferimenti(targa);

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
	public synchronized List<AnnuncioBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<AnnuncioBean> annunci = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + AnnuncioDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				AnnuncioBean bean = new AnnuncioBean();

				bean.setTarga(rs.getString("targa"));
				bean.setTitolo(rs.getString("titolo"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setTipologia(rs.getString("tipologia"));
				bean.setColore(rs.getString("colore"));
				bean.setKm(rs.getInt("km"));
				bean.setAnno(rs.getInt("anno"));
				bean.setCarburante(rs.getString("carburante"));
				bean.setMarca(rs.getString("marca"));
				bean.setModello(rs.getString("modello"));
				bean.setCilindrata(rs.getInt("cilindrata"));
				bean.setN_porte(rs.getInt("n_porte"));
				bean.setCitta(rs.getString("citta"));
				bean.setEmail(rs.getString("email"));
				annunci.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return annunci;
	}
	
	// cancella riferimenti all'annuncio
	public int cancellaRiferimenti(String targa) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		
		int result = 0;

		String selectSQL = "SELECT * FROM Contiene WHERE targa=?";
		String deleteSQL = "DELETE FROM Contiene WHERE codice_carrello = ?";


		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement2 = connection.prepareStatement(deleteSQL);
			
			preparedStatement.setString(1, targa);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				// ottiene il codice carrello corrispondente alla targa
				String codice_carrello = rs.getString("codice_carrello");
				
				// cancella il riferimento da contiene
				preparedStatement2.setString(1, codice_carrello);
				result = preparedStatement2.executeUpdate();
				
				// decremento totale del carrello
				
				// ottiene carrello che conteneva l'annuncio
				CarrelloDAO carrellodao = new CarrelloDAO();
				CarrelloBean carrello = carrellodao.doRetrieveByKey(codice_carrello);
				
				Connection connection2 = null;
				PreparedStatement preparedStatement3 = null;

				// modifica totale nel carrello
				String modificaSQL = "UPDATE Carrello SET totale = ? WHERE codice_carrello = " + codice_carrello;

				try {
					connection2 = DriverManagerConnectionPool.getConnection(db, username, password);

					preparedStatement3 = connection2.prepareStatement(modificaSQL);
					
					preparedStatement3.setDouble(1, carrello.getTotale() - doRetrieveByKey(targa).getPrezzo());
					
					// aggiorna totale carrello
					preparedStatement3.executeUpdate();

				} finally {
					try {
						if (preparedStatement3 != null)
							preparedStatement3.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection2);
					}
				}
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
	
	// ricerca un annuncio
	public List<AnnuncioBean> ricercaAnnuncio(AnnuncioBean annuncio) throws SQLException
	{
		List<AnnuncioBean> annunci = new ArrayList<>();
		List<AnnuncioBean> annuncidb = new ArrayList<>();
		
		// ottengo tutti gli annunci dal db
		AnnuncioDAO dao = new AnnuncioDAO();
		annuncidb = dao.doRetrieveAll("titolo");
		
		
		
		return annunci;
	}
		
	// riprende annuncio precedentemente cancellato
	public void restoreAnnuncio(String targa) throws SQLException
	{
		// imposta visibile l'annuncio
		doRetrieveByKey(targa).setVisibilita(true);
	}
	
	
}
