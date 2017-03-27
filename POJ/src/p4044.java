import java.util.*;

public class p4044 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		TreeSet<Rat> rts = new TreeSet<Rat>();
		int n = scan.nextInt();
		for (int i = 0; i < n; i++) {
			rts.add(new Rat(scan.nextInt(), scan.next()));
		}
		Iterator<Rat> iter = rts.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}

class Rat implements Comparable<Rat> {
	int w;
	String h;

	Rat(int weight, String hat) {
		w = weight;
		h = hat;
	}

	public int compareTo(Rat r) {
		return w - r.w;
	}

	public String toString() {
		return h;
	}
}
