import java.io.*;
import java.util.*;

public class HDU_4259_Double_Dealing{
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

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static long gcd(long a,long b){
		for(long t;a!=0;t=a,a=b%a,b=t);
		return b;
	}

	static long lcm(long a,long b){
		return a/gcd(a,b)*b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int n=nextInt(),k=nextInt();n!=0&&k!=0;n=nextInt(),k=nextInt()){
			int i,j,l,c[]=new int[n];
			boolean flg[]=new boolean[n];
			long ans;
			for(l=n-1,i=k-1;i>=0;i--)
				for(j=i;j<n;c[j]=l--,j+=k);
			for(ans=1,i=0;i<n;i++){
				if(flg[i]) continue;
				for(l=0,j=i;!flg[j];l++,flg[j]=true,j=c[j]);
				ans=lcm(l,ans);
			}
			out.println(ans);
		}
		out.close();
	}
}