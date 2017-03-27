import java.io.*;
import java.util.*;

public class SPOJ_CITY2{
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

	static int Int() throws Exception{
		int ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int tst=1,b[],i,n=Int();n!=-1;n=Int(),tst++){
			int[] l=new int[n+2],r=new int[n+2];
			HashSet<Long> set=new HashSet<Long>();
			for(i=0;i<=n+1;l[i]=r[i]=i,i++);
			for(i=1,b=new int[n+2],b[0]=b[n+1]=-1;i<=n;b[i++]=Int());
			for(i=1;i<=n;i++)
				while(b[i]<=b[l[i]-1])
					l[i]=l[l[i]-1];
			for(i=n;i>=1;i--)
				while(b[i]<=b[r[i]+1])
					r[i]=r[r[i]+1];
			for(i=1;i<=n;i++)
				if(b[i]!=0) set.add(l[i]*100000000L+r[i]);
			out.println("Case "+tst+": "+set.size());
		}
		out.close();
	}
}