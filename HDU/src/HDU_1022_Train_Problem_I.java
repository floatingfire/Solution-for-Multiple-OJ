
import java.util.*;

public class HDU_1022_Train_Problem_I {
	private static Scanner scan;

	public static void main(String[] args) {
		scan=new Scanner(System.in);
		while (scan.hasNext()) {
			int n = scan.nextInt();
			String o1 = scan.next(), o2 = scan.next();
			Stack<String> station = new Stack<String>();
			String[] rem = new String[2 * n];
			int f1 = 0, f2 = 0;
			String per = null;
			for (int i = 0; i < 2 * n; i++) {
				per = o2.substring(f2, f2 + 1);
				if (!station.isEmpty() && station.peek().equals(per)) {
					station.pop();
					rem[i] = "out";
					f2++;
				} else {
					if (f1 < n) {
						station.push(o1.substring(f1, f1 + 1));
						rem[i] = "in";
						f1++;
					} else {
						break;
					}
				}
			}
			if (f2 == n) {
				System.out.println("Yes.");
				for (String str : rem) {
					System.out.println(str);
				}
				System.out.println("FINISH");
			} else {
				System.out.println("No.");
				System.out.println("FINISH");
			}
		}
	}
}
