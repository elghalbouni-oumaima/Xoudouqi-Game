package xoudouqi.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



public class DbInstaller {
	public  static  boolean checkIfDbExist() {
        try{
        Connection connection = DbConnexion.getConnection();

        // Vérifier si la table Livre existe
        String checkTableSQL =
                """
                        SELECT COUNT(*) AS tableCount
                        FROM INFORMATION_SCHEMA.TABLES
                        WHERE TABLE_NAME = 'joueurs';
                        """;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(checkTableSQL);

        return resultSet.next() && resultSet.getInt("tableCount") > 0;

        } catch (Exception ex) {
            throw new DatabaseException("Erreur de vérification de l'installation", ex);
        }
    }
    public static boolean install() {
        try {
            Connection connection = DbConnexion.getConnection();
            Statement statement = connection.createStatement();
            String createTableJoueursSQL = """
                    CREATE TABLE joueurs (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        email varchar(255),
                        password varchar(255),
                        score int ,
                        victoires int ,
                        defaites int
                    );
                    """;

            statement.execute(createTableJoueursSQL);

            return true;


        } catch (Exception ex) {
            throw new DatabaseException("Erreur lors de l'installation de la base de données", ex);
        }
    }

}
