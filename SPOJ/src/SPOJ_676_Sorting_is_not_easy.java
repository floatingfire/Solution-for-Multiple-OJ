import java.io.*;
import java.util.*;

public class SPOJ_676_Sorting_is_not_easy{
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
			int n=nextInt()+1;
			int[][] f=new int[n][n],r[]=new int[2][n][n];
			int s[]=new int[n];
			for(int i=1;i<n;i++){
				s[i]=nextInt();
				f[s[i]][s[i]]=r[0][s[i]][s[i]]=r[1][s[i]][s[i]]=i;
			}
			for(int i=1;i<n;i++){
				for(int j=1;j+i<n;j++){
					r[0][j][j+i]=r[0][j][j+i-1]
							-(r[1][j+1][j+i]>r[0][j][j+i-1]?0:1);
					r[1][j][j+i]=r[1][j+1][j+i]
							-(r[0][j][j+i-1]>r[1][j+1][j+i]?0:1);
					f[j][j+i]=Math.min(f[j+1][j+i]+r[0][j][j+i]*(i+1),f[j][j+i
							-1]
							+r[1][j][j+i]*(i+1));
				}
			}
			out.println(f[1][n-1]);
		}
		out.close();
	}
}