import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: clocks
 */
public class clocks {
	static StreamTokenizer in;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int cl[] = new int[9];
	static int mv[] = new int[9];
	static String rem = "999888777666555444333222111";
	static int map[][] = { { 0, 1, 3, 4 }, { 0, 1, 2 }, { 1, 2, 4, 5 },
			{ 0, 3, 6 }, { 1, 3, 4, 5, 7 }, { 2, 5, 8 }, { 3, 4, 6, 7 },
			{ 6, 7, 8 }, { 4, 5, 7, 8 } };

	static boolean check() {
		int tmp[] = new int[9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < map[i].length; j++) {
				tmp[map[i][j]] += mv[i];
			}
		}
		for (int i = 0; i < 9; i++) {
			tmp[i] += cl[i];
			if (tmp[i] % 4 != 0) {
				return false;
			}
		}
		return true;
	}

	static String getstr() {
		StringBuilder sb = new StringBuilder();
		if (check()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < mv[i]; j++) {
					sb.append(i + 1);
				}
			}
		}
		return sb.toString();
	}

	static void record() {
		String tmp = getstr();
		if (tmp.equals(new StringBuilder().toString())) {
			return;
		}
		if ((rem.length() > tmp.length())
				|| (rem.length() == tmp.length() && rem.compareTo(tmp) > 0)) {
			rem = tmp;
		}
	}

	static void dfs(int id) {
		if (id == 9) {
			record();
		} else {
			for (int i = 0; i < 4; i++) {
				mv[id] = i;
				dfs(id + 1);
			}
		}
	}

	static void init() throws Exception {
		in = new StreamTokenizer(
				new BufferedReader(new FileReader("clocks.in")));
		for (int i = 0; i < 9; i++) {
			cl[i] = (nextInt() % 12) / 3;
		}
	}

	static void output() throws Exception {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"clocks.out")));
		out.print(rem.charAt(0));
		for (int i = 1; i < rem.length(); i++) {
			out.print(" " + rem.charAt(i));
		}
		out.println();
		out.close();
	}

	public static void main(String[] args) throws Exception {
		init();
		dfs(0);
		output();
	}
}
