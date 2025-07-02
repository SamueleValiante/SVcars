package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FatturaDAO implements InterfaceDataAccessObject<FatturaBean>
{
	private static final String TABLE_NAME = "Fattura";
	private final String db = "svcars_db";
	private final String username = "samuele";
	private final String password = "samuele99";


	@Override
	public synchronized void doSave(FatturaBean fattura) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + FatturaDAO.TABLE_NAME
				+ " (codice_fattura) VALUES (?)";
		

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, fattura.getCodice_fattura());

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
	public synchronized FatturaBean doRetrieveByKey(String codice_fattura) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		FatturaBean bean = new FatturaBean();

		String selectSQL = "SELECT * FROM " + FatturaDAO.TABLE_NAME + " WHERE codice_fattura = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codice_fattura);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice_fattura(rs.getString("codice_fattura"));
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
	public synchronized boolean doDelete(String codice_fattura) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + FatturaDAO.TABLE_NAME + " WHERE codice_fattura = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, codice_fattura);

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
	public synchronized List<FatturaBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<FatturaBean> fatture = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + FatturaDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				FatturaBean bean = new FatturaBean();

				bean.setCodice_fattura(rs.getString("codice_fattura"));
				fatture.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return fatture;
	}
}

