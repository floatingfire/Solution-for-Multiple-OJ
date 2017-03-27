import java.util.*;

public class ZOJ_1649_Rescue {
	class Point implements Comparable<Point> {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int compareTo(Point p) {
			return time[x][y] - time[p.x][p.y];
		}
	}

	int time[][], n, m;
	boolean map[][], x[][], r[][];
	private Scanner scan;

	int bfs(int x, int y) {
		PriorityQueue<Point> que = new PriorityQueue<Point>();
		que.add(new Point(x, y));
		map[x][y] = false;
		while (!que.isEmpty()) {
			Point p = que.poll();
			if (r[p.x][p.y]) {
				return time[p.x][p.y];
			}
			if (!this.x[p.x][p.y]) {
				if (p.x > 0 && map[p.x - 1][p.y]) {
					time[p.x - 1][p.y] += time[p.x][p.y] + 1;
					que.add(new Point(p.x - 1, p.y));
					map[p.x - 1][p.y] = false;
				}
				if (p.x + 1 < n && map[p.x + 1][p.y]) {
					time[p.x + 1][p.y] += time[p.x][p.y] + 1;
					que.add(new Point(p.x + 1, p.y));
					map[p.x + 1][p.y] = false;
				}
				if (p.y > 0 && map[p.x][p.y - 1]) {
					time[p.x][p.y - 1] += time[p.x][p.y] + 1;
					que.add(new Point(p.x, p.y - 1));
					map[p.x][p.y - 1] = false;
				}
				if (p.y + 1 < m && map[p.x][p.y + 1]) {
					time[p.x][p.y + 1] += time[p.x][p.y] + 1;
					que.add(new Point(p.x, p.y + 1));
					map[p.x][p.y + 1] = false;
				}
			} else {
				this.x[p.x][p.y] = false;
				time[p.x][p.y]++;
				que.add(p);
			}
		}
		return 0;
	}

	void run() throws Exception {
		scan=new Scanner(System.in);
		while (scan.hasNext()) {
			n = scan.nextInt();
			m = scan.nextInt();
			map = new boolean[n][m];
			x = new boolean[n][m];
			r = new boolean[n][m];
			time = new int[n][m];
			int ax = 0, ay = 0;
			for (int i = 0; i < n; i++) {
				String tmp = scan.next();
				for (int j = 0; j < m; j++) {
					char t = tmp.charAt(j);
					if (t != '#') {
						map[i][j] = true;
						if (t == 'a') {
							ax = i;
							ay = j;
						} else if (t == 'r') {
							r[i][j] = true;
						} else if (t == 'x') {
							x[i][j] = true;
						}
					}
				}
			}
			int ans = bfs(ax, ay);
			if (ans == 0) {
				System.out
						.println("Poor ANGEL has to stay in the prison all his life.");
			} else {
				System.out.println(ans);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new ZOJ_1649_Rescue().run();
	}
}
