import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class HDU_4513_吉哥系列故事_完美队形II {
	static StreamTokenizer in;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		for (int tst = nextInt(); tst > 0; tst--) {
			int n = nextInt();
			double s[] = new double[(n << 1) + 1];
			int p[] = new int[(n << 1) + 1];
			s[0] = -1;
			for (int i = 1; i <= n; i++) {
				s[i << 1] = nextInt();
				s[(i << 1) - 1] = (s[i - 1 << 1] + s[i << 1]) / 2;
			}
			int id = 1, lf = 1, ans = 0;
			for (int i = 1; i < s.length; i++) {
				if (i < id + lf) {
					p[i] = Math.min(p[(id << 1) - i], id + lf - i);
				}
				for (int j = i + p[i]; j < p.length && s[(i << 1) - j] == s[j]
						&& (j == i ? true : s[j - 1] >= s[j]); j++) {
					p[i]++;
				}
				if (id + lf < i + p[i]) {
					id = i;
					lf = p[i];
				}
				ans = Math.max(ans, p[i]);
			}
			System.out.println(ans);
		}
	}
}
