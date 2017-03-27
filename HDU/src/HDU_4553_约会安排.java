import java.io.*;
import java.util.*;

public class HDU_4553_‘ºª·∞≤≈≈{
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
		int ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
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

	static class Node{
		int l,r,ll,rl,ml,lz;

		Node(int l,int r){
			this.l=l;
			this.r=r;
			lz=ll=rl=ml=len();
		}

		int mid(){
			return l+r>>1;
		}

		int len(){
			return r-l+1;
		}

		void chg(int c){
			lz=c;
			if(c==1) ll=rl=ml=0;
			else ll=rl=ml=len();
		}
	}

	static int max(int a,int b){
		return a>b?a:b;
	}

	static void build(Node tree[],int id,int l,int r){
		tree[id]=new Node(l,r);
		if(l==r) return;
		int mid=l+r>>1;
		build(tree,id<<1,l,mid);
		build(tree,id<<1|1,mid+1,r);
	}

	static void pushup(Node tree[],int id){
		tree[id].ll=tree[id<<1].ll;
		tree[id].rl=tree[id<<1|1].rl;
		tree[id].ml=max(tree[id<<1].ml,tree[id<<1|1].ml);
		tree[id].ml=max(tree[id].ml,tree[id<<1].rl+tree[id<<1|1].ll);
		if(tree[id].ll==tree[id<<1].len()) tree[id].ll+=tree[id<<1|1].ll;
		if(tree[id].rl==tree[id<<1|1].len()) tree[id].rl+=tree[id<<1].rl;
	}

	static void pushdown(Node tree[],int id){
		if(tree[id].lz==0) return;
		tree[id<<1|1].chg(tree[id].lz);
		tree[id<<1].chg(tree[id].lz);
		tree[id].lz=0;
	}

	static int query(Node tree[],int id,int k){
		if(tree[id].l==tree[id].r) return tree[id].l;
		pushdown(tree,id);
		if(tree[id<<1].ml>=k) return query(tree,id<<1,k);
		else if(tree[id<<1].rl+tree[id<<1|1].ll>=k) return tree[id].mid()-tree[id<<1].rl+1;
		else return query(tree,id<<1|1,k);
	}

	static void update(Node tree[],int id,int l,int r,int k){
		if(l<=tree[id].l&&tree[id].r<=r) tree[id].chg(k);
		else{
			pushdown(tree,id);
			int mid=tree[id].mid();
			if(l<=mid) update(tree,id<<1,l,r,k);
			if(mid<r) update(tree,id<<1|1,l,r,k);
			pushup(tree,id);
		}
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int tst=1,ttt=nextInt();tst<=ttt;tst++){
			int n=nextInt(),m=nextInt();
			Node[] ns=new Node[n<<2],ds=new Node[n<<2];
			build(ns,1,1,n);
			build(ds,1,1,n);
			out.println("Case "+tst+":");
			for(int i=0;i<m;i++){
				String o=next();
				if(o.charAt(0)=='N'){
					int p=nextInt();
					if(p<=ds[1].ml){
						int q=query(ds,1,p);
						out.println(q+",don't put my gezi");
						update(ds,1,q,q+p-1,1);
						update(ns,1,q,q+p-1,1);
					}else if(p<=ns[1].ml){
						int q=query(ns,1,p);
						out.println(q+",don't put my gezi");
						update(ds,1,q,q+p-1,1);
						update(ns,1,q,q+p-1,1);
					}else out.println("wait for me");
				}else if(o.charAt(0)=='D'){
					int p=nextInt();
					if(p<=ds[1].ml){
						int q=query(ds,1,p);
						out.println(q+",let's fly");
						update(ds,1,q,q+p-1,1);
					}else out.println("fly with yourself");
				}else{
					int l=nextInt(),r=nextInt();
					out.println("I am the hope of chinese chengxuyuan!!");
					update(ds,1,l,r,2);
					update(ns,1,l,r,2);
				}
			}
		}
		out.close();
	}
}