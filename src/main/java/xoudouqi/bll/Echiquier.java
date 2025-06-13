package xoudouqi.bll;

import java.util.ArrayList;
import java.util.Arrays;

import xoudouqi.bo.Chat;
import xoudouqi.bo.Chien;
import xoudouqi.bo.Elephant;
import xoudouqi.bo.Joueur;
import xoudouqi.bo.Lion;
import xoudouqi.bo.Loup;
import xoudouqi.bo.Panthere;
import xoudouqi.bo.Piece;
import xoudouqi.bo.Position;
import xoudouqi.bo.Rat;
import xoudouqi.bo.Tigre;
import xoudouqi.exceptions.Gameover;
import xoudouqi.exceptions.ImpossibleMoveException;

public class Echiquier {
	private Joueur[] joueur = new Joueur[2];
	private Position [] riviere1 ;
	private Position [] riviere2 ;
	public Echiquier(Joueur j1 , Joueur j2) {
		
		riviere1 = new Position [] {
				new Position(1,3),
				new Position(1,4),
				new Position(1,5),
				new Position(2,3),
				new Position(2,4),
				new Position(2,5)
		};
		riviere2 = new Position [] {
				new Position(4,3),
				new Position(4,4),
				new Position(4,5),
				new Position(5,3),
				new Position(5,4),
				new Position(5,5)
		};
		joueur[0] = j1;
		joueur[1] = j2;
		
	
		
		joueur[1].setAnimaux(
				new ArrayList<>(Arrays.asList (
						new Chat(new Position(1,7)),
						new Chien(new Position(5,7)),
						new Elephant(new Position(0,6)),
						new Lion(new Position(6,8)),
						new Tigre(new Position(0,8)),
						new Panthere(new Position(4,6)),
						new Loup(new Position(2,6)),
						new Rat(new Position(6,6))
				)), 
				new Position[] {
						new Position(2,8),
						new Position(4,8),
						new Position(3,7)
						}, 
				new Position(3,8)
				);
		
		joueur[0].setAnimaux(
				new ArrayList<>(Arrays.asList (
						new Chat(new Position(5,1)),
						new Chien(new Position(1,1)),
						new Elephant(new Position(6,2)),
						new Lion(new Position(0,0)),
						new Tigre(new Position(6,0)),
						new Panthere(new Position(2,2)),
						new Loup(new Position(4,2)),
						new Rat(new Position(0,2))
				)), 
				new Position[] {
						new Position(2,0),
						new Position(4,0),
						new Position(3,1)
						}, 
				new Position(3,0)
				);
		
		
	}
	
	public static boolean isInList(Position[] animals, Position target) {
	    for (Position animal : animals) {
	        if (animal.equals(target)) {
	            return true;
	        }
	    }
	    return false;
	}

	public void affiche() {
		final String ANSI_GREEN = "\u001B[32m";
	    final String ANSI_RESET = "\u001B[0m";
	    final String ANSI_ORANGE = "\u001B[38;5;208m";
	    final String ANSI_BLUE = "\u001B[34m";

		int k =0;
		for(int i =0 ; i<9 ; i++) {
			for(int j = 0; j<7 ; j++) {
				k = 0;
				if(isInList( joueur[1].getPiege(), new Position(j,i)) || isInList( joueur[0].getPiege(), new Position(j,i))){
					System.out.print(ANSI_GREEN + "#\t" + ANSI_RESET );
					k = 1;
				}
				else if(isInList(riviere1, new Position(j,i)) || isInList(riviere2, new Position(j,i))){
					System.out.print(ANSI_BLUE + "#\t" + ANSI_RESET );
					k = 1;
				}
				
				
				else if(joueur[0].getSanctuaire().equals(new Position(j,i)) || joueur[1].getSanctuaire().equals(new Position(j,i))) {
					System.out.print(ANSI_ORANGE + "#\t" + ANSI_RESET );
					k = 1;
				}
				for(Piece animal : joueur[0].getAnimaux()) {
					if(animal.getPosition().equals(new Position(j,i))) {
						System.out.print(animal.getName().charAt(0) + "1\t" );
						k = 1;
						break;
					}
				}
				
				for(Piece animal : joueur[1].getAnimaux()) {
					if(animal.getPosition().equals(new Position(j,i))) {
						System.out.print( animal.getName().charAt(0) + "2\t");
						k = 1;
						break;
					}
				}
				
				
				if(k == 0) {
					System.out.print("*\t");
				}
			}
			System.out.println();
			System.out.println();

		}
	}
	
	public Joueur[] getJoueur() {
		return joueur;
	}
	public void handleGame (int indexA,String dir, int indexJ) throws Exception {
		int indexEnnemie = indexJ ==0?1:0;
		Position p = joueur[indexJ].getAnimaux().get(indexA).getPosition();
		joueur[indexJ].getAnimaux().get(indexA).move(dir);
		for(Piece animal : joueur[indexEnnemie].getAnimaux()) {
			if(animal.getPosition().equals( joueur[indexJ].getAnimaux().get(indexA).getPosition())) {
				if(animal.getRang()<joueur[indexJ].getAnimaux().get(indexA).getRang()) {
					if(
							isInList(joueur[indexJ].getPiege(), animal.getPosition()) ||
							isInList(joueur[indexEnnemie].getPiege(), animal.getPosition())
							
							) {
						joueur[indexJ].setScore(animal.getScore());
						joueur[indexEnnemie].getAnimaux().remove(animal);
						
					}
					else {
						if(joueur[indexJ].getAnimaux().get(indexA).getName().equalsIgnoreCase("Rat") && animal.getName().equalsIgnoreCase("Elephant")) {
							joueur[indexJ].setScore(animal.getScore());
							joueur[indexEnnemie].getAnimaux().remove(animal);
						}
						else {
							joueur[indexJ].getAnimaux().get(indexA).setPosition(p);
							throw new ImpossibleMoveException();
						}
						
					}
				}
				else {
					joueur[indexJ].setScore(animal.getScore());
					joueur[indexEnnemie].getAnimaux().remove(animal);
				}
				break;
			}
		}
		
		for(Piece animal : joueur[indexJ].getAnimaux()) {
			if(!animal.getName().equals(joueur[indexJ].getAnimaux().get(indexA).getName()) && animal.getPosition().equals( joueur[indexJ].getAnimaux().get(indexA).getPosition()) ) {
				joueur[indexJ].getAnimaux().get(indexA).setPosition(p);
				throw new ImpossibleMoveException();
			}

		}
		if(joueur[indexJ].getAnimaux().get(indexA).getPosition().equals(joueur[indexEnnemie].getSanctuaire())) {
			joueur[indexJ].setVictoires(1);
			joueur[indexEnnemie].setDefaites(1);
			throw new Gameover(indexJ);
		}
	}
}
