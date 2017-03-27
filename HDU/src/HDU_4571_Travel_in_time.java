import java.io.*;
import java.util.*;

public class HDU_4571_Travel_in_time{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static void initiot() throws Exception{
		in=new BufferedReader(new FileReader("test"));
		out=new PrintWriter(new File("outtest"));
		// out=new PrintWriter(System.out);
	}

	static int Int() throws Exception{
		int ch,x,s=1;
		for(ch=in.read();ch<'0'&&ch!=-1&&ch!='-'||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		if(ch=='-'){
			s=-1;
			ch=in.read();
		}
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x*s;
	}

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static class Node implements Comparable<Node>{
		int id,val;

		Node(int id,int val){
			this.id=id;
			this.val=val;
		}

		public int compareTo(Node n){
			return val-n.val;
		}
	}
	static class Spot implements Comparable<Spot>{
		int id,c,v;

		Spot(int id){
			this.id=id;
		}

		public int compareTo(Spot s){
			return v-s.v;
		}
	}
	static class Edge{
		int id,val;
		Edge nxt;

		Edge(int id,int val,Edge nxt){
			this.id=id;
			this.val=val;
			this.nxt=nxt;
		}
	}

	static Edge h[];

	static void add(int u,int v,int w){
		h[u]=new Edge(v,w,h[u]);
		h[v]=new Edge(u,w,h[v]);
	}

	static int n;

	static int[] bfs(int rt){
		int ans[]=new int[n];
		Arrays.fill(ans,1000000000);
		PriorityQueue<Node> q=new PriorityQueue<Node>();
		q.add(new Node(rt,ans[rt]=0));
		while(!q.isEmpty()){
			Node tmp=q.poll();
			for(Edge e=h[tmp.id];e!=null;e=e.nxt){
				if(ans[e.id]<=ans[tmp.id]+e.val) continue;
				ans[e.id]=ans[tmp.id]+e.val;
				q.add(new Node(e.id,ans[e.id]));
			}
		}
		return ans;
	}

	static int max(int a,int b){
		return a>b?a:b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int ans,i,l,tst=1,ttt=Int();tst<=ttt;tst++){
			Spot s[]=new Spot[n=Int()];
			int m=Int(),t=Int(),st=Int(),ed=Int();
			for(i=0;i<n;s[i]=new Spot(i),i++);
			for(i=0;i<n;s[i++].c=Int());
			for(i=0;i<n;s[i++].v=Int());
			for(h=new Edge[n],i=0;i<m;add(Int(),Int(),Int()),i++);
			int[][] f=new int[n][t+1],map=new int[n][];
			for(i=0;i<n;map[i]=bfs(i),i++);
			Arrays.sort(s);
			out.println("Case #"+tst+":");
			if(map[st][ed]>t){
				out.println(0);
				continue;
			}
			for(i=0;i<n;Arrays.fill(f[i++],-1));
			for(ans=i=0;i<n;i++){
				if((l=map[st][s[i].id]+s[i].c)<=t) f[s[i].id][l]=s[i].v;
				if(l+map[s[i].id][ed]<=t) ans=max(ans,f[s[i].id][l]);
			}
			for(i=0;i<n;i++)
				for(int j=t;j>=0;j--){
					for(int k=0;k<i;k++)
						if((l=map[s[k].id][s[i].id]+s[i].c)<=j&&f[s[k].id][j-l]!=-1) f[s[i].id][j]=max(
								f[i][j],f[s[k].id][j-l]+s[i].v);
					if(j+map[s[i].id][ed]<=t) ans=max(ans,f[s[i].id][j]);
				}
			out.println(ans);
		}
		out.close();
	}
}