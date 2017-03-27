import java.io.*;
import java.util.*;

public class Solution{
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

	static int Int() throws Exception{
		int ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	static class Edge{
		int v;
		Edge nxt;

		Edge(int v,Edge nxt){
			this.v=v;
			this.nxt=nxt;
		}
	}

	static Edge h[];

	static void add(int s,int e){
		h[s]=new Edge(e,h[s]);
	}

	static int n,f[],g[],cnt,rem[];

	static void dfs(int rt){
		int id=-1,max=0;
		g[rt]=1;
		for(Edge e=h[rt];e!=null;e=e.nxt){
			dfs(e.v);
			g[rt]+=f[e.v];
			if(max<g[e.v]-f[e.v]){
				max=g[e.v]-f[e.v];
				id=e.v;
			}
		}
		f[rt]=g[rt]-1+max;
		rem[cnt++]=id+1;
	}

	public static void main(String[] args) throws Exception{
		initio();
		n=Int();
		h=new Edge[n];
		f=new int[n];
		g=new int[n];
		rem=new int[n];
		for(int i=1;i<n;add(Int()-1,i++));
		dfs(cnt=0);
		out.println(f[0]*1000);
		Arrays.sort(rem,0,cnt);
		for(int i=0;i<cnt;i++)
			if(rem[i]!=0) out.print(rem[i]+" ");
		out.close();
	}
}