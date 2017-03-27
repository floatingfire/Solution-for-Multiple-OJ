import java.util.*;
import java.io.*;

public class POJ_1442_Black_Box {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int m = nextInt(), n = nextInt();
		int a[] = new int[m], u[] = new int[n];
		for (int i = 0; i < m; i++) {
			a[i] = nextInt();
		}
		for (int i = 0; i < n; i++) {
			u[i] = nextInt();
		}
		PriorityQueue<Integer> max = new PriorityQueue<Integer>();
		PriorityQueue<Integer> min = new PriorityQueue<Integer>();
		for (int i = 0, j = 0; i < n; i++) {
			while (max.size() + min.size() < u[i]) {
				if (min.isEmpty()) {
					if (max.isEmpty()) {
						min.add(a[j]);
					} else if (a[j] < -max.peek()) {
						max.add(-a[j]);
						min.add(-max.poll());
					} else {
						min.add(a[j]);
					}
				} else if (a[j] > min.peek()) {
					min.add(a[j]);
				} else {
					max.add(-a[j]);
					min.add(-max.poll());
				}
				j++;
			}
			out.println(min.peek());
			max.add(-min.poll());
		}
		out.close();
	}
}