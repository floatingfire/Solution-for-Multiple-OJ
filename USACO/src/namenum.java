/*
 ID: lloo1351
 LANG: JAVA
 PROG: namenum
 */
import java.io.*;

public class namenum {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("namenum.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("namenum.out"));
		BufferedReader fr = new BufferedReader(new FileReader("dict.txt"));
		long num = Long.parseLong(br.readLine());
		String str;
		boolean b = true;
		do {
			str = fr.readLine();
			if (num == change(str.toCharArray())) {
				b = false;
				pw.println(str);
			}
		} while (!str.equals("ZYTA"));
		if (b)
			pw.println("NONE");
		br.close();
		pw.close();
	}

	private static long change(char[] ch) {
		long t = 0;
		for (int i = 0; i < ch.length; i++) {
			t = t * 10 + change(ch[i]);
		}
		return t;
	}

	private static int change(char ch) {
		if (ch < 'Q')
			ch++;
		return (ch / 3) - 20;
	}
}
