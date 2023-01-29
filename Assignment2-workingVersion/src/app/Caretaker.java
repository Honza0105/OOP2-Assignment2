package app;

import util.IObserver;
import util.ISubject;
import util.Notification;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Caretakers are taking care of animals and are informed by them in specific situations.
 */
public class Caretaker implements IObserver {
	private final int id;
	private String name;
	private static BigInteger currentId = new BigInteger("100057");
	private Set<Animal> pets;

	private static final Set<Caretaker> allCaretakers = new HashSet<>();
	
	public Caretaker(String name) {
		super();
		this.name = name;
		currentId = currentId.nextProbablePrime();
		this.id = currentId.intValue();
		this.pets = new HashSet<>();
		allCaretakers.add(this);
	}

	public static Set<Caretaker> getAllCaretakers() {
		return allCaretakers;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	public boolean addPet(Animal animal) {
		return pets.add(animal);
	}
	
	public boolean removePet(Animal animal) {
		animal.removeCaretaker();
		return pets.remove(animal);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caretaker other = (Caretaker) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "app.Caretaker [id=" + id + ", name=" + name + "]";
	}


	@Override
	public void update(ISubject iSubject, Notification notification) {
		System.out.println(name + " has been notified by " + iSubject + ", because it is " + notification);
	}
}
