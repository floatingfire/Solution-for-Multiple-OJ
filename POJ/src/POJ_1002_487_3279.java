import java.io.*;
import java.util.*;

public class POJ_1002_487_3279 {
	static int tonum(char ch) {
		if ('A' <= ch && ch <= 'z') {
			return (ch < 'Q' ? ch - 59 : ch - 60) / 3;
		} else {
			return ch - '0';
		}
	}

	static String trans(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch != '-') {
				sb.append(tonum(ch));
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		int n = Integer.parseInt(in.readLine());
		TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
		for (int i = 0; i < n; i++) {
			String tmp = trans(in.readLine());
			if (tm.containsKey(tmp)) {
				tm.put(tmp, tm.get(tmp) + 1);
			} else {
				tm.put(tmp, 1);
			}
		}
		int cnt = 0;
		for (String i : tm.keySet()) {
			int r = tm.get(i);
			if (r > 1) {
				out.println(i.substring(0, 3) + "-" + i.substring(3) + " " + r);
				cnt++;
			}
		}
		if (cnt == 0) {
			out.println("No duplicates.");
		}
		out.close();
	}
}