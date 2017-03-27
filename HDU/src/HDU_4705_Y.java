import java.io.*;
import java.util.*;

public class HDU_4705_Y{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static void initiot() throws Exception{
		in=new BufferedReader(new FileReader("test"));
		out=new PrintWriter("outtest");
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

	static class Edge{
		int v;
		Edge n;

		Edge(int v,Edge n){
			this.v=v;
			this.n=n;
		}
	}

	static Edge h[];

	static void add(int u,int v){
		h[u]=new Edge(v,h[u]);
		h[v]=new Edge(u,h[v]);
	}

	static long ans;
	static int n,cnt[];

	static void dfs(int id,int p){
		for(Edge e=h[id];e!=null;e=e.n){
			if(e.v==p) continue;
			dfs(e.v,id);
			ans-=(long)cnt[id]*cnt[e.v];
			cnt[id]+=cnt[e.v];
		}
		ans-=(long)cnt[id]*(n-++cnt[id]);
	}

	public static void main(String[] args) throws Exception{
		initio();
		String ss;
		while((ss=in.readLine())!=null){
			stk=new StringTokenizer(ss);
			h=new Edge[n=nextInt()];
			for(int i=1;i<n;add(nextInt()-1,nextInt()-1),i++);
			cnt=new int[n];
			ans=(long)n*(n-1)*(n-2)/6;
			dfs(0,-1);
			out.println(ans);
		}
		out.close();
	}
}
