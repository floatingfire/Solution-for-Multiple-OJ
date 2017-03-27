import java.io.*;
import java.util.*;

public class ZOJ_3725_Painting_Storages{
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

	static int Int() throws Exception{
		int ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		else ch-='0';
		for(x=0;ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	static final int M=1000000007;

	public static void main(String[] args) throws Exception{
		initio();
		int n,m,f[];
		while((n=Int())!=-1&&(m=Int())!=-1){
			if(m>n){
				out.println(0);
				continue;
			}
			f=new int[n+1];
			f[m]=1;
			for(int i=m+1,g=1;i<=n;i++,g=g*2%M)
				f[i]=(int)(((long)(f[i-1]<<1)+g-f[i-m-1]+M)%M);
			out.println(f[n]);
			out.flush();
		}
	}
}