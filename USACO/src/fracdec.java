import java.io.*;
import java.util.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: fracdec
 */
public class fracdec {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("fracdec.in"));
		out = new PrintWriter(new FileWriter("fracdec.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(new OutputStreamWriter(System.out));
		int i, n = nextInt(), d = nextInt();
		Queue<Integer> dec = new LinkedList<Integer>();
		Stack<Integer> s = new Stack<Integer>();
		i = n / d;
		do {
			s.add(i % 10);
			i /= 10;
		} while (i > 0);
		while (!s.isEmpty()) {
			dec.add(s.pop().intValue());
		}
		dec.add(11);
		int cnt = dec.size(), rem[] = new int[d];
		i = n % d;
		do {
			rem[i] = cnt++;
			dec.add(i * 10 / d);
			i = i * 10 % d;
		} while (rem[i] == 0 && i != 0);
		for (cnt = 0; !dec.isEmpty(); cnt++) {
			if (cnt == rem[i] && i != 0) {
				out.print('(');
				cnt++;
			}
			if (cnt % 76 == 75) {
				out.println(dec.poll());
			} else if (dec.peek() == 11) {
				out.print(".");
				dec.poll();
			} else {
				out.print(dec.poll());
			}
		}
		if (i != 0) {
			out.println(")");
		} else {
			out.println();
		}
		out.close();
	}
}