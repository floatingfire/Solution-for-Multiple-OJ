import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: ttwo
 */
public class ttwo {
	static BufferedReader in;
	static PrintWriter out;
	static final int mov[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean fvd[][][][][][] = new boolean[12][12][4][12][12][4];
	static char map[][] = new char[12][];
	static int fx, fy, cx, cy;

	static int simu() {
		int fd = 0, cd = 0, min = 0;
		while (fx != cx || fy != cy) {
			if (fvd[fx][fy][fd][cx][cy][cd]) {
				return 0;
			}
			fvd[fx][fy][fd][cx][cy][cd] = true;
			if (map[fx + mov[fd][0]][fy + mov[fd][1]] == '*') {
				fd = (fd + 1) % 4;
			} else {
				fx += mov[fd][0];
				fy += mov[fd][1];
			}
			if (map[cx + mov[cd][0]][cy + mov[cd][1]] == '*') {
				cd = (cd + 1) % 4;
			} else {
				cx += mov[cd][0];
				cy += mov[cd][1];
			}
			min++;
		}
		return min;
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new FileReader("ttwo.in"));
		out = new PrintWriter(new FileWriter("ttwo.out"));
		// in = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		map[11] = map[0] = "************".toCharArray();
		for (int i = 1; i < 11; i++) {
			map[i] = ("*" + in.readLine() + "*").toCharArray();
			for (int j = 1; j < 11; j++) {
				if (map[i][j] == 'F') {
					fx = i;
					fy = j;
				} else if (map[i][j] == 'C') {
					cx = i;
					cy = j;
				}
			}
		}
		out.println(simu());
		out.close();
	}
}