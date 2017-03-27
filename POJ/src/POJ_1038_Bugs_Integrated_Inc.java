import java.io.*;
import java.util.*;

public class POJ_1038_Bugs_Integrated_Inc{
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

	static int n,m,f[][],p[],q[];
	static boolean stu[][];
	static final int bs[]={1,3,9,27,81,243,729,2187,6561,19683,59049};

	static int max(int a,int b){
		return a>b?a:b;
	}

	static void dfs(int i,int j,int t,int cnt){
		if(j>=m) return;
		f[(i+1)&1][t]=max(f[(i+1)&1][t],f[i&1][zip(p)]);
		dfs(i,j+1,t,cnt);
		if(j<m-2&&q[j]==0&&q[j+1]==0&&q[j+2]==0){
			int c=t+(bs[j]+bs[j+1]+bs[j+2]<<1);
			f[(i+1)&1][c]=max(f[(i+1)&1][c],cnt+1);
			dfs(i,j+3,c,cnt+1);
		}
		if(j<m-1&&q[j]==0&&q[j+1]==0&&p[j]==0&&p[j+1]==0){
			int c=t+(bs[j]+bs[j+1]<<1);
			f[(i+1)&1][c]=max(f[(i+1)&1][c],cnt+1);
			dfs(i,j+2,c,cnt+1);
		}
	}

	static int zip(int a[]){
		int i,ans;
		for(ans=i=0;i<m;ans+=a[i]*bs[i],i++);
		return ans;
	}

	static void uzp(int a[],int x){
		for(int i=0;i<m;a[i++]=x%3,x/=3);
	}

	static int nxt(boolean c[],int j){
		uzp(p,j);
		for(int i=0;i<m;i++)
			if(c[i]) q[i]=2;
			else if(p[i]==2) q[i]=1;
			else q[i]=0;
		return zip(q);
	}

	public static void main(String[] args) throws Exception{
		initio();
		p=new int[10];
		q=new int[10];
		for(int t,i,d=Int();d>0;d--){
			for(stu=new boolean[n=Int()][m=Int()],i=Int();i>0;stu[Int()-1][Int()-1]=true,i--);
			f=new int[2][bs[m]];
			Arrays.fill(f[0],-1);
			for(f[t=0][nxt(stu[i=0],bs[m]-1)]=0;i<n-1;i++,t=0){
				Arrays.fill(f[(i+1)&1],-1);
				for(;t<bs[m];t++)
					if(f[i&1][t]!=-1) dfs(i,0,nxt(stu[i+1],t),f[i&1][t]);
			}
			for(int x[]:f)
				for(int a:x)
					t=max(t,a);
			out.println(t);
		}
		out.close();
	}
}