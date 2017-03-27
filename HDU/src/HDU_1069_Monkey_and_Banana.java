import java.io.*;
import java.util.*;

public class HDU_1069_Monkey_and_Banana{
	static class Rec implements Comparable<Rec>{
		int area,h,l,w;

		Rec(int hei,int len,int wei){
			h=hei;
			l=len;
			w=wei;
			area=l*w;
		}

		public int compareTo(Rec r){
			return area-r.area;
		}
	}

	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;
	static int rem[];
	static Rec rec[];

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static void init(int n) throws Exception{
		rem=new int[n*6];
		rec=new Rec[n*6];
		for(int i=0;i<n;i++){
			int a=nextInt();
			int b=nextInt();
			int c=nextInt();
			rec[i*6]=new Rec(a,b,c);
			rec[i*6+1]=new Rec(a,c,b);
			rec[i*6+2]=new Rec(b,a,c);
			rec[i*6+3]=new Rec(b,c,a);
			rec[i*6+4]=new Rec(c,a,b);
			rec[i*6+5]=new Rec(c,b,a);
		}
		Arrays.sort(rec);
	}

	static int dp(int n){
		int h=0;
		for(int i=0;i<n*6;i++){
			int max=0;
			for(int j=0;j<=i;j++){
				if(rec[i].l>rec[j].l&&rec[i].w>rec[j].w&&max<rem[j]){
					max=rem[j];
				}
			}
			rem[i]=max+rec[i].h;
			if(h<rem[i]){
				h=rem[i];
			}
		}
		return h;
	}

	public static void main(String[] args) throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
		for(int n,tst=1;(n=nextInt())!=0;tst++){
			init(n);
			out.println("Case "+tst+": maximum height = "+dp(n));
		}
		out.close();
	}
}
