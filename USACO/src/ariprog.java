import java.io.*;
import java.util.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: ariprog
 */
public class ariprog {
	static class AriPro implements Comparable<AriPro> {
		int a, d;

		AriPro(int a, int d) {
			this.a = a;
			this.d = d;
		}

		public int compareTo(AriPro ar) {
			return this.d == ar.d ? this.a - ar.a : this.d - ar.d;
		}

		public String toString() {
			return a + " " + d;
		}
	}

	static int n, m, arpr[];
	static boolean flag[];
	static TreeSet<AriPro> rem;
	static TreeSet<Integer> map;
	static StreamTokenizer in;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static void init() throws Exception {
		in = new StreamTokenizer(new BufferedReader(
				new FileReader("ariprog.in")));
		n = nextInt();
		m = nextInt();
		arpr = new int[n];
		rem = new TreeSet<AriPro>();
		map=new TreeSet<Integer>();
		flag = new boolean[m*m*2+1];
		for (int i = 0; i <= m; i++) {
			for (int j = i; j <= m; j++) {
				flag[i * i + j * j]=true;
				map.add(i*i+j*j);
			}
		}
	}

	static void dfs() {
		int id, max = m*m*2, maxd = max / (n - 1);
		Iterator<Integer> it = map.iterator();
		while (it.hasNext()) {
			arpr[0] = it.next();
			for (int d = 1; d <= maxd; d++) {
				if(arpr[0]+d*(n-1)>max){
					break;
				}
				for (id = 1; id < n; id++) {
					arpr[id] = arpr[id - 1] + d;
					if (!flag[arpr[id]]) {
						break;
					}
				}
				if (id == n) {
					rem.add(new AriPro(arpr[0], d));
				}
			}
		}
	}

	static void output() throws Exception {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"ariprog.out")));
		if (rem.isEmpty()) {
			out.println("NONE");
		}
		while (!rem.isEmpty()) {
			out.println(rem.pollFirst());
		}
		out.close();
	}

	public static void main(String[] args) throws Exception {
		init();
		dfs();
		output();
	}
}
