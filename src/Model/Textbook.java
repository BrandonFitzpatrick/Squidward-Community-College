package Model;

import java.io.Serializable;

import Utils.Utilities;

public class Textbook implements Serializable {
	private String title;
	private String isbn;
	private Name author;
	private double price;
	
	//two overloaded constructors: one with parameters for manually creating an object, and a no-arg constructor which randomly generates objects
	public Textbook(String title, String isbn, Name author, double price) {
		//if title is blank, then an exception will be thrown
		if (title.isBlank()) {
			throw new InvalidStringException("Invalid Title!");
		}
		this.title = title;
		for (int i = 0; i < isbn.length(); i++) {
			char current = isbn.charAt(i);
			//if the ISBN contains any character other than a number or a dash, or the ISBN is only dashes, then an exception will be thrown
			if (!((current >= '0' && current <= '9') || current == '-')) {
				throw new InvalidStringException("Invalid ISBN!");
			}
		}
		if (isbn.isBlank() || isbn.replace("-", "").isBlank()) {
			throw new InvalidStringException("Invalid ISBN!");
		}
		this.isbn = isbn;
		for (int j = 0; j < author.toString().length(); j++) {
			char current2 = author.toString().charAt(j);
			//if the name contains any character other than a letter, dash, or space, then an exception will be thrown
			if (!((current2 >= 'A' && current2 <= 'Z') || (current2 >= 'a' && current2 <= 'z') || (current2 == '-') || (current2 == ' '))) {
				throw new InvalidStringException("Invalid Author!");
			}
		}
		//if either the first or last names are blank, or the name only consists of dashes, then an exception will be thrown
		if (author.getFirstName().isBlank() || author.getLastName().isBlank() || author.getFirstName().replace("-", "").isBlank() || author.getLastName().replace("-", "").isBlank()) {
			throw new InvalidStringException("Invalid Author!");
		}
		this.author = author;
		//if the price is less than 0 or greater than 199.99, then an exception will be thrown
		if (price < (Double)Settings.MIN_BOOK_PRICE.getValue() || price > (Double)Settings.MAX_BOOK_PRICE.getValue()) {
			throw new InvalidDoubleException("Invalid Price!");
		}
		this.price = price;
	}
	
	public Textbook() {
		String[][] arr = Utilities.emitTitleAndIsbn();
		title = arr[0][0];
		isbn = arr[0][1];
		author = Utilities.emitName();
		price = Utilities.emitPrice();
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		//if title is blank, then an exception will be thrown
		if (title.isBlank()) {
			throw new InvalidStringException("Invalid Title!");
		}
		this.title = title;
	}
	
	//no setter for ISBN
	public String getIsbn() {
		return isbn;
	}
	
	public Name getAuthor() {
		return author;
	}

	public void setAuthor(Name author) {
		for (int i = 0; i < author.toString().length(); i++) {
			char current = author.toString().charAt(i);
			//if the name contains any character other than a letter, dash, or space, then an exception will be thrown
			if (!((current >= 'A' && current <= 'Z') || (current >= 'a' && current <= 'z') || (current == '-') || (current == ' '))) {
				throw new InvalidStringException("Invalid Author!");
			}
		}
		//if either the first or last names are blank, or the name only consists of dashes, then an exception will be thrown
		if (author.getFirstName().isBlank() || author.getLastName().isBlank() || author.getFirstName().replace("-", "").isBlank() || author.getLastName().replace("-", "").isBlank()) {
			throw new InvalidStringException("Invalid Author!");
		}
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		//if the price is less than 0 or greater than 199.99, then an exception will be thrown
		if (price < (Double)Settings.MIN_BOOK_PRICE.getValue() || price > (Double)Settings.MAX_BOOK_PRICE.getValue()) {
			throw new InvalidDoubleException("Invalid Price!");
		}
		this.price = price;
	}

	@Override
	public String toString() {
		return "Title: " + title + "\nISBN: " + isbn + "\nAuthor: " + author + "\nPrice: " + price + "\n";
	}
	
	
}
