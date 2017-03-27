import java.io.*;

public class POJ_2677_Tour {
	static class Point {
		double x, y;

		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	static StreamTokenizer in;
	static PrintWriter out;

	static double nextDouble() throws Exception {
		in.nextToken();
		return in.nval;
	}

	static double dis(Point p1, Point p2) {
		return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y)
				* (p1.y - p2.y));
	}

	static double dp(Point p[]) {
		double f[][] = new double[p.length][p.length];
		f[0][1] = f[1][0] = dis(p[0], p[1]);
		for (int i = 1; i < p.length - 1; i++) {
			for (int j = 0; j < i; j++) {
				f[i + 1][j] = f[i][j] + dis(p[i + 1], p[i]);
				f[i + 1][i] = f[i + 1][i] == 0 ? f[i][j] + dis(p[j], p[i + 1])
						: Math.min(f[i + 1][i], f[i][j] + dis(p[j], p[i + 1]));
			}
		}
		return f[p.length - 1][p.length - 2]
				+ dis(p[p.length - 1], p[p.length - 2]);
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.nval;
			Point p[] = new Point[n];
			for (int i = 0; i < n; i++) {
				p[i] = new Point(nextDouble(), nextDouble());
			}
			out.printf("%.2f", dp(p));
			out.println();
		}
		out.close();
	}
}
