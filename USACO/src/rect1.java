import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: rect1
 */
public class rect1 {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static class Rect {
		int ldx, ldy, rux, ruy, clr;

		Rect(int ldx, int ldy, int rux, int ruy, int clr) {
			this.ldx = ldx;
			this.ldy = ldy;
			this.rux = rux;
			this.ruy = ruy;
			this.clr = clr;
		}
	}

	static int a, b, n, cnt[] = new int[2501];
	static Rect rec[];

	static boolean iscrossed(Rect a, Rect b) {
		return a.rux > b.ldx && a.ruy > b.ldy && b.rux > a.ldx && b.ruy > a.ldy;
	}

	static int area(Rect r) {
		return (r.rux - r.ldx) * (r.ruy - r.ldy);
	}

	static void split(Rect r, int id) {
		if (id > n) {
			cnt[r.clr] += area(r);
		} else {
			if (iscrossed(r, rec[id])) {
				if (r.ldx < rec[id].ldx) {
					split(new Rect(r.ldx, r.ldy, rec[id].ldx, r.ruy, r.clr),
							id + 1);
				}
				if (r.rux > rec[id].rux) {
					split(new Rect(rec[id].rux, r.ldy, r.rux, r.ruy, r.clr),
							id + 1);
				}
				if (r.ldy < rec[id].ldy) {
					split(new Rect(Math.max(r.ldx, rec[id].ldx), r.ldy,
							Math.min(r.rux, rec[id].rux), rec[id].ldy, r.clr),
							id + 1);
				}
				if (r.ruy > rec[id].ruy) {
					split(new Rect(Math.max(r.ldx, rec[id].ldx), rec[id].ruy,
							Math.min(r.rux, rec[id].rux), r.ruy, r.clr), id + 1);
				}
			} else {
				split(r, id + 1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("rect1.in"));
		out = new PrintWriter(new FileWriter("rect1.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(new OutputStreamWriter(System.out));
		a = nextInt();
		b = nextInt();
		n = nextInt();
		rec = new Rect[n + 1];
		rec[0] = new Rect(0, 0, a, b, 1);
		for (int i = 1; i <= n; i++) {
			rec[i] = new Rect(nextInt(), nextInt(), nextInt(), nextInt(),
					nextInt());
		}
		for (int i = n; i >= 0; i--) {
			split(rec[i], i + 1);
		}
		for (int i = 1; i < cnt.length; i++) {
			if (cnt[i] != 0) {
				out.println(i + " " + cnt[i]);
			}
		}
		out.close();
	}
}