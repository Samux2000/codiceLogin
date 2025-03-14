package codiceLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CodiceLogin {

	public static boolean login(String username, String password) {
		// Connessione al database
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseConnection.getConnection();

			// Query sicura con PreparedStatement
			String query = "SELECT * FROM utenti WHERE nome = ? AND password = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// Login avvenuto con successo
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				return true;
			} else {
				// Credenziali errate
				System.out.println("Utente o Password scorrette");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			// Chiusura risorse
			closeResources(conn, pstmt, rs);
		}
	}

	private static void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		// Chiusura di connessioni e statement
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		login("sam--", "dib");
	}
}
