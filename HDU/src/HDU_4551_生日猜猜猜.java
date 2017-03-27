import java.io.*;
import java.util.*;

public class HDU_4551_ÉúÈÕ²Â²Â²Â{
	static BufferedReader in;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static int Int() throws Exception{
		int ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	static long Int64() throws Exception{
		long ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	static int ly[]={0,31,29,31,30,31,30,31,31,30,31,30,31};
	static int uly[]={0,31,28,31,30,31,30,31,31,30,31,30,31};

	static boolean isly(int y){
		return y%100==0?y%400==0:y%4==0;
	}

	static class Node{
		int y,m,d;

		Node(int y,int m,int d){
			this.y=y;
			this.m=m;
			this.d=d;
		}

		public String toString(){
			return y+"/"+(m>9?"":"0")+m+"/"+(d>9?"":"0")+d;
		}
	}

	static int gcd(int a,int b){
		return a==0?b:gcd(b%a,a);
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int tst=1,ttt=Int();tst<=ttt;tst++){
			int yy[],n=Int(),m=Int(),y=Int();
			if(isly(y)) yy=ly;
			else yy=uly;
			ArrayList<Node> ans=new ArrayList<Node>();
			for(int i=1;i<=12;i++)
				for(int j=1;j<=yy[i];j++){
					int x=gcd(i,j),xx=i*j/x;
					if(x==n&&xx==m) ans.add(new Node(y,i,j));
				}
			out.print("Case #"+tst+": ");
			if(ans.isEmpty()) out.println(-1);
			else if(ans.size()>1) out.println(1);
			else out.println(ans.get(0));
		}
		out.close();
	}
}