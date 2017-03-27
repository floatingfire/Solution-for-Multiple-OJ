import java.io.*;
import java.util.*;

public class HDU_4532_湫秋系列故事_安排座位{
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

	static int max(int a,int b){
		return a>b?a:b;
	}

	static int min(int a,int b){
		return a<b?a:b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		final int M=1000000007;
		long p[]=new long[50];
		p[0]=1;
		for(int i=1;i<p.length;p[i]=p[i-1]*i%M,i++);
		long c[][]=new long[500][500];
		for(int i=0;i<c.length;c[i][0]=c[i][i]=1,i++);
		for(int i=2;i<c.length;i++)
			for(int j=1;j<i;j++)
				c[i][j]=(c[i-1][j]+c[i-1][j-1])%M;
		for(int tst=1,ttt=nextInt();tst<=ttt;tst++){
			int cnt,n=nextInt(),a[]=new int[n];
			for(int i=0;i<n;a[i++]=nextInt());
			long f[][]=new long[2][447];
			f[0][a[n-1]-1]=p[a[n-1]];
			cnt=a[n-1]+1;
			for(int i=n-2;i>=0;cnt+=a[i--]){
				for(int j=0;j<cnt;j++){
					if(f[0][j]==0) continue;
					for(int k=a[i];k>0;k--)
						for(int l=min(k,j);l>=0;l--){
							long tmp=f[0][j]*p[a[i]]%M*c[j][l]%M*c[a[i]-1][k-1]%M*c[cnt-j][k-l]%M;
							f[1][j-l+a[i]-k]=(f[1][j-l+a[i]-k]+tmp)%M;
						}
				}
				f[0]=f[1];
				f[1]=new long[447];
			}
			out.println("Case "+tst+": "+f[0][0]);
		}
		out.close();
	}
}