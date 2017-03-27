import java.io.*;

public class POJ_3501_Escape_from_Enemy_Territory {
	int x[], y[], X, Y, xs, ys, xe, ye, N;
	StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));

	int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	int dis(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	void bfs() {

	}

	void run() throws IOException {
		for (int t = nextInt(); t > 0; t--) {
			N = nextInt();
			X = nextInt();
			Y = nextInt();
			xs = nextInt();
			ys = nextInt();
			xe = nextInt();
			ye = nextInt();
			x = new int[N];
			y = new int[N];
			for (int i = 0; i < N; i++) {
				x[i] = nextInt();
				y[i] = nextInt();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new POJ_3501_Escape_from_Enemy_Territory().run();
	}
}
