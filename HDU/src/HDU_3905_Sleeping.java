import java.io.*;
import java.util.*;

public class HDU_3905_Sleeping{
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

	static int max(int a,int b){
		return a>b?a:b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		int i,j,n,m,l,c[],f[],g[];
		while((n=Int())!=-1){
			for(m=Int(),l=Int(),c=new int[n+1],i=1;i<=n;c[i]=c[i-1]+Int(),i++);
			for(f=new int[n+1],i=0;i<=m;i++){
				for(j=n;j>0;f[j]=f[j-1],j--);
				for(g=new int[n+1],j=i;j<=n;j++){
					if(j>=l+i) g[j]=max(g[j-1]+c[j]-c[j-1],f[j-l]+c[j]-c[j-l]);
					f[j]=max(f[j],g[j]);
				}
			}
			out.println(f[n]);
			out.flush();
		}
		out.close();
	}
}