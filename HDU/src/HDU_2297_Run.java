import java.io.*;
import java.util.*;

public class HDU_2297_Run{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
		stk=new StringTokenizer(in.readLine());
	}

	static String next() throws Exception{
		while(!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static class Pair implements Comparable<Pair>{
		int p,s;

		Pair(int p,int s){
			this.p=p;
			this.s=s;
		}

		public int compareTo(Pair pa){
			return p!=pa.p?pa.p-p:pa.s-s;
		}
	}

	static int check(Pair a,Pair b,Pair c){
		return (a.p-b.p)*(c.s-a.s)-(a.p-c.p)*(b.s-a.s);
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int i,tst=nextInt();tst>0;tst--){
			i=nextInt();
			Pair alt[]=new Pair[i],q[]=new Pair[i<<1];
			while(--i>=0)
				alt[i]=new Pair(nextInt(),nextInt());
			Arrays.sort(alt);
			int t=0;
			q[t++]=new Pair(alt[0].p,0);
			for(Pair p:alt){
				if(p.s<q[t-1].s) continue;
				for(;t>1&&check(q[t-2],q[t-1],p)>=0;t--);
				q[t++]=p;
			}
			out.println(t-1);
		}
		out.close();
	}
}