import java.io.*;
import java.util.*;

public class SGU_198_Get_Out {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static double nextDouble() throws Exception {
		in.nextToken();
		return in.nval;
	}

	static class Point {
		double x, y, r;

		Point(double x, double y, double r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}

		void translate(Point p) {
			this.x -= p.x;
			this.y -= p.y;
			this.r += p.r;
		}
	}

	static int n;
	static Point parr[];
	static boolean map[][];

	static double dis(Point a, Point b) {
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}

	static double dis(Point p) {
		return Math.sqrt(p.x * p.x + p.y * p.y);
	}

	static double cross(Point a, Point b) {
		return a.x * b.y - a.y * b.x;
	}

	static int sign(double val) {
		return val != 0 ? val > 0 ? 1 : -1 : 0;
	}

	static double arccos(Point a, Point b) {
		return Math.acos((a.x * b.x + a.y * b.y) / (dis(a) * dis(b)));
	}

	static boolean canout() {
		boolean vst[] = new boolean[n];
		int pre[] = new int[n];
		Arrays.fill(pre, -1);
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < n; i++) {
			s.push(i);
		}
		while (!s.isEmpty()) {
			int tmp = s.pop();
			if (!vst[tmp]) {
				vst[tmp] = true;
				for (int i = 0; i < n; i++) {
					if (map[tmp][i]) {
						if (!vst[i]) {
							pre[i] = tmp;
							s.push(i);
						} else {
							double ans = sign(cross(parr[i], parr[tmp]))
									* arccos(parr[i], parr[tmp]);
							for (int t = tmp; t != i; t = pre[t]) {
								ans += sign(cross(parr[t], parr[pre[t]]))
										* arccos(parr[t], parr[pre[t]]);
							}
							if (Math.abs(Math.abs(ans) - 2 * Math.PI) < 0.000001) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		n = nextInt();
		parr = new Point[n];
		map = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			parr[i] = new Point(nextDouble(), nextDouble(), nextDouble());
		}
		Point p = new Point(nextDouble(), nextDouble(), nextDouble());
		for (int i = 0; i < n; i++) {
			parr[i].translate(p);
			for (int j = 0; j < i; j++) {
				if (dis(parr[i], parr[j]) < parr[i].r + parr[j].r) {
					map[i][j] = map[j][i] = true;
				}
			}
		}
		System.out.println(canout() ? "YES" : "NO");
	}
}