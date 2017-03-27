import java.io.*;
import java.util.*;

public class URAL_1029_Ministry{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static void initiot() throws Exception{
		in=new BufferedReader(new FileReader("test"));
		out=new PrintWriter("outtest");
	}

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	public static void main(String[] args) throws Exception{
		initio();
		int m=nextInt(),n=nextInt();
		long[][] f=new long[m][n],map=new long[m][n];
		for(int i=0;i<m;i++)
			for(int j=0;j<n;map[i][j++]=nextInt());
		f[0]=Arrays.copyOf(map[0],n);
		int rt[][]=new int[m][n];
		Arrays.fill(rt[0],-1);
		for(int i=1;i<m;i++){
			f[i][0]=f[i-1][0]+map[i][0];
			rt[i][0]=(i-1)*1000;
			for(int j=1;j<n;j++)
				if(f[i][j-1]<f[i-1][j]){
					f[i][j]=f[i][j-1]+map[i][j];
					rt[i][j]=i*1000+j-1;
				}else{
					f[i][j]=f[i-1][j]+map[i][j];
					rt[i][j]=(i-1)*1000+j;
				}
			for(int j=n-2;j>=0;j--)
				if(f[i][j]>f[i][j+1]+map[i][j]){
					f[i][j]=f[i][j+1]+map[i][j];
					rt[i][j]=i*1000+j+1;
				}
		}
		int id=0;
		for(int i=0;i<n;i++)
			if(f[m-1][id]>f[m-1][i]) id=i;
		Stack<Integer> s=new Stack<Integer>();
		for(id=(m-1)*1000+id;id>=0;s.add(id%1000+1),id=rt[id/1000][id%1000]);
		while(!s.isEmpty())
			out.print(s.pop()+" ");
		out.close();
	}
}