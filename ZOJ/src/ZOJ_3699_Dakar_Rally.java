import java.io.*;
import java.util.*;

public class ZOJ_3699_Dakar_Rally{
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

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static class Route{
		long d,c,p;

		Route() throws Exception{
			this.d=nextInt();
			this.c=nextInt()*d;
			this.p=nextInt();
		}

		Route(boolean b){
			if(b) d=c=p=0;
		}
	}

	static Route rt[];
	static long[] sum;

	public static void main(String[] args) throws Exception{
		initio();
		loo:for(long ans,sur,tst=nextInt();tst>0;tst--){
			int i,j,far[],n=nextInt(),m=nextInt();
			rt=new Route[n+1];
			far=new int[n];
			for(rt[n]=new Route(true),i=0;i<n;rt[i++]=new Route());
			sum=new long[n+1];
			for(sum[0]=0,i=1;i<=n;sum[i]=sum[i-1]+rt[i-1].c,i++);
			for(j=i=0;i<n;i++){
				for(;j<n&&sum[j+1]<=sum[i]+m;j++);
				if((far[i]=j)==i){
					out.println("Impossible");
					continue loo;
				}
			}
			int h=0,t=0,q[]=new int[n+1];
			for(q[t++]=n,i=n-1;i>=0;i--){
				for(;h<t&&q[h]>far[i];h++);
				for(j=q[t-1];h<t&&rt[q[t-1]].p>=rt[i].p;t--){
					if(rt[j].p>rt[q[t-1]].p) j=q[t-1];
				}
				if(h<t) far[i]=q[t-1];
				else far[i]=j;
				q[t++]=i;
			}
			for(sur=ans=i=0;i<n;i=j){
				long tot=(j=far[i])!=n&&rt[i].p<rt[j].p?m:sum[j]-sum[i];
				ans+=(tot-sur)*rt[i].p;
				sur=tot-(sum[j]-sum[i]);
			}
			out.println(ans);
		}
		out.close();
	}
}
