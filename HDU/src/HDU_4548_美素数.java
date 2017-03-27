import java.io.*;
import java.util.*;

public class HDU_4548_√¿Àÿ ˝{
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

	static int sum(int a){
		int ans=0;
		for(;a>0;ans+=a%10,a/=10);
		return ans;
	}

	public static void main(String[] args) throws Exception{
		initio();
		boolean flg[]=new boolean[1000001];
		int i,j,cnt,dic[]=new int[1000001];
		for(cnt=0,i=2;i<=1000000;i++){
			dic[i]=cnt;
			if(flg[i]) continue;
			if(!flg[sum(i)]) dic[i]=++cnt;
			for(j=i<<1;j<=1000000;flg[j]=true,j+=i);
		}
		for(int tst=1,ttt=Int();tst<=ttt;tst++){
			out.println("Case #"+tst+": "+(-dic[Int()-1]+dic[Int()]));
		}
		out.close();
	}
}