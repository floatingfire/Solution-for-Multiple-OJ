import java.io.*;

public class POJ_1003_Hangover {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));

	static double nextDouble() throws Exception {
		in.nextToken();
		return in.nval;
	}

	static final int MAX = 300;
	static double[] rem = new double[MAX];

	static void init() {
		for (int i = 1; i < MAX; i++) {
			rem[i] = rem[i - 1] + 1.0 / (i + 1);
		}
	}

	static int binarysearch(double val) {
		int l = 0, r = MAX;
		int mid = (l + r) >> 1;
		while (l - r < -1) {
			if (rem[mid] < val) {
				l = mid;
			} else {
				r = mid;
			}
			mid = (l + r) >> 1;
		}
		return r;
	}

	public static void main(String[] args) throws Exception {
		init();
		while (true) {
			double c = nextDouble();
			if (c == 0.00) {
				break;
			}
			System.out.println(binarysearch(c) + " card(s)");
		}
	}
}