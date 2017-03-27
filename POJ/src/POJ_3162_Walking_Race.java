import java.util.*;
import java.io.*;

public class POJ_3162_Walking_Race{
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

	static int n,max[],min[];
	static long f[],g[];
	static boolean vst[];

	static void update(int id,long val){
		if(f[id]<=val){
			g[id]=f[id];
			f[id]=val;
		}else if(g[id]<val) g[id]=val;
	}

	static void dfs(int id){
		vst[id]=true;
		for(Edge e=h[id];e!=null;e=e.n){
			if(vst[e.v]) continue;
			dfs(e.v);
			update(id,f[e.v]+e.w);
		}
	}

	static void dp(int id){
		vst[id]=true;
		for(Edge e=h[id];e!=null;e=e.n){
			if(vst[e.v]) continue;
			if(f[e.v]+e.w==f[id]) update(e.v,g[id]+e.w);
			else update(e.v,f[id]+e.w);
			dp(e.v);
		}
	}

	static void pushup(int id){
		if(f[max[id<<1]]>f[max[id<<1|1]]) max[id]=max[id<<1];
		else max[id]=max[id<<1|1];
		if(f[min[id<<1]]<f[min[id<<1|1]]) min[id]=min[id<<1];
		else min[id]=min[id<<1|1];
	}

	static void build(int id,int l,int r){
		if(l==r) max[id]=min[id]=l;
		else{
			int mid=l+r>>1;
			build(id<<1,l,mid);
			build(id<<1|1,mid+1,r);
			pushup(id);
		}
	}

	static int getmax(int id,int l,int r,int ll,int rr){
		if(l<=ll&&rr<=r) return max[id];
		int mid=ll+rr>>1;
		if(mid<l) return getmax(id<<1|1,l,r,mid+1,rr);
		if(mid>=r) return getmax(id<<1,l,r,ll,mid);
		int x=getmax(id<<1,l,r,ll,mid),y=getmax(id<<1|1,l,r,mid+1,rr);
		return f[x]>f[y]?x:y;
	}

	static int getmin(int id,int l,int r,int ll,int rr){
		if(l<=ll&&rr<=r) return min[id];
		int mid=ll+rr>>1;
		if(mid<l) return getmin(id<<1|1,l,r,mid+1,rr);
		if(mid>=r) return getmin(id<<1,l,r,ll,mid);
		int x=getmin(id<<1,l,r,ll,mid),y=getmin(id<<1|1,l,r,mid+1,rr);
		return f[x]<f[y]?x:y;
	}

	public static void main(String[] args) throws Exception{
		initio();
		h=new Edge[n=nextInt()];
		int m=nextInt();
		for(int i=1;i<n;add(i++,nextInt()-1,nextInt()));
		f=new long[n];
		g=new long[n];
		vst=new boolean[n];
		dfs(0);
		vst=new boolean[n];
		dp(0);
		max=new int[n<<2];
		min=new int[n<<2];
		build(1,0,n-1);
		int ans=0,l=0,r=0;
		while(true){
			for(;r<n&&f[getmax(1,l,r,0,n-1)]-f[getmin(1,l,r,0,n-1)]<=m;r++);
			if(ans<r-l) ans=r-l;
			if(r==n) break;
			for(l++;f[getmax(1,l,r,0,n-1)]-f[getmin(1,l,r,0,n-1)]>m;l++);
		}
		out.println(ans);
		out.flush();
	}
}