import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: holstein
 */
public class holstein {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int v, g, min, ans[], vtm[], gft[][];
	static boolean flag[];
	static StringBuilder sb;

	static boolean check() {
		boolean flag = true;
		for (int i = 0; i < v; i++) {
			if (ans[i] < vtm[i]) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	static void dfs(int id, int cnt) {
		if (check()) {
			if (cnt >= min) {
				return;
			}
			min = cnt;
			sb = new StringBuilder();
			sb.append(min);
			for (int i = 0; i < g; i++) {
				if (flag[i]) {
					sb.append(" " + (i + 1));
				}
			}
		} else if (id < g) {
			flag[id] = true;
			for (int i = 0; i < v; i++) {
				ans[i] += gft[id][i];
			}
			dfs(id + 1, cnt + 1);
			flag[id] = false;
			for (int i = 0; i < v; i++) {
				ans[i] -= gft[id][i];
			}
			dfs(id + 1, cnt);
		}
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("holstein.in"));
		out = new PrintWriter(new FileWriter("holstein.out"));
		v = nextInt();
		vtm = new int[v];
		ans = new int[v];

		for (int i = 0; i < v; i++) {
			vtm[i] = nextInt();
		}
		g = nextInt();
		min = g + 1;
		gft = new int[g][v];
		flag = new boolean[g];
		for (int i = 0; i < g; i++) {
			for (int j = 0; j < v; j++) {
				gft[i][j] = nextInt();
			}
		}
		dfs(0, 0);
		out.println(sb);
		out.close();
	}
}
