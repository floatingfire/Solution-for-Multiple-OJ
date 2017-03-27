import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class URAL_1013_K_based_Numbers{
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

	public static void main(String[] args) throws Exception{
		initio();
		int n=nextInt(),k=nextInt();
		BigInteger f[][]=new BigInteger[2][2];
		f[0][1]=BigInteger.ONE;
		f[0][0]=f[1][0]=f[1][1]=BigInteger.ZERO;
		for(int i=0;i<n;i++){
			f[1][0]=f[0][1];
			f[1][1]=f[0][1].add(f[0][0]).multiply(BigInteger.valueOf(k-1));
			f[0][0]=f[1][0];
			f[0][1]=f[1][1];
			f[1][0]=f[1][1]=BigInteger.ZERO;
		}
		out.println(f[0][1]);
		out.close();
	}
}