package xoudouqi.bo;

import xoudouqi.exceptions.ImpossibleMoveException;

public abstract class Piece {
	private int rang;
	private String name;
	private final int score ;
	private Position pos;
	public Piece(int r,String n,int s,Position p) {
		rang = r;
		name = n;
		score = s;
		pos =p ;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}
	
	public int getRang() {
		return rang;
	}
	
	public Position getPosition() {
		return pos;
	}
	
	public void setPosition(Position p) {
		pos = p;
	}
	public abstract void move(String cmd) throws  ImpossibleMoveException;

}
