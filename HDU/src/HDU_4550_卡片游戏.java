import java.io.*;
import java.util.*;

public class HDU_4550_ø®∆¨”Œœ∑{
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
		int ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int mx,i,tst=nextInt();tst>0;tst--){
			LinkedList<Character> q=new LinkedList<Character>();
			char ch[]=next().toCharArray();
			for(mx=0,i=1;i<ch.length;i++)
				if(ch[mx]=='0'||(ch[mx]>=ch[i]&&ch[i]!='0')) mx=i;
			for(i=0;i<mx;i++){
				if(i==mx) continue;
				if(q.isEmpty()||q.peek()>=ch[i]) q.push(ch[i]);
				else q.add(ch[i]);
			}
			out.print(ch[mx]);
			for(char c:q)
				out.print(c);
			for(i=mx+1;i<ch.length;out.print(ch[i++]));
			out.println();
		}
		out.close();
	}
}