import java.util.*;

public class POJ_1995_Raising_Modulo_Numbers {//本题是一道快速幂题
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int z = scan.nextInt();
		for (int k = 0; k < z; k++) {
			int m = scan.nextInt();
			long sum = 0;
			int h = scan.nextInt();
			for (int i = 0; i < h; i++) {
				sum = (sum + quickpow(scan.nextLong(), scan.nextLong(), m)) % m;
			}
			System.out.println(sum);
		}
	}

	private static long quickpow(long a, long b, int m) {
		long n = 1;
		while (b > 0) {
			if (b % 2 == 1)
				n = (n*a)%m;
			b /= 2;
			a = (a*a)%m;
		}
		return n;
	}
}
