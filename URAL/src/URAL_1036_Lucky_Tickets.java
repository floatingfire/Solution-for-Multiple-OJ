import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class URAL_1036_Lucky_Tickets{
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

	static int min(int a,int b){
		return a<b?a:b;
	}

	static long fa(int n){
		return n==0?1:n*fa(n-1);
	}

	public static void main(String[] args) throws Exception{
		initio();
		int n=nextInt(),s=nextInt();
		if((s&1)==1) out.println(0);
		else{
			BigInteger f[][]=new BigInteger[2][(s/=2)+1];
			f[0][0]=BigInteger.ONE;
			while(n-->0){
				for(int j=0;j<=s;j++){
					if(f[0][j]==null) continue;
					for(int k=0;k<=9&&k+j<=s;k++)
						if(f[1][j+k]==null) f[1][k+j]=f[0][j];
						else f[1][j+k]=f[1][j+k].add(f[0][j]);
				}
				f[0]=f[1];
				f[1]=new BigInteger[s+1];
			}
			out.println(f[0][s]==null?0:f[0][s].pow(2));
		}
		out.close();
	}
}