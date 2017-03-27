import java.io.*;

public class HDU_1061_Rightmost_Digit{
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
		else ch-='0';
		for(x=0;ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int tst=Int();tst>0;tst--){
			out.println(quickmul(Int()));
		}
		out.close();
	}

	private static long quickmul(int n){
		int b=1,x=n%10;
		while(n>0){
			if((n&1)==1) b=b*x%10;
			n>>=1;
			x=x*x%10;
		}
		return b;
	}
}
