import java.io.*;
import java.util.*;

public class HDU_4618_Palindrome_Sub_Array{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static long nextLong() throws Exception{
		return Long.parseLong(next());
	}

	static int trans(int id,int s[]){
		if(id<=0) return '$';
		else if((id&1)==1) return '#';
		else return s[id-1>>1];
	}

	static boolean check(int lid,int rid,int s[]){
		return trans(lid,s)==trans(rid,s);
	}

	static int[] get(int s[]){
		int[] rds=new int[s.length+1<<1];
		int id=1,lf=1;
		for(int i=1;i<rds.length;i++){
			if(i<id+lf) rds[i]=Math.min(rds[(id<<1)-i],id+lf-i);
			for(int j=i+rds[i];j<rds.length&&check((i<<1)-j,j,s);rds[i]++,j++);
			if(id+lf<i+rds[i]) lf=rds[id=i];
		}
		for(int i=0;i<rds.length;rds[i++]--);
		return rds;
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int tst=nextInt();tst>0;tst--){
			int n=nextInt(),m=nextInt();
			int[][] maph=new int[n][m],mapv=new int[m][n];
			int[][] remh=new int[n][],remv=new int[m][];
			for(int i=0;i<n;remh[i]=get(maph[i]),i++)
				for(int j=0;j<m;mapv[j][i]=maph[i][j]=nextInt(),j++);
			for(int i=0;i<m;remv[i]=get(mapv[i]),i++);
			int max=0;
			for(int i=2;i<remv[0].length;i++){
				for(int j=0;j<m;j++){
					int min=remv[j][i],mmin=remv[j][i];
					for(int k=0;k<remv[j][i];k++){
						min=Math.min(min,remh[(i-1)/2-remv[j][i]/2+k][(j+1)*2]);
						mmin=Math.min(mmin,remh[(i-1)/2-remv[j][i]/2+k][(j+1)*2-1]);
					}
					max=Math.max(max,Math.max(min,mmin));
				}
			}
			out.println(max);
		}
		out.close();
	}
}