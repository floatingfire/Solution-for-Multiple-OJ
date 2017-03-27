import java.io.*;

public class HDU_1166_敌兵布阵{
	public static void main(String[] args) throws Exception{
		FastInput in=new FastInput();
		PrintWriter out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int t=in.nextInt();
		for(int c=1;c<=t;c++){
			out.println("Case "+c+":");
			int n=in.nextInt();
			int val[]=new int[n+1];
			for(int i=1;i<=n;i++){
				val[i]=in.nextInt();
			}
			SegmentTree smt=new SegmentTree(val,n);
			for(String str=in.next();str.charAt(0)!='E';str=in.next()){
				if(str.charAt(0)=='A'){
					smt.update(1,in.nextInt(),in.nextInt());
				}else if(str.charAt(0)=='S'){
					smt.update(1,in.nextInt(),-in.nextInt());
				}else{
					out.println(smt.quary(1,in.nextInt(),in.nextInt()));
				}
			}
		}
		out.close();
	}

	static class FastInput{
		private StreamTokenizer in;

		public FastInput(){
			in=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		}

		public int nextInt() throws Exception{
			in.nextToken();
			return (int)in.nval;
		}

		public long nextLong() throws Exception{
			in.nextToken();
			return (long)in.nval;
		}

		public double nextDouble() throws Exception{
			in.nextToken();
			return in.nval;
		}

		public String next() throws Exception{
			in.nextToken();
			return in.sval;
		}
	}
	static class SegmentTree{// 维护区间和
		class Node{
			int l,r,sum,lazy;

			Node(int l,int r){
				this.l=l;
				this.r=r;
			}
		}

		Node tree[];
		int value[];

		public SegmentTree(int val[],int n){
			tree=new Node[n*3];
			value=val;
			build(1,1,n);
		}

		void build(int id,int left,int right){
			tree[id]=new Node(left,right);
			if(left==right){
				tree[id].sum=value[left];
			}else{
				int mid=(left+right)>>1;
				build(id<<1,left,mid);
				build(id<<1|1,mid+1,right);
				pushup(id);
			}
		}

		int quary(int id,int left,int right){
			int ans=0;
			if(left<=tree[id].l&&tree[id].r<=right){
				ans=tree[id].sum;
			}else{
				pushdown(id);
				int mid=(tree[id].l+tree[id].r)>>1;
				if(left<=mid){
					ans+=quary(id<<1,left,right);
				}
				if(mid<right){
					ans+=quary(id<<1|1,left,right);
				}
			}
			return ans;
		}

		void update(int id,int pos,int key){
			if(pos<=tree[id].l&&tree[id].r<=pos){
				tree[id].sum+=key;
				tree[id].lazy+=key;
			}else{
				pushdown(id);
				int mid=(tree[id].l+tree[id].r)>>1;
				if(pos<=mid){
					update(id<<1,pos,key);
				}else{
					update(id<<1|1,pos,key);
				}
				pushup(id);
			}
		}

		void pushup(int id){
			if(tree[id].l!=tree[id].r){
				tree[id].sum=tree[id<<1].sum+tree[id<<1|1].sum;
			}
		}

		void pushdown(int id){
			if(tree[id].l!=tree[id].r){
				tree[id<<1].sum+=tree[id].lazy;
				tree[id<<1].lazy+=tree[id].lazy;
				tree[id<<1|1].sum+=tree[id].lazy;
				tree[id<<1|1].lazy+=tree[id].lazy;
				tree[id].lazy=0;
			}
		}
	}
}
