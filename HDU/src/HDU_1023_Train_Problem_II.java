import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class HDU_1023_Train_Problem_II{
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
		for(ch=in.read();ch<'0'&&ch!=-1&&ch!='-'||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		if(ch=='-'){
			s*=-1;
			ch=in.read();
		}
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x*s;
	}

	public static void main(String[] args) throws Exception{
		initio();
		BigInteger dic[]=new BigInteger[101];
		dic[0]=BigInteger.ONE;
		for(int j,i=1;i<=100;i++)
			for(dic[i]=BigInteger.ZERO,j=0;j<i;j++)
				dic[i]=dic[i].add(dic[j].multiply(dic[i-j-1]));
		for(int n=Int();n!=-1;out.println(dic[n]),n=Int());
		out.close();
	}
}