public class DisjointSet {
	//统计集合size时可以用f[root]=-size统计
	private int f[], d[];

	public DisjointSet(int n) {
		f = new int[n + 1];
		d = new int[n + 1];
	}

	public int find(int a) {
		if (f[a] != a) {
			int k = f[a];
			f[a] = find(f[a]);
			d[a] += d[k];
		}
		return f[a];
	}

	public void union(int a, int b) {
		f[find(a)] = find(b);
	}

	public void union(int a, int b, int dis) {
		int ra = find(a), rb = find(b);
		if (ra != rb) {
			f[ra] = rb;
			d[ra] = dis + d[rb] - d[ra];
		}
	}
}
