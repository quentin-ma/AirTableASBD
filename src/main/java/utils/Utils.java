/**
 * TP n°4
 * 
 * Titre du TP : Block Nested Loop Airtable
 *
 * Date : 17 novembre 2020
 * 
 * Nom  : MA
 * Prenom : Quentin
 *
 * email : quentin.ma@etu.u-paris.fr
 * 
 * Remarques : Cette classe fournit l'ensemble des méthodes dites "utiles" notre programme.
 * Ce sont des méthodes outils qui se complètent à notre programme, car elles ne font pas corps
 * avec la logique intrinsèque du programme.
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import dto.R;

public class Utils<T> {
	
	public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
	public static final int MAX_ELEMENT = 10;
	
	public static int encodeToASCII(String str) {
		StringBuilder sb = new StringBuilder();
		for (char ch : str.toCharArray()) {
			sb.append((byte) ch);
		}
		return Integer.parseInt(sb.toString());
	}
	
	public static String decodeASCII(int ascii) {
		char[] array = Integer.toString(ascii).toCharArray();
		String key = "";
		for (int i = 0; i < array.length; i += 2) {
			String tmp = String.valueOf(array[i]) + String.valueOf(array[i + 1]);
			key += Character.toString(Integer.parseInt(tmp));
		}
		return key;
	}
	
	public int[] generateASCII(Class<T> o) {
		List<String> list = new ArrayList<>();
		int size = o.equals(R.class) ? Constants.R_ELEMENTS : Constants.S_ELEMENTS;
		for (int i = 0; i < ALPHABET.length; i++) {
			for (int j = 0; j < 8; j++) {
				if (list.size() == size) {
					break;
				}
				String element = String.valueOf(ALPHABET[i]) + String.valueOf(ALPHABET[j]);
				list.add(element);
			}
		}
		Collections.shuffle(list);
		int i = 0;
		int[] ascValues = new int[size];
		while (i < list.size()) {
			char x = list.get(i).charAt(0);
			char y = list.get(i).charAt(1);
			int a = (char) x;
			int b = (char) y;
			int asc = Integer.parseInt(Integer.toString(a) + Integer.toString(b));
			ascValues[i] = asc;
			i++;
		}
		return ascValues;
	}
	
	public static List<String> generate(int size) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < ALPHABET.length; i++) {
			for (int j = 0; j < 8; j++) {
				if (list.size() == size) {
					break;
				}
				String element = String.valueOf(ALPHABET[i]) + String.valueOf(ALPHABET[j]);
				list.add(element);
			}
		}
		Collections.shuffle(list);
		return list;
	}

	
	public int[] toASCII(List<String> list, Class<T> o) {
		int n = o.equals(R.class) ? Constants.R_ELEMENTS : Constants.S_ELEMENTS;
		int i = 0;
		int[] ascValues = new int[n];
		while (i < list.size()) {
			char x = list.get(i).charAt(0);
			char y = list.get(i).charAt(1);
			int a = (char) x;
			int b = (char) y;
			int asc = Integer.parseInt(Integer.toString(a) + Integer.toString(b));
			ascValues[i] = asc;
			i++;
		}
		return ascValues;
	}
	
	public void segmentData(int[] array, Class<T> o) throws IOException {
		int i = 0;
		int j = 0;
		int n_files = (int) Math.ceil((double) array.length / MAX_ELEMENT);
		System.out.println(n_files);
		int max = MAX_ELEMENT;
		while (i < n_files) {
			FileWriter file = new FileWriter(filePath(i + ".txt", o));
			BufferedWriter bf = new BufferedWriter(file);
			while (j < max) {
				if (j == array.length) {
					break;
				}
				bf.write(array[j] + System.lineSeparator());
				j++;
			}
			bf.close();
			max += MAX_ELEMENT;
			i++;
		}
	}

	public int[] readMemory(String filename, Class<T> o) {
		int[] array = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath(filename, o)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String collect = br.lines().collect(Collectors.joining(System.lineSeparator()));
		array = Arrays.asList(collect.split("\n")).stream().mapToInt(Integer::parseInt).toArray();
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	public static String filePathProperties(String filename) {
		return System.getProperty("user.dir")
				.concat("/src/main/resources/")
				.concat(filename);
	}
	
	public String filePath(String filename, Class<T> o) {
		return System.getProperty("user.dir")
				.concat("/src/main/resources/" + o.getSimpleName() + "/")
				.concat(filename);
	}

}
