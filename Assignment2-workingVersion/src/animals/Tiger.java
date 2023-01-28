package animals;

import app.Animal;
import app.Caretaker;
import app.SpeciesCollection;

import java.time.LocalDate;

public class Tiger extends Animal {
    public Tiger(Caretaker caretaker, String name, LocalDate dateOfBirth, Gender gender, int weight) {
        super(caretaker, SpeciesCollection.getInstance().get("Tiger"), name, dateOfBirth, gender, weight);
    }
}
