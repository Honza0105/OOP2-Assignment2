package animals;

import app.Animal;
import app.Caretaker;
import app.Species;
import app.SpeciesCollection;

import java.time.LocalDate;

/**
 * Example subclass of Animal.
 */
public class Lion extends Animal {

    public Lion(Caretaker caretaker, String name, LocalDate dateOfBirth, Gender gender, int weight) {
        super(caretaker, SpeciesCollection.getInstance().get("Lion"), name, dateOfBirth, gender, weight);
    }
}
