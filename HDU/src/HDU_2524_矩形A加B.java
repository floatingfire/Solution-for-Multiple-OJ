import java.io.*;
import java.util.*;

public class HDU_2524_¾ØÐÎA¼ÓB{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static byte nextByte() throws Exception{
		return Byte.parseByte(next());
	}

	public static void main(String[] args) throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
		for(int tst=nextInt();tst>0;tst--){
			byte n=nextByte(),m=nextByte();
			out.println(n*(n+1)*m*(m+1)>>2);
		}
		out.close();
	}
}