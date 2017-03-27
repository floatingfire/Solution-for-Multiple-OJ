import java.math.*;
import java.util.*;

public class HDU_2065_∫Ï…´≤°∂æŒ Ã‚ {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		while (true) {
			int n = in.nextInt();
			if (n == 0) {
				break;
			}
			for (int i = 1; i <= n; i++) {
				int tmp = BigInteger
						.valueOf(2)
						.modPow(in.nextBigInteger().subtract(BigInteger.ONE),
								BigInteger.valueOf(100)).intValue();
				System.out.println("Case " + i + ": " + tmp * (tmp + 1) % 100);
			}
			System.out.println();
		}
	}
}