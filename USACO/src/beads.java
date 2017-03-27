/*
 ID: lloo1351
 LANG: JAVA
 PROG: beads
 */
import java.io.*;

public class beads {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("beads.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"beads.out")), true);
		in.readLine();
		String line = in.readLine();
		String beads = line + line;
		int indexNotWhite = notWhiteIndex(0, beads);
		if (indexNotWhite == -1) {
			out.println(line.length());
			return;
		}
		int max = 0;
		do {
			char left = beads.charAt(indexNotWhite);
			int leftCount = leftCount(left, indexNotWhite, beads);
			char right = (left == 'r' ? 'b' : 'r');
			int rightCount = rightCount(right, indexNotWhite + 1, beads);
			if (leftCount + rightCount > max)
				max = leftCount + rightCount;
		} while ((indexNotWhite = notWhiteIndex(indexNotWhite + 1, beads)) != -1);
		if (max > line.length())
			out.println(line.length());
		else
			out.println(max);
		System.exit(0);
	}

	private static int notWhiteIndex(int beg, String beads) {
		for (int i = beg; i < beads.length(); i++) {
			if (beads.charAt(i) != 'w') {
				return i;
			}
		}
		return -1;
	}

	private static int leftCount(char color, int beg, String beads) {
		int cnt = 0;
		for (int i = beg; i >= 0; i--) {
			if (beads.charAt(i) == color || beads.charAt(i) == 'w')
				cnt++;
			else
				return cnt;
		}
		return cnt;
	}

	private static int rightCount(char color, int beg, String beads) {
		int cnt = 0;
		for (int i = beg; i < beads.length(); i++) {
			if (beads.charAt(i) == color || beads.charAt(i) == 'w')
				cnt++;
			else
				return cnt;
		}
		return cnt;
	}
}