import java.io.*;
import java.util.*;

public class POJ_2138_Travel_Games {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws Exception {
		in.nextToken();
		return in.sval;
	}

	static String dp(int n, String wor, String dic[]) {
		String ans = "";
		boolean rem[] = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (dic[i].equals(wor)) {
				ans = dic[i];
				rem[i] = true;
				continue;
			}
			for (int j = i - 1; j >= 0
					&& dic[j].length() + 1 >= dic[i].length(); j--) {
				if (rem[j] && match(dic[j], wor) && match(dic[i], dic[j])) {
					ans = dic[i];
					rem[i] = true;
					break;
				}
			}
		}
		return ans;
	}

	static boolean match(String a, String b) {
		int k = 0;
		boolean flag = false;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == b.charAt(k)) {
				k++;
			}
			if (k >= b.length()) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int n = nextInt();
		String wor = next();
		String dic[] = new String[n];
		for (int i = 0; i < n; i++) {
			dic[i] = next();
		}
		Arrays.sort(dic, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});
		out.println(dp(n, wor, dic));
		out.close();
	}
}
