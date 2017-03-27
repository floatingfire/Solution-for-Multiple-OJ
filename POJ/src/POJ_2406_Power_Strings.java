import java.io.*;

public class POJ_2406_Power_Strings {
	static BufferedReader in;

	static int[] getnext(String p) {
		int next[] = new int[p.length() + 1];
		for (int i = 1, cur = 0; i < p.length(); i++) {
			while (cur != 0 && p.charAt(i) != p.charAt(cur)) {
				cur = next[cur];
			}
			if (p.charAt(i) == p.charAt(cur)) {
				cur++;
			}
			next[i + 1] = cur;
		}
		return next;
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String p = in.readLine();
			if (p.equals(".")) {
				break;
			}
			int next[] = getnext(p);
			int par = p.length() - next[p.length()];
			if (par != 0 && p.length() % (par) == 0) {
				System.out.println(p.length() / par);
			} else {
				System.out.println(1);
			}
		}
	}
}