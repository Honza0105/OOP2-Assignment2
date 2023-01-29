package app;

import java.util.ArrayList;
import java.util.Set;

/**
 * A Zone can contain other Zones and / or Pens.
 * @param <T> Animal or subclasses thereof.
 */
public class Zone<T extends Animal> {
	ArrayList<Zone> zones = new ArrayList<>();
	ArrayList<Pen<Animal>> pens = new ArrayList<>();

	public Zone() {
		super();
	}

	public void addZone() {
		this.zones.add(new Zone());
	}
	public void addZone(Zone zone) {
		this.zones.add(zone);
	}

	public void addPen(int maxAnimals) {
		this.pens.add(new Pen<>(maxAnimals));
	}
	public void addPen(Pen pen) {
		this.pens.add(pen);
	}

	public ArrayList<Pen<Animal>> getPens() {
		return pens;
	}

	@Override
	public String toString() {
		return "\nZone:\n{" +
				"zones=" + zones +
				", pens=" + pens +
				"}";
	}


	/**
	 * Adds an animal to a pen in the zone, unless it is already there or the zone is completely full.
	 * @param animal
	 * @return
	 */
	public boolean addAnimal(T animal) {
		//checks if the zone is full
		if(isFull()){
			return false;
		}

		//checks if this animal has already been added
		if (isPresent(animal)) {
			return false;
		}
		//looks for a pen with the same species which is not full
		for (Pen<Animal> pen: pens
			 ) {
			if (pen.getSpecies().equals(animal.getSpecies()) && !pen.isFull()) {
				return pen.addAnimal(animal);
			}
		}
		//looks for empty pen to add my animal to
		for (Pen<Animal> pen: pens) {
			if (pen.isEmpty()) {
				return pen.addAnimal(animal);
			}
		}
		//if everything fails
		return false;
	}

	//

	/**
	 * Removes the animal if it is present.
	 */

	public boolean removeAnimal(T animal) {
		if (isPresent(animal)){
			pens.remove(animal);
		}
		return false;
	}

	public boolean isFull() {
		for (Pen<Animal> pen: pens
			 ) {
			if (!pen.isFull()){
				return false;
			}
		}
		return true;
	}
	
	public boolean isEmpty() {
		return zones.isEmpty() && pens.isEmpty();
	}



	/**
	 * Checks whether the animal is present in the Zone or any of its subzones or Pens.
	 */
	public boolean isPresent(T animal) {
		//checks for animals in the current pens or sub zone's pens because it is recursive
		for (Pen<Animal> pen: pens
			 ) {
			if (pen.isPresent(animal)){
				return true;
			}
		}
		for (Zone<Animal> subzone: zones
			 ) {
			if (subzone.isPresent(animal)){
				return true;
			}
		}
		return false;

	}


	public void timePasses(){
		for (Pen<Animal> pen : pens) {
			pen.timePasses();
		}
		timePassesInSubZones();
	}

	private void timePassesInSubZones() {
		for (Zone<Animal> zone : zones) {
			for (Pen<Animal> pen : zone.getPens()) {
				pen.timePasses();
			}
			zone.timePassesInSubZones();
		}
	}


	/**
	 * @return a pen with the same species and room for another animal, or null if absent
	 */
	public Pen<Animal> getAvailablePen(Species species) {
		for (Pen<Animal> pen: pens
			 ) {
			if (pen.getSpecies().equals(species) && !pen.isFull()){
				return pen;
			}
		}
		return null;
	}


	/**
	 *
	 * @return an empty Pen or null if no such Pen exists
	 */
	public Pen<Animal> getEmptyPen() {
		for (Pen<Animal> pen: pens){
			if (pen.isEmpty()){
				return pen;
			}
		}
		return null;
	}
}
