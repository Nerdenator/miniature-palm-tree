package io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	public static String readFile(String filename) {
		String content = null;
		File file = new File(filename); // for ex foo.txt
		try {
			FileReader reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	public static void main(String[] args) {
		String res = ReadFile.readFile("test.txt");
		System.out.println(res);
	}
}
