import java.util.Scanner;

public class POJ_1005_I_Think_I_Need_a_Houseboat {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		for (int i = 0; i < n; i++) {
			double x = scan.nextDouble();
			double y = scan.nextDouble();
			double s = Math.PI * (x * x + y * y) / 2;
			if (s % 50 == 0) {
				System.out.println("Property " + (i + 1)
						+ ": This property will begin eroding in year "
						+ (int) (s / 50) + ".");
			} else {
				System.out.println("Property " + (i + 1)
						+ ": This property will begin eroding in year "
						+ (int) (s / 50 + 1) + ".");
			}
		}
		System.out.println("END OF OUTPUT.");
	}
}
