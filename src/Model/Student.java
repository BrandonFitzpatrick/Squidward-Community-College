package Model;

import java.io.Serializable;

import Utils.Utilities;

public class Student extends Person implements Serializable {
	private double gpa;
	private Majors major;
	
	//two overloaded constructors: one with parameters for manually creating an object, and a no-arg constructor which randomly generates objects
	public Student(Name name, double gpa, Majors major) {
		super(name);
		//if GPA is not in the range of 0.0 and 4.0, an exception is thrown
		if (gpa < (Double)Settings.MIN_GPA.getValue() || gpa > (Double)Settings.MAX_GPA.getValue()) {
			//the idCount was incremented in the super constructor, but construction of the object failed
			//therefore, idCount must be decremented to its previous value
			PersonBag.getPersonBag().decreaseIdCount(); 
			throw new InvalidDoubleException("Invalid GPA!");
		} 
		this.gpa = gpa;
		this.major = major;
	}

	public Student() {
		super();
		gpa = Utilities.emitGPA();
		this.major = Utilities.emitMajor();
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		//if GPA is not in the range of 0.0 and 4.0, an exception is thrown
		if (gpa < (Double)Settings.MIN_GPA.getValue() || gpa > (Double)Settings.MAX_GPA.getValue()) {
			throw new InvalidDoubleException("Invalid GPA!");
		}
		this.gpa = gpa;
	}

	public Majors getMajor() {
		return major;
	}

	public void setMajor(Majors major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return super.toString() + "\nGPA: " + gpa + "\nMajor: " + major + "\n";
	}
}
