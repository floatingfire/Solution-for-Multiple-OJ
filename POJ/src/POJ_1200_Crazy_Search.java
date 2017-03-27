import java.io.*;
import java.util.*;

public class POJ_1200_Crazy_Search {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws Exception {
		in.nextToken();
		return in.sval;
	}

	static long pow[];

	static void init(int n, int nc) {
		pow = new long[n];
		pow[0] = 1;
		for (int i = 1; i < pow.length; i++) {
			pow[i] = pow[i - 1] * nc;
		}
	}

	public static void main(String[] args) throws Exception {
		int n = nextInt(), nc = nextInt();
		init(n, nc = 1000000007);
		String s = next();
		TreeSet<Long> cnt = new TreeSet<Long>();
		long tmp = 0;
		for (int i = 0; i < n; i++) {
			tmp = tmp * nc + s.charAt(i);
		}
		cnt.add(tmp);
		for (int i = n; i < s.length(); i++) {
			tmp = (tmp - pow[n - 1] * s.charAt(i - n)) * nc + s.charAt(i);
			cnt.add(tmp);
		}
		System.out.println(cnt.size());
	}
}