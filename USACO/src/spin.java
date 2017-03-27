import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROB: spin
 */
public class spin {
	static StreamTokenizer in;
	static PrintWriter out;
	static boolean flag[];

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("spin.in"));
		out = new PrintWriter(new FileWriter("spin.out"));
		int angle[][] = new int[5][360];
		int speed[] = new int[5];
		int i, j, k;
		for (i = 0; i < 5; i++) {
			for (j = 0; j < 360; j++) {
				angle[i][j] = 0;
			}
		}
		for (i = 0; i < 5; i++) {
			speed[i] = nextInt();
			int n = nextInt();
			for (j = 0; j < n; j++) {
				int a = nextInt(), b = nextInt();
				for (k = 0; k <= b; k++) {
					angle[i][(a + k) % 360] = 1;
				}
			}
		}
		int t = 0;
		while (t < 360) {
			boolean isOK = false;
			for (i = 0; i < 360; i++) {
				isOK = true;
				for (j = 0; j < 5; j++) {
					if (angle[j][(i - (t * speed[j]) % 360 + 360) % 360] == 0) {
						isOK = false;
					}
				}
				if (isOK == true) {
					break;
				}
			}
			if (isOK == true) {
				break;
			}
			t++;
		}
		out.println(t == 360 ? "none" : t);
		out.close();
	}
}
