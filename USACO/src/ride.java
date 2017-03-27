
/*
 ID: lloo1351
 LANG: JAVA
 PROG: ride
 */

import java.io.*;

public class ride {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("ride.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("ride.out"));
		String str = br.readLine();
		char[] ch1 = str.toCharArray();
		str = br.readLine();
		char[] ch2 = str.toCharArray();
		int c = 1, g = 1;
		for (int i = 0; i < ch1.length; i++) {
			c *= trans(ch1[i]);
		}
		for (int i = 0; i < ch2.length; i++) {
			g *= trans(ch2[i]);
		}
		if (c % 47 == g % 47)
			pw.println("GO");
		else
			pw.println("STAY");
		br.close();
		pw.close();
	}

	public static int trans(char ch) {
		return ch - 'A' + 1;
	}
}
