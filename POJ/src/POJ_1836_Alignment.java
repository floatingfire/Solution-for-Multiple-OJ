import java.io.*;
import java.util.Arrays;

public class POJ_1836_Alignment {

	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static double nextDouble() throws Exception {
		in.nextToken();
		return in.nval;
	}

	static int dp(double s[]) {
		int[] lis = new int[s.length], r = new int[s.length];
		double q[] = new double[s.length];
		int h = 0, t = 0, max = 0;
		for (int i = 0; i < s.length; i++) {
			int id = Arrays.binarySearch(q, h, t, s[i]);
			q[id = id < 0 ? -1 - id : id] = s[i];
			t = t > (lis[i] = id + 1) ? t : lis[i];
			r[i] = i == 0 ? lis[i] : Math.max(r[i - 1], lis[i]);
		}
		q = new double[s.length];
		lis = new int[s.length];
		t = h = 0;
		for (int i = s.length - 1; i >= 0; i--) {
			int id = Arrays.binarySearch(q, h, t, s[i]);
			q[id = id < 0 ? -1 - id : id] = s[i];
			t = t > (lis[i] = id + 1) ? t : lis[i];
			max = Math.max(max, i > 0 ? r[i - 1] + lis[i] : lis[i]);
		}
		return max;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int n = nextInt();
		double s[] = new double[n];
		for (int i = 0; i < n; i++) {
			s[i] = nextDouble();
		}
		out.println(n - dp(s));
		out.close();
	}
}
