
import java.util.*;

public class URAL_1654_Cipher_Message {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tail = 0;
		char[] msg = scan.next().toCharArray();
		for (int i = 1; i < msg.length; i++) {
			if (msg[tail] == msg[i]) {
				if (tail > 0) {
					tail--;
				} else if (++i < msg.length) {
					msg[tail] = msg[i];
				} else {
					tail = -1;
				}
			} else {
				msg[++tail] = msg[i];
			}
		}
		if (tail == -1) {
			System.out.println();
		}
		for (int i = 0; i <= tail; i++) {
			System.out.print(msg[i]);
		}
		System.out.println();
	}
}
