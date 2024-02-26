package Model;

public class DuplicateISBNException extends RuntimeException {
	//will be thrown if the user attempts to create a textbook with an ISBN of an already existing textbook
	public DuplicateISBNException(String message) {
		super(message);
	}
}
