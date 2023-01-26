package app;

import animals.AnimalFactory;
import animals.Lion;
import animals.Tiger;
import food.Pie;
import food.Steak;

import java.time.LocalDate;

public class Operations {

	public static void main(String[] args) {
		new Operations();
	}
	
	public Operations() {

		Zone<Animal> zoo = new Zone<>();


		Zone<Animal> sector1 = new Zone<>();
		zoo.addZone(sector1);

		Zone<Animal> sector1_1 = new Zone<>();
		sector1.addZone(sector1_1);
		Zone<Animal> sector1_2 = new Zone<>();
		sector1.addZone(sector1_2);
		Zone<Animal> sector1_3 = new Zone<>();
		sector1.addZone(sector1_3);


		Zone<Animal> sector2 = new Zone<>();
		zoo.addZone(sector2);

		Zone<Animal> sector2_1 = new Zone<>();
		sector2.addZone(sector2_1);
		Zone<Animal> sector2_2 = new Zone<>();
		sector2.addZone(sector2_2);
		Zone<Animal> sector2_3 = new Zone<>();
		sector2.addZone(sector2_3);


		Zone<Animal> sector3 = new Zone<>();
		zoo.addZone(sector3);

		Zone<Animal> sector3_1 = new Zone<>();
		sector3.addZone(sector3_1);
		Zone<Animal> sector3_2 = new Zone<>();
		sector3.addZone(sector3_2);
		Zone<Animal> sector3_3 = new Zone<>();
		sector3.addZone(sector3_3);


		Zone<Animal> sector1_1_1 = new Zone<>();
		sector1_1.addZone(sector1_1_1);
		Zone<Animal> sector2_2_1 = new Zone<>();
		sector2_2.addZone(sector2_2_1);
		Zone<Animal> sector2_2_2 = new Zone<>();
		sector2_2.addZone(sector2_2_2);

		Pen<Lion> lionPen = new Pen<>(5);
		sector1_1_1.addPen(lionPen);


		Caretaker John = new Caretaker("John");
		Caretaker Martin = new Caretaker("Martin");
		Caretaker Luke = new Caretaker("Luke");
		System.out.println(Caretaker.getAllCaretakers());

		for (Species species: SpeciesCollection.getInstance()
			 ) {
			System.out.println(species);
		}

		AnimalFactory<Lion> lionFactory = new AnimalFactory<Lion>() {
			@Override
			public Lion createAnimal(Caretaker caretaker, String name, LocalDate dateOfBirth, Animal.Gender gender, int weight) {
				return new Lion(caretaker, name, dateOfBirth, gender, weight);
			}
		};

		AnimalFactory<Tiger> tigerFactory = new AnimalFactory<Tiger>() {
			@Override
			public Tiger createAnimal(Caretaker caretaker, String name, LocalDate dateOfBirth, Animal.Gender gender, int weight) {
				return new Tiger(caretaker,name,dateOfBirth,gender,weight);
			}
		};


		Lion lion1 = lionFactory.createAnimal(John,"Jerry",LocalDate.of(2010,10,5), Animal.Gender.MALE,250);
		Lion lion2 = lionFactory.createAnimal(John,"Mark",LocalDate.of(2010,10,5), Animal.Gender.MALE,250);
		Lion lion3 = lionFactory.createAnimal(John,"Nick",LocalDate.of(2010,10,5), Animal.Gender.MALE,250);


		lionPen.addAnimal(lion1);
		sector1_1_1.addAnimal(lion2);
		sector1_1_1.addAnimal(lion3);

		for (int i = 0; i < 10; i++) {
			zoo.timePasses();
		}


		lion1.feed(new Pie(),Martin);
		lion1.feed(new Steak(),John);

//		for (int i = 0; i < 500; i++) {
//			zoo.timePasses();
//		}
//		System.out.println(lionPen);












//		lion.feed(new Steak(),Martin);
//		for (int i = 0; i < 11; i++) {
//			lion.timePasses();
//		}

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
