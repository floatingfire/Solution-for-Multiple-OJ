import java.io.*;
import java.util.*;

public class POJ_2392_Space_Elevator {
	static class Block implements Comparable<Block> {
		int h, a, c;

		Block(int h, int a, int c) {
			this.h = h;
			this.a = a;
			this.c = c;
		}

		public int compareTo(Block b) {
			return a - b.a;
		}
	}

	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		Block b[] = new Block[nextInt()];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Block(nextInt(), nextInt(), nextInt());
		}
		Arrays.sort(b);
		boolean f[] = new boolean[b[b.length - 1].a + 1];
		f[0] = true;
		for (int i = 0; i < b.length; i++) {
			int use[] = new int[f.length];
			for (int j = b[i].h; j <= b[i].a; j++) {
				if (!f[j] && f[j - b[i].h] && use[j - b[i].h] < b[i].c) {
					f[j] = true;
					use[j] = use[j - b[i].h] + 1;
				}
			}
		}
		for (int i = f.length - 1; i >= 0; i--) {
			if (f[i]) {
				out.println(i);
				break;
			}
		}
		out.close();
	}
}