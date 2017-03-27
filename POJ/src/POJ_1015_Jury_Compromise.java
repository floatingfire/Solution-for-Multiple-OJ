import java.io.*;
import java.util.*;

public class POJ_1015_Jury_Compromise{
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

	static int max(int a,int b){
		return a>b?a:b;
	}

	static int min(int a,int b){
		return a<b?a:b;
	}

	static boolean uslt(int rm[][],int p[],int i,int j,int k){
		for(;i>0&&rm[i][j]!=k;j-=p[rm[i][j]],i--);
		return i==0;
	}

	public static void main(String[] args) throws Exception{
		initio();
		int[][] f=new int[21][801],rm=new int[21][801];
		for(int i,j=0,tst=1,n=nextInt(),m=nextInt(),p[],d[],ans[];n!=0;tst++,n=nextInt(),m=nextInt()){
			for(p=new int[n],d=new int[n],i=0;i<n;p[i]=nextInt()-(j=nextInt()),d[i]=p[i]+(j<<1),i++);
			for(i=0;i<=m;Arrays.fill(rm[i],-1),Arrays.fill(f[i++],-1));
			for(f[0][400]=0,i=1;i<=m;i++)
				for(j=0;j<=800;j++)
					for(int k=0;k<n;k++){
						if(j-p[k]<0||j-p[k]>800||f[i-1][j-p[k]]==-1) continue;
						if(f[i][j]<f[i-1][j-p[k]]+d[k]&&uslt(rm,p,i-1,j-p[k],k)) f[i][j]=f[i-1][j
								-p[k]]
								+d[rm[i][j]=k];
					}
			for(i=0;i<=400;i++){
				if(f[m][400-i]<f[m][400+i]) j=400+i;
				else j=400-i;
				if(f[m][j]!=-1) break;
			}
			out.println("Jury #"+tst);
			out.println("Best jury has value "+(f[m][j]+j-400>>1)+" for prosecution and value "
					+(f[m][j]-j+400>>1)+" for defence:");
			for(ans=new int[i=m];i>0;ans[i-1]=rm[i][j]+1,j-=p[rm[i--][j]]);
			for(Arrays.sort(ans),i=0;i<m;out.print(" "+ans[i++]));
			out.println();
			out.println();
		}
		out.close();
	}
}