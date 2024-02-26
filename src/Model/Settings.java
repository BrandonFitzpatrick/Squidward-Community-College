package Model;

public enum Settings { //Hard coded values used throughout the program
	MAX_PERSONBAG_SIZE(1000),
	MIN_BOOKBAG_SIZE(5000),
	NUMBER_BOOKS_IMPORTED(5000),
	NUMBER_STUDENTS_IMPORTED(500),
	NUMEBR_INSTRUCTORS_IMPORTED(50),
	MIN_GPA(0.0),
	MAX_GPA(4.0),
	MIN_INSTRUCTOR_SALARY(10000.0),
	MAX_INSTRUCTOR_SALARY(199999.99),
	MIN_BOOK_PRICE(0.0),
	MAX_BOOK_PRICE(199.99);
	
	private Object value; 
	
	Settings(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}
}

