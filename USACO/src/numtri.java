import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: numtri
 */
public class numtri {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new BufferedReader(new FileReader("numtri.in")));
		out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		int r = nextInt();
		int tri[][] = new int[r][];
		for (int i = 0; i < r; i++) {
			tri[i] = new int[i + 1];
			for (int j = 0; j <= i; j++) {
				tri[i][j] = nextInt();
			}
		}
		for (int i = r - 2; i >= 0; i--) {
			for (int j = i; j >= 0; j--) {
				tri[i][j] += Math.max(tri[i + 1][j], tri[i + 1][j + 1]);
			}
		}
		out.println(tri[0][0]);
		out.close();
	}
}
