import java.io.*;
import java.util.*;

public class POJ_1741_Tree{
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

	static int n,k,ans,tai,dis[],siz[];
	static boolean vst[];

	static void initsiz(int id,int pa){
		siz[id]=1;
		for(Edge e=h[id];e!=null;e=e.n){
			if(vst[e.v]||e.v==pa) continue;
			initsiz(e.v,id);
			siz[id]+=siz[e.v];
		}
	}

	static int getrt(int id){
		initsiz(id,-1);
		int t=0,s[]=new int[siz[id]];
		boolean b[]=new boolean[n];
		int dev=0x7fffffff,idx=-1,tmp;
		s[t++]=id;
		while(t!=0){
			b[tmp=s[--t]]=true;
			int x=max(0,siz[id]-siz[tmp]);
			for(Edge e=h[tmp];e!=null;e=e.n){
				if(vst[e.v]||b[e.v]) continue;
				x=max(x,siz[e.v]);
				s[t++]=e.v;
			}
			if(dev>x){
				dev=x;
				idx=tmp;
			}
		}
		return idx;
	}

	static void getdis(int id,int pa,int d){
		dis[tai++]=d;
		for(Edge e=h[id];e!=null;e=e.n)
			if(!vst[e.v]&&e.v!=pa) getdis(e.v,id,d+e.w);
	}

	static void cnt(int id){
		vst[id]=true;
		for(Edge e=h[id];e!=null;e=e.n){
			if(vst[e.v]) continue;
			int t=tai;
			getdis(e.v,id,e.w);
			Arrays.sort(dis,t,tai);
			for(int i=t,j=tai-1;i<j;ans-=j-i,i++)
				for(;i<j&&dis[i]+dis[j]>k;j--);
		}
		Arrays.sort(dis,0,tai);
		for(int i=0,j=tai-1;i<j;ans+=j-i,i++)
			for(;i<j&&dis[i]+dis[j]>k;j--);
		for(;tai>0;ans+=dis[tai--]>k?0:1);
	}

	static void dp(int id){
		cnt(id=getrt(id));
		for(Edge e=h[id];e!=null;e=e.n)
			if(!vst[e.v]) dp(e.v);
	}

	static int max(int a,int b){
		return a>b?a:b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		siz=new int[10000];
		while((n=Int())!=0|(k=Int())!=0){
			h=new Edge[n];
			for(int i=1;i<n;add(Int()-1,Int()-1,Int()),i++);
			dis=new int[n];
			vst=new boolean[n];
			dp(ans=tai=0);
			out.println(ans);
		}
		out.flush();
	}
}
