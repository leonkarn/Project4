
// Name: Leonidas Karnesis
// USC NetID: karnesis
// CS 455 PA4
// Spring 2018

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A dictionary of all anagram sets. Note: the processing is case-sensitive; so
 * if the dictionary has all lower case words, you will likely want any string
 * you test to have all lower case letters too, and likewise if the dictionary
 * words are all upper case.
 */

public class AnagramDictionary {
	private Map<String, ArrayList<String>> anagramdict;

	/**
	 * Create an anagram dictionary from the list of words given in the file
	 * indicated by fileName. PRE: The strings in the file are unique.
	 * 
	 * @param fileName
	 *            the name of the file to read from
	 * @throws FileNotFoundException
	 *             if the file is not found
	 */
	public AnagramDictionary(String fileName) throws FileNotFoundException {//create a Hashmap which has as a key a sorted word and as a value an Arraylist with all the words in the dictionnary that are anagrams of this sorted word
		anagramdict = new HashMap<String, ArrayList<String>>();
		Scanner in = new Scanner(new File(fileName));
		while (in.hasNextLine()) {
			String dictword = in.nextLine();
			String sorted=sortedword(dictword);
			
			if (!anagramdict.containsKey(sorted)) {
				ArrayList<String> anagrams = new ArrayList<String>();
				anagrams.add(dictword);
				anagramdict.put(sorted, anagrams);

			} else {
				anagramdict.get(sorted).add(dictword);
			}
		}

	}
	public String sortedword(String word) {//return the sorted word
		 
		char[] ch = word.toCharArray();
		Arrays.sort(ch);
		String sorted = String.valueOf(ch);
		return sorted;
		
		
	}

	/**
	 * Get all anagrams of the given string. This method is case-sensitive. E.g.
	 * "CARE" and "race" would not be recognized as anagrams.
	 * 
	 * @param s
	 *            string to process
	 * @return a list of the anagrams of s
	 * 
	 */
	public ArrayList<String> getAnagramsOf(String s) {//returns an ArrayList of the anagrams of a specific String
		
		return anagramdict.get(sortedword(s));

		 
	}

}