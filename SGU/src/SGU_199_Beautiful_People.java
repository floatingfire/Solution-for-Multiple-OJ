import java.io.*;
import java.util.*;
//LIS的二分查找写法nlogn复杂度
public class SGU_199_Beautiful_People {
	static class People implements Comparable<People> {
		int b, s, p, m;

		People(int b, int s, int m) {
			this.b = b;
			this.s = s;
			this.m = m;
			this.p = -1;
		}

		public int compareTo(People p) {
			return b == p.b ? p.s - s : b - p.b;
		}
	}

	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static People p[];
	static int add[], tail = 0;

	static int binsearch(int i) {
		int l = -1, r = tail, mid = (l + r) >> 1;
		while (r - l > 1) {
			if (p[add[mid]].s == p[i].s) {
				return mid;
			}
			if (p[add[mid]].s < p[i].s) {
				l = mid;
			} else {
				r = mid;
			}
			mid = (l + r) >> 1;
		}
		return r;
	}

	static void init(int n) throws Exception {
		p = new People[n];
		add = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = new People(nextInt(), nextInt(), i + 1);
		}
		Arrays.sort(p);
	}

	public static void main(String[] args) throws Exception {
		int n = nextInt();
		init(n);
		for (int i = 0; i < n; i++) {
			int id = binsearch(i);
			add[id] = i;
			if (id == tail) {
				tail++;
			}
			if (id != 0) {
				p[i].p = add[id - 1];
			}
		}
		System.out.println(n = tail);
		for (int i = add[--tail]; i != -1; i = p[i].p) {
			add[tail--] = p[i].m;
		}
		System.out.print(add[0]);
		for (int i = 1; i < n; i++) {
			System.out.print(" " + add[i]);
		}
		System.out.println();
	}
}
