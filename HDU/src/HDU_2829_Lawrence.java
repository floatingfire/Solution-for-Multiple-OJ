import java.io.*;
import java.util.*;

public class HDU_2829_Lawrence{
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
		else ch-='0';
		for(x=0;ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
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

	static long min(long a,long b){
		return a<b?a:b;
	}

	static int i,j,n,m,c[],h,t,q[]=new int[10000];
	static long x,s[],ss[],f[][];

	static long gx(int k){
		return s[k];
	}

	static long gy(int i,int k){
		return f[i-1][k]+ss[k];
	}

	static long gg(int i,int j,int k){
		return gy(i,k)-s[j]*gx(k);
	}

	static boolean check(int i,int j){
		return (gy(i,j)-gy(i,q[t-1]))*(gx(j)-gx(q[t-2]))<=(gy(i,j)-gy(i,q[t-2]))*(gx(j)-gx(q[t-1]));
	}

	public static void main(String[] args) throws Exception{
		initio();
		while((n=Int())!=0|(m=Int())!=0){
			for(c=new int[n+1],i=1;i<=n;c[i++]=Int());
			for(s=new long[n+1],ss=new long[n+1],i=1;i<=n;s[i]=c[i]+s[i-1],ss[i]=c[i]*s[i]+ss[i-1],i++);
			for(f=new long[m+1][n+1],i=1;i<=n;f[0][i]=s[i]*s[i]-ss[i],i++);
			for(i=1;i<=m;i++){
				for(h=t=0,j=1;j<=n;j++){
					for(;h<t-1&&check(i,j);t--);
					for(q[t++]=j;h<t-1&&gg(i,j,q[h])>=gg(i,j,q[h+1]);h++);
					f[i][j]=gg(i,j,q[h])+s[j]*s[j]-ss[j];
				}
			}
			out.println(f[m][n]);
		}
		out.close();
	}
}