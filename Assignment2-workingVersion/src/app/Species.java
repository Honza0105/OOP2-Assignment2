package app;

import java.math.BigDecimal;
import java.util.*;

/**
 * Species class contains all the necessary information about given species.
 */
public class  Species {
	private static final Random r = new Random();
	private String commonName;
	private int maxHungriness;
	private BigDecimal value;
	private List<Animal.Gender> allowedGenders;
	private final Type type;
	private int maxWeight;
	
	public Species(String commonName, int maxHungriness, int maxWeight, BigDecimal value, Type type, Animal.Gender... genders) {
		super();
		this.commonName = commonName;
		this.maxHungriness = maxHungriness;
		this.value = value;
		this.allowedGenders = new ArrayList<>();
		this.allowedGenders.addAll(Arrays.asList(genders));
		this.type = type;
		this.maxWeight = maxWeight;
	}
	
	public String getCommonName() {
		return commonName;
	}
	
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public int getMaxHungriness() {
		return maxHungriness;
	}
	
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	

	public int getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public Type getType() {
		return type;
	}

	public void setMaxHungriness(int maxHungriness) {
		if ((maxHungriness < 1) || (maxHungriness > 100)) {
			throw new IllegalArgumentException("Max hungriness must be between 1 and 100, was set as" + maxHungriness);
		}
		this.maxHungriness = maxHungriness;
	}

	@Override
	public int hashCode() {
		return Objects.hash(commonName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Species other = (Species) obj;
		return Objects.equals(commonName, other.commonName);
	}

	@Override
	public String toString() {
		return commonName + " (" + type + ")";
	}
	
	public Animal.Gender randomGender() {
		return allowedGenders.get(r.nextInt(allowedGenders.size()));
	}


	/**
	 * This class describes types of animals.By default, Common and Fluffy are adopted, creepy and scary are bought.
	 */
	public enum Type { COMMON(0.2), CREEPY(0.1), SCARY(0.4), FLUFFY(0.3);
		private double digestSpeed;
		
		Type(double digestSpeed) {
			this.digestSpeed = digestSpeed;
		}
		
		public double getDigestSpeed() {
			return digestSpeed;
		}
		
		public String toString() {
			return this.name().substring(0,1).toUpperCase() + this.name().substring(1).toLowerCase();
		}
	}
}
