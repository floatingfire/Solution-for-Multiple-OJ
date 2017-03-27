import java.io.*;
import java.util.*;

public class HDU_2516_取石子游戏{
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

	static int[] fib;

	static void init(){
		fib=new int[52];
		fib[0]=2;
		fib[1]=3;
		for(int i=2;i<52;i++)
			fib[i]=fib[i-1]+fib[i-2];
	}

	static boolean uncontains(int n){
		for(int i:fib)
			if(i==n) return false;
		return true;
	}

	public static void main(String[] args) throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
		init();
		for(int n=nextInt();n>0;n=nextInt())
			if(uncontains(n)) out.println("First win");
			else out.println("Second win");
		out.close();
	}
}