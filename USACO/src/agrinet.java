import java.io.*;
import java.util.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: agrinet
 */
public class agrinet {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static class Edge implements Comparable<Edge> {
		int o, d, w;

		Edge(int o, int d, int w) {
			this.o = o;
			this.d = d;
			this.w = w;
		}

		public int compareTo(Edge e) {
			return w - e.w;
		}
	}

	static int n, f[];
	static Edge e[];

	static int find(int a) {
		if (f[a] != a) {
			f[a] = find(f[a]);
		}
		return f[a];
	}

	static void union(int a, int b) {
		f[find(a)] = find(b);
	}

	static int kruskal() {
		int sum = 0;
		f = new int[n];
		for (int i = 0; i < n; i++) {
			f[i] = i;
		}
		Arrays.sort(e);
		for (int i = 0; i < n * n; i++) {
			if (find(e[i].o) != find(e[i].d)) {
				sum += e[i].w;
				union(e[i].o, e[i].d);
			}
		}
		return sum;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("agrinet.in"));
		out = new PrintWriter(new FileWriter("agrinet.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(new OutputStreamWriter(System.out));
		n = nextInt();
		e = new Edge[n * n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				e[i * n + j] = new Edge(i, j, nextInt());
			}
		}
		out.println(kruskal());
		out.close();
	}
}