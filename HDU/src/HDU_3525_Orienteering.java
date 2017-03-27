import java.io.*;
import java.util.*;

public class HDU_3525_Orienteering{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static int max(int a,int b){
		return a>b?a:b;
	}

	static int tree[],n;

	static void update(int pos,int val){
		for(;pos<=n;pos+=-pos&pos)
			tree[pos]=max(tree[pos],val);
	}

	static int query(int pos){
		int ans=0;
		for(;pos>0;pos-=-pos&pos)
			ans=max(ans,tree[pos]);
		return ans;
	}

	static int nx[];

	static void add(int cnt[],int val,int id){
		int x;
		for(x=id;cnt[x]!=0;x=nx[x]);
		cnt[x]=val;
		for(int tmp;id!=x;tmp=nx[id],nx[id]=nx[x],id=tmp);
	}

	static class LNode{
		int val;
		LNode nxt;

		LNode(int v,LNode n){
			val=v;
			nxt=n;
		}
	}

	public static void main(String[] args) throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
		for(int tst=1,ttt=nextInt();tst<=ttt;tst++){
			n=nextInt()*nextInt();
			int[] t=new int[n<<1],p=new int[n<<1];
			nx=new int[n<<1];
			for(int i=0;i<nx.length;i++)
				nx[i]=i+1;
			for(int i=0;i<n;i++)
				add(t,nextInt(),nextInt());
			for(int i=0;i<nx.length;i++)
				nx[i]=i+1;
			for(int i=0;i<n;i++)
				add(p,nextInt(),nextInt());
			LNode map[]=new LNode[10001];
			int cnt=0;
			for(int i:p){
				if(i==0) continue;
				if(++cnt>n) break;
				map[i]=new LNode(cnt,map[i]);
			}
			cnt=0;
			tree=new int[n<<2];
			int hd,tl,q[]=new int[n<<1];
			for(int i:t){
				if(i==0) continue;
				if(++cnt>n) break;
				tl=hd=0;
				for(LNode j=map[i];j!=null;j=j.nxt)
					q[tl++]=query(j.val-1)+1;
				for(LNode j=map[i];j!=null;j=j.nxt)
					update(j.val,q[hd++]);
			}
			out.println("Case #"+tst+": "+query(n));
		}
		out.close();
	}
}