import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: money
 */
public class money {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int v, n, max, dic[], up[];
	static long f[];

	static void dp() {
		f=new long[n+1];
		f[0]=1;
		for(int i=0;i<v;i++){
			for(int j=dic[i];j<=n;j++){
				f[j]+=f[j-dic[i]];
			}
		}
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("money.in"));
		out = new PrintWriter(new FileWriter("money.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(new OutputStreamWriter(System.out));
		v = nextInt();
		n = nextInt();
		dic = new int[v];
		up = new int[v];
		max = 0;
		for (int i = 0; i < v; i++) {
			dic[i] = nextInt();
			up[i] = n / dic[i];
			max = Math.max(max, up[i]);
		}
		dp();
		out.println(f[n]);
		out.close();
	}
}