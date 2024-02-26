package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public class PersonBag implements Serializable { //singleton bag class
	private Person[] arr;
	private int nElems;
	private int idCount;
	
	private static PersonBag personBag;
	
	private PersonBag() {
		arr = new Person[(Integer)Settings.MAX_PERSONBAG_SIZE.getValue()];
		//since every Person object created will be added to the single PersonBag instance, IDs are assigned through the bag's idCount
		idCount = 0;
	}
	
	public static PersonBag getPersonBag() { 
		if (personBag == null) {
			personBag = new PersonBag();
		} 
		return personBag;
	}
	
	public static void setPersonBag(PersonBag personBag) {
		PersonBag.personBag = personBag;
	}
	
	public int increaseIdCount() { 
		return idCount++; //this method will be called each time a Person object is created
	}
	
	public int decreaseIdCount() {
		return idCount--; //this method will be called every time an exception is thrown in the Student or Instructor constructors (not including the super constructor)
	}

	public void insert(Person person) {
		arr[nElems++] = person;
	}
	
	public Optional<Person> search(Predicate<Person> predicate) { 
		for (int i = 0; i < nElems; i++) {
			if (predicate.test(arr[i])) { //the test boolean will vary depending on whether you're searching a Student or Instructor
				return Optional.of(arr[i]);
			}
		}
		return Optional.ofNullable(null);
	}
	
	public Optional<Person> remove(Predicate<Person> predicate) {
		for (int i = 0; i < nElems; i++) {
			if (predicate.test(arr[i])) { //the test boolean will vary depending on whether you're removing a Student or Instructor
				Optional<Person> temp = Optional.of(arr[i]);
				for (int j = i; j < nElems - 1; j++) {
					arr[j] = arr[j + 1];
				}
				arr = Arrays.copyOf(Arrays.copyOf(arr, nElems--), 1000);
				return temp;
			}
		}
		return Optional.ofNullable(null);
	}
	
	public Optional<Person> update(Predicate<Person> predicate, Name name, double tempDouble, String tempStr) {
		for (int i = 0; i < nElems; i++) {
			if (predicate.test(arr[i])) { //the test boolean will vary depending on whether you're updating a Student or Instructor
				arr[i].setName(name);
				//set Student exclusive fields if you're updating a Student, otherwise set Instructor exclusive fields if you're updating an Instructor
				if (arr[i] instanceof Student) {
					((Student) arr[i]).setGpa(tempDouble);
					((Student) arr[i]).setMajor(Majors.valueOf(tempStr));
				} else {
					((Instructor) arr[i]).setRank(Ranks.valueOf(tempStr));
					((Instructor) arr[i]).setSalary(tempDouble);
				}
				return Optional.of(arr[i]);
			}
		}
		return Optional.ofNullable(null);
	}
	
	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(arr[i]);
		}
	}
}
