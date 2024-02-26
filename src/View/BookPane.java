package View;

import javafx.scene.control.TextField;

//subclass of ViewPane for the textbook view
public class BookPane extends ViewPane {
	private static BookPane bookPane;
	
	private static TextField isbnField;
	
	public static BookPane getBookPane() {
		if (bookPane == null) {
			bookPane = new BookPane();
		}
		return bookPane;
	}
	
	private BookPane() {
		super();
		getStrField1().setPromptText("Title");
		getStrField2().setPromptText("Author");
		getDoubleField().setPromptText("Price");
		getUpdateField().setPromptText("ISBN");
		getSearchField().setPromptText("Search By ISBN");
		getRemoveField().setPromptText("Remove by ISBN");
		getTitle().setText("TEXTBOOKS");
		
		isbnField = new TextField();
		isbnField.setPromptText("ISBN (Can't be updated)");
		getFields2().getChildren().add(isbnField);
	}

	public TextField getIsbnField() {
		return isbnField;
	}
	
	//uses the superclass method to set the shared text fields, in addition to setting the ISBN for the subclass-exclusive insertIsbnField
	public void setFields(String str1, String str2, String str3, String isbn) {
		super.setFields(str1, str2, str3);
		isbnField.setText(isbn);
	}
	
	//uses the superclass method to clear the shared text fields, in addition to clearing the insertIsbnField
	@Override
	public void clear() {
		super.clear();
		isbnField.clear();
	}
}
