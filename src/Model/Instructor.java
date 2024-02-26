package Model;

import java.io.Serializable;

import Utils.Utilities;

public class Instructor extends Person implements Serializable {
	private Ranks rank;
	private double salary;

	//two overloaded constructors: one with parameters for manually creating an object, and a no-arg constructor which randomly generates objects
	public Instructor(Name name, Ranks rank, double salary) {
		super(name);
		this.rank = rank;
		//if salary is less than 10,000 or greater than 199,999.99, then an exception will be thrown
		if (salary < (Double)Settings.MIN_INSTRUCTOR_SALARY.getValue() || salary > (Double)Settings.MAX_INSTRUCTOR_SALARY.getValue()) {
			//the idCount was incremented in the super constructor, but construction of the object failed
			//therefore, idCount must be decremented to its previous value
			PersonBag.getPersonBag().decreaseIdCount();
			throw new InvalidDoubleException("Invalid Salary!");
		} 
		this.salary = salary;
	}

	public Instructor() {
		super();
		this.rank = Utilities.emitRank();
		this.salary = Utilities.emitSalary();
	}

	public Ranks getRank() {
		return rank;
	}

	public void setRank(Ranks rank) {
		this.rank = rank;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		//if salary is less than 10,000 or greater than 199,999.99, then an exception will be thrown
		if (salary < (Double)Settings.MIN_INSTRUCTOR_SALARY.getValue() || salary > (Double)Settings.MAX_INSTRUCTOR_SALARY.getValue()) {
			throw new InvalidDoubleException("Invalid Salary!");
		} 
		this.salary = salary;
	}

	@Override
	public String toString() {
		return super.toString() + "\nRank: " + rank + "\nSalary: " + salary + "\n";
	}
}
