import java.util.Scanner;
//������һ����һ������Ⱑ����
public class POJ_1001_Exponentiation {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			System.out.println(in.nextBigDecimal().pow(in.nextInt())
					.stripTrailingZeros().toPlainString().replaceAll("^0", ""));
		}
	}
}
