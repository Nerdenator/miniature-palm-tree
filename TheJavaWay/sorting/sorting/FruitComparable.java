package sorting;

public class FruitComparable extends Fruit implements Comparable<FruitComparable> {

	public FruitComparable(String fruitName, String fruitDesc, int quantity) {
		super(fruitName, fruitDesc, quantity);
	}

	// To sort an Object by its property, you have to make the Object implement
	// the Comparable interface and override the compareTo() method.
	// Returns a negative integer, zero, or a positive integer as this object is
	// less than, equal to, or greater than the specified object.
	@Override
	public int compareTo(FruitComparable fruit) {
		// ascending order
		return this.getQuantity() - fruit.getQuantity();

		// // descending order
		// return fruit.getQuantity() - this.quantity;
	}

}