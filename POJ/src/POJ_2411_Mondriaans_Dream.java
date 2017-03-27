import java.io.*;
import java.util.*;

public class POJ_2411_Mondriaans_Dream{
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

	static int n,m;
	static long f[][];

	static void dfs(int i,int j,int fm,int to){
		if(j>=m) f[(i+1)&1][to]+=f[i&1][fm];
		else{
			if(((1<<j)&fm)==0) dfs(i,j+1,fm,to|(1<<j));
			else{
				dfs(i,j+1,fm,to);
				if(j<m-1&&((2<<j)&fm)>0) dfs(i,j+2,fm,to|(3<<j));
			}
		}
	}

	public static void main(String[] args) throws Exception{
		initio();
		while((n=Int())!=0|(m=Int())!=0){
			if(n<m){
				int t=m;
				m=n;
				n=t;
			}
			f=new long[2][1<<m];
			f[0][(1<<m)-1]=1;
			for(int i=0;i<n;i++){
				f[(i+1)&1]=new long[1<<m];
				for(int j=0;j<1<<m;j++)
					if(f[i&1][j]>0) dfs(i,0,j,0);
			}
			out.println(f[n&1][(1<<m)-1]);
		}
		out.close();
	}
}