import java.util.*;
//¶ÁÌâÒªÇå³þ
public class POJ_Labeling_Balls {
	class Edge {
		int v, next;

		Edge(int v, int next) {
			this.v = v;
			this.next = next;
		}
	}

	int head[], outde[], val[], next[], n, m, edgenum, listsize, label;
	Edge e[];

	void addEdge(int ve, int vs) {
		e[++edgenum] = new Edge(ve, head[vs]);
		head[vs] = edgenum;
		outde[ve]++;
	}

	void addList(int value) {
		val[++listsize] = value;
		next[listsize] = next[0];
		next[0] = listsize;
	}

	void topsort() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 1; i <= n; i++) {
			if (outde[i] == 0) {
				pq.add(-i);
			}
		}
		while (!pq.isEmpty()) {
			addList(-pq.peek());
			label++;
			for (int i = head[-pq.poll()]; i != 0; i = e[i].next) {
				if (--outde[e[i].v] == 0) {
					pq.add(-e[i].v);
				}
			}
		}
	}

	void run() {
		Scanner scan = new Scanner(System.in);
		for (int c = scan.nextInt(); c > 0; c--) {
			n = scan.nextInt();
			m = scan.nextInt();
			edgenum = 0;
			listsize = 0;
			label = 0;
			head = new int[n + 1];
			outde = new int[n + 1];
			e = new Edge[m + 1];
			val = new int[n + 1];
			next = new int[n + 1];
			for (int i = 1; i <= m; i++) {
				addEdge(scan.nextInt(), scan.nextInt());
			}
			topsort();
			if (label != n) {
				System.out.println("-1");
			} else {
				TreeMap<Integer, Integer> wei = new TreeMap<Integer, Integer>();
				int w = 0;
				for (int i = next[0]; i != 0; i = next[i]) {
					wei.put(val[i], ++w);
				}
				Iterator<Integer> iter = wei.keySet().iterator();
				while (iter.hasNext()) {
					System.out.print(wei.get(iter.next()) + " ");
				}
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		new POJ_Labeling_Balls().run();
	}
}
