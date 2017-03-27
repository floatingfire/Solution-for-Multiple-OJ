import java.util.*;

//作为状压dp的练手
public class SGU_222_Little_Rooks_else1 {
	int rem[][], sum[];// rem[]用作dp的记忆数组，sum[]统计每种状态所含的棋子数

	void dp(int n, int k) {
		// 初始化sum[]数组
		for (int i = 0; i < sum.length; i++) {
			for (int j = i; j > 0; j -= -j & j) {
				sum[i]++;
			}
		}
		// dp初始化
		rem[1][0] = 1;
		for (int i = 1; i < 1 << n; i <<= 1) {
			rem[1][i] = 1;
		}
		for (int i = 2; i <= n; i++) {
			rem[i & 1][0] = rem[(i - 1) & 1][0];
			for (int j = 1; j < 1 << n; j++) {
				if (sum[j] <= k) {
					rem[i & 1][j] = rem[(i - 1) & 1][j];
					for (int l = j; l > 0; l -= -l & l) {
						rem[i & 1][j] += rem[(i - 1) & 1][j ^ (-l & l)];
					}
				}
			}
		}
	}

	void run() {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		if (k <= n) {
			rem = new int[2][1 << n];
			sum = new int[1 << n];
			dp(n, k);
			int ans = 0;
			for (int i = 0; i < rem[n & 1].length; i++) {
				ans += sum[i] == k ? rem[n & 1][i] : 0;
			}
			System.out.println(ans);
		} else {
			System.out.println("0");
		}
	}

	public static void main(String[] args) {
		new SGU_222_Little_Rooks_else1().run();
	}
}
