// Name: Leonidas Karnesis
// USC NetID: karnesis
// CS 455 PA4
// Spring 2018
public class ScoreTable {
	/*
	 * (1 point)-A, E, I, O, U, L, N, S, T, R (2 points)-D, G (3 points)-B, C, M, P
	 * (4 points)-F, H, V, W, Y (5 points)-K (8 points)- J, X (10 points)-Q, Z
	 */
	private int[] scores = new int[26];

	public ScoreTable() {//create the score table (a is in position 0, b is in position 1... and put the points of each letter proportionally)

		for (int i = 0; i < scores.length; i++) {
			if (i == (int) 'a' - (int) 'a' || i == (int) 'e' - (int) 'a' || i == (int) 'i' - (int) 'a'
					|| i == (int) 'o' - (int) 'a' || i == (int) 'u' - (int) 'a' || i == (int) 'l' - (int) 'a'
					|| i == (int) 'n' - (int) 'a' || i == (int) 's' - (int) 'a' || i == (int) 't' - (int) 'a'
					|| i == (int) 'r' - (int) 'a') {
				scores[i] = 1;
			}
			if (i == (int) 'd' - (int) 'a' || i == (int) 'g' - (int) 'a') {
				scores[i] = 2;
			}
			if (i == (int) 'b' - (int) 'a' || i == (int) 'c' - (int) 'a' || i == (int) 'm' - (int) 'a'
					|| i == (int) 'p' - (int) 'a') {
				scores[i] = 3;
			}
			if (i == (int) 'f' - (int) 'a' || i == (int) 'h' - (int) 'a' || i == (int) 'v' - (int) 'a'
					|| i == (int) 'w' - (int) 'a' || i == (int) 'y' - (int) 'a') {
				scores[i] = 4;
			}
			if (i == (int) 'k' - (int) 'a') {
				scores[i] = 5;
			}
			if (i == (int) 'j' - (int) 'a' || i == (int) 'x' - (int) 'a') {
				scores[i] = 8;
			}
			if (i == (int) 'q' - (int) 'a' || i == (int) 'z' - (int) 'a') {
				scores[i] = 10;
			}
		}

	}

	public int getScore(String word) {//calculate the score of a word
		int score = 0;
		for (int j = 0; j < word.length(); j++) {
			Character ch=Character.toLowerCase(word.charAt(j));
			score = score + scores[(int) ch - (int) 'a'];
		}
		return score;
	}
}
