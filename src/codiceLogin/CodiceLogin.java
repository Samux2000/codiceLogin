package codiceLogin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CodiceLogin {

	public static boolean login(String username, String password) {
		// Connessione al database
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseConnection.getConnection();
			stmt = conn.createStatement();

			// Query SQL costruita in modo insicuro
			String query = "SELECT * FROM utenti WHERE nome = '" + username + "' AND password = '" + password + "'";

			rs = stmt.executeQuery(query);

			if (rs.next()) {
				System.out.println(rs.getString(3));
				return true;
			} else {
				System.out.println("Utente o Password scorretti");
				// Credenziali errate
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			// Chiusura risorse
			closeResources(conn, stmt, rs);
		}
	}

	private static void closeResources(Connection conn, Statement stmt, ResultSet rs) {
		// Chiusura di connessioni e statement
	}

	public static void main(String[] args) {
    	login("sam' OR '1'='1", "");
    }
}