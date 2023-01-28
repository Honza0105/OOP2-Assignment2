package animals;

import app.Animal;
import app.Caretaker;
import app.SpeciesCollection;

import java.time.LocalDate;

public class Hippo extends Animal {
    public Hippo(Caretaker caretaker, String name, LocalDate dateOfBirth, Gender gender, int weight) {
        super(caretaker, SpeciesCollection.getInstance().get("Hippo"), name, dateOfBirth, gender, weight);

    }
}
