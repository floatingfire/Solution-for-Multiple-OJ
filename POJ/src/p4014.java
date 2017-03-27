import java.util.*;

public class p4014 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			StringBuilder sb = new StringBuilder();
			String str = scan.next();
			int n = scan.nextInt();
			for (int i = 0; i < str.length(); i++) {
				sb.append((char) ((str.charAt(scan.nextInt() - 1) - 65 + n) % 26 + 65));
			}
			System.out.println(sb);
		}
	}
}
