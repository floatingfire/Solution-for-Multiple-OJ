import java.io.*;
import java.util.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: maze1
 */
public class maze1 {
	static BufferedReader in;
	static PrintWriter out;

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static char map[][];
	static int w, h;

	static int dfs(Stack<Point> e) {
		int max = 0, stp[][] = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				stp[i][j] = Integer.MAX_VALUE;
			}
		}
		Stack<Point> s = new Stack<Point>();
		stp[e.peek().x][e.peek().y] = 1;
		s.add(e.pop());
		while (!s.isEmpty()) {
			Point tmp = s.pop();
			if (map[(tmp.x << 1)][(tmp.y << 1) + 1] == ' '
					&& stp[tmp.x - 1][tmp.y] > stp[tmp.x][tmp.y] + 1) {
				stp[tmp.x - 1][tmp.y] = stp[tmp.x][tmp.y] + 1;
				s.add(new Point(tmp.x - 1, tmp.y));
			}
			if (map[(tmp.x << 1) + 2][(tmp.y << 1) + 1] == ' '
					&& stp[tmp.x + 1][tmp.y] > stp[tmp.x][tmp.y] + 1) {
				stp[tmp.x + 1][tmp.y] = stp[tmp.x][tmp.y] + 1;
				s.add(new Point(tmp.x + 1, tmp.y));
			}
			if (map[(tmp.x << 1) + 1][(tmp.y << 1)] == ' '
					&& stp[tmp.x][tmp.y - 1] > stp[tmp.x][tmp.y] + 1) {
				stp[tmp.x][tmp.y - 1] = stp[tmp.x][tmp.y] + 1;
				s.add(new Point(tmp.x, tmp.y - 1));
			}
			if (map[(tmp.x << 1) + 1][(tmp.y << 1) + 2] == ' '
					&& stp[tmp.x][tmp.y + 1] > stp[tmp.x][tmp.y] + 1) {
				stp[tmp.x][tmp.y + 1] = stp[tmp.x][tmp.y] + 1;
				s.add(new Point(tmp.x, tmp.y + 1));
			}
		}
		stp[e.peek().x][e.peek().y] = 1;
		s.add(e.pop());
		while (!s.isEmpty()) {
			Point tmp = s.pop();
			if (map[(tmp.x << 1)][(tmp.y << 1) + 1] == ' '
					&& stp[tmp.x - 1][tmp.y] > stp[tmp.x][tmp.y] + 1) {
				stp[tmp.x - 1][tmp.y] = stp[tmp.x][tmp.y] + 1;
				s.add(new Point(tmp.x - 1, tmp.y));
			}
			if (map[(tmp.x << 1) + 2][(tmp.y << 1) + 1] == ' '
					&& stp[tmp.x + 1][tmp.y] > stp[tmp.x][tmp.y] + 1) {
				stp[tmp.x + 1][tmp.y] = stp[tmp.x][tmp.y] + 1;
				s.add(new Point(tmp.x + 1, tmp.y));
			}
			if (map[(tmp.x << 1) + 1][(tmp.y << 1)] == ' '
					&& stp[tmp.x][tmp.y - 1] > stp[tmp.x][tmp.y] + 1) {
				stp[tmp.x][tmp.y - 1] = stp[tmp.x][tmp.y] + 1;
				s.add(new Point(tmp.x, tmp.y - 1));
			}
			if (map[(tmp.x << 1) + 1][(tmp.y << 1) + 2] == ' '
					&& stp[tmp.x][tmp.y + 1] > stp[tmp.x][tmp.y] + 1) {
				stp[tmp.x][tmp.y + 1] = stp[tmp.x][tmp.y] + 1;
				s.add(new Point(tmp.x, tmp.y + 1));
			}
		}
		for (int in[] : stp) {
			for (int i : in) {
				if (i != Integer.MAX_VALUE) {
					max = Math.max(max, i);
				}
			}
		}
		return max;
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new FileReader("maze1.in"));
		out = new PrintWriter(new File("maze1.out"));
		// in = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(in.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new char[(h << 1) + 1][];
		Stack<Point> exit = new Stack<Point>();
		map[0] = in.readLine().toCharArray();
		for (int i = 1; i < map[0].length - 1; i++) {
			if (map[0][i] == ' ') {
				map[0][i] = '-';
				exit.add(new Point(0, i >> 1));
			}
		}
		for (int i = 1; i < map.length - 1; i++) {
			map[i] = in.readLine().toCharArray();
			if (map[i][0] == ' ') {
				map[i][0] = '|';
				exit.add(new Point(i >> 1, 0));
			}
			if (map[i][map[i].length - 1] == ' ') {
				map[i][map[i].length - 1] = '|';
				exit.add(new Point(i >> 1, (map[i].length - 2) >> 1));
			}
		}
		map[map.length - 1] = in.readLine().toCharArray();
		for (int i = 1; i < map[map.length - 1].length - 1; i++) {
			if (map[map.length - 1][i] == ' ') {
				map[map.length - 1][i] = '-';
				exit.add(new Point((map.length - 2) >> 1, i >> 1));
			}
		}
		out.println(dfs(exit));
		out.close();
	}
}
