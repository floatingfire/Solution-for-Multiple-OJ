import java.math.BigInteger;
import java.util.*;

public class HDU4002 {//欧拉函数与大素数

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		BigInteger[] pn = primeN();
		int t = scan.nextInt();
		for (int z = 0; z < t; z++) {
			BigInteger n = scan.nextBigInteger();
			int i = 0;
			BigInteger x = BigInteger.valueOf(1);
			BigInteger y = null;
			while (x.compareTo(n) <= 0) {
				y = x;
				x = x.multiply(pn[i]);
				i++;
			}
			System.out.println(y);
		}
	}

	public static BigInteger[] primeN() {
		byte[] b = new byte[2001];
		BigInteger[] pn = new BigInteger[1000];
		for (int i = 2; i < 2001; i++) {
			if (b[i] != 1)
				for (int j = 2; j * i < 2001; j++) {
					b[i * j] = 1;
				}
		}
		int i = 0;
		for (int j = 2; j < 2001; j++) {
			if (b[j] == 0) {
				pn[i] = BigInteger.valueOf(j);
				i++;
			}
		}
		return pn;
	}
}
