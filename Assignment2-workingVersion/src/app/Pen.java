package app;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A pen is the place where the animals are being kept.
 * @param <T> Subclass of Animal
 */
public class Pen<T extends Animal> {
	private int maxAnimals;
	private Set<T> inhabitants;

	public Pen(int maxAnimals) {
		this.maxAnimals = maxAnimals;
		this.inhabitants = new HashSet<>();
	}

	public boolean addAnimal(T animal) {
		if (!isFull()) {
			for (Animal inhabitant: inhabitants
				 ) {
				if (inhabitant.getClass()==animal.getClass()){
					return this.inhabitants.add(animal);
				}
				else {
					return false;
				}
			}
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


	/**
	 * Returns a pen where the species already is present and which has room left for another animal.
	 */
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

	public void timePasses(){
		for (Animal animal: inhabitants
			 ) {
			animal.timePasses();
		}
	}

	/**
	 * returns an empty pen.
	 */
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
