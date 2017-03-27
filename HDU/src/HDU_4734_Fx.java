import java.io.*;
import java.util.*;

public class HDU_4734_Fx{
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

	static int Int() throws Exception{
		int ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		else ch-='0';
		for(x=0;ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	public static void main(String[] args) throws Exception{
		initio();
		int f[][]=new int[10][10000],d[]=new int[10];
		f[0][0]=1;
		for(int i=0;i<9;i++)
			for(int j=0;j<10000;j++){
				if(f[i][j]==0) continue;
				for(int k=0;k<10;k++)
					f[i+1][j+(k<<i)]+=f[i][j];
			}
		for(int i=0;i<10;i++)
			for(int j=1;j<10000;f[i][j]+=f[i][j-1],j++);
		for(int tst=1,ttt=Int();tst<=ttt;tst++){
			int a=Int(),b=Int(),l=0,v=0,ans=0;
			for(int i=0;a>0;v+=a%10<<i++,a/=10);
			if(b==0) d[++l]=0;
			for(;b>0;d[++l]=b%10,b/=10);
			for(int i=l;i>0;v-=d[i]<<i-1,i--)
				for(int j=0;j<d[i]&&v>=j<<i-1;j++)
					ans+=f[i-1][v-(j<<i-1)];
			if(v>=0) ans++;
			out.println("Case #"+tst+": "+ans);
		}
		out.flush();
	}
}