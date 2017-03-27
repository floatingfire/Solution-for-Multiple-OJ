import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class POJ_2084_Game_of_Connections{
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

	static BigInteger rem[];

	static void init(){
		rem=new BigInteger[101];
		rem[0]=rem[1]=BigInteger.ONE;
	}

	static BigInteger catt(int n){
		if(rem[n]==null){
			rem[n]=BigInteger.ZERO;
			for(int i=0;i<n;i++)
				rem[n]=rem[n].add(catt(i).multiply(catt(n-i-1)));
		}
		return rem[n];
	}

	public static void main(String[] args) throws Exception{
		initio();
		int n;
		init();
		while((n=nextInt())!=-1)
			out.println(catt(n));
		out.close();
	}
}