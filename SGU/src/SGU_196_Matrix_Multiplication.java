import java.io.*;
import java.util.*;

public class SGU_196_Matrix_Multiplication{
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

	static int Int() throws Exception{
		int ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	public static void main(String[] args) throws Exception{
		initio();
		int n=Int(),m=Int(),c[]=new int[n];
		for(int i=0;i<m;c[Int()-1]++,c[Int()-1]++,i++);
		long ans=0;
		for(int i=0;i<n;ans+=c[i]*c[i],i++);
		out.println(ans);
		out.close();
	}
}