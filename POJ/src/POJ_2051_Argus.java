import java.util.*;

public class POJ_2051_Argus {
	static class Argus implements Comparable<Argus> {
		int q, p, t;

		Argus(int q_num, int period) {
			q = q_num;
			p = period;
			t = p;
		}

		public int compareTo(Argus reg) {
			return t == reg.t ? q - reg.q : t - reg.t;
		}

		public void excute() {
			t += p;
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PriorityQueue<Argus> aque = new PriorityQueue<Argus>();
		int k = 0;
		while (true) {
			String temp = scan.next();
			if (temp.equals("Register")) {
				aque.offer(new Argus(scan.nextInt(), scan.nextInt()));
			} else if (temp.equals("#")) {
				continue;
			} else {
				k = Integer.parseInt(temp);
				break;
			}
		}
		for (int i = 0; i < k; i++) {
			Argus reg = aque.poll();
			System.out.println(reg.q);
			reg.excute();
			aque.offer(reg);
		}
	}
}
