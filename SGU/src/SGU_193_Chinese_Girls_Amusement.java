import java.util.*;
import java.math.*;
//��ѧ�����
public class SGU_193_Chinese_Girls_Amusement {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BigInteger n = in.nextBigInteger();
		System.out.println(n.getLowestSetBit() == 0 ? n.shiftRight(1) : (n
				.shiftRight(1).getLowestSetBit() == 0 ? n.shiftRight(1)
				.subtract(BigInteger.valueOf(2)) : n.shiftRight(1).subtract(
				BigInteger.ONE)));
	}
}
