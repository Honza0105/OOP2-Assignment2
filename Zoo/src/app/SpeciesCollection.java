package app;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//
//There can be only one app.SpeciesCollection ever!
//
public class SpeciesCollection implements Set<Species>{
	private Set<Species> species;
	
	public static SpeciesCollection instance() {
		return null;
	}
	
	public SpeciesCollection() {
		this.species = new HashSet<>();
		readSpeciesFromStorage();
	}
	//
	// The following implementation is inefficient. Improve this class so the looping is not necessary.
	// As part of that you will need to implement a custom iterator using an inner class.
	//
	public Species get(String commonName) {
		for (Species s: species) {
			if (s.getCommonName().equals(commonName)) {
				return s;
			}
		}
		return null;
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
		return species.contains(o);
	}

	@Override
	public Iterator<Species> iterator() {
		return species.iterator();
	}

	@Override
	public Object[] toArray() {
		return species.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return species.toArray(a);
	}

	@Override
	public boolean add(Species e) {
		return species.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return species.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return species.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Species> c) {
		boolean result = false;
		for (Object o: c) {
			if (species.add((Species) o)) {
				result = true;
			}
		}
		return result;
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
		for (Object o: c) {
			if (species.remove((Species) o)) {
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
	private void readSpeciesFromStorage() {
		add(new Species("Tiger", 8, 550, new BigDecimal("15000"), Species.Type.SCARY, Animal.Gender.MALE, Animal.Gender.FEMALE));
		add(new Species("Polar Bear", 777, 9, new BigDecimal("15000"), Species.Type.SCARY, Animal.Gender.MALE, Animal.Gender.FEMALE));
		add(new Species("Panda", 18, 333, new BigDecimal("1500"), Species.Type.FLUFFY, Animal.Gender.MALE, Animal.Gender.FEMALE));
		add(new Species("Tarantula", 1, 1, new BigDecimal("15"), Species.Type.CREEPY, Animal.Gender.MALE, Animal.Gender.FEMALE));
		add(new Species("Cobra", 3, 2, new BigDecimal("22"), Species.Type.CREEPY, Animal.Gender.MALE, Animal.Gender.FEMALE));
	}
}
