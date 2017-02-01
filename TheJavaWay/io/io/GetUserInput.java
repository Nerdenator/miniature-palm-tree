package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetUserInput {
	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static double getDouble() throws IOException {
		String s = getString();
		Double aDub = Double.valueOf(s);
		return aDub.doubleValue();
	}

	public static void main(String[] args) throws IOException {
		String inp = getString();
		System.out.println(inp);
		double inpD = getDouble();
		System.out.println(inpD);
	}
}
