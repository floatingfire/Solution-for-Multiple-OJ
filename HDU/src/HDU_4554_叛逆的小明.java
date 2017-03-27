import java.io.*;
import java.util.*;

public class HDU_4554_ÅÑÄæµÄÐ¡Ã÷{
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
		// out=new PrintWriter(System.out);
	}

	static int Int() throws Exception{
		int ch,x,s=1;
		for(ch=in.read();ch<'0'&&ch!=-1&&ch!='-'||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		else if(ch=='-'){
			s=-1;
			ch=in.read();
		}
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x*s;
	}

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static int rvs(int a){
		int ans=0;
		for(;a!=0;ans=ans*10+a%10,a/=10);
		return ans;
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int tst=Int();tst>0;tst--){
			int x=Int(),y=Int();
			int a=rvs(x+y>>1),b=rvs(x-y>>1);
			out.println((a+b)+" "+(a-b));
		}
		out.close();
	}
}