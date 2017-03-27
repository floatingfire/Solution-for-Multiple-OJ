import java.io.*;
import java.util.*;

public class HDU_4664_Triangulation{
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

	static int g[];

	static void init(){
		// sg()从53到86是循环节
		g=new int[87];
		for(int i=2;i<g.length;i++){
			boolean vst[]=new boolean[i];
			for(int j=0;j<=i-2>>1;j++)
				vst[g[j]^g[i-2-j]]=true;
			for(int j=0;j<i;j++){
				if(vst[j]) continue;
				g[i]=j;
				break;
			}
		}
	}

	static int sg(int x){
		return x<53?g[x]:g[(x-53)%34+53];
	}

	public static void main(String[] args) throws Exception{
		initio();
		init();
		for(int x,i,tst=nextInt();tst>0;tst--){
			for(x=0,i=nextInt();i>0;i--,x^=sg(nextInt()));
			out.println(x==0?"Dave":"Carol");
		}
		out.flush();
	}
}