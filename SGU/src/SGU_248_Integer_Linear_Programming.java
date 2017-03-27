import java.util.*;

//一只必须要正好填满的背包，只需要维护dp的每一步都是填满的即可
public class SGU_248_Integer_Linear_Programming {
	int rem[], c[], n, v;

	int min(int a, int b) {
		return a < b ? a : b;
	}

	void dp() {
		for (int i = 1; i <= v; i++) {
			rem[i] = -1;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = c[i]; j <= v; j++) {
				if (rem[j - c[i]] == -1) {
					if (j % c[i] == 0)
						rem[j] = 1;
				} else {
					rem[j] = rem[j] == -1 ? rem[j - c[i]] + 1 : min(rem[j],
							rem[j - c[i]] + 1);
				}
			}
		}
	}

	void run() {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		c = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			c[i] = scan.nextInt();
		}
		v = scan.nextInt();
		rem = new int[v + 1];
		dp();
		System.out.println(rem[v]);
	}

	public static void main(String[] args) {
		new SGU_248_Integer_Linear_Programming().run();
	}
}
