import java.io.*;
import java.util.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: zerosum
 */
public class zerosum {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int n;
	static char dic[] = { ' ', '+', '-' };
	static boolean used[];

	static String trans(int ops) {
		String tmp = "" + n;
		int op = 0;
		for (int i = n - 1; i > 0; i--) {
			tmp = i + "" + dic[(ops & 3) % 3] + tmp;
			op = (op << 2) + (ops & 3) % 3;
			ops >>= 2;
		}
		if (used[op]) {
			return null;
		} else {
			used[op] = true;
			return tmp;
		}
	}

	static void cal(int ops) {
		Stack<Integer> num = new Stack<Integer>();
		Stack<Character> ope = new Stack<Character>();
		String exp = trans(ops);
		if (exp == null) {
			return;
		}
		for (char c : exp.toCharArray()) {
			if (c > '/') {
				num.add(c - '0');
			} else {
				if (ope.isEmpty()) {
					ope.add(c);
				} else {
					while (!ope.isEmpty()
							&& (ope.peek() == ' '
									|| (ope.peek() == '-' && c != ' ') || (ope
									.peek() == '+' && c == '+'))) {
						char o = ope.pop();
						if (o == ' ') {
							num.push(num.pop() + num.pop() * 10);
						} else if (o == '-') {
							num.push(-num.pop() + num.pop());
						} else {
							num.push(num.pop() + num.pop());
						}
					}
					ope.add(c);
				}
			}
		}
		while (!ope.isEmpty()) {
			char o = ope.pop();
			if (o == ' ') {
				num.push(num.pop() + num.pop() * 10);
			} else if (o == '-') {
				num.push(-num.pop() + num.pop());
			} else {
				num.push(num.pop() + num.pop());
			}
		}
		if (num.pop() == 0) {
			out.println(exp);
		}
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("zerosum.in"));
		out = new PrintWriter(new FileWriter("zerosum.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		n = nextInt();
		used = new boolean[1 << (n + n - 2)];
		for (int i = 0; i < 1 << (n + n - 2); i++) {
			cal(i);
		}
		out.close();
	}
}