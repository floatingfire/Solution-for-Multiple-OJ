/*
 ID: lloo1351
 LANG: JAVA
 PROG: packrec
 */
import java.io.*;
import java.util.*;

public class packrec {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static class Area implements Comparable<Area> {
		int x, y;

		Area(int x, int y) {
			this.x = Math.min(x, y);
			this.y = Math.max(x, y);
		}

		public int compareTo(Area a) {
			return this.x - a.x;
		}

		public String toString() {
			return x + " " + y;
		}
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new BufferedReader(/*
													 * new InputStreamReader(
													 * System.in)
													 */
		new FileReader("packrec.in")));
		out = new PrintWriter(/* new OutputStreamWriter(System.out) */
		new FileWriter("packrec.out"));
		TreeSet<Area> rem = new TreeSet<Area>();
		int rec[][] = new int[2][4];
		int min = 10000, x, y, a;
		for (int i = 0; i < 4; i++) {
			rec[0][i] = nextInt();
			rec[1][i] = nextInt();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					for (int l = 0; l < 4; l++) {
						if (i != j && i != k && i != l && j != k && j != l
								&& k != l) {
							for (int h = 0; h < 16; h++) {
								x = rec[h & 1][i] + rec[(h >> 1) & 1][j]
										+ rec[(h >> 2) & 1][k] + rec[h >> 3][l];
								y = Math.max(Math.max(rec[(h & 1) ^ 1][i],
										rec[((h >> 1) & 1) ^ 1][j]), Math.max(
										rec[((h >> 2) & 1) ^ 1][k],
										rec[(h >> 3) ^ 1][l]));
								a = x * y;
								if (min > a) {
									min = a;
									rem.clear();
									rem.add(new Area(x, y));
								} else if (min == a) {
									rem.add(new Area(x, y));
								}
								x = Math.max(rec[h & 1][i]
										+ rec[(h >> 1) & 1][j]
										+ rec[(h >> 2) & 1][k], rec[h >> 3][l]);
								y = Math.max(Math.max(rec[(h & 1) ^ 1][i],
										rec[((h >> 1) & 1) ^ 1][j]),
										rec[((h >> 2) & 1) ^ 1][k])
										+ rec[(h >> 3) ^ 1][l];
								a = x * y;
								if (min > a) {
									min = a;
									rem.clear();
									rem.add(new Area(x, y));
								} else if (min == a) {
									rem.add(new Area(x, y));
								}
								x = Math.max(rec[h & 1][i]
										+ rec[(h >> 1) & 1][j],
										rec[(h >> 2) & 1][k])
										+ rec[h >> 3][l];
								y = Math.max(
										Math.max(rec[(h & 1) ^ 1][i],
												rec[((h >> 1) & 1) ^ 1][j])
												+ rec[((h >> 2) & 1) ^ 1][k],
										rec[(h >> 3) ^ 1][l]);
								a = x * y;
								if (min > a) {
									min = a;
									rem.clear();
									rem.add(new Area(x, y));
								} else if (min == a) {
									rem.add(new Area(x, y));
								}
								x = Math.max(rec[h & 1][i],
										rec[(h >> 1) & 1][j])
										+ rec[(h >> 2) & 1][k] + rec[h >> 3][l];
								y = Math.max(Math.max(rec[(h & 1) ^ 1][i]
										+ rec[((h >> 1) & 1) ^ 1][j],
										rec[((h >> 2) & 1) ^ 1][k]),
										rec[(h >> 3) ^ 1][l]);
								a = x * y;
								if (min > a) {
									min = a;
									rem.clear();
									rem.add(new Area(x, y));
								} else if (min == a) {
									rem.add(new Area(x, y));
								}
								x = Math.max(Math.max(rec[h & 1][i]
										+ rec[(h >> 1) & 1][j],
										rec[(h >> 2) & 1][k] + rec[h >> 3][l]),
										rec[h & 1][i] + rec[h >> 3][l]);
								y = Math.max(Math.max(rec[(h & 1) ^ 1][i]
										+ rec[((h >> 2) & 1) ^ 1][k],
										rec[((h >> 1) & 1) ^ 1][j]
												+ rec[(h >> 3) ^ 1][l]),
										rec[((h >> 1) & 1) ^ 1][j]
												+ rec[((h >> 2) & 1) ^ 1][k]);
								a = x * y;
								if (min > a) {
									min = a;
									rem.clear();
									rem.add(new Area(x, y));
								} else if (min == a) {
									rem.add(new Area(x, y));
								}

							}
						}
					}
				}
			}
		}
		out.println(min);
		while (!rem.isEmpty()) {
			out.println(rem.pollFirst());
		}
		out.close();
	}
}
