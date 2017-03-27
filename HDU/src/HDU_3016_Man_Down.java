import java.io.*;
import java.util.*;

public class HDU_3016_Man_Down{
	static BufferedReader in;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static int Int() throws Exception{
		int ch,x,s=1;
		for(ch=in.read();ch<'0'&&ch!=-1&&ch!='-'||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		if(ch=='-'){
			s=-1;
			ch=in.read();
		}
		for(x=0;ch>='0'&&ch<='9';x=x*10+ch-'0',ch=in.read());
		return x*s;
	}

	static class Plank implements Comparable<Plank>{
		int h,l,r,v;

		Plank(int h,int l,int r,int v){
			this.h=h;
			this.l=l;
			this.r=r;
			this.v=v;
		}

		public int compareTo(Plank p){
			return h-p.h;
		}
	}
	static class Node{
		int l,r,c,lz;

		Node(int l,int r){
			this.l=l;
			this.r=r;
		}

		int mid(){
			return l+r>>1;
		}

		void chg(int c){
			this.c=lz=c;
		}
	}

	static Node[] tree=new Node[400000];

	static void build(int id,int l,int r){
		tree[id]=new Node(l,r);
		if(l==r) return;
		int mid=tree[id].mid();
		build(id<<1,l,mid);
		build(id<<1|1,mid+1,r);
	}

	static void pushup(int id){
		if(tree[id<<1].c==tree[id<<1|1].c) tree[id].c=tree[id<<1].c;
		else tree[id].c=-1;
	}

	static void pushdown(int id){
		if(tree[id].lz!=0){
			tree[id<<1].chg(tree[id].lz);
			tree[id<<1|1].chg(tree[id].lz);
			tree[id].lz=0;
		}
	}

	static void update(int id,Plank p,int c){
		if(p.l<=tree[id].l&&tree[id].r<=p.r) tree[id].chg(c);
		else{
			pushdown(id);
			int mid=tree[id].mid();
			if(p.l<=mid) update(id<<1,p,c);
			if(mid<p.r) update(id<<1|1,p,c);
			pushup(id);
		}
	}

	static int query(int id,int x){
		if(tree[id].c!=-1) return tree[id].c;
		pushdown(id);
		if(x>tree[id].mid()) return query(id<<1|1,x);
		else return query(id<<1,x);
	}

	static int max(int a,int b){
		return a>b?a:b;
	}

	static int min(int a,int b){
		return a<b?a:b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		int n,i,l,r,f[],rm[][]=new int[100002][2];
		Plank[] p=new Plank[100002];
		while((n=Int())!=-1){
			for(p[l=r=0]=new Plank(0,0,100000,0),i=1;i<=n;i++){
				p[i]=new Plank(Int(),Int(),Int(),Int());
				l=min(l,p[i].l);
				r=max(r,p[i].r);
			}
			Arrays.sort(p,0,n+1);
			build(1,l,r);
			for(i=1;i<=n;i++){
				rm[i][0]=query(1,p[i].l);
				rm[i][1]=query(1,p[i].r);
				update(1,p[i],i);
			}
			f=new int[n+1];
			for(f[i=n]=100+p[i].v;i>0;i--){
				if(f[i]==0) continue;
				f[rm[i][0]]=max(f[rm[i][0]],f[i]+p[rm[i][0]].v);
				f[rm[i][1]]=max(f[rm[i][1]],f[i]+p[rm[i][1]].v);
			}
			out.println(f[0]>0?f[0]:-1);
			out.flush();
		}
		out.close();
	}
}