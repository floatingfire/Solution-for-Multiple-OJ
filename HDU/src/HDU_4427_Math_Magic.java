import java.io.*;
import java.util.*;

public class HDU_4427_Math_Magic{
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

	static final int M=1000000007;

	static int gcd(int a,int b){
		return a==0?b:gcd(b%a,a);
	}

	static int lcm(int a,int b){
		return a*b/gcd(a,b);
	}

	public static void main(String[] args) throws Exception{
		initio();
		String ss;
		while((ss=in.readLine())!=null){
			stk=new StringTokenizer(ss);
			int n=nextInt(),m=nextInt(),k=nextInt();
			int t=1,c[]=new int[m+1],d[]=new int[m+1];
			for(int i=1;i*i<=m;i++){
				if(m%i!=0) continue;
				d[c[i]=t++]=i;
				if(i==m/i) continue;
				d[c[m/i]=t++]=m/i;
			}
			int lcm[][]=new int[t][t];
			for(int i=1;i<t;i++){
				for(int j=1;j<t;j++){
					lcm[i][j]=c[lcm(d[i],d[j])];
				}
			}
			int f[][][]=new int[2][n+1][t];
			f[0][0][c[1]]=1;
			for(int i=0;i<k;i++){
				for(int j=0;j<=n;j++)
					for(int l=1;l<t;l++){
						if(f[0][j][l]==0) continue;
						for(int o=0;o<t;o++){
							if(j+d[o]>n) continue;
							int tmp=lcm[l][o];
							f[1][j+d[o]][tmp]+=f[0][j][l];
							f[1][j+d[o]][tmp]%=M;
						}
					}
				f[0]=f[1];
				f[1]=new int[n+1][t];
			}
			out.println(f[0][n][c[m]]);
		}
		out.flush();
	}
}