import java.io.*;
import java.util.*;

public class POJ_1655_Balancing_Act{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static int Int() throws Exception{
		int ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		else ch-='0';
		for(x=0;ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
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

	static int n,ans,idx;
	static boolean vst[];

	static int dfs(int id){
		int cnt=1,bel=0;
		vst[id]=true;
		for(Edge e=h[id];e!=null;e=e.n){
			if(vst[e.v]) continue;
			int tmp=dfs(e.v);
			cnt+=tmp;
			bel=max(bel,tmp);
		}
		bel=max(bel,n-cnt);
		if(ans>bel){
			ans=bel;
			idx=id;
		}else if(ans==bel&&idx>id) idx=id;
		return cnt;
	}

	static int max(int a,int b){
		return a>b?a:b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int tst=Int();tst>0;tst--){
			h=new Edge[n=Int()];
			for(int i=1;i<n;add(Int()-1,Int()-1),i++);
			vst=new boolean[n];
			ans=idx=1000000000;
			dfs(0);
			out.println(idx+1+" "+ans);
		}
		out.flush();
	}
}
