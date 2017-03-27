import java.io.*;
import java.util.*;

public class HDU_4642_Fliping_game{
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
		for(int tst=nextInt();tst>0;tst--){
			for(int n=nextInt()*nextInt();n>1;nextInt(),n--);
			out.println(nextInt()==0?"Bob":"Alice");
		}
		out.flush();
	}
}