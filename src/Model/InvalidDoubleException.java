package Model;

public class InvalidDoubleException extends RuntimeException {
	//will be thrown if the user enters a double that doesn't meet the established criteria
	public InvalidDoubleException (String message) {
		super(message);
	}
}
