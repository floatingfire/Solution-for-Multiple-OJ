import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Codeforces_7D_Palindrome_Degree {
	static BufferedReader in;

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer(in.readLine());
		char p[] = (sb + "$" + sb.reverse()).toCharArray();
		int c = 0, next[] = new int[p.length + 1];
		for (int i = 1; i < p.length; i++) {
			while (c > 0 && p[i] != p[c]) {
				c = next[c];
			}
			next[i + 1] = p[i] == p[c] ? ++c : c;
		}
		int cnt[] = new int[p.length + 1];
		for (int i = next[p.length]; i != 0; i = next[i]) {
			cnt[i] = 1;
		}
		long ans = 0;
		for (int i = 1; i < cnt.length; i++) {
			if (cnt[i] > 0) {
				cnt[i] += cnt[i >> 1];
			}
			ans += cnt[i];
		}
		System.out.println(ans);
	}
}
