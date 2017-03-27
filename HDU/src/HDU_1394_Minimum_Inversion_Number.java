import java.io.*;

public class HDU_1394_Minimum_Inversion_Number {
	public static void main(String[] args) throws Exception {
		FastInput in = new FastInput();
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		while (in.in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.in.nval;
			SegmentTree smt = new SegmentTree(n);
			int count = 0;
			int val[] = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				int tem = val[i] = in.nextInt();
				count += smt.query(1, tem, n);
				smt.update(1, tem, tem);
			}
			int ans = Integer.MAX_VALUE;
			for (int i = 1; i <= n; i++) {
				count += n - (val[i] << 1) - 1;
				ans = Math.min(ans, count);
			}
			out.println(ans);
		}
		out.close();
	}

	static class SegmentTree {// 以维护区间最大值为例
		private class Node {
			int l, r, num, lazy;

			Node(int l, int r) {
				this.l = l;
				this.r = r;
			}
		}

		private Node tree[];

		public SegmentTree(int n) {
			tree = new Node[n * 4];
			build(1, 0, n - 1);
		}

		private void build(int id, int left, int right) {
			tree[id] = new Node(left, right);
			if (left == right) {
				tree[id].num = 0;
			} else {
				int mid = (left + right) >> 1;
				build(id << 1, left, mid);
				build(id << 1 | 1, mid + 1, right);
				pushup(id);
			}
		}

		public int query(int id, int left, int right) {
			int ans = 0;
			if (left <= tree[id].l && tree[id].r <= right) {
				ans = tree[id].num;
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

		public void update(int id, int left, int right) {
			// 更新操作为对某段区间上的值都加key
			if (left <= tree[id].l && tree[id].r <= right) {
				tree[id].num++;
				tree[id].lazy++;
			} else {
				pushdown(id);
				int mid = (tree[id].l + tree[id].r) >> 1;
				if (left <= mid) {
					update(id << 1, left, right);
				}
				if (mid < right) {
					update(id << 1 | 1, left, right);
				}
				pushup(id);
			}
		}

		private void pushup(int id) {
			tree[id].num = tree[id << 1].num + tree[id << 1 | 1].num;
		}

		private void pushdown(int id) {
			if (tree[id].lazy != 0) {
				tree[id << 1].num += tree[id].lazy;
				tree[id << 1].lazy += tree[id].lazy;
				tree[id << 1 | 1].num += tree[id].lazy;
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
