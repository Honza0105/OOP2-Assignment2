package app;

import animals.AnimalFactory;
import animals.Lion;
import animals.Tiger;
import food.Pie;
import food.Steak;
import util.CustomClassLoader;
import util.Till;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class Operations {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		new Operations();
	}
	
	public Operations() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		//Till is initiated
		Till till = Till.getInstance(new BigDecimal("100"));

		//Zones creation
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

		//Pen creation
		Pen<Lion> lionPen = new Pen<>(5);
		sector1_1_1.addPen(lionPen);

		//Caretakers creation
		Caretaker John = new Caretaker("John");
		Caretaker Martin = new Caretaker("Martin");
		Caretaker Luke = new Caretaker("Luke");

		//Showing all species currently present
		System.out.println("Currently all the species are:");
		for (Species species: SpeciesCollection.getInstance()
			 ) {
			System.out.println(species);
		}

		//Creation of Lion factory
		AnimalFactory<Lion> lionFactory = new AnimalFactory<Lion>() {
			@Override
			public Lion createAnimal(Caretaker caretaker, String name, LocalDate dateOfBirth, Animal.Gender gender, int weight) {
				return new Lion(caretaker, name, dateOfBirth, gender, weight);
			}
		};

		//Creation of Tiger factory
		AnimalFactory<Tiger> tigerFactory = new AnimalFactory<Tiger>() {
			@Override
			public Tiger createAnimal(Caretaker caretaker, String name, LocalDate dateOfBirth, Animal.Gender gender, int weight) {
				return new Tiger(caretaker,name,dateOfBirth,gender,weight);
			}
		};

		//Creation of Lions using lion factory
		Lion jerry = lionFactory.createAnimal(John,"Jerry",LocalDate.of(2010,10,5), Animal.Gender.MALE,250);
		Lion lion2 = lionFactory.createAnimal(John,"Mark",LocalDate.of(2010,10,5), Animal.Gender.MALE,250);
		Lion lion3 = lionFactory.createAnimal(John,"Nick",LocalDate.of(2010,10,5), Animal.Gender.MALE,250);

		//Adding lion to lionPen directly
		lionPen.addAnimal(jerry);
		//Adding lion to sector including lionPen
		sector1_1_1.addAnimal(lion2);
		//Adding lion to sector not including lionPen
		sector1_1.addAnimal(lion3);

		System.out.println();
		System.out.print("Below we can see only 2 lions because sector1_1 doesn't have a pen for lions");
		System.out.println(sector1_1_1);
		System.out.println();
		sector1_1_1.addAnimal(lion3);

		//Time passing functionality.
		System.out.println("As we can see, hungriness is increasing each time:");
		for (int i = 0; i < 2; i++) {
			zoo.timePasses();
			System.out.println(jerry);
		}

		//Let more time pass
		for (int i = 0; i < 250; i++) {
			zoo.timePasses();
		}
		System.out.println("\nAfter a while our lion is pretty hungry:" + jerry);

		System.out.println("But now when we feed him:");
		jerry.feed(new Steak(),John);
		System.out.println("Steak is worth 10 hungriness points, hence our lion loses 10 points: " + jerry);
		System.out.println("\nBut what happens when a lion is fed by someone else?");
		System.out.println("I am not sure whether to only inform the main caretaker or all caretakers," +
				" so now I do inform all of them.");
		System.out.println("Let's show that with Martin:\n");
		jerry.feed(new Pie(),Martin);
		System.out.println("\nAs we can see, all caretakers have been inform about this horrendous action!");
		System.out.println("Except for the lion who's enjoying his Pie and is less hungry. "+ jerry);

		System.out.println("\nWhile everyone is trying to figure out who dared to feed Jerry a lot of time passes...");
		System.out.println("When suddenly!\n");

		for (int i = 0; i < 248; i++) {
			zoo.timePasses();
		}

		System.out.println("\nIt has turned out, that while they were investigating, they have forgotten about the 2 other lions!");

		System.out.println("Let's take a look what do we have: ");
		System.out.println(lionPen);
		System.out.println();

		System.out.println("Meanwhile a rare species of crocodile has been offered to the zoo. And zoo tries to buy it.");

		SpeciesCollection.getInstance().acquireAnimal("Very expensive Crocodile",100,20,new BigDecimal("1000"), Species.Type.CREEPY, Animal.Gender.MALE, Animal.Gender.FEMALE);

		System.out.println("That is unfortunate. Fortunately there is a generous donor!");

		till.deposit(new BigDecimal("1000"));
		SpeciesCollection.getInstance().acquireAnimal("Very expensive",500,20,new BigDecimal("1000"), Species.Type.CREEPY, Animal.Gender.MALE, Animal.Gender.FEMALE);

		System.out.println();



		//Let's test the plugin now
		//This classloader has been developed by Harald Drillenburg and then modified,
		//because it sometimes had a problem recognizing class names.
		CustomClassLoader ccl = new CustomClassLoader();

		//Importing hippo class
		Class<? extends Animal> HippoClass = (Class<? extends Animal>) ccl.findClass("/Users/janjelinek/Library/CloudStorage/OneDrive-HogeschoolInholland/Inholland/AM2/Term 2/OOP2/Assignment 2/Assignment2-plugin/out/production/Assignment2-plugin/animals/Hippo","animals.Hippo");

		Constructor<? extends Animal> hippoConstructor = HippoClass.getConstructor(Caretaker.class, String.class, LocalDate.class, Animal.Gender.class, int.class);
		Animal hippo = hippoConstructor.newInstance(John,"Carl",LocalDate.of(2021,2,2),Animal.Gender.MALE,2);


		//Hippo factory
		AnimalFactory<Animal> hippoFactory = new AnimalFactory<>() {
			@Override
			public Animal createAnimal(Caretaker caretaker, String name, LocalDate dateOfBirth, Animal.Gender gender, int weight) {
				return new Animal(caretaker,SpeciesCollection.getInstance().get("Hippo"), name, dateOfBirth, gender, weight);
			}
		};

		//Creation of HippoPen. I can't generify hippoPen with Hippo.
		//Theoretically I can add other animals, but if I do so, only species of that Animal can be added to given pen.
		Pen<Animal> hippoPen= new Pen<>(5);
		hippoPen.addAnimal(hippo);
		System.out.println("Here is our pen with a Hippo included by a plugin!");
		System.out.println(hippoPen);
		System.out.println();

		System.out.println("Last but not least we try cloning!");
		System.out.println("Currently we have "+ Till.getInstance().getFunds()+ "€ left in till.");

		//Importing the new method for acquiring Animals
		Class<?> speciesCollectionClass = ccl.findClass("/Users/janjelinek/Library/CloudStorage/OneDrive-HogeschoolInholland/Inholland/AM2/Term 2/OOP2/Assignment 2/Assignment2-plugin/out/production/Assignment2-plugin/app/PluginSpeciesCollection","app.PluginSpeciesCollection");
		//Getting the method
		Method acquireAnimal = speciesCollectionClass.getDeclaredMethod("acquireAnimal", String.class, int.class, int.class, BigDecimal.class, Species.Type.class, Animal.Gender[].class);
		//Getting object of the plugged in speciesCollection
		Object speciesCollectionInstance = speciesCollectionClass.getDeclaredConstructor().newInstance();
		//Executing the new method.
		acquireAnimal.invoke(speciesCollectionInstance, "Shark", 300, 300, new BigDecimal("300"), Species.Type.CREEPY, new Animal.Gender[] {Animal.Gender.MALE});

		System.out.println("And now we have "+ Till.getInstance().getFunds()+ "€ left in till. Because cloning is only 20€!");

		System.out.println("As we can see below. Shark has been added to our Species Collection." +
				" And our plugged in Hippo is also there.");
		System.out.println(Arrays.toString(SpeciesCollection.getInstance().toArray()));
















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
