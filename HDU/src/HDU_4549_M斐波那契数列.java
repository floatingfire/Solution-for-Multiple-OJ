import java.io.*;
import java.util.*;

public class HDU_4549_Mì³²¨ÄÇÆõÊýÁÐ{
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

	static long[][] mul(long a[][],long b[][]){
		long ans[][]=new long[a.length][b[0].length];
		for(int i=0;i<a.length;i++)
			for(int j=0;j<b[0].length;j++)
				for(int k=0;k<b.length;k++)
					ans[i][j]=(ans[i][j]+a[i][k]*b[k][j])%(mod-1);
		return ans;
	}

	static long pow(long a,long n){
		long ans=1;
		while(n>0){
			if((n&1)==1) ans=ans*a%mod;
			a=a*a%mod;
			n>>=1;
		}
		return ans;
	}

	static final int mod=1000000007;

	public static void main(String[] args) throws Exception{
		initio();
		long a,b,n;
		while((a=Int())!=-1&&(b=Int())!=-1&&(n=Int())!=-1){
			long ans[][]={{1,0},{0,1}},x[][]={{1,1},{1,0}};
			while(n>0){
				if((n&1)==1) ans=mul(ans,x);
				x=mul(x,x);
				n>>=1;
			}
			out.println(pow(a,ans[1][1])*pow(b,ans[1][0])%mod);
		}
		out.close();
	}
}