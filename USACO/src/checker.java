import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: checker
 */
public class checker {
	static StreamTokenizer in;
	static PrintWriter out;
	static int n, cnt = 0, upl,con[];

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int getpos(int p){
		int cnt=0;
		while(p>0){
			cnt++;
			p>>=1;
		}
		return cnt;
	}
	static void dfs(int id, int col, int ld, int rd) {
		if (id == n) {
			if (cnt++ < 3) {
				out.print(con[0]);
				for (int i = 1; i < n; i++) {
					out.print(" " + con[i]);
				}
				out.println();
			}
		} else {
			int pos = ~(col | ld | rd) & upl;
			while (pos != 0) {
				int p=-pos & pos;
				if (cnt < 3) {
					con[id] = getpos(p);
				}
				pos -= p;
				dfs(id+1,col|p,(ld|p)<<1,(rd|p)>>1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new BufferedReader(new FileReader("checker.in")));
		out = new PrintWriter(new BufferedWriter(new FileWriter("checker.out")));
		n = nextInt();
		upl=(1<<n)-1;
		con = new int[n];
		dfs(0,0,0,0);
		out.println(cnt);
		out.close();
	}
}
