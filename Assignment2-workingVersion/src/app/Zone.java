package app;

import java.util.ArrayList;
import java.util.Set;

//
// A app.Zone can contain other Zones and / or Pens.
// Only animals of one app.Species can be put in a app.Zone. For the sake of simplicity it is
// parameterized with an app.Animal (or subclass thereof); the species of that animal determines
// what can be put in the pen.
// This could potentially lead to conflicts due to a app.Pen allowing a broader category
// of inhabitants than the app.Zone it is in. You can ignore this possibility for this assignment.
// Please note parts of this code are incorrect and need to be corrected for a good
// implementation of the assignment
//
//
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

	//
	// If the animal is already present do not add it
	// If the zone is completely full do not add the animal
	// If there is room check whether there already is a app.Pen with room
	// for that animal containing the same species; if so, add it there
	// If not check for an empty app.Pen and add it there
	// If this fails return false
	// Return true if adding was succesfull, false otherwise
	//
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
	// Remove the animal if it is present
	//
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
	//
	// Check whether the animal is present in the app.Zone or any of its subzones or Pens
	//
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

//	public void timePasses(){
//		for (Pen<Animal> pen: pens) {
//			for (Animal animal: pen.getInhabitants()) {
//				animal.timePasses();
//			}
//		}
//
//		for (Zone<Animal> subzone: zones) {
//			for (Animal animal: subzone.timePasses()) {
//				animal.timePasses();
//			}
//		}
//
//	}

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


	//
	// Return a app.Pen with the same species and room for another animal, or null if absent
	// Might need some adaptation
	//
	public Pen<Animal> getAvailablePen(Species species) {
		for (Pen<Animal> pen: pens
			 ) {
			if (pen.getSpecies().equals(species) && !pen.isFull()){
				return pen;
			}
		}
		return null;
	}
	//
	// Return an empty app.Pen or null of no such app.Pen exists
	// Might need some adaptation
	//
	public Pen<Animal> getEmptyPen() {
		for (Pen<Animal> pen: pens){
			if (pen.isEmpty()){
				return pen;
			}
		}
		return null;
	}
}
