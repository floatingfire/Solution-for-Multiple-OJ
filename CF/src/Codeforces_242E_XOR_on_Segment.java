import java.io.*;
import java.util.*;

public class Codeforces_242E_XOR_on_Segment{
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

	static long nextLong() throws Exception{
		return Long.parseLong(next());
	}

	static final int M=20;

	static class Node{
		int l,r,lz,bit[]=new int[M];

		Node(int l,int r){
			this.l=l;
			this.r=r;
		}

		int mid(){
			return l+r>>1;
		}

		int len(){
			return r-l+1;
		}

		void init(int num){
			for(int i=0;i<M;bit[i++]=num&1,num>>=1);
		}

		void chg(int v){
			lz^=v;
			for(int i=0;i<M;i++,v>>=1)
				if((v&1)==1) bit[i]=len()-bit[i];
		}

		long sum(){
			long ans=0;
			for(int i=0;i<M;ans+=(long)bit[i]<<i,i++);
			return ans;
		}
	}

	static Node tree[];
	static int num[];

	static void build(int id,int l,int r){
		tree[id]=new Node(l,r);
		if(l==r) tree[id].init(num[l-1]);
		else{
			int mid=tree[id].mid();
			build(id<<1,l,mid);
			build(id<<1|1,mid+1,r);
			pushup(id);
		}
	}

	static void pushup(int id){
		for(int i=0;i<M;tree[id].bit[i]=tree[id<<1].bit[i]+tree[id<<1|1].bit[i],i++);
	}

	static void pushdown(int id){
		if(tree[id].lz==0) return;
		tree[id<<1].chg(tree[id].lz);
		tree[id<<1|1].chg(tree[id].lz);
		tree[id].lz=0;
	}

	static void update(int id,int l,int r,int v){
		if(l<=tree[id].l&&tree[id].r<=r) tree[id].chg(v);
		else{
			pushdown(id);
			int mid=tree[id].mid();
			if(l<=mid) update(id<<1,l,r,v);
			if(mid<r) update(id<<1|1,l,r,v);
			pushup(id);
		}
	}

	static long query(int id,int l,int r){
		if(l<=tree[id].l&&tree[id].r<=r) return tree[id].sum();
		pushdown(id);
		int mid=tree[id].mid();
		if(mid<l) return query(id<<1|1,l,r);
		if(r<=mid) return query(id<<1,l,r);
		return query(id<<1|1,l,r)+query(id<<1,l,r);
	}

	public static void main(String[] args) throws Exception{
		initio();
		num=new int[nextInt()];
		tree=new Node[num.length<<2];
		for(int i=0;i<num.length;num[i++]=nextInt());
		build(1,1,num.length);
		for(int m=nextInt();m>0;m--){
			if(nextInt()==1) out.println(query(1,nextInt(),nextInt()));
			else update(1,nextInt(),nextInt(),nextInt());
		}
		out.close();
	}
}