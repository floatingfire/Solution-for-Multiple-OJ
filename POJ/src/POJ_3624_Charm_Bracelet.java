import java.util.*;

public class POJ_3624_Charm_Bracelet {
	int rem[], d[], w[], n, m;

	int max(int a, int b) {
		return a > b ? a : b;
	}

	void dp() {
		for (int i = 1; i <= n; i++) {
			for (int j = m; j >= 0; j--) {
				if (w[i] <= j) {
					rem[j] = max(rem[j - w[i]] + d[i], rem[j]);
				}
			}
		}
	}

	void run() {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		w = new int[n + 1];
		d = new int[n + 1];
		rem = new int[m + 1];
		for (int i = 1; i <= n; i++) {
			w[i] = scan.nextInt();
			d[i] = scan.nextInt();
		}
		dp();
		System.out.println(rem[m]);
	}

	public static void main(String[] args) {
		new POJ_3624_Charm_Bracelet().run();
	}
}
