import java.io.*;

public class HDU_4501_小明系列故事_买年货 {
	static class Goods {
		int m, p, v;

		Goods(int m, int p, int v) {
			this.m = m;
			this.p = p;
			this.v = v;
		}
	}

	static StreamTokenizer in;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.nval;
			int vm = nextInt() + 1;
			int vp = nextInt() + 1;
			int fr = nextInt() + 1;
			int max = vm * vp * fr - 1;
			int f[] = new int[max + 1];
			for (int i = 0; i < n; i++) {
				Goods tmp = new Goods(nextInt(), nextInt(), nextInt());
				for (int j = max; j >= 0; j--) {
					int b1 = j % vm, b2 = j / vm % vp, b3 = j / vm / vp % fr;
					int a = 0, b = 0, c = 0;
					if (b1 >= tmp.m) {
						a = f[j - tmp.m] + tmp.v;
					}
					if (b2 >= tmp.p) {
						b = f[j - tmp.p * vm] + tmp.v;
					}
					if (b3 >= 1) {
						c = f[j - vm * vp] + tmp.v;
					}
					f[j] = Math.max(Math.max(f[j], a), Math.max(b, c));
				}
			}
			int ans = 0;
			for (int i : f) {
				ans = Math.max(ans, i);
			}
			System.out.println(ans);
		}
	}
}
