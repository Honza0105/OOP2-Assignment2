package app;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
//
// A app.Pen is the place where the animals are being kept. A app.Pen is a kind of app.Zone, but one
// with animals (whereas a app.Zone has Pens)
// Only animals of one app.Species can be put in a app.Pen. For the sake of simplicity it is
// parameterized with an app.Animal (or subclass thereof); the species of that animal determines
// what can be put in the pen
// Please note parts of this code are incorrect and need to be corrected for a good
// implementation of the assignment
//
public class Pen<T extends Animal> {
	private int maxAnimals;
	private Set<T> inhabitants;

	public Pen(int maxAnimals) {
		this.maxAnimals = maxAnimals;
		this.inhabitants = new HashSet<>();
	}

	public boolean addAnimal(T animal) {
		if (!isFull()) {
			return this.inhabitants.add(animal);
		}
		else {
 			return false;
		}
	}

	@Override
	public String toString() {
		return "Pen{" +
				"maxAnimals=" + maxAnimals +
				", inhabitants=" + inhabitants +
				'}';
	}

	public boolean removeAnimal(T animal) {
		return this.inhabitants.remove(animal);
	}

	public boolean isFull() {
		return (this.inhabitants.size() > maxAnimals);
	}

	public boolean isPresent(T animal) {
		return inhabitants.contains(animal);
	}
	
	public boolean isEmpty() {
		return inhabitants.isEmpty();
	}
	
	//
	// Return a pen where the species already is present and which has room left for another animal
	// Might need some adaptation
	//
	public Pen<T> getAvailablePen(Species species) {
		if (isFull() ) {
			return null;
		}
		Iterator<T> i = inhabitants.iterator();
		if (!i.hasNext()) {
			return null;
		}
		T result = i.next();
		if (result.getSpecies().equals(species)) {
			return this;
		}
		
		else {
			return null;
		}
	}
	//
	// Return an empty pen
	// Might need some adaptation
	//

	public void timePasses(){
		for (Animal animal: inhabitants
			 ) {
			animal.timePasses();
		}
	}
	public Pen<T> getEmptyPen() {
		if (inhabitants.isEmpty()) {
			return this;
		}
		else {
			return null;
		}
	}

	public Set<T> getInhabitants() {
		return inhabitants;
	}

	public Species getSpecies() {
		return inhabitants.iterator().next().getSpecies();
	}
}
