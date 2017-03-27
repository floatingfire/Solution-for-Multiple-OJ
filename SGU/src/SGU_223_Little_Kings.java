import java.util.*;

public class SGU_223_Little_Kings {
	long rem[][][];
	int sum[], s[], n, k, num;

	void dfs(int now, int state) {
		if (now == n) {
			s[++num] = state;
		} else {
			dfs(now + 1, state << 1);
			if ((state & 1) == 0) {
				dfs(now + 1, (state << 1) ^ 1);
			}
		}
	}

	void dp(int n, int k) {
		for (int i = 0; i < sum.length; i++) {
			for (int j = s[i]; j > 0; j -= -j & j) {
				sum[i]++;
			}
		}
		rem[0][1][0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= num; j++) {
				for (int l = sum[j]; l <= k; l++) {
					for (int p = 1; p <= num; p++) {
						if ((s[p] & s[j]) == 0 && ((s[p] << 1) & s[j]) == 0
								&& ((s[p] >> 1) & s[j]) == 0) {
							rem[i][j][l] += rem[i - 1][p][l - sum[j]];
						}
					}
				}
			}
		}
	}

	void run() {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		k = scan.nextInt();
		rem = new long[n + 1][1 << n][k + 1];
		sum = new int[1 << n];
		s = new int[1 << n];
		num = 0;
		dfs(0, 0);
		dp(n, k);
		long ans = 0;
		for (int i = 1; i <= num; ++i) {
			ans += rem[n][i][k];
		}
		System.out.println(ans);
	}

	public static void main(String[] args) {
		new SGU_223_Little_Kings().run();
	}
}
