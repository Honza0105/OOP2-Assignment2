package animals;

import app.Animal;
import app.Caretaker;
import app.Species;
import app.SpeciesCollection;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Hippo extends Animal {
    static {
        SpeciesCollection.getInstance().add(new Species("Hippo",1000,50,new BigDecimal("400"), Species.Type.CREEPY));
    }
    public Hippo(Caretaker caretaker, String name, LocalDate dateOfBirth, Gender gender, int weight) {
        super(caretaker, SpeciesCollection.getInstance().get("Hippo"), name, dateOfBirth, gender, weight);

    }
}
