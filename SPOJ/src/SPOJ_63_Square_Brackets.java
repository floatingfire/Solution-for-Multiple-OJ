import java.io.*;
import java.util.*;

public class SPOJ_63_Square_Brackets{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static String next() throws Exception{
		if(stk==null||!stk.hasMoreTokens()){
			stk=new StringTokenizer(in.readLine());
		}
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	public static void main(String[] args) throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
		for(int tst=nextInt();tst>0;tst--){
			boolean b[]=new boolean[nextInt()<<1|1];
			int f[][]=new int[2][b.length];
			for(int k=nextInt();k>0;k--){
				b[nextInt()]=true;
			}
			f[0][0]=1;
			for(int i=1;i<b.length;i++){
				for(int j=0;j<b.length;j++){
					if(f[0][j]==0) continue;
					if(j+1<b.length) f[1][j+1]+=f[0][j];
					if(b[i]) continue;
					if(j-1>=0) f[1][j-1]+=f[0][j];
				}
				f[0]=f[1];
				f[1]=new int[b.length];
			}
			out.println(f[0][0]);
		}
		out.close();
	}
}