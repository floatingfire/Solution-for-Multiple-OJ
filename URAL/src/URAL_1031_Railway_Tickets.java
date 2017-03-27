import java.io.*;
import java.util.*;

public class URAL_1031_Railway_Tickets{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static void initiot() throws Exception{
		in=new BufferedReader(new FileReader("test"));
		out=new PrintWriter("outtest");
	}

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static int min(int a,int b){
		return a<b?a:b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		int[] l=new int[3],c=new int[3];
		for(int i=0;i<3;l[i++]=nextInt());
		for(int i=0;i<3;c[i++]=nextInt());
		int n=nextInt(),s=nextInt()-1,e=nextInt()-1;
		if(s>e){
			int t=s;
			s=e;
			e=t;
		}
		int[] r=new int[n],f=new int[n];
		int[][] q=new int[3][n<<1];
		int[] h=new int[3],t=new int[3];
		for(int i=0;i<3;q[i][t[i]++]=s,i++);
		for(int i=1;i<n;r[i++]=nextInt());
		for(int i=s+1,j;i<=e;i++)
			for(f[i]=1000000000,j=0;j<3;j++){
				for(;r[i]-r[q[j][h[j]]]>l[j]&&h[j]<t[j];h[j]++);
				if(h[j]<t[j]) f[i]=min(f[i],f[q[j][h[j]]]+c[j]);
				q[j][t[j]++]=i;
			}
		out.println(f[e]);
		out.close();
	}
}