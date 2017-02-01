package sorting;

import java.util.Comparator;

public class SortByQuantity implements Comparator<Fruit> {

	@Override
	public int compare(Fruit fruit1, Fruit fruit2) {
		// ascending order
		return fruit1.getQuantity() - fruit2.getQuantity();

		// // descending order
		// return fruit2.getQuantity() - fruit1.getQuantity();
	}

}
