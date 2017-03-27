import java.util.*;

public class POJ_3253_Fence_Repair {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PriorityQueue<Integer> price = new PriorityQueue<Integer>();
		int n = scan.nextInt();
		long total = 0;
		for (int i = 0; i < n; i++) {
			price.offer(scan.nextInt());
		}
		while (price.size() > 1) {
			int temp = price.poll() + price.poll();
			total += temp;
			price.offer(temp);
		}
		System.out.println(total);
	}
}
