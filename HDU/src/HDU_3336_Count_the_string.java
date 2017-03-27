import java.io.*;
import java.util.*;

public class HDU_3336_Count_the_string {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				System.out)));
		for (int cs = Integer.parseInt(in.readLine()); cs > 0; cs--) {
			int n = Integer.parseInt(in.readLine());
			String str = in.readLine();
			int next[] = new int[n + 1];
			int k = 0;
			for (int i = 1; i < n; i++) {
				while (k > 0 && str.charAt(k) != str.charAt(i)) {
					k = next[k];
				}
				if (str.charAt(k) == str.charAt(i)) {
					k++;
				}
				next[i + 1] = k;
			}
			int dp[] = new int[n + 1];
			Arrays.fill(dp, 1);
			for (int i = n; i >= 1; i--) {
				dp[next[i]] = (dp[i] + dp[next[i]]) % 10007;
			}
			int cnt = 0;
			for (int i = 1; i <= n; i++) {
				cnt = (cnt + dp[i]) % 10007;
			}
			out.println(cnt);
		}
		out.close();
	}
}