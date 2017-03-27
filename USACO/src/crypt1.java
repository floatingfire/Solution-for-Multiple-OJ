/*
 ID: lloo1351
 LANG: JAVA
 PROG: crypt1
 */
import java.io.*;
import java.util.*;

public class crypt1 {
	static int[] store;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("crypt1.out"));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		store = new int[n];
		for (int i = 0; i < n; i++) {
			store[i] = Integer.parseInt(st.nextToken());
		}
		int[] test = new int[5];
		int count = 0;
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < n; b++) {
				for (int c = 0; c < n; c++) {
					for (int d = 0; d < n; d++) {
						for (int e = 0; e < n; e++) {
							test[0] = store[a];
							test[1] = store[b];
							test[2] = store[c];
							test[3] = store[d];
							test[4] = store[e];
							if (test[0] * test[4] >= 10
									|| test[0] * test[3] >= 10)
								continue;
							int[][] temp = new int[2][3];
							temp[0][2] = (test[2] * test[4]) % 10;
							temp[0][1] = (test[1] * test[4] + ((test[2] * test[4]) / 10)) % 10;
							temp[0][0] = test[0] * test[4] + (test[1]
									* test[4] + ((test[2] * test[4]) / 10)) / 10;
							temp[1][2] = (test[2] * test[3]) % 10;
							temp[1][1] = (test[1] * test[3] + ((test[2] * test[3]) / 10)) % 10;
							temp[1][0] = test[0] * test[3] + (test[1]
									* test[3] + ((test[2] * test[3]) / 10)) / 10;
							if (!search(temp[0]))
								continue;
							if (!search(temp[1]))
								continue;
							int[] fin = new int[4];
							fin[3] = temp[0][2];
							fin[2] = (temp[0][1] + temp[1][2]) % 10;
							fin[1] = (temp[0][0] + temp[1][1] + (temp[0][1] + temp[1][2]) / 10) % 10;
							fin[0] = (temp[1][0] + (temp[0][0] + temp[1][1] + (temp[0][1] + temp[1][2]) / 10) / 10) % 10;
							if (!search(fin))
								continue;
							count++;
						}
					}
				}
			}
		}
		pw.println(count);
		br.close();
		pw.close();
	}

	private static boolean search(int[] sch) {
		int count = 0;
		for (int i = 0; i < sch.length; i++) {
			for (int j = 0; j < store.length; j++) {
				if (sch[i] == store[j]) {
					count++;
					break;
				}
			}
		}
		return count == sch.length;
	}
}
