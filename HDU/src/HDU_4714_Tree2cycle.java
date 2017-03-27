import java.io.*;
import java.util.*;

public class HDU_4714_Tree2cycle{
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

	static final int M=0x7fffffff;
	static int n,s[],f[][];

	static void dfs(){
		int id,t=1;
		int vst[]=new int[n];
		while(t!=0){
			if(vst[id=s[t-1]]++==1){
				int a=M,b=M;
				for(Edge e=h[s[--t]];e!=null;e=e.n){
					if(vst[e.v]==1) continue;
					f[1][id]+=f[0][e.v]+1;
					int x=f[1][e.v]-f[0][e.v];
					if(x<a){
						b=a;
						a=x;
					}else if(x<b) b=x;
				}
				if(a==M) continue;
				f[0][id]=f[1][id]+=a-1;
				if(b==M) continue;
				f[0][id]+=b-1;
			}else{
				for(Edge e=h[id];e!=null;e=e.n)
					if(vst[e.v]==0) s[t++]=e.v;
			}
		}
	}

	public static void main(String[] args) throws Exception{
		initio();
		s=new int[1000000];
		for(int tst=nextInt();tst>0;tst--){
			h=new Edge[n=nextInt()];
			for(int i=1;i<n;i++)
				add(nextInt()-1,nextInt()-1);
			f=new int[2][n];
			dfs();
			out.println(f[0][0]<<1|1);
		}
		out.flush();
	}
}