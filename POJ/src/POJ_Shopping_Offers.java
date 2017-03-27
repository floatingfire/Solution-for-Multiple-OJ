import java.io.*;
import java.util.*;

public class POJ_Shopping_Offers{
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

	static int bst[];

	static int max(int a,int b){
		return a>b?a:b;
	}

	static boolean check(int sa){
		for(int i=0;i<bst.length;sa/=6,i++)
			if(sa%6>bst[i]) return false;
		return true;
	}

	static int min(int a,int b){
		return a<b?a:b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		final int bs[]={1,6,36,216,1296,7776};
		int i,n,j,bss=0,f[],hsh[]=new int[1000],p[]=new int[104],pr[]=new int[7776];
		for(bst=new int[n=Int()],i=0;i<n;pr[i++]=Int())
			bss+=(p[i]=bs[i])*(bst[hsh[Int()]=i]=Int());
		for(i=n,n+=Int();i<n;pr[i++]=Int())
			for(j=Int();j>0;p[i]+=bs[hsh[Int()]]*Int(),j--);
		for(f=new int[bss+(i=1)];i<=bss;f[i++]=1000000000);
		for(i=0;i<n;i++)
			for(j=p[i];j<=bss;j++)
				if(check(j)) f[j]=min(f[j],f[j-p[i]]+pr[i]);
		out.println(f[bss]);
		out.close();
	}
}