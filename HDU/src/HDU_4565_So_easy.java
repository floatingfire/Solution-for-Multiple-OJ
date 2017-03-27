import java.io.*;
import java.util.*;

public class HDU_4565_So_easy{
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

	static int[][] mul(int a[][],int b[][],int m){
		int ans[][]=new int[a.length][b[0].length];
		for(int i=0;i<a.length;i++)
			for(int j=0;j<b[0].length;j++)
				for(int k=0;k<b.length;k++)
					ans[i][j]=(ans[i][j]+a[i][k]*b[k][j]%m)%m;
		return ans;
	}

	public static void main(String[] args) throws Exception{
		initio();
		while(true){
			int a=Int(),b=Int(),n=Int(),m=Int();
			if(a==-1) break;
			int ans[][]={{a*2%m,2}};
			for(int x[][]={{a*2%m,1},{(b%m-a*a%m+m)%m,0}};n>0;x=mul(x,x,m),n>>=1)
				if((n&1)==1) ans=mul(ans,x,m);
			out.println(ans[0][1]);
		}
		out.close();
	}
}