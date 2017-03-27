import java.io.*;
import java.util.*;

public class HDU_2818_Building_Block{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static long nextLong() throws Exception{
		return Long.parseLong(next());
	}

	static int f[],g[],h[];

	static int find(int a){
		if(f[a]!=a){
			int aa=find(f[a]);
			g[a]+=g[f[a]];
			f[a]=aa;
		}
		return f[a];
	}

	static int count(int a){
		find(a);
		return g[a];
	}

	static void union(int a,int b){
		int aa=find(a),bb=find(b);
		if(aa==bb) return;
		g[aa]+=h[bb];
		h[bb]+=h[aa];
		f[aa]=bb;
	}

	static void init(int n){
		f=new int[n];
		g=new int[n];
		h=new int[n];
		for(int i=0;i<n;f[i]=i,h[i++]=1);
	}

	public static void main(String[] args) throws Exception{
		initio();
		init(30001);
		for(int i=nextInt();i>0;i--){
			if(next().charAt(0)=='M') union(nextInt(),nextInt());
			else out.println(count(nextInt()));
		}
		out.close();
	}
}