package animals;

import app.Animal;
import app.Caretaker;

import java.time.LocalDate;

/**
 * This interface enables AnimalFactory to create Animals.
 * @param <T> Any animal.
 */
public interface AnimalFactory<T extends Animal> {
    T createAnimal(Caretaker caretaker, String name, LocalDate dateOfBirth, Animal.Gender gender, int weight);
}

