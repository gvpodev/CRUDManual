package posjavajdbc.posjavajddbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class App {
	private static String url = "jdbc:postgresql://localhost:5433/bancojava";
	private static String password = "q1w2e3";
	private static String user = "postgres";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public App() {
		conectar();
	}
	
	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Conectou 1a vez.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
}
