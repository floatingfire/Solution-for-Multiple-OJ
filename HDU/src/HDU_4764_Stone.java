import java.io.*;
import java.util.*;

public class HDU_4764_Stone{
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

	static double nextDouble() throws Exception{
		return Double.parseDouble(next());
	}

	public static void main(String[] args) throws Exception{
		initio();
		while(true){
			int n=nextInt(),k=nextInt();
			if(n==0&&k==0) break;
			out.println(n%(k+1)==1?"Jiang":"Tang");
		}
		out.flush();
	}
}