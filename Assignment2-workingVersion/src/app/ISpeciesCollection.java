package app;

import java.math.BigDecimal;

/**
 * This interface makes acquireAnimal possible to be overwritten using a plugin.
 */
public interface ISpeciesCollection {
    default boolean acquireAnimal(String commonName, int maxHungriness, int maxWeight, BigDecimal value, Species.Type type, Animal.Gender... genders) {
        return false;
    }
}
