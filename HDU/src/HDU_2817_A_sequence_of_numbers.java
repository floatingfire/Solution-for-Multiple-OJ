import java.io.*;
import java.util.*;

public class HDU_2817_A_sequence_of_numbers{
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

	static final int M=200907;

	static long pow(long a,long n){
		long ans=1;
		while(n>0){
			if((n&1)==1) ans=(ans*a)%M;
			a=(a*a)%M;
			n>>=1;
		}
		return ans;
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int tst=nextInt();tst>0;tst--){
			long a=nextLong(),b=nextLong(),c=nextLong(),n=nextInt();
			out.println((c-b==b-a?a%M+(n-1)%M*((b-a)%M):a%M*pow(b/a%M,n-1))%M);
		}
		out.close();
	}
}