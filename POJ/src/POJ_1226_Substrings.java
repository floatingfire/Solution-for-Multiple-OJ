import java.util.*;

public class POJ_1226_Substrings {
	static int[] getnext(String p) {
		int next[] = new int[p.length() + 1];
		int k = 0;
		for (int i = 1; i < p.length(); i++) {
			while (k > 0 && p.charAt(k) != p.charAt(i)) {
				k = next[k];
			}
			if (p.charAt(k) == p.charAt(i)) {
				k++;
			}
			next[i + 1] = k;
		}
		return next;
	}

	static boolean kmp(String p, String t) {
		int next[] = getnext(p);
		int k = 0;
		for (int i = 0; i < t.length(); i++) {
			while (k > 0 && p.charAt(k) != t.charAt(i)) {
				k = next[k];
			}
			if (p.charAt(k) == t.charAt(i)) {
				k++;
			}
			if (k == p.length()) {
				return true;
			}
		}
		return false;
	}

	static String inverse(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for (int cs = in.nextInt(); cs > 0; cs--) {
			int n = in.nextInt();
			String str[] = new String[n];
			int ms = 0;
			for (int i = 0; i < n; i++) {
				str[i] = in.next();
				if (str[i].length() < str[ms].length()) {
					ms = i;
				}
			}
			boolean flag = false;
			for (int l = str[ms].length(); l > 0; l--) {
				for (int i = 0; i + l <= str[ms].length(); i++) {
					flag = false;
					for (int j = 0; j < n; j++) {
						if (!kmp(str[ms].substring(i, i + l), str[j])
								&& !kmp(inverse(str[ms].substring(i, i + l)),
										str[j])) {
							flag = true;
							break;
						}
					}
					if (!flag) {
						break;
					}
				}
				if (!flag) {
					System.out.println(l);
					break;
				}
			}
			if (flag) {
				System.out.println("0");
			}
		}
	}
}