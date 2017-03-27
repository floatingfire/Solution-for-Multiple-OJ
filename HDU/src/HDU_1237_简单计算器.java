import java.io.*;
import java.util.*;

//decimalformat有不为人知的bug……
public class HDU_1237_简单计算器{
	static int pro(String x){
		return x.equals("+")||x.equals("-")?0:1;
	}

	static boolean isnum(String x){
		return !(x.equals("+")||x.equals("-")||x.equals("*")||x.equals("/"));
	}

	static double exe(double b,double a,String x){
		if(x.equals("+")) return a+b;
		if(x.equals("-")) return a-b;
		if(x.equals("*")) return a*b;
		if(x.equals("/")) return a/b;
		return 0;
	}

	public static void main(String[] args) throws Exception{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		Stack<String> s=new Stack<String>();
		Stack<Double> q=new Stack<Double>();
		while(true){
			String str=in.readLine();
			StringTokenizer st=new StringTokenizer(str);
			if(str.equals("0")) break;
			while(st.hasMoreTokens()){
				String tmp=st.nextToken();
				if(isnum(tmp)) q.add(Double.parseDouble(tmp));
				else if(s.isEmpty()) s.push(tmp);
				else{
					while((!s.isEmpty())&&pro(s.peek())>=pro(tmp))
						q.push(exe(q.pop(),q.pop(),s.pop()));
					s.push(tmp);
				}
			}
			while(!s.isEmpty())
				q.push(exe(q.pop(),q.pop(),s.pop()));
			out.printf("%.2f",q.pop());
			out.println();
		}
		out.close();
	}
}