import java.util.*;

public class HDU1243 {
	int cs[][], score[];
	String cri, pol, nl;

	int max(int a, int b) {
		return a > b ? a : b;
	}

	void dp(int a, int b) {
		for (int i = 1; i <= a; i++) {
			for (int j = 1; j <= b; j++) {
				if (pol.charAt(i - 1) == cri.charAt(j - 1)) {
					cs[i][j] = cs[i - 1][j - 1]
							+ score[nl.indexOf(cri.charAt(j - 1))];
				} else {
					cs[i][j] = max(cs[i - 1][j], cs[i][j - 1]);
				}
			}
		}
	}

	void run() {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			int n = scan.nextInt();
			score = new int[n];
			nl = scan.next();
			for (int i = 0; i < n; i++) {
				score[i] = scan.nextInt();
			}
			pol = scan.next();
			cri = scan.next();
			cs = new int[pol.length() + 1][cri.length() + 1];
			dp(pol.length(), cri.length());
			System.out.println(cs[pol.length()][cri.length()]);
		}
	}

	public static void main(String[] args) {
		new HDU1243().run();
	}
}
