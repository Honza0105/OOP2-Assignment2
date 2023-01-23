package app;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
//
// A app.Caretaker is a person who cleans Pens and feeds the animals
// Each app.Caretaker knows what Animals to take care of
// If an app.Animal in his care is being fed by someone else the app.Caretaker wants to know
// If an app.Animal in his care reaches maximum hungriness he also wants to know
//
// Anytime a pet is added the app.Animal should update its app.Caretaker.
// Anytime a pet is removed the app.Animal should set its app.Caretaker to null, unless the
// app.Animal just died (then there is no caretaker anymore.)
//
public class Caretaker {
	private final int id;
	private String name;
	private static BigInteger currentId = new BigInteger("100057");
	private Set<Animal> pets;
	
	public Caretaker(String name) {
		super();
		this.name = name;
		currentId = currentId.nextProbablePrime();
		this.id = currentId.intValue();
		this.pets = new HashSet<>();
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
}
