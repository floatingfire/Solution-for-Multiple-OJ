import java.io.*;
import java.util.*;

public class HDU_4366_Successor {
	static class Stuff {
		int l, r, lo, ab, id;

		Stuff(int id, int lo, int ab) {
			this.id = id;
			this.lo = lo;
			this.ab = ab;
		}
	}

	static class Node {
		int l, r, max;

		Node(int l, int r) {
			this.l = l;
			this.r = r;
			max = -1;
		}

		int mid() {
			return (l + r) >> 1;
		}
	}

	static class Edge {
		int v;
		Edge next;

		Edge(int v, Edge next) {
			this.v = v;
			this.next = next;
		}
	}

	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static Node tree[];
	static Stuff stu[];
	static Edge head[];
	static int cnt;

	static void adde(int id, int v) {
		head[id] = new Edge(v, head[id]);
	}

	static void build(int id, int l, int r) {
		tree[id] = new Node(l, r);
		if (l != r) {
			int mid = tree[id].mid();
			build(id << 1, l, mid);
			build(id << 1 | 1, mid + 1, r);
		}
	}

	static void pushup(int id) {
		tree[id].max = Math.max(tree[id << 1].max, tree[id << 1 | 1].max);
	}

	static int query(int id, int l, int r) {
		if (l <= tree[id].l && tree[id].r <= r) {
			return tree[id].max;
		} else {
			int ans = -1;
			int mid = tree[id].mid();
			if (l <= mid) {
				ans = Math.max(ans, query(id << 1, l, r));
			}
			if (mid < r) {
				ans = Math.max(ans, query(id << 1 | 1, l, r));
			}
			return ans;
		}
	}

	static void update(int id, int l, int r, int key) {
		if (l <= tree[id].l && tree[id].r <= r) {
			tree[id].max = key;
		} else {
			int mid = tree[id].mid();
			if (l <= mid) {
				update(id << 1, l, r, key);
			}
			if (mid < r) {
				update(id << 1 | 1, l, r, key);
			}
			pushup(id);
		}
	}

	static void dfs(int id) {
		stu[id].l = ++cnt;
		for (Edge cur = head[id]; cur != null; cur = cur.next) {
			dfs(cur.v);
		}
		stu[id].r = cnt;
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("test"));
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int tst = nextInt(); tst > 0; tst--) {
			int n = nextInt(), m = nextInt();
			tree = new Node[n << 2];
			stu = new Stuff[n];
			head = new Edge[n];
			stu[0] = new Stuff(0, Integer.MAX_VALUE, Integer.MAX_VALUE);
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			map.put(-1, -1);
			map.put(0, 0);
			for (int i = 1; i < n; i++) {
				adde(nextInt(), i);
				stu[i] = new Stuff(i, nextInt(), nextInt());
				map.put(stu[i].lo, i);
			}
			cnt = -1;
			dfs(0);
			build(1, 0, cnt);
			int ans[] = new int[n];
			Arrays.sort(stu, new Comparator<Stuff>() {
				public int compare(Stuff s1, Stuff s2) {
					return s2.ab - s1.ab;
				}
			});
			for (int i = 0, j = 0; i < n; i++) {
				ans[stu[i].id] = query(1, stu[i].l, stu[i].r);
				if (i + 1 >= n || stu[i].ab == stu[i + 1].ab) {
					continue;
				}
				for (; j <= i; j++) {
					update(1, stu[j].l, stu[j].l, stu[j].lo);
				}
			}
			for (int i = 0; i < m; i++) {
				out.println(map.get(ans[nextInt()]));
			}
		}
		out.close();
	}
}