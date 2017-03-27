import java.io.*;
import java.util.*;
/*
 * 注意离散化时要留出空白部分，poj数据太弱，有缺陷的离散化也能过
 */
public class POJ_2528_Mayors_posters {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static class Poster {
		int l, r;

		Poster(int l, int r) {
			this.l = l;
			this.r = r;
		}
	}

	static class Node {
		int l, r;
		boolean can;

		Node(int l, int r) {
			this.l = l;
			this.r = r;
			can = true;
		}
	}

	static Poster pst[] = new Poster[10001];
	static Node tree[] = new Node[160000];

	static void build(int id, int l, int r) {
		tree[id] = new Node(l, r);
		if (l != r) {
			int mid = (l + r) >> 1;
			build(id << 1, l, mid);
			build(id << 1 | 1, mid + 1, r);
		}
	}

	static boolean update(int id, int l, int r) {
		boolean flag = false;
		if (l <= tree[id].l && tree[id].r <= r) {
			flag |= tree[id].can;
			tree[id].can = false;
		} else {
			pushdown(id);
			int mid = (tree[id].l + tree[id].r) >> 1;
			if (l <= mid) {
				flag |= update(id << 1, l, r);
			}
			if (mid < r) {
				flag |= update(id << 1 | 1, l, r);
			}
			pushup(id);
		}
		return flag;
	}

	static void pushup(int id) {
		tree[id].can = tree[id << 1].can | tree[id << 1 | 1].can;
	}

	static void pushdown(int id) {
		tree[id << 1 | 1].can &= tree[id].can;
		tree[id << 1].can &= tree[id].can;
	}

	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		TreeMap<Integer, Integer> disc = new TreeMap<Integer, Integer>();
		int n;
		for (int c = nextInt(); c > 0; c--) {
			disc.clear();
			n = nextInt();
			for (int i = 1; i <= n; i++) {
				pst[i] = new Poster(nextInt(), nextInt());
				disc.put(pst[i].l, 0);
				disc.put(pst[i].r, 0);
			}
			int cnt = 1, pre = 0;
			for (Iterator<Integer> i = disc.keySet().iterator(); i.hasNext();) {
				int cur = i.next();
				if (cur - pre != 1) {
					disc.put(cur, ++cnt);
				} else {
					disc.put(cur, cnt);
				}
				cnt++;
				pre = cur;
			}
			build(1, 1, disc.lastEntry().getValue());
			int ans = 0;
			for (int i = n; i > 0; i--) {
				if (update(1, disc.get(pst[i].l), disc.get(pst[i].r))) {
					ans++;
				}
			}
			out.println(ans);
		}
		out.close();
	}
}