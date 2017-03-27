import java.io.*;

public class HDU_2795_Billboard {
	StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));

	int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	class Node {
		int l, r, val;

		Node(int l, int r) {
			this.l = l;
			this.r = r;
		}
	}

	Node tree[] = new Node[800000];
	int h, w, n;

	void build(int id, int l, int r) {
		tree[id] = new Node(l, r);
		if (l == r) {
			tree[id].val = w;
		} else {
			int mid = (l + r) >> 1;
			build(id << 1, l, mid);
			build(id << 1 | 1, mid + 1, r);
			pushup(id);
		}
	}

	int quary(int id, int len) {
		int ans = -1;
		if (tree[id].l == tree[id].r) {
			if (tree[id].val >= len) {
				tree[id].val -= len;
				ans = tree[id].l;
			}
		} else if (tree[id << 1].val >= len) {
			ans = quary(id << 1, len);
		} else if (tree[id << 1 | 1].val >= len) {
			ans = quary(id << 1 | 1, len);
		}
		pushup(id);
		return ans;
	}

	void pushup(int id) {
		if (tree[id].l != tree[id].r) {
			tree[id].val = Math.max(tree[id << 1].val, tree[id << 1 | 1].val);
		}
	}

	void run() throws Exception {
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			h = (int) in.nval;
			w = nextInt();
			n = nextInt();
			h = Math.min(h, n);
			build(1, 1, h);
			for (int i = 0; i < n; i++) {
				out.println(quary(1, nextInt()));
			}
			out.flush();
		}
		out.close();
	}

	public static void main(String[] args) throws Exception {
		new HDU_2795_Billboard().run();
	}
}