import java.io.*;

public class p4043 {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static int MAX_STU = 20;
	static int MAX_SUB = 10;

	private static String next() throws Exception {
		st.nextToken();
		return st.sval;
	}

	private static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}

	public static void main(String[] args) throws Exception {
		int m = nextInt(), n = nextInt();
		int[][] grade = new int[MAX_STU][MAX_SUB];
		int[] allaverage = new int[MAX_SUB];
		int[] subaverage = new int[MAX_SUB];
		int[] studentnum = new int[MAX_SUB];
		String[] subject = new String[MAX_SUB];
		for (int i = 0; i < n; ++i) {
			subject[i] = next();
		}
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				grade[i][j] = nextInt();
				if (grade[i][j] != 0) {
					studentnum[j]++;
					subaverage[j] += grade[i][j];
				}
			}
		}
		for (int i = 0; i < n; ++i) {
			subaverage[i] /= studentnum[i];
			studentnum[i] = 0;
			for (int j = 0; j < m; ++j) {
				if (grade[j][i] != 0) {
					for (int k = 0; k != n; ++k) {
						if (grade[j][k] != 0) {
							allaverage[i] += grade[j][k];
							studentnum[i]++;
						}
					}
				}
			}
			allaverage[i] /= studentnum[i];
		}
		for (int i = 0; i < n; ++i) {
			System.out.println(subject[i] + " "
					+ (allaverage[i] - subaverage[i]));
		}
	}
}
