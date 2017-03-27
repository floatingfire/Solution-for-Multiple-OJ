import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: pprime
 */
public class pprime {
	//生成回文判素数，筛法MLE
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static boolean check(int val) {
		int up = (int) Math.sqrt(val);
		for (int i = 2; i <= up; i++) {
			if (val % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(
				new BufferedReader(new FileReader("pprime.in")));
		out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		int l = nextInt();
		int r = nextInt();
		for (int i = 1; i <= 9; i += 2) {
			if (i > r) {
				out.close();
				System.exit(0);
			} else if (i < l) {
				continue;
			} else if (check(i)) {
				out.println(i);
			}
		}
		if(l<=11&&11<=r){
			out.println("11");
		}
		for (int i = 1; i <= 9; i += 2) {
			for (int j = 0; j <= 9; j++) {
				int tmp = 100 * i + 10 * j + i;
				if (tmp > r) {
					out.close();
					System.exit(0);
				} else if (tmp < l) {
					continue;
				} else if (check(tmp)) {
					out.println(tmp);
				}
			}
		}
		for (int i = 1; i <= 9; i += 2) {
			for (int j = 0; j <= 9; j++) {
				for (int k = 0; k <= 9; k++) {
					int tmp = 10000 * i + 1000 * j + 100 * k + 10 * j + i;
					if (tmp > r) {
						out.close();
						System.exit(0);
					} else if (tmp < l) {
						continue;
					} else if (check(tmp)) {
						out.println(tmp);
					}
				}
			}
		}
		for (int i = 1; i <= 9; i += 2) {
			for (int j = 0; j <= 9; j++) {
				for (int k = 0; k <= 9; k++) {
					for (int h = 0; h <= 9; h++) {
						int tmp = 1000000 * i + 100000 * j + 10000 * k + 1000
								* h + 100 * k + 10 * j + i;
						if (tmp > r) {
							out.close();
							System.exit(0);
						} else if (tmp < l) {
							continue;
						} else if (check(tmp)) {
							out.println(tmp);
						}
					}
				}
			}
		}
		out.close();
	}
}
