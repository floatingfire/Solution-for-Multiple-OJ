import java.io.*;
import java.util.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: comehome
 */
public class comehome {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int next() throws Exception {
		in.nextToken();
		return (int) in.sval.charAt(0);
	}

	static int p, map[][] = new int[128][128];

	static String dijkstra() {
		int dis[] = new int[128];
		for (int i = 0; i < 128; i++) {
			dis[i] = Integer.MAX_VALUE;
		}
		Stack<Integer> s = new Stack<Integer>();
		s.add((int) 'Z');
		dis['Z'] = 0;
		while (!s.isEmpty()) {
			int tmp = s.pop();
			for (int i = 0; i < 128; i++) {
				if (map[tmp][i] != 0 && dis[tmp] + map[tmp][i] < dis[i]) {
					dis[i] = dis[tmp] + map[tmp][i];
					s.add(i);
				}
			}
		}
		int min = Integer.MAX_VALUE;
		char cow = 0;
		for (int i = 'A'; i < 'Z'; i++) {
			if (min > dis[i]) {
				min = dis[i];
				cow = (char) i;
			}
		}
		return cow + " " + min;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("comehome.in"));
		out = new PrintWriter(new FileWriter("comehome.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(new OutputStreamWriter(System.out));
		p = nextInt();
		for (int i = 0; i < p; i++) {
			int y = next(), x = next();
			if (map[x][y] != 0) {
				map[y][x] = map[x][y] = Math.min(map[x][y], nextInt());
			} else {
				map[y][x] = map[x][y] = nextInt();
			}
		}
		out.println(dijkstra());
		out.close();
	}
}