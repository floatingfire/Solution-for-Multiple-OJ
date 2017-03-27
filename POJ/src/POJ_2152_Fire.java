import java.io.*;
import java.util.*;

public class POJ_2152_Fire{
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

	static int min(int a,int b){
		return a<b?a:b;
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

	static int n,w[],d[],b[],f[][],map[][];

	static void init() throws Exception{
		w=new int[n=Int()];
		d=new int[n];
		h=new Edge[n];
		map=new int[n][n];
		f=new int[n][n];
		b=new int[n];
	}

	static void dfs(){
		int t=0,s[]=new int[n];
		for(int i=0,id;i<n;i++){
			s[t++]=i;
			boolean vst[]=new boolean[n];
			while(t!=0){
				vst[id=s[--t]]=true;
				for(Edge e=h[id];e!=null;e=e.n){
					if(vst[e.v]) continue;
					map[i][e.v]=map[i][id]+e.w;
					s[t++]=e.v;
				}
			}
		}
	}

	static void dp(int id,int pa){
		for(Edge e=h[id];e!=null;e=e.n)
			if(e.v!=pa) dp(e.v,id);
		b[id]=0x7fffffff;
		for(int i=0;i<n;b[id]=min(b[id],f[id][i++]))
			if(map[id][i]>d[id]) f[id][i]=0x7fffffff;
			else{
				f[id][i]=w[i];
				for(Edge e=h[id];e!=null;e=e.n)
					if(e.v!=pa) f[id][i]+=min(f[e.v][i]-w[i],b[e.v]);
			}
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int tst=Int();tst>0;tst--){
			init();
			for(int i=0;i<n;w[i++]=Int());
			for(int i=0;i<n;d[i++]=Int());
			for(int i=1;i<n;add(Int()-1,Int()-1,Int()),i++);
			dfs();
			dp(0,-1);
			out.println(b[0]);
		}
		out.flush();
	}
}
