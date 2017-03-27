import java.util.*;
import java.io.*;

public class POJ_2752_Seek_the_Name_Seek_the_Fame {
	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		try {
			while (true) {
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
				Stack<Integer> s = new Stack<Integer>();
				for (int i = p.length(); i != 0; i = next[i]) {
					s.push(i);
				}
				out.print(s.pop());
				while (!s.isEmpty()) {
					out.print(" " + s.pop());
				}
				out.println();
			}
		} catch (Exception e) {
			out.close();
			System.exit(0);
		}
	}
}