package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool 
{
	// creo una lista di connessioni al DB
	private static List<Connection> connessioniDisponibili;
	
	// inizializza la lista di connessioni
	static 
	{
		connessioniDisponibili = new LinkedList<Connection>();
		
		// caricamento classe driver jdbc
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("DB driver not found:"+ e.getMessage());
		} 
	}
	
	// funzione che crea la connessione e la restituisce come istanza di oggetto
	private static synchronized Connection creaConnessioneDB(String db, String username, String password) throws SQLException {
		Connection newConnection = null;
		String ip = "localhost";
		String port = "3306";
		
		
		
		newConnection = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db, username, password);
		return newConnection;
	}
	
	// funzione che verifica dì se vi sono connessioni disponibili
	// se ci sono  ne assegna una
	// altrinmenti la crea
	public static synchronized Connection getConnection(String db, String username, String password) throws SQLException {
		Connection connection;

		if (!connessioniDisponibili.isEmpty()) {
			connection = connessioniDisponibili.get(0);
			connessioniDisponibili.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection(db, username, password);
			} catch (SQLException e) {
				connection.close();
				connection = getConnection(db, username, password);
			}
		} else {
			connection = creaConnessioneDB(db, username, password);	
		}

		return connection;
	}
	
	// funzione che chiude la connessione
	public static synchronized void releaseConnection(Connection connection) throws SQLException {
		if(connection != null) connessioniDisponibili.add(connection);
	}
}
