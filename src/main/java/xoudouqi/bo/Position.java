package xoudouqi.bo;

import java.util.Objects;

public class Position {
	private int x,y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int  getX() {
		return x;
	}
	
	public int  getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || o.getClass() != Position.class) return false;
		return this.x == ((Position)o).x && this.y == ((Position)o).y; 
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x,y);
	}

}
