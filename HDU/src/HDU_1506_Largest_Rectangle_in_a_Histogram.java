import java.io.*;
import java.util.*;

public class HDU_1506_Largest_Rectangle_in_a_Histogram{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		stk=new StringTokenizer(in.readLine());
		out=new PrintWriter(System.out);
	}

	static String next() throws Exception{
		while(!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static long max(long a,long b,long c){
		return a>b*c?a:b*c;
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int i,n=nextInt();n>0;n=nextInt()){
			int[] a=new int[n+2],l=new int[n+2],r=new int[n+2];
			long ans=0;
			for(a[0]=a[n+1]=-1,i=1;i<=n;i++)
				a[l[i]=r[i]=i]=nextInt();
			for(i=1;i<=n;i++)
				for(;a[l[i]-1]>=a[i];l[i]=l[l[i]-1]);
			for(i=n;i>=1;ans=max(ans,a[i],(r[i]-l[i--]+1)))
				for(;a[r[i]+1]>=a[i];r[i]=r[r[i]+1]);
			out.println(ans);
		}
		out.close();
	}
}