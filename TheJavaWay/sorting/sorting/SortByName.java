package sorting;

import java.util.Comparator;

public class SortByName implements Comparator<Fruit> {

	@Override
	public int compare(Fruit fruit1, Fruit fruit2) {
		// ascending order
		return fruit1.getFruitName().compareTo(fruit2.getFruitName());

		// // descending order
		// return fruit2.getFruitName().compareTo(fruit2.getFruitName());
	}

}
