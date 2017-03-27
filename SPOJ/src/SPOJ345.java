import java.util.*;

public class SPOJ345 {
	int rem[][], sum[][], data[];

	int min(int a, int b) {
		return a < b ? a : b;
	}

	void dp(int a, int b) {
		for (int l = 2; l <= b; l++) {
			for (int i = a; i + l <= b; i++) {
				int j = i + l;
				for (int k = i; k <= j - 1; k++) {
					if (rem[i][j] == 0) {
						rem[i][j] = rem[i][k] + rem[k + 1][j] + sum[i][k]
								* sum[k + 1][j];
					} else {
						rem[i][j] = min(rem[i][j], rem[i][k] + rem[k + 1][j]
								+ sum[i][k] * sum[k + 1][j]);
					}
					sum[i][j] = (sum[i][k] + sum[k + 1][j]) % 100;
				}
			}
		}
	}

	void run() {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			int n = scan.nextInt();
			rem = new int[n][n];
			sum = new int[n][n];
			data = new int[n];
			for (int i = 0; i < n; i++) {
				sum[i][i] = data[i] = scan.nextInt();
			}
			for (int i = 0; i < n - 1; i++) {
				rem[i][i + 1] = data[i] * data[i + 1];
				sum[i][i + 1] = (data[i] + data[i + 1]) % 100;
			}
			dp(0, n - 1);
			System.out.println(rem[0][n - 1]);
		}
	}

	public static void main(String[] args) {
		new SPOJ345().run();
	}
}
