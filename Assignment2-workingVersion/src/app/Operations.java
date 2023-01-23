package app;

import food.Steak;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Operations {

	public static void main(String[] args) {
		new Operations();
	}
	
	public Operations() {

		Caretaker John = new Caretaker("John");
		Caretaker Martin = new Caretaker("Martin");

		Animal lion = new Animal(John,
				new Species("Lion lion",10,10, BigDecimal.ONE, Species.Type.SCARY, Animal.Gender.MALE),
				"Lion", LocalDate.of(2022,12,1), Animal.Gender.MALE,10);
		lion.feed(new Steak(),John);

		// Do basic initializations
		
		// Create a Zoo consisting of zones, subzones, subsubzones and pens
		
		// Create a bunch of Caretakers. Keep track of all Caretakers in some Collection 
		// so you can loop over it
		
		// Initialize an AnimalFactory using an anonymous inner class and get animals from it
		// Then add the animals to the Zoo
		
		// Have several caretakers feed some animals. Use lambda expressions in that
		// Loop over all animals to pass time and see that their Caretakers get informed about
		// their pets getting hungry
		
		// Implement a plugin for acquiring new animals. The strategy used for that is to clone an existing animal.
		// Cloning has a fixed price defined in the plugin code itself
		// Run the test with the plugin as well
	}
}
