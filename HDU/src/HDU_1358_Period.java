import java.io.*;

public class HDU_1358_Period {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		for (int cs = 1;; cs++) {
			int n = Integer.parseInt(in.readLine());
			if (n == 0) {
				break;
			}
			String p = in.readLine();
			int next[] = new int[p.length() + 1];
			int k = 0;
			for (int i = 1; i < p.length(); i++) {
				while (k > 0 && p.charAt(k) != p.charAt(i)) {
					k = next[k];
				}
				if (p.charAt(k) == p.charAt(i)) {
					k++;
				}
				next[i + 1] = k;
			}
			out.println("Test case #" + cs);
			for (int i = 2; i < next.length; i++) {
				if (next[i] != 0 && next[i] % (i - next[i]) == 0) {
					out.println(i + " " + (next[i] / (i - next[i]) + 1));
				}
			}
			out.println();
		}
		out.close();
	}
}