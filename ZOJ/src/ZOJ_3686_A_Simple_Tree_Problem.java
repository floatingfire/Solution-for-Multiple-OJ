import java.io.*;
import java.util.*;

public class ZOJ_3686_A_Simple_Tree_Problem{
	static class Edge{
		Edge n;
		int v;

		Edge(Edge ne,int ve){
			n=ne;
			v=ve;
		}
	}

	static Edge h[];

	static void addedge(int r,int s){
		h[r]=new Edge(h[r],s);
	}

	static class Node{
		int c;
		boolean lazy,clean;

		void chg(int s){
			c=s-c;
			lazy=!lazy;
		}

		void cln(){
			c=0;
			lazy=false;
			clean=true;
		}
	}

	static Node tree[];

	static void pushup(int id){
		tree[id].c=tree[id<<1].c+tree[id<<1|1].c;
	}

	static void pushdown(int id,int l,int mid,int r){
		if(tree[id].clean){
			tree[id].clean=false;
			tree[id<<1].cln();
			tree[id<<1|1].cln();
		}
		if(tree[id].lazy){
			tree[id].lazy=false;
			tree[id<<1].chg(mid-l+1);
			tree[id<<1|1].chg(r-mid);
		}
	}

	static void update(int id,int l,int r,int L,int R){
		if(l<=L&&R<=r){
			tree[id].chg(R-L+1);
		}else{
			int M=L+R>>1;
			pushdown(id,L,M,R);
			if(l<=M){
				update(id<<1,l,r,L,M);
			}
			if(M<r){
				update(id<<1|1,l,r,M+1,R);
			}
			pushup(id);
		}
	}

	static int ans;

	static void query(int id,int l,int r,int L,int R){
		if(l<=L&&R<=r){
			ans+=tree[id].c;
		}else{
			int M=L+R>>1;
			pushdown(id,L,M,R);
			if(l<=M){
				query(id<<1,l,r,L,M);
			}
			if(M<r){
				query(id<<1|1,l,r,M+1,R);
			}
		}
	}

	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static int cnt,lx[],rx[];

	static void dfs(int n){
		boolean rem[]=new boolean[n+1];
		int s[]=new int[n+1],t=0;
		s[t++]=1;
		while(t>0){
			int id=s[t-1];
			if(rem[id]){
				rx[s[--t]]=cnt;
			}else{
				lx[id]=++cnt;
				rem[id]=true;
				for(Edge i=h[id];i!=null;i=i.n){
					s[t++]=i.v;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		// in=new BufferedReader(new FileReader("test"));
		out=new PrintWriter(System.out);
		// out=new PrintWriter(new File("outtest"));
		tree=new Node[100001<<2];
		for(int i=1;i<tree.length;i++){
			tree[i]=new Node();
		}
		String s;
		while((s=in.readLine())!=null){
			stk=new StringTokenizer(s);
			int n=Integer.parseInt(stk.nextToken());
			int m=Integer.parseInt(stk.nextToken());
			h=new Edge[n+1];
			lx=new int[n+1];
			rx=new int[n+1];
			stk=new StringTokenizer(in.readLine());
			for(int i=2;i<=n;i++){
				addedge(Integer.parseInt(stk.nextToken()),i);
			}
			cnt=0;
			dfs(n);
			while(m-->0){
				stk=new StringTokenizer(in.readLine());
				char od=stk.nextToken().charAt(0);
				int id=Integer.parseInt(stk.nextToken());
				if(od=='o'){
					update(1,lx[id],rx[id],1,n);
				}else{
					ans=0;
					query(1,lx[id],rx[id],1,n);
					out.println(ans);
				}
			}
			tree[1].cln();
			out.println();
			out.flush();
		}
		out.close();
	}
}