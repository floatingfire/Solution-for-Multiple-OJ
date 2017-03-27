/*
 ID: lloo1351
 LANG: JAVA
 PROG: friday
 */
import java.io.*;

public class friday {
	private static int[] month = { 1, 3, 5, 7, 8, 10, 12 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("friday.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("friday.out"));

		int[] w = new int[7];
		int y = Integer.parseInt(br.readLine());
		int d = (int) (y * 365.25);
		int i = 13, j = 1, k = 1900;
		while (i < d) {
			w[i % 7]++;
			i += getDayAmont(j, k);
			j++;
			if (j > 12) {
				j -= 12;
				k++;
			}
		}
		pw.println(w[6] + " " + w[0] + " " + w[1] + " " + w[2] + " " + w[3]
				+ " " + w[4] + " " + w[5]);
		br.close();
		pw.close();
	}

	private static boolean bigMonth(int m) {
		for (int i : month) {
			if (i == m) {
				return true;
			}
		}
		return false;
	}

	private static boolean leapYear(int y) {
		return y % 100 == 0 ? (y % 400 == 0 ? true : false)
				: (y % 4 == 0 ? true : false);
	}

	private static int getDayAmont(int m, int y) {
		return bigMonth(m) ? 31 : (m != 2 ? 30 : (leapYear(y) ? 29 : 28));
	}
}
