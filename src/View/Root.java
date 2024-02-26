package View;

import java.util.Optional;

import Model.BookBag;
import Model.DuplicateISBNException;
import Model.Instructor;
import Model.InvalidDoubleException;
import Model.InvalidStringException;
import Model.Majors;
import Model.Name;
import Model.Person;
import Model.PersonBag;
import Model.Ranks;
import Model.Student;
import Model.Textbook;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

//contains the BorderPane that makes up the entire view of the window, in addition to all of the button functionality and the ability to switch views
public class Root {
	private BorderPane borderPane;
	
	private static Root root;
	
	public static Root getRoot() {
		if (root == null) {
			root = new Root();
		}
		return root;
	}
	
	private Root() {
		HeaderPane headerPane = HeaderPane.getHeaderPane();
		HomepagePane homepagePane = HomepagePane.getHomepagePane();
		StudentPane studentPane = StudentPane.getStudentPane();
		InstructorPane instructorPane = InstructorPane.getInstructorPane();
		BookPane bookPane = BookPane.getBookPane();
		ViewSwitchPane viewSwitchPane = ViewSwitchPane.getViewSwitchPane();
		
		borderPane = new BorderPane();
		borderPane.setPadding(new Insets(25));
		borderPane.setTop(new VBox(headerPane.getHeader()));
		borderPane.setCenter(homepagePane.getHomepage());
		borderPane.setBottom(viewSwitchPane.getViewSwitch());
		
		viewSwitchPane.getToStudentViewBtn().setOnAction(e -> {
			borderPane.setCenter(studentPane.getCenterPane());
			//if the homepage view button is present, it will be replaced with either the instructor or book view button depending on its position
			if (viewSwitchPane.getViewSwitch().getChildren().contains(viewSwitchPane.getToHomepageBtn())) {
				if (viewSwitchPane.getViewSwitch().getChildren().indexOf(viewSwitchPane.getToHomepageBtn()) == 1) {
					viewSwitchPane.getViewSwitch().getChildren().set(1, viewSwitchPane.getToInstructorViewBtn());
				} else {
					viewSwitchPane.getViewSwitch().getChildren().set(2, viewSwitchPane.getToBookViewBtn());
				}
			}
			//the student view button will be replaced with the homepage view button
			viewSwitchPane.getViewSwitch().getChildren().set(0, viewSwitchPane.getToHomepageBtn());
			
			studentPane.getInsertBtn().setOnAction(f -> {
				try {
					Name name = new Name(studentPane.getStrField1().getText(), studentPane.getStrField2().getText());
					Majors major = studentPane.getComboBox().getValue();
					double gpa = Double.parseDouble(studentPane.getDoubleField().getText());
					PersonBag.getPersonBag().insert(new Student(name, gpa, major));
					studentPane.customAlertThrowing("Success!", "Student Created!", "Student has been added to the database");
					studentPane.clear();
				} catch (InvalidStringException exception) {
					studentPane.customAlertThrowing("Student Creation Unsuccessful", "Failed To Create Student", "First and last names can only contain letters or dashes, cannot be blank, and cannot consist of only dashes");
				} catch (InvalidDoubleException exception) {
					studentPane.customAlertThrowing("Student Creation Unsuccessful", "Failed To Create Student", "GPA must be in between 0.0 and 4.0 inclusive");
				} catch (Exception exception) {
					studentPane.customAlertThrowing("Student Creation Unsuccessful", "Failed To Create Student", "Please try again");
				}
			});
			
			studentPane.getSearchBtn().setOnAction(f -> {
				Optional<Person> retrieved = PersonBag.getPersonBag().search(p -> (p instanceof Student) && (p.getId().equals(studentPane.getSearchField().getText())));
				retrievedStudentHandling(retrieved, studentPane, "Student Search Unsuccessful", "retrieved");
			});
			
			studentPane.getSearchField().setOnKeyPressed(f -> {
				if (f.getCode() == KeyCode.ENTER) {
					Optional<Person> retrieved = PersonBag.getPersonBag().search(p -> (p instanceof Student) && (p.getId().equals(studentPane.getSearchField().getText())));
					retrievedStudentHandling(retrieved, studentPane, "Student Search Unsuccessful", "retrieved");
				}
			});
			
			studentPane.getRemoveBtn().setOnAction(f -> {
				Optional<Person> retrieved = PersonBag.getPersonBag().remove(p -> (p instanceof Student) && (p.getId().equals(studentPane.getRemoveField().getText())));
				retrievedStudentHandling(retrieved, studentPane, "Student Removal Unsuccessful", "removed");
			});
			
			studentPane.getRemoveField().setOnKeyPressed(f -> {
				if (f.getCode() == KeyCode.ENTER) {
					Optional<Person> retrieved = PersonBag.getPersonBag().remove(p -> (p instanceof Student) && (p.getId().equals(studentPane.getRemoveField().getText())));
					retrievedStudentHandling(retrieved, studentPane, "Student Removal Unsuccessful", "removed");
				}
			});
			
			studentPane.getUpdateBtn().setOnAction(f -> {
				try {
					Name name = new Name(studentPane.getStrField1().getText(), studentPane.getStrField2().getText());
					//major is converted to a String and back to a major inside of the update method
					//since the method could require either a major or rank depending on the object type being updated
					String major = studentPane.getComboBox().getValue().toString();
					double gpa = Double.parseDouble(studentPane.getDoubleField().getText());
					Optional<Person> updated = PersonBag.getPersonBag().update(p -> (p instanceof Student) && (p.getId().equals(studentPane.getUpdateField().getText())), name, gpa, major);
					retrievedStudentHandling(updated, studentPane, "Student Update Unsuccessful", "updated");
				} catch (InvalidStringException exception) {
					studentPane.customAlertThrowing("Student Update Unsuccessful", "Failed To Update Student", "First and last names can only contain letters or dashes, cannot be blank, and cannot consist of only dashes");
				} catch (InvalidDoubleException exception) {
					studentPane.customAlertThrowing("Student Update Unsuccessful", "Failed To Update Student", "GPA must be in between 0.0 and 4.0 inclusive");
				} catch (Exception exception) {
					studentPane.customAlertThrowing("Student Update Unsuccessful", "Failed To Update Student", "Please try again");
				}
			});
		});
		
		viewSwitchPane.getToInstructorViewBtn().setOnAction(e -> {
			borderPane.setCenter(instructorPane.getCenterPane());
			//if the homepage view button is present, it will be replaced with either the student or book view button depending on its position
			if (viewSwitchPane.getViewSwitch().getChildren().contains(viewSwitchPane.getToHomepageBtn())) {
				if (viewSwitchPane.getViewSwitch().getChildren().indexOf(viewSwitchPane.getToHomepageBtn()) == 0) {
					viewSwitchPane.getViewSwitch().getChildren().set(0, viewSwitchPane.getToStudentViewBtn());
				} else {
					viewSwitchPane.getViewSwitch().getChildren().set(2, viewSwitchPane.getToBookViewBtn());
				}
			}
			//the instructor view button will be replaced with the homepage view button
			viewSwitchPane.getViewSwitch().getChildren().set(1, viewSwitchPane.getToHomepageBtn());
			
			instructorPane.getInsertBtn().setOnAction(f -> {
				try {
					Name name = new Name(instructorPane.getStrField1().getText(), instructorPane.getStrField2().getText());
					Ranks rank = instructorPane.getComboBox().getValue();
					Double salary = Double.parseDouble(instructorPane.getDoubleField().getText());
					PersonBag.getPersonBag().insert(new Instructor(name, rank, salary));
					instructorPane.customAlertThrowing("Success!", "Instructor Created!", "Instructor has been added to the database");
					instructorPane.clear();
				} catch (InvalidStringException exception) {
					instructorPane.customAlertThrowing("Instructor Creation Unsuccessful", "Failed To Create Instructor", "First and last names can only contain letters or dashes, cannot be blank, and cannot consist of only dashes");
				} catch (InvalidDoubleException exception) {
					instructorPane.customAlertThrowing("Instructor Creation Unsuccessful", "Failed To Create Instructor", "Salary must be in between 10,000.00 and 199,999.99 inclusive");
				} catch (Exception exception) {
					instructorPane.customAlertThrowing("Instructor Creation Unsuccessful", "Failed To Create Instructor", "Please try again");
				}
			});
			
			instructorPane.getSearchBtn().setOnAction(f -> {
				Optional<Person> retrieved = PersonBag.getPersonBag().search(p -> (p instanceof Instructor) && (p.getId().equals(instructorPane.getSearchField().getText())));
				retrievedInstructorHandling(retrieved, instructorPane, "Instructor Search Unsuccessful", "retrieved");
			});
			
			instructorPane.getSearchField().setOnKeyPressed(f -> {
				if (f.getCode() == KeyCode.ENTER) {
					Optional<Person> retrieved = PersonBag.getPersonBag().search(p -> (p instanceof Instructor) && (p.getId().equals(instructorPane.getSearchField().getText())));
					retrievedInstructorHandling(retrieved, instructorPane, "Instructor Search Unsuccessful", "retrieved");
				}
			});
			
			instructorPane.getRemoveBtn().setOnAction(f -> {
				Optional<Person> retrieved = PersonBag.getPersonBag().remove(p -> (p instanceof Instructor) && (p.getId().equals(instructorPane.getRemoveField().getText())));
				retrievedInstructorHandling(retrieved, instructorPane, "Instructor Removal Unsuccessful", "removed");
			});
			
			instructorPane.getRemoveField().setOnKeyPressed(f -> {
				if (f.getCode() == KeyCode.ENTER) {
					Optional<Person> retrieved = PersonBag.getPersonBag().remove(p -> (p instanceof Instructor) && (p.getId().equals(instructorPane.getRemoveField().getText())));
					retrievedInstructorHandling(retrieved, instructorPane, "Instructor Removal Unsuccessful", "removed");
				}
			});
			
			instructorPane.getUpdateBtn().setOnAction(f -> {
				try {
					Name name = new Name(instructorPane.getStrField1().getText(), instructorPane.getStrField2().getText());
					//rank is converted to a String and back to a rank inside of the update method
					//since the method could require either a major or rank depending on the object type being updated
					String rank = instructorPane.getComboBox().getValue().toString();
					double salary = Double.parseDouble(instructorPane.getDoubleField().getText());
					Optional<Person> updated = PersonBag.getPersonBag().update(p -> (p instanceof Instructor) && (p.getId().equals(instructorPane.getUpdateField().getText())), name, salary, rank);
					retrievedInstructorHandling(updated, instructorPane, "Instructor Update Unsuccessful", "updated");
				} catch (InvalidStringException exception) {
					instructorPane.customAlertThrowing("Instructor Update Unsuccessful", "Failed To Update Instructor", "First and last names can only contain letters or dashes, cannot be blank, and cannot consist of only dashes");
				} catch (InvalidDoubleException exception) {
					instructorPane.customAlertThrowing("Instructor Update Unsuccessful", "Failed To Update Instructor", "Salary must be in between 10,000.00 and 199,999.99 inclusive");
				} catch (Exception exception) {
					instructorPane.customAlertThrowing("Instructor Update Unsuccessful", "Failed To Update Instructor", "Please try again");
				}
			});
		});
		
		viewSwitchPane.getToBookViewBtn().setOnAction(e -> {
			borderPane.setCenter(bookPane.getCenterPane());
			//if the homepage view button is present, it will be replaced with either the student or instructor view button depending on its position
			if (viewSwitchPane.getViewSwitch().getChildren().contains(viewSwitchPane.getToHomepageBtn())) {
				if (viewSwitchPane.getViewSwitch().getChildren().indexOf(viewSwitchPane.getToHomepageBtn()) == 0) {
					viewSwitchPane.getViewSwitch().getChildren().set(0, viewSwitchPane.getToStudentViewBtn());
				} else {
					viewSwitchPane.getViewSwitch().getChildren().set(1, viewSwitchPane.getToInstructorViewBtn());
				}
			}
			//the instructor view button will be replaced with the homepage view button
			viewSwitchPane.getViewSwitch().getChildren().set(2, viewSwitchPane.getToHomepageBtn());
			
			bookPane.getInsertBtn().setOnAction(f -> {
				try {
					String title = bookPane.getStrField1().getText();
					String isbn = bookPane.getIsbnField().getText();
					String fullAuthor = bookPane.getStrField2().getText();
					Name author = new Name(fullAuthor.substring(0, fullAuthor.indexOf(" ")), fullAuthor.substring(fullAuthor.indexOf(" ")).trim());
					double price = Double.parseDouble(bookPane.getDoubleField().getText());
					BookBag.getBookBag().insert(new Textbook(title, isbn, author, price));
					bookPane.customAlertThrowing("Success!", "Textbook Created!", "Textbook has been added to the database");
					bookPane.clear();
				} catch (InvalidStringException exception) {
					bookPane.customAlertThrowing("Textbook Creation Unsuccessful", "Failed To Create Textbook", "Author can only contain letters and numbers, cannot consist of only dashes, and neither title nor author can be blank. ISBN can only contain numbers and dashes, and cannot consist of only dashes ");
				} catch (InvalidDoubleException exception) {
					bookPane.customAlertThrowing("Textbook Creation Unsuccessful", "Failed To Create Textbook", "Price must be in between 0.0 and 199.99 inclusive");
				} catch (DuplicateISBNException exception) {
					bookPane.customAlertThrowing("Textbook Creation Unsuccessful", "Failed To Create Textbook", "A textbook with that ISBN already exists");
				} catch (StringIndexOutOfBoundsException exception) {
					bookPane.customAlertThrowing("Textbook Creation Unsuccessful", "Failed To Create Textbook", "The author was entered incorrectly; should be first and last name with a space in between");
				} catch (Exception exception) {
					bookPane.customAlertThrowing("Textbook Creation Unsuccessful", "Failed To Create Textbook", "Please try again");
				}
			});
			
			bookPane.getSearchBtn().setOnAction(f -> {
				Optional<Textbook> retrieved = BookBag.getBookBag().search(p -> p.getIsbn().equals(bookPane.getSearchField().getText()));
				retrievedBookHandling(retrieved, bookPane,"Textbook Search Unsuccessful", "retrieved");
			});
			
			bookPane.getSearchField().setOnKeyPressed(f -> {
				if (f.getCode() == KeyCode.ENTER) {
					Optional<Textbook> retrieved = BookBag.getBookBag().search(p -> p.getIsbn().equals(bookPane.getSearchField().getText()));
					retrievedBookHandling(retrieved, bookPane, "Textbook Search Unsuccessful", "retrieved");
				}
			});
			
			bookPane.getRemoveBtn().setOnAction(f -> {
				Optional<Textbook> retrieved = BookBag.getBookBag().remove(p -> p.getIsbn().equals(bookPane.getRemoveField().getText()));
				retrievedBookHandling(retrieved, bookPane,"Textbook Removal Unsuccessful", "removed");
			});
			
			bookPane.getRemoveField().setOnKeyPressed(f -> {
				if (f.getCode() == KeyCode.ENTER) {
					Optional<Textbook> retrieved = BookBag.getBookBag().remove(p -> p.getIsbn().equals(bookPane.getSearchField().getText()));
					retrievedBookHandling(retrieved, bookPane,"Textbook Removal Unsuccessful", "removed");
				}
			});
			
			bookPane.getUpdateBtn().setOnAction(f -> {
				try {
					String title = bookPane.getStrField1().getText();
					String fullAuthor = bookPane.getStrField2().getText();
					Name author = new Name(fullAuthor.substring(0, fullAuthor.indexOf(" ")), fullAuthor.substring(fullAuthor.indexOf(" ")).trim());
					double price = Double.parseDouble(bookPane.getDoubleField().getText());
					Optional<Textbook> updated = BookBag.getBookBag().update(p -> p.getIsbn().equals(bookPane.getUpdateField().getText()), title, author, price);
					retrievedBookHandling(updated, bookPane, "Textbook Update Unsuccessful", "updated");
				} catch (InvalidStringException exception) {
					bookPane.customAlertThrowing("Textbook Update Unsuccessful", "Failed To Update Textbook", "Author can only contain letters and numbers, cannot consist of only dashes, and neither title nor author can be blank");
				} catch (InvalidDoubleException exception) {
					bookPane.customAlertThrowing("Textbook Update Unsuccessful", "Failed To Update Textbook", "Price must be in between 0.0 and 199.99 inclusive");
				} catch (DuplicateISBNException exception) {
					bookPane.customAlertThrowing("Textbook Update Unsuccessful", "Failed To Update Textbook", "A textbook with that ISBN already exists");
				} catch (StringIndexOutOfBoundsException exception) {
					bookPane.customAlertThrowing("Textbook Update Unsuccessful", "Failed To Update Textbook", "The author was entered incorrectly; should be first and last name with a space in between");
				} catch (Exception exception) {
					bookPane.customAlertThrowing("Textbook Update Unsuccessful", "Failed To Update Textbook", "Please try again");
				}
			});
		});
		
		viewSwitchPane.getToHomepageBtn().setOnAction(f -> {
			borderPane.setCenter(homepagePane.getHomepage());
			
			if (viewSwitchPane.getViewSwitch().getChildren().indexOf(viewSwitchPane.getToHomepageBtn()) == 0) {
				viewSwitchPane.getViewSwitch().getChildren().set(0, viewSwitchPane.getToStudentViewBtn());
			} else if (viewSwitchPane.getViewSwitch().getChildren().indexOf(viewSwitchPane.getToHomepageBtn()) == 1) {
				viewSwitchPane.getViewSwitch().getChildren().set(1, viewSwitchPane.getToInstructorViewBtn());
			} else {
				viewSwitchPane.getViewSwitch().getChildren().set(2, viewSwitchPane.getToBookViewBtn());
			}
		});
	}
	
	//checks to see if a Student object has been retrieved when searching, removing, or updating a Student
	//if a Student was retrieved, its fields will be set to the text fields and a success alert will be displayed
	//otherwise, a failure alert will be displayed
	private void retrievedStudentHandling(Optional<Person> retrieved, StudentPane studentPane, String customHeader, String customAction){
		if (retrieved.isPresent()) {
			Student retrievedStudent = (Student)retrieved.get();
			studentPane.customAlertThrowing("Success!", "Student Found!", "Student has been successfully " + customAction);
			studentPane.setFields(retrievedStudent.getName().getFirstName(), retrievedStudent.getName().getLastName(), String.valueOf(retrievedStudent.getGpa()), retrievedStudent.getMajor());
		} else {
			studentPane.customAlertThrowing(customHeader,"Student Not Found", "Please try again");
		}
	}
	
	//checks to see if an Instructor object has been retrieved when searching, removing, or updating an Instructor
	//if an Instructor was retrieved, its fields will be set to the text fields and a success alert will be displayed
	//otherwise, a failure alert will be displayed
	private void retrievedInstructorHandling(Optional<Person> retrieved, InstructorPane instructorPane, String customHeader, String customAction){
		if (retrieved.isPresent()) {
			Instructor retrievedInstructor = (Instructor)retrieved.get();
			instructorPane.customAlertThrowing("Success!", "Instructor Found!", "Instructor has been successfully " + customAction);
			instructorPane.setFields(retrievedInstructor.getName().getFirstName(), retrievedInstructor.getName().getLastName(), String.valueOf(retrievedInstructor.getSalary()), retrievedInstructor.getRank());
		} else {
			instructorPane.customAlertThrowing(customHeader,"Instructor Not Found", "Please try again");
		}
	}
	
	//checks to see if a Textbook object has been retrieved when searching or removing a Textbook
	//if a Textbook was retrieved, its fields will be set to the text fields and a success alert will be displayed
	//otherwise, a failure alert will be displayed
	private void retrievedBookHandling(Optional<Textbook> retrieved, BookPane bookPane, String customHeader, String customAction){
		if (retrieved.isPresent()) {
			Textbook bookRetrieved = retrieved.get();
			bookPane.customAlertThrowing("Success!", "Textbook Found!", "Textbook has been successfully " + customAction);
			bookPane.setFields(bookRetrieved.getTitle(), bookRetrieved.getAuthor().toString(), String.valueOf(bookRetrieved.getPrice()), bookRetrieved.getIsbn());
		} else {
			bookPane.customAlertThrowing(customHeader,"Textbook Not Found", "Please try again");
		}
	}

	public BorderPane getBorderPane() {
		return borderPane;
	}
}
