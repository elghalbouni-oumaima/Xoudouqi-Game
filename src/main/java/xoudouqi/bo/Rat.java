package xoudouqi.bo;

import xoudouqi.exceptions.ImpossibleMoveException;

public class Rat extends Piece{
	public Rat(Position p) {
		super(8, "Rat",1,p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(String cmd) throws ImpossibleMoveException {
		switch (cmd.toLowerCase()) {
			case "up" :
				if(this.getPosition().getY() == 8 ) {
					throw new ImpossibleMoveException();
				}
				else {
					this.getPosition().setY(this.getPosition().getY() + 1);
				}
				break;
			case "down" :
				if(this.getPosition().getY() == 0 ) {
					throw new ImpossibleMoveException();
				}
				else {
					this.getPosition().setY(this.getPosition().getY() - 1);
				}
				break;
				
			case "right" :
				if(this.getPosition().getX() == 0 ) 
				{
					throw new ImpossibleMoveException();
				}
				else {
					this.getPosition().setX(this.getPosition().getX() - 1);
				}
				break;
				
			case "left" :
				if( this.getPosition().getX() == 6 ) 
				{
					throw new ImpossibleMoveException();
				}
				else {
					this.getPosition().setX(this.getPosition().getX() + 1);
				}
				break;
			
		}
	}
	
	

}
