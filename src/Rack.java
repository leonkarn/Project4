
// Name: Leonidas Karnesis
// USC NetID: karnesis
// CS 455 PA4
// Spring 2018


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * A Rack of Scrabble tiles
 */

public class Rack {
	private String unique = "";
	private int[] mult;

	public Rack(String rack) {
		Map<Character, Integer> map = new TreeMap<Character, Integer>();//initialize a TreeMap with key each character of the rack and Integer how many times each character appears in the Rack
		
		for (int i = 0; i < rack.length(); i++) {
			if (null == map.get(rack.charAt(i))) {//if the character does not exit in the map put it with initial value 1
				map.put(rack.charAt(i), 1);

			} else {//add to the value that the Character(key) already has the value of 1 because the character appeared one more time
				int x = (int) map.get(rack.charAt(i)) + 1;
				map.put(rack.charAt(i), x);

			}
		}

		mult = new int[map.size()];//mult will contain the number of times each character appears with alphabetic order
		int j = 0;
		for (Map.Entry<Character, Integer> curr : map.entrySet()) {
			
			unique = unique + curr.getKey();//unique all the letters that appear in the rack in a string but one time each
			mult[j] = curr.getValue();
			j++;
		}

	}



	/**
	 * Finds all subsets of the multiset starting at position k in unique and mult.
	 * unique and mult describe a multiset such that mult[i] is the multiplicity of
	 * the char unique.charAt(i). PRE: mult.length must be at least as big as
	 * unique.length() 0 <= k <= unique.length()
	 * 
	 * @param unique
	 *            a string of unique letters
	 * @param mult
	 *            the multiplicity of each letter from unique.
	 * @param k
	 *            the smallest index of unique and mult to consider.
	 * @return all subsets of the indicated multiset
	 * @author Claire Bono
	 */
	private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {// public method for subset
		ArrayList<String> allCombos = new ArrayList<>();

		if (k == unique.length()) { // multiset is empty
			allCombos.add("");
			return allCombos;
		}

		// get all subsets of the multiset without the first unique char
		ArrayList<String> restCombos = allSubsets(unique, mult, k + 1);

		// prepend all possible numbers of the first char (i.e., the one at position k)
		// to the front of each string in restCombos. Suppose that char is 'a'...

		String firstPart = ""; // in outer loop firstPart takes on the values: "", "a", "aa", ...
		for (int n = 0; n <= mult[k]; n++) {
			for (int i = 0; i < restCombos.size(); i++) { // for each of the subsets
															// we found in the recursive call
				// create and add a new string with n 'a's in front of that subset
				allCombos.add(firstPart + restCombos.get(i));
			}
			firstPart += unique.charAt(k); // append another instance of 'a' to the first part
		}

		return allCombos;
	}

	public ArrayList<String> Subsets() {//returns the subsets of a specific Rack and allSubsets works as a helper method
		return allSubsets(unique, mult, 0);
	}
}
