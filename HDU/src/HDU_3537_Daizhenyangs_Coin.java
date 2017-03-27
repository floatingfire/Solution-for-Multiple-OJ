import java.io.*;
import java.util.*;

public class HDU_3537_Daizhenyangs_Coin{
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

	static int sg(int x){
		int cnt=1,i=x;
		for(;i>0;cnt^=i&1,i>>=1);
		return x<<1|cnt;
	}

	public static void main(String[] args) throws Exception{
		initio();
		String ss;
		while((ss=in.readLine())!=null){
			stk=new StringTokenizer(ss);
			int i=nextInt(),ans=0;
			HashSet<Integer> rem=new HashSet<Integer>();
			for(int x;i>0;i--){
				if(rem.contains(x=nextInt())) continue;
				ans^=sg(x);
				rem.add(x);
			}
			out.println(ans==0?"Yes":"No");
			out.flush();
		}
	}
}