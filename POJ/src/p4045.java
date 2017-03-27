import java.util.*;

public class p4045 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0 || i % 5 == 0 || i % 10 == 3 || (i / 10) % 10 == 3
					|| (i / 10) % 10 == 5) {
			} else {
				sum += i * i;
			}
		}
		System.out.println(sum);
	}
}
