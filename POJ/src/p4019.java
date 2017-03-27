import java.util.*;

public class p4019 {
	static int[] leapmon = { 1, 3, 5, 7, 8, 10, 12 };

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			int n = scan.nextInt() + 12;
			int count = 0;
			for (int i = 1; i <= 12; i++) {
				if (n % 7 == 5) {
					if (count == 0)
						System.out.print(i);
					else
						System.out.print(" " + i);
					count++;
				}
				n += i == 2 ? 29 : binarySearch(0, 6, i) ? 31 : 30;
			}
			if (count == 0)
				System.out.println("NULL");
			else
				System.out.println();
		}
	}

	private static boolean binarySearch(int start, int end, int key) {
		int mid = (start + end) / 2;
		if (leapmon[mid] == key)
			return true;
		if (start >= end)
			return false;
		if (leapmon[mid] < key)
			return binarySearch(mid + 1, end, key);
		else
			return binarySearch(start, mid - 1, key);
	}
}
