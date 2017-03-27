import java.io.*;

public class POJ_2828_Buy_Tickets {
	StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));

	int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	short nextShort() throws Exception {
		in.nextToken();
		return (short) in.nval;
	}

	class Node {
		int l, r, num, pos;

		Node(int l, int r) {
			this.l = l;
			this.r = r;
			num = r - l + 1;
		}
	}

	Node tree[] = new Node[800000];
	short ans[] = new short[200001], val[] = new short[200000];
	int pos[] = new int[200000];

	void build(int id, int l, int r) {
		tree[id] = new Node(l, r);
		if (l == r) {
			tree[id].pos = l;
		} else {
			int mid = (l + r) >> 1;
			build(id << 1, l, mid);
			build(id << 1 | 1, mid + 1, r);
		}
	}

	void update(int id, int num, short val) {
		tree[id].num--;
		if (tree[id].l == tree[id].r) {
			ans[tree[id].pos] = val;
		} else {
			if (num >= tree[id << 1].num) {
				update(id << 1 | 1, num - tree[id << 1].num, val);
			} else {
				update(id << 1, num, val);
			}
		}
	}

	void run() throws Exception {
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.nval;
			for (int i = 0; i < n; i++) {
				pos[i] = nextInt();
				val[i] = nextShort();
			}
			build(1, 1, n);
			for (int i = n - 1; i >= 0; i--) {
				update(1, pos[i], val[i]);
			}
			out.print(ans[1]);
			for (int i = 2; i <= n; i++) {
				out.print(" " + ans[i]);
			}
			out.println();
		}
		out.close();
	}

	public static void main(String[] args) throws Exception {
		new POJ_2828_Buy_Tickets().run();
	}
}