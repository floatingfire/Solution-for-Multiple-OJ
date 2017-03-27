import java.util.Scanner;
//这是神一样的一道类库题啊……
public class POJ_1001_Exponentiation {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			System.out.println(in.nextBigDecimal().pow(in.nextInt())
					.stripTrailingZeros().toPlainString().replaceAll("^0", ""));
		}
	}
}
