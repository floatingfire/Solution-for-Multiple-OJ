import java.io.*;
import java.util.*;

public class POJ_2533_Longest_Ordered_Subsequence {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int dp(int s[]) {
		int t = 0, f[] = new int[s.length], q[] = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			int id = Arrays.binarySearch(q, 0, t, s[i]);
			q[id = id < 0 ? -1 - id : id] = s[i];
			t = t < (f[id] = id + 1) ? f[id] : t;
		}
		return t;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int s[] = new int[nextInt()];
		for (int i = 0; i < s.length; i++) {
			s[i] = nextInt();
		}
		out.println(dp(s));
		out.close();
	}
}
