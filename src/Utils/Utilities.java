package Utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.BookBag;
import Model.Instructor;
import Model.Majors;
import Model.Name;
import Model.PersonBag;
import Model.Ranks;
import Model.Settings;
import Model.Student;
import Model.Textbook;

public class Utilities {
	private static ArrayList<String> firstNames = fillList("First Names.txt");
	private static ArrayList<String> lastNames = fillList("Last Names.txt");
	private static ArrayList<String> textbookTitles = fillList("textbook_titles.txt");;
	private static ArrayList<String> textbookIsbns = fillList("textbook_isbns.txt");;
	private static Majors[] majors = Majors.values(); //taken from the Majors enum
	
	public static ArrayList<String> fillList(String fileName) {
		//takes in a file name, then uses it to read a text file and store the data into an ArrayList line by line
		ArrayList<String> list = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("InputData/" + fileName));
			while (br.ready()) {
				list.add(br.readLine());
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
//	public static Majors[] getMajors() {
//		return majors;
//	}
	
	public static Name emitName() {
		//returns a name based on a randomly chosen first name and last name from the text files 
		return new Name(firstNames.get((int)(Math.random() * firstNames.size())), lastNames.get((int)(Math.random() * lastNames.size())));
	}
	
	public static Majors emitMajor() {
		//returns a randomly chosen major from the list of majors
		return majors[(int)(Math.random() * majors.length)];
	}

	public static Ranks emitRank() {
		//returns a randomly chosen rank from the list of ranks
		return Ranks.values()[(int)(Math.random() * Ranks.values().length)];
	}
	
	public static String[][] emitTitleAndIsbn() {
		//returns a random title and its matching ISBN number from the text files in a 2D array 
		int random = (int)(Math.random() * textbookTitles.size());
		return new String [][]{{textbookTitles.get(random), textbookIsbns.get(random)}};
	}
	
	public static double emitPrice() {
		//returns a price between 0.0 and 199.99 inclusive, rounded to two decimal places
		return ((int)(Math.random() * 20000))/100.0;
	}
	
	public static double emitSalary() {
		//returns a salary between 10,000.00 and 199,999.99 inclusive, rounded to two decimal places
		return ((int)((10000 + (Math.random() * 190000)) * 100)/100.0);
	}
	
	public static double emitGPA() {
		//returns a GPA between 0.0 and 4.0 inclusive
		return (int)(Math.random() * 41) / 10.0;
	}
	
	public static void importBooks() {
		//adds 5000 randomly generated textbooks to the BookBag singleton
		//if a textbook is generated with an ISBN of an already existing textbook, an exception will be caught and the textbook will not be added to the bag
		while(BookBag.getBookBag().getSize() < (int)Settings.NUMBER_BOOKS_IMPORTED.getValue()) {
			try {
				BookBag.getBookBag().insert(new Textbook());
			} catch (Exception exception) {
				
			}
		}
	}
	
	public static void importStudents() {
		//adds 500 randomly generated Students to the PersonBag singleton
		for (int i = 0; i < (Integer)Settings.NUMBER_STUDENTS_IMPORTED.getValue(); i++) {
			PersonBag.getPersonBag().insert(new Student());
		}
	}
	
	public static void importInstructors() {
		//adds 50 randomly generated Instructors to the PersonBag singleton
		for (int i = 0; i < (Integer)Settings.NUMEBR_INSTRUCTORS_IMPORTED.getValue(); i++) {
			PersonBag.getPersonBag().insert(new Instructor());
		}
	}
	
	public static void backup() {
		//writes the PersonBag and BookBag into each of their respective data files, saving them in memory
		try {
			ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("Backup/Persons.dat"));
			oos1.writeObject(PersonBag.getPersonBag());
			
			ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("Backup/Textbooks.dat"));
			oos2.writeObject(BookBag.getBookBag());
			
			oos1.close();
			oos2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void restore() {
		//reads the PersonBag and BookBag stored in their data files, and sets them to the singleton instances of their respective classes
		try {
			ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream("Backup/Persons.dat"));
			PersonBag.setPersonBag((PersonBag)(ois1.readObject()));
			
			ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream("Backup/Textbooks.dat"));
			BookBag.setBookBag((BookBag)(ois2.readObject()));
			
			ois1.close();
			ois2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
