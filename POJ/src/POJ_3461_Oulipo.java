import java.util.Scanner;

public class POJ_3461_Oulipo {
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

	static int kmp(String p, String t) {
		int next[] = getnext(p);
		int k = 0, cnt = 0;
		for (int i = 0; i < t.length(); i++) {
			while (k > 0 && p.charAt(k) != t.charAt(i)) {
				k = next[k];
			}
			if (p.charAt(k) == t.charAt(i)) {
				k++;
			}
			if (k == p.length()) {
				k = next[k];
				cnt++;
			}
		}
		return cnt;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for (int cs = in.nextInt(); cs > 0; cs--) {
			System.out.println(kmp(in.next(), in.next()));
		}
	}
}