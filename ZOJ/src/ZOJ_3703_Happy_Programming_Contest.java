import java.io.*;
import java.util.*;

public class ZOJ_3703_Happy_Programming_Contest{
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

	static class Prob implements Comparable<Prob>{
		int v,t;

		public int compareTo(Prob p){
			return t-p.t;
		}
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int i,j,tst=Int();tst>0;tst--){
			int t=Int(),n=Int();
			Prob pr[]=new Prob[n];
			for(i=0;i<n;pr[i]=new Prob(),pr[i++].t=Int());
			for(i=0;i<n;pr[i++].v=Int());
			int[] f=new int[t+1],p=new int[t+1],pt=new int[t+1];
			for(Arrays.sort(pr),i=0;i<n;i++)
				for(j=t;j>=pr[i].t;j--){
					if(f[j]<f[j-pr[i].t]+pr[i].v){
						f[j]=f[j-pr[i].t]+pr[i].v;
						p[j]=p[j-pr[i].t]+1;
						pt[j]=pt[j-pr[i].t]+j;
					}else if(f[j]==f[j-pr[i].t]+pr[i].v){
						if(p[j]<p[j-pr[i].t]+1){
							p[j]=p[j-pr[i].t]+1;
							pt[j]=pt[j-pr[i].t]+j;
						}else if(p[j]==p[j-pr[i].t]+1){
							if(pt[j]>pt[j-pr[i].t]+j) pt[j]=pt[j-pr[i].t]+j;
						}
					}
				}
			for(j=i=0;i<=t;i++){
				if(f[j]<f[i]||f[j]==f[i]&&(p[j]<p[i]||p[j]==p[i]&&pt[j]>pt[i])) j=i;
			}
			out.println(f[j]+" "+p[j]+" "+pt[j]);
		}
		out.close();
	}
}
