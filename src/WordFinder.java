// Name: Leonidas Karnesis
// USC NetID: karnesis
// CS 455 PA4
// Spring 2018
import java.io.FileNotFoundException;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class WordFinder {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		String filename;

		if (args.length == 0) {// the filename is "sowpods.txt" if there is no argument
			filename = "sowpods.txt";
		} else {
			filename = args[0];// else filename=argument
		}
		AnagramDictionary anagram = null;
		try {// if there is argument check if the file exits and creates an AnagramDictionary
				// out of it
			anagram = new AnagramDictionary(filename);
		} catch (FileNotFoundException exception) {// if the file does not exit we print an error and exit the program
			System.out.println("ERROR: File not found:" + filename);
			System.exit(1);
		}
		boolean exit = false;// variable to exit the loop
		System.out.println("Type . to quit.");
		System.out.print("Rack? ");
		String rackinput = in.next();
		rackinput = removeCharAt(rackinput);
		if (rackinput.charAt(0) == '.') {// if the input is . then exit equals true and we exit the loop
			exit = true;
		}
		run(rackinput, exit, anagram, in);
	}

	private static void run(String rackinput, boolean exit, AnagramDictionary anagram, Scanner in) {// runs the loop
																									// until exit=true
		while (!exit) {

			if (rackinput.charAt(0) == '.') {
				exit = true;
			}
			Rack racknew = new Rack(rackinput);//creates a new Rack
			ArrayList<String> subs = racknew.Subsets();// initialize an Arraylist with all the subsets of the specific rack
			Map<String, Integer> order = new TreeMap<String, Integer>();//initialize a Map with ordered keys 
			createanagrams(subs, anagram, order);// add the anagrams of each subset of the rack in Map order if these exit in the AnagramDictionary
			printresults(racknew, order, rackinput, anagram);// print the results as specified by the assignment 
			System.out.println("Rack? ");
			rackinput = in.next();
			if (rackinput.charAt(0) == '.') {
				exit = true;
			}
		}

	}

	private static String removeCharAt(String rackinput) {// remove the characters from a word that are not letters and
															// returns the word
		for (int i = 0; i < rackinput.length(); i++) {
			if (!Character.isLetter(rackinput.charAt(i))) {
				rackinput = rackinput.substring(0, i) + rackinput.substring(i + 1);
				i--;
			}
		}
		return rackinput;
	}

	private static void createanagrams(ArrayList<String> subs, AnagramDictionary anagram, Map<String, Integer> order) {
		
		ScoreTable scores = new ScoreTable();// new ScoreTable with the scores

		for (int j = 0; j < subs.size(); j++) {
			String cur = subs.get(j);
			int val = 0;
			if (anagram.getAnagramsOf(cur) != null) {//if the anagrams of each subset exist in the AnagramDictionary
				for (int i = 0; i < anagram.getAnagramsOf(cur).size(); i++) {

					String word = anagram.getAnagramsOf(cur).get(i);//get each String of the Arraylist of anagrams 
					val = scores.getScore(word);//calculate the score of a String 

					order.put(word, val);//fill the keys and values 

				}
			}
		}

	}

	private static void printresults(Rack racknew, Map<String, Integer> order, String rackinput,
			AnagramDictionary anagram) {
		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(order.entrySet());

		Collections.sort(list, new decreasingorder());//sort the list by value 
		System.out.println("We can make " + list.size() + " words from " + '"' + anagram.sortedword(rackinput) + '"');
		if (list.size() > 0) {
			System.out.println("All of the words with their scores (sorted by score):");
		}
		for (Map.Entry<String, Integer> now : list) {
			System.out.println(now.getValue() + ": " + now.getKey());
		}
	}
}

class decreasingorder implements Comparator<Map.Entry<String, Integer>> {// implements the Comparator and we define the
																			// compare method so that the greatest value
																			// should be printed first

	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		return o2.getValue() - o1.getValue();
	}

}
