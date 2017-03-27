/*
 ID: lloo1351
 LANG: JAVA
 PROG: transform
 */
import java.io.*;

public class transform {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("transform.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("transform.out"));

		int n = Integer.parseInt(br.readLine());
		char[][] chb = new char[n][n];
		char[][] cha = new char[n][n];
		for (int i = 0; i < n; i++) {
			chb[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < n; i++) {
			cha[i] = br.readLine().toCharArray();
		}
		if (eq(r1(chb), cha))
			pw.println("1");
		else if (eq(r2(chb), cha))
			pw.println("2");
		else if (eq(r3(chb), cha))
			pw.println("3");
		else if (eq(rf(chb), cha))
			pw.println("4");
		else if (eq(r1(rf(chb)), cha) || eq(r2(rf(chb)), cha)
				|| eq(r3(rf(chb)), cha))
			pw.println("5");
		else if (eq(chb, cha))
			pw.println("6");
		else
			pw.println("7");
		br.close();
		pw.close();
	}

	private static char[][] r1(char[][] ch) {
		int n = ch.length;
		char[][] chx = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				chx[i][n - j - 1] = ch[j][i];
			}
		}
		return chx;
	}

	private static char[][] r2(char[][] ch) {
		int n = ch.length;
		char[][] chx = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				chx[i][j] = ch[n - i - 1][n - j - 1];
			}
		}
		return chx;
	}

	private static char[][] r3(char[][] ch) {
		int n = ch.length;
		char[][] chx = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				chx[j][i] = ch[i][n - j - 1];
			}
		}
		return chx;
	}

	private static char[][] rf(char[][] ch) {
		int n = ch.length;
		char[][] chx = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				chx[i][j] = ch[i][n - j - 1];
			}
		}
		return chx;
	}

	private static boolean eq(char[][] ch1, char[][] ch2) {
		int n = ch1.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (ch1[i][j] != ch2[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
