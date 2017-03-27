import java.io.*;
import java.util.*;

public class HDU_4545_Ä§·¨´®{
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
		// out=new PrintWriter(System.out);
	}

	static int Int() throws Exception{
		int ch,x,s=1;
		for(ch=in.read();ch<'0'&&ch!=-1&&ch!='-'||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		else if(ch=='-'){
			s=-1;
			ch=in.read();
		}
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x*s;
	}

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static boolean dic[][];
	static char a[],b[];

	static boolean check(int i,int j){
		if(a[i]==b[j]) return true;
		else return dic[b[j]][a[i]];
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int i,j,tst=1,ttt=nextInt();tst<=ttt;tst++){
			a=next().toCharArray();
			b=next().toCharArray();
			dic=new boolean[128][128];
			for(i=nextInt();i>0;dic[next().charAt(0)][next().charAt(0)]=true,i--);
			for(i=j=0;i<a.length&&j<b.length;){
				while(j<b.length&&!check(i,j++));
				if(check(i,j-1)) i++;
			}
			out.println("Case #"+tst+": "+(i==a.length?"happy":"unhappy"));
		}
		out.close();
	}
}