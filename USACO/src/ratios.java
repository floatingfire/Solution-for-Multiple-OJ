import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: ratios
 */
public class ratios {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static long gcd(long a, long b) {
		if (a == 0)
			return b;
		if (b == 0)
			return a;
		if (a < b) {
			long tmp = a;
			a = b;
			b = tmp;
		}
		while (b != 0) {
			long tmp = a % b;
			a = b;
			b = tmp;
		}
		return a;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("ratios.in"));
		out = new PrintWriter(new FileWriter("ratios.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		long ans[][] = new long[3][4];
		for (int i = 0; i < 3; i++) {
			ans[i][3] = nextInt();
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				ans[j][i] = nextInt();
			}
		}
		long rem = ans[1][0];
		for (int i = 0; i < 4; i++) {
			ans[1][i] = ans[1][i] * ans[0][0] - rem * ans[0][i];
		}
		rem = gcd(gcd(ans[1][0], ans[1][1]), gcd(ans[1][2], ans[1][3]));
		if (ans[1][1] < 0) {
			rem *= -1;
		}
		for (int i = 0; i < 4; i++) {
			ans[1][i] /= rem;
		}
		rem = ans[2][0];
		for (int i = 0; i < 4; i++) {
			ans[2][i] = ans[2][i] * ans[0][0] - rem * ans[0][i];
		}
		rem = gcd(gcd(ans[2][0], ans[2][1]), gcd(ans[2][2], ans[2][3]));
		if (ans[2][1] < 0) {
			rem *= -1;
		}
		for (int i = 0; i < 4; i++) {
			ans[2][i] /= rem;
		}
		rem = ans[2][1];
		for (int i = 0; i < 4; i++) {
			ans[2][i] = ans[2][i] * ans[1][1] - rem * ans[1][i];
		}
		rem = gcd(gcd(ans[2][0], ans[2][1]), gcd(ans[2][2], ans[2][3]));
		if (ans[2][2] < 0) {
			rem *= -1;
		}
		for (int i = 2; i < 4; i++) {
			ans[2][i] /= rem;
		}
		rem = ans[1][2];
		for (int i = 0; i < 4; i++) {
			ans[1][i] = ans[1][i] * ans[2][2] - rem * ans[2][i];
		}
		rem = ans[0][2];
		for (int i = 0; i < 4; i++) {
			ans[0][i] = ans[0][i] * ans[2][2] - rem * ans[2][i];
		}
		rem = ans[0][1];
		for (int i = 0; i < 4; i++) {
			ans[0][i] = ans[0][i] * ans[1][1] - rem * ans[1][i];
		}
		for (int i = 0; i < 3; i++) {
			long tmp = gcd(ans[i][i], ans[i][3]);
			ans[i][3] /= tmp;
			ans[i][i] /= tmp;
			if (ans[i][i] <= 0) {
				out.println("NONE");
				out.close();
				System.exit(0);
			}
		}
		long lcm = Math.max(Math.max(ans[0][0], ans[1][1]), ans[2][2]);
		for (int i = 0; i < 3; i++) {
			out.print(ans[i][3] * lcm / ans[i][i] + " ");
		}
		out.println(lcm);
		out.close();
	}
}
