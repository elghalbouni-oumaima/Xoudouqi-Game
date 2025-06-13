package xoudouqi.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnexion {

    private static final String URL = "jdbc:h2:~/xoudouqi/.data";
    private static final String USER = "sa";
    private static final String PASS = "";
    // Plus besoin de charger le driver manuellement avec JDBC 4+

    private static Connection con;

    private DbConnexion()  {
        try {
        	Class.forName("org.h2.Driver");  // Forcer l’enregistrement du driver
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la connexion à la base H2", e);
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                new DbConnexion(); // relance la connexion
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la vérification de la connexion", e);
        }
        return con;
    }
}



