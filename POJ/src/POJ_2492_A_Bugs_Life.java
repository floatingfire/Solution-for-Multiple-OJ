import java.io.*;

public class POJ_2492_A_Bugs_Life {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int f[], d[];

	static void init(int n) {
		f = new int[n + 1];
		d = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			f[i] = i;
		}
	}

	static int find(int a) {
		if (f[a] != a) {
			int k = f[a];
			f[a] = find(f[a]);
			d[a] = (d[a] + d[k]) & 1;
		}
		return f[a];
	}

	static boolean union(int a, int b) {
		int ra = find(a), rb = find(b);
		if (ra != rb) {
			f[ra] = rb;
			d[ra] = (d[b] + 3 - d[a]) & 1;
			return true;
		}
		return d[a] != d[b];
	}

	public static void main(String[] args) throws Exception {
		int sce = nextInt();
		for (int c = 1; c <= sce; c++) {
			boolean flag = true;
			int n = nextInt(), m = nextInt();
			init(n);
			for (int i = 0; i < m; i++) {
				flag &= union(nextInt(), nextInt());
			}
			if (c != 1) {
				System.out.println();
			}
			System.out.println("Scenario #" + c + ":");
			System.out.println((flag ? "No s" : "S") + "uspicious bugs found!");

		}
	}
}