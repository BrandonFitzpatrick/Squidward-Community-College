package Model;

public class InvalidStringException extends RuntimeException {
	//will be thrown if the user enters a String that doesn't meet the established criteria
	public InvalidStringException(String message) {
		super(message);
	}
}
