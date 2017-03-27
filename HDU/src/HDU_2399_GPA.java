import java.io.*;
import java.util.*;

public class HDU_2399_GPA{
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

	static char nextChar() throws Exception{
		return next().charAt(0);
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
		loo:while(true){
			String s=in.readLine();
			if(s==null) break;
			stk=new StringTokenizer(s);
			double ans=0,cnt=0;
			while(stk.hasMoreTokens()){
				char ch=nextChar();
				cnt++;
				if(ch=='A') ans+=4;
				else if(ch=='B') ans+=3;
				else if(ch=='C') ans+=2;
				else if(ch=='D') ans+=1;
				else if(ch=='F') ans+=0;
				else{
					System.out.println("Unknown letter grade in input");
					continue loo;
				}
			}
			System.out.println(String.format("%.2f",ans/cnt));
		}
		out.close();
	}
}