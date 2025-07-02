package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CarrelloDAO implements InterfaceDataAccessObject<CarrelloBean>
{
	private static final String TABLE_NAME = "Carrello";
	private final String db = "svcars_db";
	private final String username = "samuele";
	private final String password = "samuele99";


	@Override
	public synchronized void doSave(CarrelloBean carrello) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + CarrelloDAO.TABLE_NAME
				+ " (codice_carrello, totale) VALUES (?, ?)";
		

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, carrello.getCodice_carrello());
			preparedStatement.setDouble(2, carrello.getTotale());

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
	public synchronized CarrelloBean doRetrieveByKey(String codice_carrello) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		CarrelloBean bean = new CarrelloBean();

		String selectSQL = "SELECT * FROM " + CarrelloDAO.TABLE_NAME + " WHERE codice_carrello = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codice_carrello);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice_carrello(rs.getString("codice_carrello"));
				bean.setTotale(rs.getDouble("totale"));
				bean.setProdotti(getAnnunciCarrello(codice_carrello));
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
	public synchronized boolean doDelete(String codice_carrello) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + CarrelloDAO.TABLE_NAME + " WHERE codice_carrello = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, codice_carrello);

			result = cancellaRiferimenti(codice_carrello) + preparedStatement.executeUpdate();

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
	public synchronized List<CarrelloBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<CarrelloBean> carrelli = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + CarrelloDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				CarrelloBean bean = new CarrelloBean();

				bean.setCodice_carrello(rs.getString("codice_carrello"));
				bean.setTotale(rs.getDouble("totale"));
				bean.setProdotti(getAnnunciCarrello(rs.getString("codice_carrello")));
				carrelli.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return carrelli;
	}
	
	// cancella riferimenti al carrello
	public int cancellaRiferimenti(String codice_carrello) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
			
		int result = 0;

		String deleteSQL = "DELETE FROM Contiene WHERE codice_carrello = ?";


		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(deleteSQL);
				
			preparedStatement.setString(1, codice_carrello);

			preparedStatement.executeUpdate();

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
	
	// ottiene tutti gli annunci che contiene il carrello
	public List<AnnuncioBean> getAnnunciCarrello(String codice_carrello) throws SQLException
	{
		List<AnnuncioBean> annunci = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		// ottiene la targa di ogni riferimento al codice_carrello
		String selectSQL = "SELECT targa FROM Contiene WHERE codice_carrello = ?";


		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, codice_carrello);

			ResultSet rs = preparedStatement.executeQuery();

			// genera il DAO
			AnnuncioDAO dao = new AnnuncioDAO();
			
			while (rs.next()) {
				annunci.add(dao.doRetrieveByKey(rs.getString("targa")));
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
	
	// funzione che svuota il carrello
	public boolean svuotaCarrello(String codice_carrello) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		boolean result = true;

		String deleteSQL = "DELETE FROM Contiene WHERE codice_carrello = ?";


		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			preparedStatement.setString(1, codice_carrello);

			preparedStatement.executeUpdate();
			
			// imposta totale carrello a 0
			doRetrieveByKey(codice_carrello).setTotale(0);

		} 
		
		catch(Exception e)
		{
			System.out.println(e);
			result = false;
		}
		
		finally 
		{
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				else
					result = false;
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return result;
	}
}

