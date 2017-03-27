import java.io.*;
import java.util.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: frac1
 */
public class frac1 {
	static class Fraction implements Comparable<Fraction> {
		int nt, dt;

		Fraction(int nt, int dt) {
			this.nt = nt;
			this.dt = dt;
		}

		public int compareTo(Fraction f) {
			return nt * f.dt - dt * f.nt;
		}

		public String toString() {
			return nt + "/" + dt;
		}
	}

	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("frac1.in"));
		out = new PrintWriter(new FileWriter("frac1.out"));
		int n = nextInt();
		TreeSet<Fraction> lis = new TreeSet<Fraction>();
		lis.add(new Fraction(0, 1));
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				lis.add(new Fraction(j, i));
			}
		}
		lis.add(new Fraction(1, 1));
		for (Fraction f : lis) {
			out.println(f);
		}
		out.close();
	}
}
