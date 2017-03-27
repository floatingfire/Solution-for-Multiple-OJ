import java.util.*;
import java.io.*;
//拓扑序唯一 的条件是无环且有哈密顿通路
public class ZOJ_1060_Sorting_It_All_Out {
	class Edge {
		int v, next;

		Edge(int v, int next) {
			this.v = v;
			this.next = next;
		}
	}

	StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	final int A = 65;
	int head[], inde[], cnt, cnte, n, m;
	Edge e[];
	boolean g[][];
	LinkedList<Integer> ans = new LinkedList<Integer>();

	int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	int next() throws Exception {
		in.nextToken();
		return in.sval.charAt(0) - A;
	}

	void addEdge(int sv, int ev) {
		g[sv][ev] = true;
		e[cnte] = new Edge(ev, head[sv]);
		head[sv] = cnte++;
		inde[ev]++;
	}

	void topsort(int inde[]) {
		Queue<Integer> que = new LinkedList<Integer>();
		ans.clear();
		cnt = 0;
		for (int i = 0; i < n; i++) {
			if (inde[i] == 0) {
				que.add(i);
			}
		}
		while (!que.isEmpty()) {
			ans.add(que.peek());
			cnt++;
			for (int i = head[que.poll()]; i != -1; i = e[i].next) {
				if (--inde[e[i].v] == 0) {
					que.add(e[i].v);
				}
			}
		}
	}

	boolean repeat() {
		Iterator<Integer> iter = ans.iterator();
		int pre, now = iter.next();
		while (iter.hasNext()) {
			pre = now;
			now = iter.next();
			if (!g[pre][now]) {
				return true;
			}
		}
		return false;
	}

	String tostring() {
		StringBuilder sb = new StringBuilder();
		for (int i : ans) {
			sb.append((char) (i + A));
		}
		return sb.toString();
	}

	void run() throws Exception {
		while (true) {
			n = nextInt();
			m = nextInt();
			cnte = 0;
			if (n == 0 && m == 0) {
				break;
			}
			head = new int[n];
			Arrays.fill(head, -1);
			inde = new int[n];
			e = new Edge[m];
			g = new boolean[n][n];
			boolean find = false;
			for (int i = 0; i < m; i++) {
				int sv = next();
				in.nextToken();
				int ev = next();
				if (find) {
					continue;
				}
				addEdge(sv, ev);
				topsort(inde.clone());
				if (cnt == n) {
					if (!repeat()) {
						System.out.println("Sorted sequence determined after "
								+ (i + 1) + " relations: " + tostring() + ".");
						find = true;
					}
				} else {
					System.out.println("Inconsistency found after " + (i + 1)
							+ " relations.");
					find = true;
				}
			}
			if (!find) {
				System.out.println("Sorted sequence cannot be determined.");
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new ZOJ_1060_Sorting_It_All_Out().run();
	}
}
