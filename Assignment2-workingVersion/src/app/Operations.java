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
		Till till = Till.getInstance(new BigDecimal("100"));


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
		SpeciesCollection.getInstance().acquireAnimal("Crocodile",100,20,new BigDecimal("150"), Species.Type.FLUFFY, Animal.Gender.MALE, Animal.Gender.FEMALE);

		till.deposit(new BigDecimal("1000"));
		SpeciesCollection.getInstance().acquireAnimal("Long living crocodile",500,20,new BigDecimal("150"), Species.Type.CREEPY, Animal.Gender.MALE, Animal.Gender.FEMALE);

		for (Species species: SpeciesCollection.getInstance()
		) {
			System.out.println(species);
		}

//		CustomClassLoader ccl = new CustomClassLoader();
//		ccl.findClass("/Users/janjelinek/temp/Crocodile");
//		CustomClassLoader customClassLoader = new CustomClassLoader();
//		try {
//			Object obj;
//			obj = customClassLoader.findClass("/Users/janjelinek/temp/Crocodile").newInstance();
//			Method[] methods = obj.getClass().getDeclaredMethods();
//			System.out.println(String.format("Methods of %s class:",obj.getClass().getName()));
//			for(Method method : methods) {
//				System.out.println(method.getName());
//			}
//		} catch (ClassFormatError e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		}




//		ccl.findClass("/Users/janjelinek/Library/CloudStorage/OneDrive-HogeschoolInholland/Inholland/AM2/Term 2/OOP2/Assignment 2/Assignment2-plugin/out/production/Assignment2-plugin/animals/Crocodile");

		CustomClassLoader ccl = new CustomClassLoader();

		//do I need hippo? bcs I do the cloning stuff actually
		Class<? extends Animal> HippoClass = (Class<? extends Animal>) ccl.findClass("/Users/janjelinek/Library/CloudStorage/OneDrive-HogeschoolInholland/Inholland/AM2/Term 2/OOP2/Assignment 2/Assignment2-plugin/out/production/Assignment2-plugin/animals/Hippo","animals.Hippo");

		Constructor<? extends Animal> hippoConstructor = HippoClass.getConstructor(Caretaker.class, String.class, LocalDate.class, Animal.Gender.class, int.class);
		Animal hippo = hippoConstructor.newInstance(John,"Hippo",LocalDate.of(2021,2,2),Animal.Gender.MALE,2);
		System.out.println(hippo);

		Class<?> speciesCollectionClass = ccl.findClass("/Users/janjelinek/Library/CloudStorage/OneDrive-HogeschoolInholland/Inholland/AM2/Term 2/OOP2/Assignment 2/Assignment2-plugin/out/production/Assignment2-plugin/app/PluginSpeciesCollection","app.PluginSpeciesCollection");
		for (Method method: speciesCollectionClass.getDeclaredMethods()
			 ) {
			System.out.println(method);
		}
		//now I have to add methods and then try to invoke it and test if it is added
		Method acquireAnimal = speciesCollectionClass.getDeclaredMethod("acquireAnimal",String.class,int.class,int.class, BigDecimal.class, Species.Type.class, Animal.Gender.class);
		acquireAnimal.invoke(speciesCollectionClass,"Hippo",300,300,new BigDecimal("300"), Species.Type.CREEPY, Animal.Gender.MALE);
		System.out.println(Arrays.toString(speciesCollectionClass.getDeclaredMethods()));














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
