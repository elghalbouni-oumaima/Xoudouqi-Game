package xoudouqi.exceptions;

import xoudouqi.bo.Joueur;

public class Gameover extends Exception{
	public Gameover(int winner) {
		super("The game is over 👏!\nThe winner is : Player " + winner);
	}
}
