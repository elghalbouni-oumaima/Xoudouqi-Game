package xoudouqi.bo;

import xoudouqi.exceptions.ImpossibleMoveException;

public class Loup extends Piece{
	
	public Loup(Position p) {
		super(6, "Wolf",3,p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(String cmd) throws ImpossibleMoveException {
		
		
		switch (cmd.toLowerCase()) {
			case "up" :
				if(this.getPosition().getY() == 8 
				|| this.getPosition().equals(new Position(1,2))
				|| this.getPosition().equals(new Position(2,2))
				|| this.getPosition().equals(new Position(4,2))
				|| this.getPosition().equals(new Position(5,2))
				) {
					throw new ImpossibleMoveException();
				}
				else {
					this.getPosition().setY(this.getPosition().getY() + 1);
				}
				break;
			case "down" :
				if(this.getPosition().getY() == 0 
				|| this.getPosition().equals(new Position(1,6))
				|| this.getPosition().equals(new Position(2,6))
				|| this.getPosition().equals(new Position(4,6))
				|| this.getPosition().equals(new Position(5,6))
				) {
					throw new ImpossibleMoveException();
				}
				else {
					this.getPosition().setY(this.getPosition().getY() - 1);
				}
				break;
				
			case "right" :
				if(
					this.getPosition().getX() == 0 
					|| (
							(this.getPosition().getX() == 3 || this.getPosition().getX() == 6)
							&& this.getPosition().getY() >= 3
							&& this.getPosition().getY() <= 5
						)
				) 
				{
					throw new ImpossibleMoveException();
				}
				else {
					this.getPosition().setX(this.getPosition().getX() - 1);
				}
				break;
				
			case "left" :
				if(
						this.getPosition().getX() == 6 
						|| (
								(this.getPosition().getX() == 3 || this.getPosition().getX() == 0)
								&& this.getPosition().getY() >= 3
								&& this.getPosition().getY() <= 5
							)
					) 
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
