import java.io.*;
import java.util.*;

public class HDU_1520_Anniversary_party{
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

	static void closeio() throws Exception{
		in.close();
		out.close();
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

	static boolean add(int e,int s){
		if(s+e<0) return false;
		h[s]=new Edge(e,h[s]);
		h[e]=new Edge(s,h[e]);
		return true;
	}

	static int n,c[],f[][];
	static boolean vst[];

	static void dp(int id){
		f[id][1]=c[id];
		vst[id]=true;
		for(Edge e=h[id];e!=null;e=e.n){
			if(vst[e.v]) continue;
			dp(e.v);
			f[id][1]+=f[e.v][0];
			f[id][0]+=max(f[e.v][1],f[e.v][0]);
		}
	}

	static int max(int a,int b){
		return a>b?a:b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		while((n=Int())!=-1){
			c=new int[n];
			for(int i=0;i<n;c[i++]=Int());
			h=new Edge[n];
			while(add(Int()-1,Int()-1));
			f=new int[n][2];
			vst=new boolean[n];
			dp(0);
			out.println(max(f[0][0],f[0][1]));
		}
		closeio();
	}
}
