package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

public class BookBag implements Serializable {
	private ArrayList<Textbook> bookList;
	
	private static BookBag bookBag;
	
	private BookBag() {
		bookList = new ArrayList<Textbook>((Integer)Settings.MIN_BOOKBAG_SIZE.getValue());
	}
	
	public static BookBag getBookBag() { 
		if (bookBag == null) {
			bookBag = new BookBag();
		} 
		return bookBag;
	}
	
	public static void setBookBag(BookBag bookBag) {
		BookBag.bookBag = bookBag;
	}

	public void insert(Textbook textbook) {
		//if the Textbook being inserted has the same ISBN of an existing textbook, then it won't be inserted and an exception will be thrown
		bookList.forEach(book -> {
			if (book.getIsbn().equals(textbook.getIsbn())) {
				throw new DuplicateISBNException("Duplicate ISBN!");
			}	
		});
		bookList.add(textbook);
	}
	
	public Optional<Textbook> search(Predicate<Textbook> predicate) {
		for (int i = 0; i < bookList.size(); i++) {
			if (predicate.test(bookList.get(i))) {
				return Optional.of(bookList.get(i));
			}
		}
		return Optional.ofNullable(null);
	}
	
	public Optional<Textbook> remove(Predicate<Textbook> predicate) {
		for (int i = 0; i < bookList.size(); i++) {
			if (predicate.test(bookList.get(i))) {
				return Optional.of(bookList.remove(i));
			}
		}
		return Optional.ofNullable(null);
	}
	
	public Optional<Textbook> update(Predicate<Textbook> predicate, String title, Name author, double price) {
		for (int i = 0; i < bookList.size(); i++) {
			if (predicate.test(bookList.get(i))) {
				bookList.get(i).setTitle(title);
				bookList.get(i).setAuthor(author);
				bookList.get(i).setPrice(price);
				
				return Optional.of(bookList.get(i));
			}
		}
		return Optional.ofNullable(null);
	}
	
	public void display() {
		bookList.forEach((book) -> System.out.println(book));
	}
	
	public int getSize() {
		return bookList.size();
	}
}
