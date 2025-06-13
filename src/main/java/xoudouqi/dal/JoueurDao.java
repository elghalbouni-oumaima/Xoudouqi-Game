package xoudouqi.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import xoudouqi.bo.Joueur;
import xoudouqi.exceptions.WrongAcount;

public class JoueurDao {
	
	public Joueur create (String email,String password) throws WrongAcount {
		
		try {
			if(exist(email,password) != null) {
				return null;
			}
			Connection cnn = DbConnexion.getConnection();
			String sql = "insert into joueurs (email,password,score,victoires,defaites) values (?,?,0,0,0)";
			PreparedStatement stm = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, email);
			stm.setString(2, password);
			
			stm.execute();
		}catch (SQLException e) {
            throw new DatabaseException("Erreur lors du chargement du pilote ou de la connexion", e);
		}
		
		return new Joueur(email,password,0,0,0,0);
	}
	
	public void update (Joueur j) {
		try {
			Connection cnn = DbConnexion.getConnection();
			String sql = "UPDATE joueurs  SET score =  ?, victoires =  ?,  defaites =  ? WHERE id = ?;";
			PreparedStatement stm = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stm.setLong(1, j.getScore());
			stm.setLong(2, j.getVictoires());
			stm.setLong(3, j.getDefaites());
			stm.setLong(4, j.getId());
			stm.execute();
		}catch (Exception e) {
            throw new DatabaseException("Erreur lors du chargement du pilote ou de la connexion", e);
		}
	}
	
	public Joueur exist (String email,String password) throws WrongAcount {
		
		int id = -1;
		try {
			Connection cnn = DbConnexion.getConnection();
			String sql = "select email,password,id,score,victoires,defaites from joueurs where email = ?;";
			PreparedStatement stm = cnn.prepareStatement(sql);
			stm.setString(1,email);
			ResultSet rs =stm.executeQuery();
			if(rs.next()) {
				if(rs.getString("password").equals(password)) {
					return new Joueur(email,password ,rs.getInt("id"),rs.getInt("score"),rs.getInt("victoires"), rs.getInt("defaites"));
					
				}
			}
			
			else {
				throw new WrongAcount();
			}
		}catch (SQLException  e) {
            throw new DatabaseException("Erreur lors du chargement du pilote ou de la connexion", e);
		}
		return null;
	}
	
}
