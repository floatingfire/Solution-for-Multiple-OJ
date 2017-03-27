import java.io.*;
import java.util.*;

public class URAL_1018_Binary_Apple_Tree{
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

	static int max(int a,int b){
		return a>b?a:b;
	}

	static int min(int a,int b){
		return a<b?a:b;
	}

	static class Tree{
		int id,cnt,app;
		Tree l,r;

		Tree(int id,int app){
			this.id=id;
			this.app=app;
		}
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

	static void add(int v,int u,int w){
		h[v]=new Edge(u,w,h[v]);
		h[u]=new Edge(v,w,h[u]);
	}

	static int f[][];
	static boolean vst[];

	static void dfs(Tree t){
		vst[t.id]=true;
		for(Edge e=h[t.id];e!=null;e=e.n){
			if(vst[e.v]) continue;
			if(t.l==null){
				dfs(t.l=new Tree(e.v,e.w));
				t.cnt+=t.l.cnt+1;
			}else{
				dfs(t.r=new Tree(e.v,e.w));
				t.cnt+=t.r.cnt+1;
			}
		}
	}

	static void dp(Tree t){
		if(t.cnt==0) return;
		dp(t.l);
		dp(t.r);
		for(int i=1;i<=t.cnt;i++){
			f[t.id][i]=max(f[t.r.id][i-1]+t.r.app,f[t.l.id][i-1]+t.l.app);
			for(int j=0;j<=i-2;j++)
				f[t.id][i]=max(f[t.id][i],f[t.l.id][j]+f[t.r.id][i-2-j]+t.l.app+t.r.app);
		}
	}

	public static void main(String[] args) throws Exception{
		initio();
		int n=nextInt(),m=nextInt();
		h=new Edge[n];
		for(int i=1;i<n;add(nextInt()-1,nextInt()-1,nextInt()),i++);
		f=new int[n][n];
		vst=new boolean[n];
		Tree rt=new Tree(0,0);
		dfs(rt);
		dp(rt);
		out.println(f[0][m]);
		out.close();
	}
}