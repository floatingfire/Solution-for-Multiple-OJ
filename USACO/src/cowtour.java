import java.io.*;
import java.util.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: cowtour
 */
public class cowtour {
	static BufferedReader in;
	static PrintWriter out;

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int n, clr[];
	static Point p[];
	static double len[], map[][], dis[][];

	static double dist(Point a, Point b) {
		double x = a.x - b.x, y = a.y - b.y;
		return Math.sqrt(x * x + y * y);
	}

	static void dijkstra() {
		dis = new double[n][n];
		len = new double[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dis[i][j] = Double.MAX_VALUE;
			}
		}
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < n; i++) {
			dis[i][i] = 0;
			s.add(i);
			while (!s.isEmpty()) {
				int tmp = s.pop();
				for (int j = 0; j < n; j++) {
					if (map[tmp][j] != 0
							&& map[tmp][j] + dis[i][tmp] < dis[i][j]) {
						dis[i][j] = map[tmp][j] + dis[i][tmp];
						s.add(j);
					}
				}
			}
			for (double j : dis[i]) {
				if (j != Double.MAX_VALUE) {
					len[i] = Math.max(len[i], j);
				}
			}
		}
	}

	static void flood() {
		clr = new int[n];
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < n; i++) {
			if (clr[i] == 0) {
				s.add(i);
			}
			while (!s.isEmpty()) {
				int tmp = s.pop();
				clr[tmp] = i + 1;
				for (int j = 0; j < n; j++) {
					if (map[tmp][j] != 0 && clr[j] == 0) {
						s.add(j);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new FileReader("cowtour.in"));
		out = new PrintWriter(new FileWriter("cowtour.out"));
		// in = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(in.readLine());
		p = new Point[n];
		map = new double[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			p[i] = new Point(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		for (int i = 0; i < n; i++) {
			String tmp = in.readLine();
			for (int j = 0; j < n; j++) {
				if (tmp.charAt(j) == '1') {
					map[i][j] = dist(p[i], p[j]);
				}
			}
		}
		flood();
		dijkstra();
		double dia[] = new double[n];
		for (int i = 0; i < n; i++) {
			dia[clr[i] - 1] = Math.max(dia[clr[i] - 1], len[i]);
		}
		for (int i = 0; i < n; i++) {
			dia[i] = dia[clr[i] - 1];
		}
		double min = Double.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (clr[i] != clr[j]) {
					min = Math.min(
							min,
							Math.max(len[i] + len[j] + dist(p[i], p[j]),
									Math.max(dia[i], dia[j])));
				}
			}
		}
		out.printf("%.6f", min);
		out.println();
		out.close();
	}
}
