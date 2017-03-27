import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: preface
 */
public class preface {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int ndic[] = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
	static char cdic[] = { 'M', 'D', 'C', 'L', 'X', 'V', 'I' };

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("preface.in"));
		out = new PrintWriter(new FileWriter("preface.out"));
		int n = nextInt();
		int cnt[] = new int[7];
		for (int i = 1; i <= n; i++) {
			int tmp = i;
			for (int j = 0; j < 13; j++) {
				if ((j & 1) == 1) {
					if ((j & 3) == 3) {
						cnt[j >> 1] += tmp / ndic[j];
						cnt[(j >> 1) + 1] += tmp / ndic[j];
					} else {
						cnt[j >> 1] += tmp / ndic[j];
						cnt[(j >> 1) + 2] += tmp / ndic[j];
					}
				} else {
					cnt[j >> 1] += tmp / ndic[j];
				}
				tmp %= ndic[j];
			}
		}
		for (int i = 6; i >= 0; i--) {
			if (cnt[i] != 0) {
				out.println(cdic[i] + " " + cnt[i]);
			}
		}
		out.close();
	}
}
