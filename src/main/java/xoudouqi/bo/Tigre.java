package xoudouqi.bo;

import xoudouqi.exceptions.ImpossibleMoveException;

public class Tigre extends Piece {
	public Tigre(Position p) {
		super(3, "Tigre",6,p);
	}

	@Override
	public void move(String cmd) throws ImpossibleMoveException {
		switch (cmd.toLowerCase()) {
			case "up" :
				if(this.getPosition().getY() == 8 ) {
					throw new ImpossibleMoveException();
				}
				else if
						(
						this.getPosition().equals(new Position(1,2))
						|| this.getPosition().equals(new Position(2,2))
						|| this.getPosition().equals(new Position(4,2))
						|| this.getPosition().equals(new Position(5,2))
						) {
					this.getPosition().setY(this.getPosition().getY() + 4);
					
				}
				else {
					this.getPosition().setY(this.getPosition().getY() + 1);
				}
				break;
			case "down" :
				if(this.getPosition().getY() == 0 ) {
					throw new ImpossibleMoveException();
				}
				else if
						(
						this.getPosition().equals(new Position(1,6))
						|| this.getPosition().equals(new Position(2,6))
						|| this.getPosition().equals(new Position(4,6))
						|| this.getPosition().equals(new Position(5,6))
						) {
					this.getPosition().setY(this.getPosition().getY() - 4);
					
				}
				else {
					this.getPosition().setY(this.getPosition().getY() - 1);
				}
				break;
				
			case "right" :
				if(this.getPosition().getX() == 0 ) {
					throw new ImpossibleMoveException();
				}
				else if
						(
							(this.getPosition().getX() == 3 || this.getPosition().getX() == 6)
							&& this.getPosition().getY() >= 3
							&& this.getPosition().getY() <= 5
						) {
					this.getPosition().setX(this.getPosition().getY() - 3);
					
				}
				else {
					this.getPosition().setX(this.getPosition().getY() - 1);
				}
				break;
				
			case "left" :
				if(this.getPosition().getX() == 6 ) {
					throw new ImpossibleMoveException();
				}
				if(
					(this.getPosition().getX() == 3 || this.getPosition().getX() == 0)
					&& this.getPosition().getY() >= 3
					&& this.getPosition().getY() <= 5
						
				) 
				{
					this.getPosition().setX(this.getPosition().getY()  + 3);				}
				else {
					this.getPosition().setX(this.getPosition().getX() + 1);
				}
				break;
			
		}
	}
	

}
