import java.io.*;
import java.util.*;

public class FastIOs{
	// BufferedReader°æ¿ìËÙIO
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static int Int() throws Exception{
		int ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		else ch-='0';
		for(x=0;ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	static long Int64() throws Exception{
		long ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		else ch-='0';
		for(x=0;ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	static double Dou() throws Exception{
		double x=0;
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

	static long nextLong() throws Exception{
		return Long.parseLong(next());
	}

	static double nextDouble() throws Exception{
		return Double.parseDouble(next());
	}
}
