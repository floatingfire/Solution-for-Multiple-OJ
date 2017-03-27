import java.io.*;
import java.util.*;

public class URAL_1017_Staircases{
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

	static long f[][];

	static long dp(int i,int j){
		if(f[i][j]==0){
			if(i<j*(j+1)>>1) return 0;
			if(j==1) f[i][j]=1;
			else f[i][j]=dp(i-j,j)+dp(i-j,j-1);
		}
		return f[i][j];
	}

	public static void main(String[] args) throws Exception{
		initio();
		int n=nextInt(),x=(int)(Math.sqrt(n<<3|1)/2);
		f=new long[n+1][x+1];
		while(x>1)
			dp(n,x--);
		long ans=0;
		for(long l:f[n])
			ans+=l;
		out.println(ans);
		out.close();
	}
}