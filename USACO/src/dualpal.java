/*
 ID: lloo1351
 LANG: JAVA
 PROG: dualpal
 */
import java.io.*;
import java.util.*;

public class dualpal {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("dualpal.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int i = 0;
		while (i < n) {
			if (changeAndEquals(++s)) {
				pw.println(s);
				i++;
			}
		}
		br.close();
		pw.close();
	}

	private static boolean changeAndEquals(int a) {
		int j = 0;
		for (int i = 2; i <= 10; i++) {
			int x=a,m=0;
			long p = 0, f = 0;
			while (x > 0) {
				p = p * 10 + x % i;
				f = f + (x % i) * (long)Math.pow(10,m);
				x /= i;m++;
			}
			//System.out.println(p+" "+f);
			if (p == f)
				j++;
		}
		return j >= 2;
	}
}
