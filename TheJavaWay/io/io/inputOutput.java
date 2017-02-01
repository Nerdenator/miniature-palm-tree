package io;

import java.util.Scanner;

public class inputOutput {

	public static void readFromConsole() {
		System.out.print("Write a string: ");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		System.out.println("You wrote: " + input);
		scanner.close();
	}

	public static void readFromFile() {
		System.out.print("Write a string: ");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		System.out.println("You wrote: " + input);
		scanner.close();
	}

	public static void main(String[] args) {
		readFromConsole();
	}
}
