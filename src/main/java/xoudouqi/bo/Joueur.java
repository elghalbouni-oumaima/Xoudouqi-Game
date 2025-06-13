package xoudouqi.bo;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
	private String email;
	private String password;
	private int id;
	private int score;
	private int victoires;
	private int defaites;
	private List<Piece>  animaux = new ArrayList<>();
	private Position [] piege;
	private Position  sanctuaire;
	
	public Joueur(String e, String p,int id,int s ,int d ,int v) {
		email = e;
		password = p;
		this.id = id;
		score = s;
		victoires = v;
		defaites = d;
	}
	
	public void setAnimaux (  List<Piece>  a,Position [] p,Position s) {
		animaux = a;
		piege = p;
		sanctuaire = s;
		
	}
	
	public void affiche() {
		System.out.println("emial :" + email);
		System.out.println("Le score :" + score);
		System.out.println(" Victoires :" + victoires);
		System.out.println("Defaites :" + defaites);

	}
	
	public void setScore(int s) {
		score += s;
	}
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getVictoires() {
		return victoires;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setVictoires(int s) {
		 score += s;
	}
	
	public int getDefaites() {
		return defaites;
	}
	
	public void setDefaites(int d) {
		 defaites += d;
	}

	
	public int getId() {
		return id;
	}
	
	public List<Piece>  getAnimaux() {
		return animaux;
	}
	
	public Position getSanctuaire() {
		return sanctuaire;
	}
	
	public Position[] getPiege() {
		return piege;
	}

}
