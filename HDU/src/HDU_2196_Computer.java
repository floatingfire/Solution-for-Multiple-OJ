import java.io.*;
import java.util.*;

public class HDU_2196_Computer{
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

	static int Int() throws Exception{
		int ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		else ch-='0';
		for(x=0;ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static class Edge{
		int v,w;
		Edge n;

		Edge(int v,int w,Edge n){
			this.v=v;
			this.w=w;
			this.n=n;
		}
	}

	static Edge h[];

	static void add(int u,int v,int w){
		h[u]=new Edge(v,w,h[u]);
		h[v]=new Edge(u,w,h[v]);
	}

	static int n,f[][];
	static boolean vst[];

	static void update(int id,int val){
		if(f[id][0]<val){
			f[id][1]=f[id][0];
			f[id][0]=val;
		}else if(f[id][1]<val){
			f[id][1]=val;
		}
	}

	static void dfs(int id){
		vst[id]=true;
		for(Edge e=h[id];e!=null;e=e.n){
			if(vst[e.v]) continue;
			dfs(e.v);
			update(id,f[e.v][0]+e.w);
		}
	}

	static void dp(int id){
		vst[id]=true;
		for(Edge e=h[id];e!=null;e=e.n){
			if(vst[e.v]) continue;
			if(f[id][0]==f[e.v][0]+e.w) update(e.v,f[id][1]+e.w);
			else update(e.v,f[id][0]+e.w);
			dp(e.v);
		}
	}

	static int max(int a,int b){
		return a>b?a:b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		while((n=Int())!=-1){
			h=new Edge[n];
			for(int i=1;i<n;add(Int()-1,i++,Int()));
			f=new int[n][2];
			vst=new boolean[n];
			dfs(0);
			vst=new boolean[n];
			dp(0);
			for(int i=0;i<n;out.println(f[i++][0]));
		}
		out.flush();
	}
}