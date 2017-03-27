import java.util.Scanner;

public class POJ_1004_Financial_Management {
	private static Scanner scan;

	public static void main(String[] args) {
		scan=new Scanner(System.in);
		double sum = 0;
		for (int i = 0; i < 12; i++) {
			sum += scan.nextDouble();
		}
		sum = (int) (sum / 12 * 100);
		sum /= 100;
		System.out.println("$" + sum);
	}
}