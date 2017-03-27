import java.util.*;

public class p4013 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			int n = scan.nextInt();
			if (n == 0)
				break;
			int[] num = new int[n];
			for (int i = 0; i < n; i++) {
				num[i] = scan.nextInt();
			}
			Arrays.sort(num);
			System.out.println((num[n / 2] + num[(n - 1) / 2]) / 2);
		}
	}
}
