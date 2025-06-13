package xoudouqi.exceptions;

public class WrongAcount  extends Exception{
	
	public WrongAcount() {
		super("This email does not exist");
	}
}
