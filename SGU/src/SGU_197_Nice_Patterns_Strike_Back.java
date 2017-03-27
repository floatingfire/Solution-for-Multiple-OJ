import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class SGU_197_Nice_Patterns_Strike_Back{
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

	static BigInteger nextBigInteger() throws Exception{
		return new BigInteger(next());
	}

	static int Int() throws Exception{
		int ch,x,s=1;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		if(ch=='-'){
			s*=-1;
			ch=in.read();
		}
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x*s;
	}

	static int check(int a,int b,int m){
		for(int rem=2,i=0;i<m;a>>=1,b>>=1,i++){
			if(rem==(a&1)&&rem==(b&1)) return 0;
			rem=(a&1)==(b&1)?a&1:2;
		}
		return 1;
	}

	static int[][] mul(int a[][],int b[][],int p){
		int ans[][]=new int[a.length][b[0].length];
		for(int i=0;i<a.length;i++)
			for(int j=0;j<b[0].length;j++)
				for(int k=0;k<b.length;k++)
					ans[i][j]=(ans[i][j]+a[i][k]*b[k][j])%p;
		return ans;
	}

	public static void main(String[] args) throws Exception{
		initio();
		BigInteger n=nextBigInteger().subtract(BigInteger.ONE);
		int ans=0,m=nextInt(),p=nextInt();
		int[][] a=new int[1][1<<m],map=new int[1<<m][1<<m];
		for(int i=0;i<1<<m;i++)
			for(int j=0;j<1<<m;j++)
				map[i][j]=check(i,j,m);
		int[][] pow=new int[1<<m][1<<m];
		for(int i=0;i<1<<m;pow[i][i]=1,i++);
		while(n.signum()>0){
			if(n.getLowestSetBit()==0) pow=mul(pow,map,p);
			map=mul(map,map,p);
			n=n.shiftRight(1);
		}
		Arrays.fill(a[0],1);
		a=mul(a,pow,p);
		for(int i=0;i<1<<m;ans=(ans+a[0][i++])%p);
		out.println(ans);
		out.close();
	}
}