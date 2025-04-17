package exceptions;

public class NUmberLengthException extends RuntimeException{

	public NUmberLengthException() {
		super("Value for number needs to be positive");
	}

}
