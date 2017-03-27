import java.io.*;

public class HDU_4506_小明系列故事_师兄帮帮忙 {
	static StreamTokenizer in;
	static final int MOD = 1000000007;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static long pow(long k, int n) {
		long ans = 1;
		while (n > 0) {
			if ((n & 1) == 1) {
				ans = (ans * k) % MOD;
			}
			k = (k * k) % MOD;
			n >>= 1;
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		for (int tst = nextInt(); tst > 0; tst--) {
			int n = nextInt(), t = nextInt();
			long k = nextInt();
			long cha[] = new long[n];
			for (int i = 0; i < n; i++) {
				cha[i] = nextInt();
			}
			k = pow(k, t);
			t = t % n;
			long ans[] = new long[n];
			for (int i = 0; i < t; i++) {
				ans[i] = (k * cha[n - t + i]) % MOD;
			}
			for (int i = t; i < n; i++) {
				ans[i] = (k * cha[i - t]) % MOD;
			}
			for (int i = 0; i < n; i++) {
				System.out.print(ans[i] + (i == n - 1 ? "" : " "));
			}
			System.out.println();
		}
	}
}