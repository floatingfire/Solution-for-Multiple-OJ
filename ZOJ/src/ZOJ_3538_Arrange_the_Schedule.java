import java.io.*;
import java.util.*;

public class ZOJ_3538_Arrange_the_Schedule{
	static class Pair implements Comparable<Pair>{
		int id,ch;

		Pair(int id,int ch){
			this.id=id;
			this.ch=ch;
		}

		public int compareTo(Pair p){
			return id-p.id;
		}
	}

	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static final int mod=1000000007;

	static String next() throws Exception{
		if(stk==null||!stk.hasMoreTokens()){
			stk=new StringTokenizer(in.readLine());
		}
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static long pow(int n){
		long ans=1,a=3;
		while(n>0){
			if((n&1)==1) ans=(ans*a)%mod;
			a=(a*a)%mod;
			n>>=1;
		}
		return ans;
	}

	static long[][] mul(long a[][],long b[][]){
		long ans[][]=new long[a.length][b[0].length];
		for(int i=0;i<a.length;i++){
			for(int j=0;j<b[0].length;j++){
				for(int k=0;k<b.length;k++){
					ans[i][j]=(ans[i][j]+a[i][k]*b[k][j])%mod;
				}
			}
		}
		return ans;
	}

	static long mapow(int n){
		long ans[][]={{1,0},{0,1}};
		long a[][]={{2,1},{3,0}};
		n--;
		while(n>0){
			if((n&1)==1) ans=mul(a,ans);
			a=mul(a,a);
			n>>=1;
		}
		a[0][0]=1;
		a[0][1]=a[1][0]=a[1][1]=0;
		ans=mul(a,ans);
		return ans[0][1];
	}

	public static void main(String[] args) throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
		String s;
		long ans;
		Pair a,p[];
		while((s=in.readLine())!=null){
			if(s.isEmpty()) continue;
			stk=new StringTokenizer(s);
			int n=nextInt();
			p=new Pair[nextInt()];
			for(int i=0;i<p.length;i++){
				p[i]=new Pair(nextInt(),next().charAt(0));
			}
			if(p.length==0){
				out.println(pow(n-1)*4%mod);
				continue;
			}
			Arrays.sort(p);
			ans=1;
			a=null;
			for(Pair i:p){
				if(a==null) ans=(ans*pow(i.id-1))%mod;
				else if(i.ch==a.ch){
					ans=(ans*(i.id==a.id?1:3*mapow(i.id-a.id)))%mod;
				}else ans=(ans*mapow(i.id-a.id+1))%mod;
				if(ans==0) break;
				a=i;
			}
			if(ans!=0&&a.id<n) ans=(ans*pow(n-a.id))%mod;
			out.println(ans);
		}
		out.close();
	}
}