import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class SPOJ_FMODF{
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

	static BigInteger pow(BigInteger a,long n,BigInteger m){
		BigInteger ans=BigInteger.ONE;
		while(n>0){
			if((n&1)==1) ans=ans.multiply(a).mod(m);
			a=a.multiply(a).mod(m);
			n>>=1;
		}
		return ans;
	}

	public static void main(String[] args) throws Exception{
		initio();
		BigInteger dic[]=new BigInteger[1002];
		dic[0]=BigInteger.ZERO;
		dic[1]=BigInteger.ONE;
		for(int i=2;i<=1001;dic[i]=dic[i-1].add(dic[i-2]),i++);
		for(int tst=nextInt();tst>0;tst--){
			long n=nextLong();
			int k=nextInt();
			long cnt=n/k;
			BigInteger bas;
			if((k-1&1)==1){
				if((cnt&1)==1) bas=dic[k-1];
				else bas=dic[1];
			}else{
				if((cnt&3)==1) bas=dic[k-1];
				else if((cnt&3)==2) bas=dic[k].subtract(dic[1]);
				else if((cnt&3)==3) bas=dic[k-2];
				else bas=dic[1];
			}
			out.println(bas.multiply(dic[(int)(n%k)]).mod(dic[k]));
		}
		out.close();
	}
}