package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestReadArray {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter array separated by commas:");

		String input = br.readLine();
		System.out.println(input);

		String[] arr = input.split(",");
		int[] num = new int[arr.length];
		for (int i = 0; i < arr.length; i++)
			num[i] = Integer.parseInt(arr[i]);
		for (int n : num)
			System.out.println(n);
	}
}
