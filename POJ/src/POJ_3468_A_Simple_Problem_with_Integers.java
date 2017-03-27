import java.io.*;

public class POJ_3468_A_Simple_Problem_with_Integers {
	/*
	 * 线段树
	 * 维护区间和
	 * 更新操作为成段加减
	 * 查找操作为成段查询
	 */
	public static void main(String[] args) throws Exception {
		FastInput in = new FastInput();
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		int n = in.nextInt();
		int m = in.nextInt();
		int val[] = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			val[i] = in.nextInt();
		}
		SegmentTree smt = new SegmentTree(val, n);
		for (int i = 0; i < m; i++) {
			if (in.next().charAt(0) == 'Q') {
				out.println(smt.query(1, in.nextInt(), in.nextInt()));
			} else {
				smt.update(1, in.nextInt(), in.nextInt(), in.nextInt());
			}
		}
		out.close();
	}

	static class SegmentTree {
		private class Node {
			int l, r;
			long sum, lazy;

			Node(int l, int r) {
				this.l = l;
				this.r = r;
			}
		}

		private Node tree[];
		private int value[];

		public SegmentTree(int val[], int n) {
			tree = new Node[n * 3];
			value = val;
			build(1, 1, n);
		}

		private void build(int id, int left, int right) {
			tree[id] = new Node(left, right);
			if (left == right) {
				tree[id].sum = value[left];
			} else {
				int mid = (left + right) >> 1;
				build(id << 1, left, mid);
				build(id << 1 | 1, mid + 1, right);
				pushup(id);
			}
		}

		public long query(int id, int left, int right) {
			long ans = 0;
			if (left <= tree[id].l && tree[id].r <= right) {
				ans = tree[id].sum;
			} else {
				pushdown(id);
				int mid = (tree[id].l + tree[id].r) >> 1;
				if (left <= mid) {
					ans += query(id << 1, left, right);
				}
				if (mid < right) {
					ans += query(id << 1 | 1, left, right);
				}
			}
			return ans;
		}

		public void update(int id, int left, int right, int key) {
			// 更新操作为对某段区间上的值都加key
			if (left <= tree[id].l && tree[id].r <= right) {
				tree[id].sum += (tree[id].r - tree[id].l + 1) * key;
				tree[id].lazy += key;
			} else {
				pushdown(id);
				int mid = (tree[id].l + tree[id].r) >> 1;
				if (left <= mid) {
					update(id << 1, left, right, key);
				}
				if (mid < right) {
					update(id << 1 | 1, left, right, key);
				}
				pushup(id);
			}
		}

		private void pushup(int id) {
			tree[id].sum = tree[id << 1].sum + tree[id << 1 | 1].sum;
		}

		private void pushdown(int id) {
			if (tree[id].lazy != 0) {
				tree[id << 1].sum += (tree[id << 1].r - tree[id << 1].l + 1)
						* tree[id].lazy;
				tree[id << 1].lazy += tree[id].lazy;
				tree[id << 1 | 1].sum += (tree[id << 1 | 1].r
						- tree[id << 1 | 1].l + 1)
						* tree[id].lazy;
				tree[id << 1 | 1].lazy += tree[id].lazy;
				tree[id].lazy = 0;
			}
		}
	}

	static class FastInput {
		private StreamTokenizer in;

		public FastInput() {
			in = new StreamTokenizer(new BufferedReader(new InputStreamReader(
					System.in)));
		}

		public int nextInt() throws Exception {
			in.nextToken();
			return (int) in.nval;
		}

		public long nextLong() throws Exception {
			in.nextToken();
			return (long) in.nval;
		}

		public double nextDouble() throws Exception {
			in.nextToken();
			return in.nval;
		}

		public String next() throws Exception {
			in.nextToken();
			return in.sval;
		}
	}

}
