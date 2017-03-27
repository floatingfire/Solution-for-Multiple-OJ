import java.io.*;
import java.util.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: castle
 */
public class castle {
	static int n, m, max, clrcnt[], map[][], color[][];
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static void floodfill(int id, int clr) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(id);
		while (!s.isEmpty()) {
			int tmp = s.pop();
			if (color[tmp / m][tmp % m] != 0) {
				continue;
			}
			clrcnt[clr]++;
			color[tmp / m][tmp % m] = clr;
			if ((map[tmp / m][tmp % m] & 1) == 0) {
				s.push(tmp - 1);
			}
			if ((map[tmp / m][tmp % m] & 2) == 0) {
				s.push(tmp - m);
			}
			if ((map[tmp / m][tmp % m] & 4) == 0) {
				s.push(tmp + 1);
			}
			if ((map[tmp / m][tmp % m] & 8) == 0) {
				s.push(tmp + m);
			}
		}
		max = Math.max(max, clrcnt[clr]);
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("castle.in"));
		out = new PrintWriter(new FileWriter("castle.out"));
		max = 0;
		m = nextInt();
		n = nextInt();
		clrcnt = new int[n * m + 1];
		map = new int[n][m];
		color = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = nextInt();
			}
		}
		int clr = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (color[i][j] == 0) {
					floodfill(i * m + j, ++clr);
				}
			}
		}
		out.println(clr);
		out.println(max);
		int wali = 0, walj = 0;
		char dir = 0;
		for (int j = 0; j < m; j++) {
			for (int i = n - 1; i >= 0; i--) {
				if (i > 0 && color[i][j] != color[i - 1][j]
						&& max < clrcnt[color[i][j]] + clrcnt[color[i - 1][j]]) {
					max = clrcnt[color[i][j]] + clrcnt[color[i - 1][j]];
					wali = i + 1;
					walj = j + 1;
					dir = 'N';
				}
				if (j + 1 < m && color[i][j] != color[i][j + 1]
						&& max < clrcnt[color[i][j]] + clrcnt[color[i][j + 1]]) {
					max = clrcnt[color[i][j]] + clrcnt[color[i][j + 1]];
					wali = i + 1;
					walj = j + 1;
					dir = 'E';
				}
			}
		}
		out.println(max);
		out.println(wali + " " + walj + " " + dir);
		out.close();
	}
}
