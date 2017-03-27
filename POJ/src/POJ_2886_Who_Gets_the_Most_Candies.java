import java.io.*;
import java.util.*;

public class POJ_2886_Who_Gets_the_Most_Candies {
	static class Node {
		int l, r, num;

		Node(int l, int r) {
			this.l = l;
			this.r = r;
			num = r - l + 1;
		}
	}

	static Node tree[] = new Node[2000000];
	static String name[] = new String[500001];
	static int card[] = new int[500001];
	static int map[][] = { { 498960, 200 }, { 332640, 192 }, { 277200, 180 },
			{ 221760, 168 }, { 166320, 160 }, { 110880, 144 }, { 83160, 128 },
			{ 55440, 120 }, { 50400, 108 }, { 45360, 100 }, { 27720, 96 },
			{ 25200, 90 }, { 20160, 84 }, { 15120, 80 }, { 10080, 72 },
			{ 7560, 64 }, { 5040, 60 }, { 2520, 48 }, { 1680, 40 },
			{ 1260, 36 }, { 840, 32 }, { 720, 30 }, { 360, 24 }, { 240, 20 },
			{ 180, 18 }, { 120, 16 }, { 60, 12 }, { 48, 10 }, { 36, 9 },
			{ 24, 8 }, { 12, 6 }, { 6, 4 }, { 4, 3 }, { 2, 2 }, { 1, 1 } };

	static int binarySearch(int l, int r, int key) {
		if (l == r) {
			return l;
		} else {
			int mid = (l + r) >> 1;
			if (key >= map[mid][0]) {
				return binarySearch(l, mid, key);
			} else {
				return binarySearch(mid + 1, r, key);
			}
		}
	}

	static void build(int id, int l, int r) {
		tree[id] = new Node(l, r);
		if (l != r) {
			int mid = (l + r) >> 1;
			build(id << 1, l, mid);
			build(id << 1 | 1, mid + 1, r);
		}
	}

	static int quary(int id, int num) {
		tree[id].num--;
		if (tree[id].l == tree[id].r) {
			return tree[id].l;
		} else {
			if (num > tree[id << 1].num) {
				return quary(id << 1 | 1, num - tree[id << 1].num);
			} else {
				return quary(id << 1, num);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		// BufferedReader in=new BufferedReader(new
		// InputStreamReader(System.in));
		// StringTokenizer st;
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			int n = scan.nextInt();
			int k = scan.nextInt();
			for (int i = 1; i <= n; i++) {
				name[i] = scan.next();
				card[i] = scan.nextInt();
			}
			build(1, 1, n);
			int a = binarySearch(0, map.length - 1, n);
			int pos = quary(1, k);
			for (int i = 1; i < map[a][0]; i++) {
				int t = card[pos] > 0 ? card[pos] - 1 : (card[pos]
						/ -tree[1].num + 1)
						* tree[1].num + card[pos];
				t = (k + t) % tree[1].num;
				k = t == 0 ? tree[1].num : t;
				pos = quary(1, k);
			}
			out.println(name[pos] + " " + map[a][1]);
		}
		out.close();
	}
}