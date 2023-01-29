package app;

import util.Till;

import java.math.BigDecimal;

public class PluginSpeciesCollection implements ISpeciesCollection{
    SpeciesCollection speciesCollection = SpeciesCollection.getInstance();
    @Override
    public boolean acquireAnimal(String commonName, int maxHungriness, int maxWeight, BigDecimal value, Species.Type type, Animal.Gender gender) {
        value = new BigDecimal("20");
        if (value.compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new IllegalArgumentException("Cost of animal should be greater than zero.");
        }
        if (!Till.getInstance().withdraw(value)) {
            System.out.println("Not enough funds to clone");
            return false;
        }
        System.out.println(" has been cloned!");
        speciesCollection.add(new Species(commonName, maxHungriness, maxWeight, value, type, gender));
        return true;
    }

}

