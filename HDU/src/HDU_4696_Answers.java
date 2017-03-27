import java.io.*;
import java.util.*;

public class HDU_4696_Answers{
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

	static void closeio() throws Exception{
		in.close();
		out.close();
	}

	public static void main(String[] args) throws Exception{
		initio();
		String ss;
		while((ss=in.readLine())!=null){
			stk=new StringTokenizer(ss);
			int i=nextInt(),j=nextInt();
			in.readLine();
			boolean b[]=new boolean[2];
			for(;i>0;b[nextInt()-1]=true,i--);
			if(b[0]) for(;j>0;out.println(nextInt()<=0?"NO":"YES"),j--);
			else for(;j>0;i=nextInt(),out.println((i&1)==1||i<=0?"NO":"YES"),j--);
			out.flush();
		}
		closeio();
	}
}
