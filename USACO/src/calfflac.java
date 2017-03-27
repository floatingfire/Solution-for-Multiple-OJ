/*
 ID: lloo1351
 LANG: JAVA
 PROG: calfflac
 */
import java.io.*;

public class calfflac {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("calfflac.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("calfflac.out"));
		StringBuilder strb = new StringBuilder();
		while (br.ready()) {
			strb.append(br.readLine() + '\n');
		}
		char[] ch = strb.toString().toCharArray();
		char[] ch1 = maxOddNum(ch);
		char[] ch2 = maxEvenNum(ch);
		if (ch1[ch1.length - 1] > ch2[ch2.length - 1]) {
			pw.println((int) ch1[ch1.length - 1]);
			output(ch1, pw);
		} else {
			pw.println((int) ch2[ch2.length - 1]);
			output(ch2, pw);
		}
		br.close();
		pw.close();
	}

	private static char[] maxOddNum(char[] ch) {
		int count, max = 0;
		char[] cha = null;
		for (int i = 1; i < ch.length - 1; i++) {
			if (ch[i] == '\n')
				continue;
			count = 1;
			int a = i - 1, b = i + 1, x = a, y = b;
			while (true) {
				if (a < 0 || b >= ch.length) {
					if (max < count) {
						max = count;
						cha = new char[y - x + 2];
						for (int j = x; j <= y; j++) {
							cha[j - x] = ch[j];
						}
						cha[cha.length - 1] = (char) max;
					}
					break;
				}
				while (!meet(ch[a])) {
					a--;
					if (a < 0)
						break;
				}
				while (!meet(ch[b])) {
					b++;
					if (b >= ch.length)
						break;
				}
				if (a < 0 || b >= ch.length) {
					if (max < count) {
						max = count;
						cha = new char[y - x + 2];
						for (int j = x; j <= y; j++) {
							cha[j - x] = ch[j];
						}
						cha[cha.length - 1] = (char) max;
					}
					break;
				}
				if (ch[a] == ch[b] || ch[a] - 32 == ch[b]
						|| ch[a] + 32 == ch[b]) {
					count += 2;
					x = a;
					y = b;
					a--;
					b++;
				} else {
					if (max < count) {
						max = count;
						cha = new char[y - x + 2];
						for (int j = x; j <= y; j++) {
							cha[j - x] = ch[j];
						}
						cha[cha.length - 1] = (char) max;
					}
					break;
				}
			}
		}
		return cha;
	}

	private static char[] maxEvenNum(char[] ch) {
		int count, max = 0;
		char[] cha = null;
		for (int i = 0; i < ch.length - 1; i++) {
			count = 0;
			int a = i, b = i + 1, x = a, y = b;
			while (true) {
				if (a < 0 || b >= ch.length) {
					if (max < count) {
						max = count;
						cha = new char[y - x + 2];
						for (int j = x; j <= y; j++) {
							cha[j - x] = ch[x];
						}
						cha[cha.length - 1] = (char) max;
					}
					break;
				}
				while (!meet(ch[a])) {
					a--;
					if (a < 0)
						break;
				}
				while (!meet(ch[b])) {
					b++;
					if (b >= ch.length)
						break;
				}
				if (a < 0 || b >= ch.length) {
					if (max < count) {
						max = count;
						System.out.println(i + " " + ch[i]);
						cha = new char[y - x + 2];
						for (int j = x; j <= y; j++) {
							cha[j - x] = ch[x];
						}
						cha[cha.length - 1] = (char) max;
					}
					break;
				}
				if (ch[a] == ch[b] || ch[a] - 32 == ch[b]
						|| ch[a] + 32 == ch[b]) {
					count += 2;
					x = a;
					y = b;
					a--;
					b++;
				} else {
					if (max < count) {
						max = count;
						cha = new char[y - x + 2];
						for (int j = x; j <= y; j++) {
							cha[j - x] = ch[j];
						}
						cha[cha.length - 1] = (char) max;
					}
					break;
				}
			}
		}
		return cha;
	}

	private static boolean meet(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	private static void output(char[] ch, PrintWriter pw) {
		int i;
		for (i = 1; i < ch.length; i++) {
			pw.print(ch[i - 1]);
		}
		if (--i % 80 != 0)
			pw.println();
	}
}
