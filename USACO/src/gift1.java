
/*
 ID: lloo1351
 LANG: JAVA
 PROG: gift1
 */
import java.io.*;
import java.util.*;

public class gift1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("gift1.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("gift1.out"));
		int np = Integer.parseInt(br.readLine());
		String[] name = new String[np];
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for (int i = 0; i < np; i++) {
			name[i] = br.readLine();
			hm.put(name[i], new Integer(0));
		}
		for (int i = 0; i < np; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int im = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			if (n != 0) {
				hm.put(str, new Integer(hm.get(str).intValue() - (im / n) * n));
			}
			for (int j = 0; j < n; j++) {
				String s = br.readLine();
				if (n != 0) {
					hm.put(s, new Integer(hm.get(s).intValue() + im / n));

				}
			}
		}
		for (int i = 0; i < np; i++) {
			pw.println(name[i] + " " + hm.get(name[i]));
		}
		br.close();
		pw.close();
	}
}
