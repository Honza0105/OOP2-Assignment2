package animals;

import app.Animal;
import app.Caretaker;

import java.time.LocalDate;

public interface AnimalFactory<T extends Animal> {
    T createAnimal(Caretaker caretaker, String name, LocalDate dateOfBirth, Animal.Gender gender, int weight);
}

