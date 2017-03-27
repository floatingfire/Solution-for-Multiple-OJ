import java.util.*;

public class POJ_3629_Card_Stacking {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		int p = scan.nextInt();
		TreeSet<Integer> ts = new TreeSet<Integer>();
		int[] cards = new int[1111111];
		int head = 0, tail = 0, play = 0;
		for (int i = 1; i <= k; i++) {
			cards[tail++] = i;
		}
		while (head <= tail) {
			if (play == n - 1)
				ts.add(cards[head]);
			head++;
			for (int i = 0; i < p; i++) {
				cards[tail++] = cards[head++];
			}
			play = (play + 1) % n;
		}
		Iterator<Integer> iter = ts.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
