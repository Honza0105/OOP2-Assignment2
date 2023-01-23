package app;

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

	public Zone() {
		super();
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
		return false;
	}

	//
	// Remove the animal if it is present
	//
	public boolean removeAnimal(T animal) {
		return false;
	}

	public boolean isFull() {
		return false;
	}
	
	public boolean isEmpty() {
		return false;
	}
	//
	// Check whether the animal is present in the app.Zone or any of its subzones or Pens
	//
	public boolean isPresent(T animal) {
		return false;
	}

	//
	// Return a app.Pen with the same species and room for another animal, or null if absent
	// Might need some adaptation
	//
	public Pen<T> getAvailablePen(Species species) {
		return null;
	}
	//
	// Return an empty app.Pen or null of no such app.Pen exists
	// Might need some adaptation
	//
	public Pen<T> getEmptyPen() {
		return null;
	}
}
