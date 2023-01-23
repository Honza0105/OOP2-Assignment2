import java.time.LocalDate;
import java.util.Objects;

//
// An Animal has one primary Caretaker. It should not be possible to just change the 
// Caretaker from the outside; only if an object of class Caretaker adds an Animal
// as a pet it is updated here. Only if a Caretaker removes a pet it is set to null
// here.
//
// While not planned it was envisioned Animal could have subclasses; feel free to add some
// if you need to do so
//
public class Animal implements Cloneable {
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

	public void setCaretaker(Caretaker caretaker) {
		this.caretaker = caretaker;
	}

	public int getHungriness() {
		return hungriness;
	}

	//
	// Increase hungriness by 1
	// If the hungriness reaches its maximum inform their Caretaker
	//
	public void timePasses() {

	}
	
	//
	// If an Animal dies the Caretaker obviously should not try feeding him anymore
	// Remove the Animal from his pets in this case
	//
	public void die() {
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
		return name + " (" + species + ")";
	}
	
	//
	// Whenever an animal is being fed, the code needs to check whether it was fed by
	// its caretaker or someone else.
	// In case of the caretaker the animal eats and decreases its hungriness, but that's it
	// In case of other people also inform all caretakers (all of them so they 
	// can make the culprit stop!)
	//
	public void feed(IFood food) {

	}
	
	public enum Gender { MALE, FEMALE, OTHER

	}

}
