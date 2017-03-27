/*
 ID: lloo1351
 LANG: JAVA
 PROG: palsquare
 */
import java.io.*;

public class palsquare {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("palsquare.out"));
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < 300; i++) {
			changeAndCompare(i + 1, n, pw);
		}
		br.close();
		pw.close();
	}

	private static void changeAndCompare(int i, int n, PrintWriter pw) {
		char[] p = new char[100];
		char[] f = new char[100];
		char[] iy = new char[100];
		int j = 0, ix = i * i;
		while (i != 0) {
			iy[j] = change(i % n);
			j++;
			i /= n;
		}
		char[] ci = new char[j];
		for (int k = 0; k < j; k++) {
			ci[j - k - 1] = iy[k];
		}
		String si = new String(ci);
		j = 0;
		while (ix != 0) {
			p[99 - j] = change(ix % n);
			f[j] = change(ix % n);
			j++;
			ix /= n;
		}
		char[] cp = new char[j];
		char[] cf = new char[j];
		for (int k = 0; k < j; k++) {
			cp[k] = p[99 - k];
			cf[j - k - 1] = f[k];
		}
		String sp = new String(cp);
		String sf = new String(cf);
		if (sp.equalsIgnoreCase(sf))
			pw.println(si + " " + sp);
	}

	private static char change(int i) {
		if (i < 10)
			return (char) (i + 48);
		else
			return (char) (i + 55);
	}
}
