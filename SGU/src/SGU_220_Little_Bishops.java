import java.io.*;
import java.util.*;

public class SGU_220_Little_Bishops{
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

	public static void main(String[] args) throws Exception{
		initio();
		int i,c,p,j,n=Int(),k=Int();
		int[] a=new int[n],b=new int[n];
		long[][] fa=new long[2][k+1],fb=new long[2][k+1];
		for(a[0]=i=1;i<n;a[i]=(i&1)==1?a[i-1]:a[i-1]+2,i++);
		for(b[0]=2,i=1;i<n-1;b[i]=(i&1)==1?b[i-1]:b[i-1]+2,i++);
		for(fa[0][0]=fa[1][i=0]=1;i<n;i++)
			for(c=i&1,p=(i+1)&1,j=1;j-1<=a[i]&&j<=k;j++)
				fa[c][j]=fa[p][j]+fa[p][j-1]*(a[i]-j+1);
		for(fb[0][0]=fb[1][i=0]=1;i<n-1;i++)
			for(c=i&1,p=(i+1)&1,j=1;j-1<=b[i]&&j<=k;j++)
				fb[c][j]=fb[p][j]+fb[p][j-1]*(b[i]-j+1);
		long ans=0;
		for(c=(n+1)&1,p=n&1,i=0;i<=k;ans+=fa[c][i]*fb[p][k-i],i++);
		out.println(ans);
		out.close();
	}
}