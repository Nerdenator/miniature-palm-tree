package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class sortingExamples {

	/**
	 * 1. Sort an array using Arrays.sort(array)
	 */
	private static void sortArraysSort() {
		System.out.println("\nArrays.sort:");
		String[] fruits = new String[] { "Pineapple", "Apple", "Orange", "Banana" };

		Arrays.sort(fruits);

		for (int i = 0; i < fruits.length; i++) {
			System.out.println("fruits " + (i + 1) + " : " + fruits[i]);
		}
	}

	/**
	 * 2. Sort an ArrayList using Collections.sort(arrayList)
	 */
	private static void sortCollectionsArrayList() {
		System.out.println("\nCollections.sort:");
		List<String> fruits = new ArrayList<String>();

		fruits.add("Pineapple");
		fruits.add("Apple");
		fruits.add("Orange");
		fruits.add("Banana");

		Collections.sort(fruits);

		for (int i = 0; i < fruits.size(); i++) {
			System.out.println("fruits " + (i + 1) + " : " + fruits.get(i));
		}
	}

	/**
	 * 3. Sort an object using Comparable (implement Comparable, override
	 * compareTo method)
	 */
	private static void sortObjectComparableCompareTo() {
		System.out.println("\nObject Comparable.compareTo:");
		FruitComparable[] fruits = new FruitComparable[4];

		FruitComparable pineappale = new FruitComparable("Pineapple", "Pineapple description", 70);
		FruitComparable apple = new FruitComparable("Apple", "Apple description", 100);
		FruitComparable orange = new FruitComparable("Orange", "Orange description", 80);
		FruitComparable banana = new FruitComparable("Banana", "Banana description", 90);

		fruits[0] = pineappale;
		fruits[1] = apple;
		fruits[2] = orange;
		fruits[3] = banana;

		Arrays.sort(fruits);

		for (int i = 0; i < fruits.length; i++) {
			System.out.println("fruits " + (i + 1) + " : " + fruits[i].getFruitName() + ", " + fruits[i].getQuantity());
		}
	}

	/**
	 * 4. Sort an object using a Comparator functor (allows multiple sort
	 * fields)
	 */
	private static void sortObjectComparableComparatorCompare() {
		System.out.println("\nObject Comparator.compare:");
		Fruit[] fruits = new Fruit[4];

		Fruit pineappale = new Fruit("Pineapple", "Pineapple description", 70);
		Fruit apple = new Fruit("Apple", "Apple description", 100);
		Fruit orange = new Fruit("Orange", "Orange description", 90);
		Fruit banana = new Fruit("Banana", "Banana description", 80);

		fruits[0] = pineappale;
		fruits[1] = apple;
		fruits[2] = orange;
		fruits[3] = banana;

		System.out.println("\nSort by name:");
		Arrays.sort(fruits, new SortByName());

		for (int i = 0; i < fruits.length; i++) {
			System.out.println("fruits " + (i + 1) + " : " + fruits[i].getFruitName() + ", " + fruits[i].getQuantity());
		}

		System.out.println("\nSort by quantity:");
		Arrays.sort(fruits, new SortByQuantity());

		for (int i = 0; i < fruits.length; i++) {
			System.out.println("fruits " + (i + 1) + " : " + fruits[i].getFruitName() + ", " + fruits[i].getQuantity());
		}
	}

	/**
	 * 5. Passing the comparator class directly to sort method anonymously
	 */
	private static void sortObjectAnonymousComparator() {
		System.out.println("\nObject Comparator anonymous function passed to sort:");
		Fruit[] fruits = new Fruit[4];

		Fruit pineappale = new Fruit("Pineapple", "Pineapple description", 70);
		Fruit apple = new Fruit("Apple", "Apple description", 100);
		Fruit orange = new Fruit("Orange", "Orange description", 90);
		Fruit banana = new Fruit("Banana", "Banana description", 80);

		fruits[0] = pineappale;
		fruits[1] = apple;
		fruits[2] = orange;
		fruits[3] = banana;

		Arrays.sort(fruits, new Comparator<Fruit>() {
			@Override
			public int compare(Fruit f1, Fruit f2) {
				return f1.getFruitName().compareTo(f2.getFruitName());
			}
		});

		for (int i = 0; i < fruits.length; i++) {
			System.out.println("fruits " + (i + 1) + " : " + fruits[i].getFruitName() + ", " + fruits[i].getQuantity());
		}

	}

	public static void main(String[] args) {
		sortArraysSort();

		sortCollectionsArrayList();

		sortObjectComparableCompareTo();

		sortObjectComparableComparatorCompare();

		sortObjectAnonymousComparator();
	}
}
