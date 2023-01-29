package app;

import util.IFood;
import util.ISubject;
import util.Notification;

import java.time.LocalDate;
import java.util.Objects;

//
// An app.Animal has one primary app.Caretaker. It should not be possible to just change the
// app.Caretaker from the outside; only if an object of class app.Caretaker adds an app.Animal
// as a pet it is updated here. Only if a app.Caretaker removes a pet it is set to null
// here.
//
// While not planned it was envisioned app.Animal could have subclasses; feel free to add some
// if you need to do so
//
public class Animal implements Cloneable, ISubject {
	private Species species;
	private String name;
	private LocalDate dateOfBirth;
	private int hungriness;
	private Gender gender;
	private Caretaker caretaker;
	private Pen<?> pen = null;
	private int weight;
	
	public Animal(Caretaker caretaker, Species species, String name, LocalDate dateOfBirth, Gender gender, int weight) {
		super();
		this.species = species;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.hungriness = 0;
		this.gender = gender;
		this.caretaker = caretaker;
		this.weight = weight;
	}
	
	@Override
	public Animal clone() {
		return new Animal(caretaker, species, name, LocalDate.from(dateOfBirth), gender, weight);
	}

	public Pen<?> getPen() {
		return pen;
	}

	public void setPen(Pen<?> pen) {
		this.pen = pen;
	}

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Caretaker getCaretaker() {
		return caretaker;
	}

	public void removeCaretaker() {
		this.caretaker = null;
	}

	public int getHungriness() {
		return hungriness;
	}

	//
	// Increase hungriness by 1
	// If the hungriness reaches its maximum inform their app.Caretaker
	//!seems to be done
	public void timePasses() {
		if (hungriness== species.getMaxHungriness()){
				notifyObservers(Notification.RAVINGLY_HUNGRY);
		}
		else {
			hungriness++;
			if (hungriness== species.getMaxHungriness()) {
				notifyObservers(Notification.RAVINGLY_HUNGRY);
			}
		}
	}
	
	//
	// If an app.Animal dies the app.Caretaker obviously should not try feeding him anymore
	// Remove the app.Animal from his pets in this case
	//!seems to be done
	public void die() {
		notifyMainObserver(Notification.DIED);
		this.caretaker.removePet(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateOfBirth, name, species);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(name, other.name)
				&& Objects.equals(species, other.species);
	}

	@Override
	public String toString() {
		return name + " the " + species.getCommonName() + ", hungry of " + hungriness;

	}

	//
	// Whenever an animal is being fed, the code needs to check whether it was fed by
	// its caretaker or someone else.
	// In case of the caretaker the animal eats and decreases its hungriness, but that's it
	// In case of other people also inform all caretakers (all of them so they 
	// can make the culprit stop!)
	//
	public void feed(IFood food, Caretaker caretaker) {
		if (!caretaker.equals(this.caretaker)){
			notifyObservers(Notification.FED_BY_SOMEONE_ELSE);
		}
		if (hungriness != 0){
			hungriness -= food.getFillingValue();
		}
		if (hungriness < 0){
			hungriness = 0;
		}
	}


	@Override
	public void notifyObservers(Notification notification) {
		//implement to inform all caretakers
		for (Caretaker caretaker: Caretaker.getAllCaretakers()
			 ) {
			caretaker.update(this, notification);
		}
	}

	@Override
	public void notifyMainObserver(Notification notification) {
		caretaker.update(this, notification);
	}


	public enum Gender { MALE, FEMALE, OTHER

	}

}
