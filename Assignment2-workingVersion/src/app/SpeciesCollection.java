package app;

import util.Till;

import java.math.BigDecimal;
import java.util.*;

//
//There can be only one app.SpeciesCollection ever!
//
public class SpeciesCollection implements Set<Species>, ISpeciesCollection{
	private Map<String, Species> species;
	private static SpeciesCollection instance = new SpeciesCollection();
	
	public static SpeciesCollection getInstance() {
		return instance;
	}
	
	private SpeciesCollection() {
		this.species = new HashMap<>();
		readSpeciesFromStorage();
	}
	//
	// The following implementation is inefficient. Improve this class so the looping is not necessary.
	// As part of that you will need to implement a custom iterator using an inner class.
	//
	@Override
	public Iterator<Species> iterator() {
		return new SpeciesIterator(species.entrySet().iterator());
	}

	@Override
	public Object[] toArray() {
		return species.values().toArray();
	}


	@Override
	public <T> T[] toArray(T[] a) {
		return species.values().toArray(a);
	}


	@Override
	public boolean add(Species species) {
		return this.species.put(species.getCommonName(),species) == null;
	}

	@Override
	public boolean remove(Object o) {
		if (o instanceof Species) {
			return this.species.remove(((Species) o).getCommonName()) != null;
		} else if (o instanceof String) {
			return this.species.remove(o) != null;
		} else {
			return false;
		}
	}


	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
	}


	@Override
	public boolean addAll(Collection<? extends Species> c) {
		boolean result = false;
		for (Species s : c) {
			if (add(s)) {
				result = true;
			}
		}
		return result;
	}


	private class SpeciesIterator implements Iterator<Species> {
		private Iterator<Map.Entry<String,Species>> iterator;

		public SpeciesIterator(Iterator<Map.Entry<String, Species>> iterator) {
			this.iterator = iterator;
		}


		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public Species next() {
			return iterator.next().getValue();
		}
	}


	public Species get(String commonName) {
		return species.get(commonName);
	}


	@Override
	public int size() {
		return species.size();
	}

	@Override
	public boolean isEmpty() {
		return species.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		if (o instanceof Species) {
			return species.containsKey(((Species) o).getCommonName());
		} else if (o instanceof String) {
			return species.containsKey(o);
		} else {
			return false;
		}
	}




	@Override
	public boolean retainAll(Collection<?> c) {
		boolean result = false;
		Iterator<Species> i = this.iterator();
		while (i.hasNext()) {
			Species s = i.next();
			if (!c.contains(s)) {
				i.remove();
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = false;
		for (Object o : c) {
			if (remove(o)) {
				result = true;
			}
		}
		return result;
	}





	@Override
	public void clear() {
		species.clear();
	}
	
	//
	// Feel free to add more species yourself here
	// Feel free to create specific subclasses of app.Animal to make the
	// application more versatile
	//
	public boolean acquireAnimal(String commonName, int maxHungriness, int maxWeight, BigDecimal value, Species.Type type, Animal.Gender... genders) {
		boolean isFree = (type == Species.Type.FLUFFY || type == Species.Type.COMMON);
		if (!isFree && value.compareTo(BigDecimal.ZERO) <= 0 ) {
			throw new IllegalArgumentException("Cost of animal should be greater than zero.");
		}
		if (!isFree && !Till.getInstance().withdraw(value)) {
			System.out.println("Not enough funds to acquire " + commonName);
			return false;
		}
		add(new Species(commonName, maxHungriness, maxWeight, value, type, genders));
		System.out.println(commonName + " has been acquired!");
		return true;
	}

	public boolean acquireAnimal(Animal animal){
		return acquireAnimal(animal.getSpecies().getCommonName(), animal.getSpecies().getMaxHungriness(),animal.getSpecies().getMaxWeight(),animal.getSpecies().getValue(),animal.getSpecies().getType(),animal.getGender());
	}

	public boolean buyAnimal(String commonName, int maxHungriness, int maxWeight, BigDecimal value, Species.Type type, Animal.Gender... genders) {
		if (value.compareTo(BigDecimal.ZERO) <= 0 ) {
			throw new IllegalArgumentException("Cost of animal should be greater than zero.");
		}
		if (!Till.getInstance().withdraw(value)) {
			System.out.println("Not enough funds to acquire " + commonName);
			return false;
		}
		add(new Species(commonName,maxHungriness,maxWeight,value,type, genders));
		System.out.println(commonName+" has been acquired!");
		return true;
	}

	public boolean adoptAnimal(String commonName, int maxHungriness, int maxWeight, BigDecimal value, Species.Type type, Animal.Gender... genders) {
		add(new Species(commonName,maxHungriness,maxWeight,value,type, genders));
		System.out.println(commonName+" has been acquired!");
		return true;
	}


	private void readSpeciesFromStorage() {
		add(new Species("Tiger", 8, 550, new BigDecimal("15000"), Species.Type.SCARY, Animal.Gender.MALE, Animal.Gender.FEMALE));
		add(new Species("Polar Bear", 777, 9, new BigDecimal("15000"), Species.Type.SCARY, Animal.Gender.MALE, Animal.Gender.FEMALE));
		add(new Species("Panda", 18, 333, new BigDecimal("1500"), Species.Type.FLUFFY, Animal.Gender.MALE, Animal.Gender.FEMALE));
		add(new Species("Tarantula", 1, 1, new BigDecimal("15"), Species.Type.CREEPY, Animal.Gender.MALE, Animal.Gender.FEMALE));
		add(new Species("Cobra", 3, 2, new BigDecimal("22"), Species.Type.CREEPY, Animal.Gender.MALE, Animal.Gender.FEMALE));
		add(new Species("Lion", 500, 300, new BigDecimal("20000"), Species.Type.SCARY, Animal.Gender.MALE, Animal.Gender.FEMALE));
	}
}
