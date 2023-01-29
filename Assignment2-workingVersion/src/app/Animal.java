package app;

import util.IFood;
import util.ISubject;
import util.Notification;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Animal class represents all animals.
 */
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

	/**
	 * Increases hungriness by 1
	 * If hungriness reaches its maximum, animal informs its Caretaker.
	 */
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


	/**
	 * If animal dies, main Caretaker is informed and animal is removed from his pet.
	 */
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


	/**
	 * Whenever an animal is being fed, it is checked whether it was fed by its Caretaker or someone else.
	 * In case of another Caretaker, all Caretakers are informed.
	 * Food decreases hungriness of the animal.
	 */
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
