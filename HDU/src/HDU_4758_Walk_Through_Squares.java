import java.io.*;
import java.util.*;

public class HDU_4758_Walk_Through_Squares{
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

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static class Node{
		int f,o,g[]=new int[K];

		Node(){
			f=-1;
			o=0;
			Arrays.fill(g,-1);
		}
	}

	static int cnt,K=2,M=1000000007;
	static Node tree[];

	static int trans(char c){
		return c=='R'?1:0;
	}

	static void insert(String p,int o){
		int cur=0;
		for(int i=0;i<p.length();i++){
			int c=trans(p.charAt(i));
			if(tree[cur].g[c]==-1) tree[tree[cur].g[c]=cnt++]=new Node();
			cur=tree[cur].g[c];
		}
		tree[cur].o|=o;
	}

	static void build(){
		int cur,q[]=new int[210],h=0,t=0;
		for(int i=0;i<K;i++)
			if(tree[0].g[i]!=-1){
				q[t++]=tree[0].g[i];
				tree[q[t-1]].f=0;
			}else tree[0].g[i]=0;
		while(h<t){
			cur=q[h++];
			for(int i=0;i<K;i++)
				if(tree[cur].g[i]!=-1){
					q[t++]=tree[cur].g[i];
					tree[q[t-1]].f=tree[tree[cur].f].g[i];
					tree[q[t-1]].o|=tree[tree[q[t-1]].f].o;
				}else tree[cur].g[i]=tree[tree[cur].f].g[i];
		}
	}

	static int dp(int m,int n){
		int ans=0,f[][][][]=new int[2][m+1][cnt][4];
		f[0][0][0][0]=1;
		for(int i=0;i<=n;i++){
			for(int j=0;j<=m;j++)
				for(int k=0;k<cnt;k++)
					for(int l=0;l<4;l++){
						if(f[0][j][k][l]==0) continue;
						if(i==n&&j==m&&l==3){
							ans+=f[0][j][k][l];
							ans%=M;
							continue;
						}
						if(i<n){
							int t=tree[k].g[0],x=l|tree[t].o;
							f[1][j][t][x]+=f[0][j][k][l];
							f[1][j][t][x]%=M;
						}
						if(j<m){
							int t=tree[k].g[1],x=l|tree[t].o;
							f[0][j+1][t][x]+=f[0][j][k][l];
							f[0][j+1][t][x]%=M;
						}
					}
			f[0]=f[1];
			f[1]=new int[m+1][cnt][4];
		}
		return ans;
	}

	public static void main(String[] args) throws Exception{
		initio();
		tree=new Node[210];
		for(int tst=nextInt();tst>0;tst--){
			int m=nextInt(),n=nextInt();
			cnt=0;
			tree[cnt++]=new Node();
			insert(next(),1);
			insert(next(),2);
			build();
			out.println(dp(m,n));
		}
		out.flush();
	}
}