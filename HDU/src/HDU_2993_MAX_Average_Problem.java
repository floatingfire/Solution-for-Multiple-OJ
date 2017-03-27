import java.io.*;
import java.util.*;

public class HDU_2993_MAX_Average_Problem{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static int nextInt() throws Exception{
		int ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		for(x=0;ch>='0'&&ch<='9';x=x*10+ch-'0',ch=in.read());
		return x;
	}

	static double max(double a,double b){
		return a>b?a:b;
	}

	static int[] c=new int[100011],q=new int[100011];

	static double slope(int a,int b){
		return (c[a]-c[b])*1.0/(a-b);
	}

	public static void main(String[] args) throws Exception{
		initio();
		int i,n,k,t,h;
		double ans;
		while(true){
			if((n=nextInt())==-1) break;
			for(i=1,k=nextInt();i<=n;c[i]=nextInt()+c[i-1],i++);
			for(ans=t=h=0,i=k;i<=n;i++){
				for(;h<t-1&&slope(i-k,q[t-2])>slope(i-k,q[t-1]);t--);
				for(q[t++]=i-k;h<t-1&&slope(i,q[h+1])>=slope(i,q[h]);h++);
				ans=max(ans,slope(i,q[h]));
			}
			out.printf("%.2f",ans);
			out.println();
		}
		out.close();
	}
}