import java.util.*;
import java.io.*;

public class HDU_4679_Terrorists_destroy{
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

	static class Edge{
		int v,w,id;
		Edge n;

		Edge(int v,int w,int id,Edge n){
			this.v=v;
			this.w=w;
			this.id=id;
			this.n=n;
		}
	}

	static Edge h[];

	static void add(int u,int v,int w,int id){
		h[u]=new Edge(v,w,id,h[u]);
		h[v]=new Edge(u,w,id,h[v]);
	}

	static int n,ans,idx,r[][],f[][],s[][];
	static boolean vst[];

	static void updatef(int id,int val){
		if(f[id][0]<=val){
			f[id][2]=f[id][1];
			f[id][1]=f[id][0];
			f[id][0]=val;
		}else if(f[id][1]<=val){
			f[id][2]=f[id][1];
			f[id][1]=val;
		}else if(f[id][2]<val) f[id][2]=val;
	}

	static void updates(int id,int val){
		if(s[id][0]<=val){
			s[id][1]=s[id][0];
			s[id][0]=val;
		}else if(s[id][1]<val) s[id][1]=val;
	}

	static void dfs(int id){
		vst[id]=true;
		for(Edge e=h[id];e!=null;e=e.n){
			if(vst[e.v]) continue;
			dfs(e.v);
			updatef(id,f[e.v][0]+1);
			updates(id,r[e.v][0]);
			r[id][0]=max(r[id][0],r[e.v][0]);
		}
		r[id][0]=max(r[id][0],f[id][0]+f[id][1]);
	}

	static void dp(int id){
		vst[id]=true;
		for(Edge e=h[id];e!=null;e=e.n){
			if(vst[e.v]) continue;
			if(r[e.v][0]==s[id][0]) r[e.v][1]=max(r[id][1],s[id][1]);
			else r[e.v][1]=max(r[id][1],s[id][0]);
			if(f[e.v][0]+1==f[id][0]){
				r[e.v][1]=max(r[e.v][1],f[id][1]+f[id][2]);
				updatef(e.v,f[id][1]+1);
			}else{
				if(f[e.v][0]+1==f[id][1]) r[e.v][1]=max(r[e.v][1],f[id][0]+f[id][2]);
				else r[e.v][1]=max(r[e.v][1],f[id][0]+f[id][1]);
				updatef(e.v,f[id][0]+1);
			}
			int x=e.w*max(r[e.v][0],r[e.v][1]);
			if(ans>x){
				ans=x;
				idx=e.id;
			}else if(ans==x&&idx>e.id) idx=e.id;
			dp(e.v);
		}
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int tst=1,ttt=nextInt();tst<=ttt;tst++){
			h=new Edge[n=nextInt()];
			for(int i=1;i<n;add(nextInt()-1,nextInt()-1,nextInt(),i++));
			f=new int[n][3];
			s=new int[n][2];
			r=new int[n][2];
			vst=new boolean[n];
			dfs(0);
			ans=idx=0x7fffffff;
			vst=new boolean[n];
			dp(0);
			out.println("Case #"+tst+": "+idx);
		}
		out.flush();
	}
}
