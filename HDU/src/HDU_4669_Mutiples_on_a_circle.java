import java.io.*;
import java.util.*;

public class HDU_4669_Mutiples_on_a_circle{
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

	static int cnt(int x){
		int ans=0;
		while(x>0){
			x/=10;
			ans++;
		}
		return ans;
	}

	public static void main(String[] args) throws Exception{
		initio();
		String s;
		while((s=in.readLine())!=null){
			stk=new StringTokenizer(s);
			int i,n=nextInt(),k=nextInt();
			int p[]=new int[n*3+1];
			for(p[0]=i=1;i<p.length;p[i]=(p[i-1]*10)%k,i++);
			int a[]=new int[n],c[]=new int[n];
			for(i=0;i<n;a[i]=nextInt(),c[i]=cnt(a[i]),i++);
			int ans,l,x,f[][]=new int[2][k];
			for(x=l=0,i=n-1;i>=0;f[0][x=(x+a[i]*p[l])%k]++,l+=c[i--]);
			for(ans=i=0;i<n;i++){
				for(int j=0;j<k;f[1][(j*p[c[i]]+a[i])%k]+=f[0][j],j++);
				f[1][a[i]%k]++;
				f[1][x=(x*p[c[i]]+a[i])%k]--;
				x=(x-a[i]*p[l]%k+k)%k;
				ans+=f[1][0];
				f[0]=f[1];
				f[1]=new int[k];
			}
			out.println(ans);
			out.flush();
		}
		out.close();
	}
}