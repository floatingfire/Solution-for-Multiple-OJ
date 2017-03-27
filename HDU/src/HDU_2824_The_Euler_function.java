import java.io.*;
import java.util.*;

public class HDU_2824_The_Euler_function{
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

	public static void main(String[] args) throws Exception{
		initio();
		long dic[]=new long[3000000];
		for(int i=2;i<3000000;i++){
			if(dic[i]!=0) continue;
			for(int j=i;j<3000000;j+=i){
				if(dic[j]==0) dic[j]=j;
				dic[j]=dic[j]/i*(i-1);
			}
		}
		for(int i=1;i<3000000;dic[i]+=dic[i-1],i++);
		String s;
		while((s=in.readLine())!=null){
			stk=new StringTokenizer(s);
			out.println(-dic[nextInt()-1]+dic[nextInt()]);
			out.flush();
		}
		out.close();
	}
}