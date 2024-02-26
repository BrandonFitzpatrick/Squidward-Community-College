package Model;

import java.io.Serializable;

import Utils.Utilities;

public abstract class Person implements Serializable {
	private Name name;
	private String id;
	
	//two overloaded constructors: one with parameters for manually creating an object, and a no-arg constructor which randomly generates objects
	public Person(Name name) {
		for (int i = 0; i < name.toString().length(); i++) {
			char current = name.toString().charAt(i);
			//if the name contains any character other than a letter, dash, or space, then an exception will be thrown
			if (!((current >= 'A' && current <= 'Z') || (current >= 'a' && current <= 'z') || (current == '-') || (current == ' '))) {
				throw new InvalidStringException("Invalid Name!");
			}
		}
		//if either the first or last names are blank, or the name only consists of dashes, then an exception will be thrown
		if (name.getFirstName().isBlank() || name.getLastName().isBlank() || name.getFirstName().replace("-", "").isBlank() || name.getLastName().replace("-", "").isBlank()) {
			throw new InvalidStringException("Invalid Name!");
		}
		this.name = name;
		//idCount is managed by the PersonBag to ensure that it is saved in the backup file
		//the only possible issue with this implementation is that IDs can only be properly assigned to Person objects being added to the PersonBag
		//but in the context of the program and how it is used, this is not an issue
		id = String.valueOf(PersonBag.getPersonBag().increaseIdCount()); 
	}
	
	public Person() {
		name = Utilities.emitName();
		id = String.valueOf(PersonBag.getPersonBag().increaseIdCount()); //idCount is managed by the PersonBag to ensure that it is saved in the backup file
	}
	
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		for (int i = 0; i < name.toString().length(); i++) {
			char current = name.toString().charAt(i);
			//if the name contains any character other than a letter, dash, or space, then an exception will be thrown
			if (!((current >= 'A' && current <= 'Z') || (current >= 'a' && current <= 'z') || (current == '-') || (current == ' '))) {
				throw new InvalidStringException("Invalid Name!");
			}
		}
		//if either the first or last names are blank, or the name only consists of dashes, then an exception will be thrown
		if (name.getFirstName().isBlank() || name.getLastName().isBlank() || name.getFirstName().replace("-", "").isBlank() || name.getLastName().replace("-", "").isBlank()) {
			throw new InvalidStringException("Invalid Name!");
		}
		this.name = name;
	}

	//no setter for ID
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\nID: " + id;
	}
}
