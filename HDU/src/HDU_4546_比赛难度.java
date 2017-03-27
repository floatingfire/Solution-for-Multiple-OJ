import java.io.*;
import java.util.*;

public class HDU_4546_比赛难度{
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

	static int c[];

	static class Pair implements Comparable<Pair>{
		int sum,id;

		Pair(int sum,int id){
			this.sum=sum;
			this.id=id;
		}

		int add(){
			return sum+c[id];
		}

		public int compareTo(Pair p){
			return add()-p.add();
		}
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int n,m,i,tst=1,ttt=Int();tst<=ttt;tst++){
			c=new int[n=Int()];
			for(m=Int(),i=0;i<n;c[i++]=Int());
			Arrays.sort(c);
			PriorityQueue<Pair> q=new PriorityQueue<Pair>();
			q.add(new Pair(0,0));
			for(i=1;i<m;i++){
				Pair p=q.poll();
				if(p.id+1<n){
					q.add(new Pair(p.sum,p.id+1));
					q.add(new Pair(p.add(),p.id+1));
				}
			}
			out.println("Case #"+tst+": "+q.peek().add());
		}
		out.close();
	}
}