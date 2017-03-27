import java.io.*;
import java.util.*;

public class ZOJ_3601_Unrequited_Love{
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

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static double nextDouble() throws Exception{
		return Double.parseDouble(next());
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int tst=nextInt();tst>0;tst--){
			int n=nextInt(),m=nextInt(),q=nextInt();
			HashSet<String> map=new HashSet<String>();
			for(int i=0;i<n+m;i++){
				String a=next();
				for(int j=nextInt();j>0;j--)
					map.add(a+" "+next());
			}
			for(int i=0,j,k;i<q;i++){
				int b=nextInt();
				String x=null,tmp[]=new String[b];
				for(j=0;j<b;j++){
					tmp[j]=next();
					if(x==null) x=tmp[j];
					else{
						boolean u=map.contains(x+" "+tmp[j]);
						boolean v=map.contains(tmp[j]+" "+x);
						if((u&&v)||(!u&&!v)) x=null;
						else if(v) x=tmp[j];
					}
				}
				if(x==null) out.println(0);
				else{
					for(j=0;j<b&&!map.contains(tmp[j]+" "+x);j++);
					for(k=0;k<b&&(x==tmp[k]||map.contains(x+" "+tmp[k]));k++);
					if(j==b&&k==b) out.println(1+" "+x);
					else out.println(0);
				}
			}
			out.println();
		}
		out.flush();
	}
}