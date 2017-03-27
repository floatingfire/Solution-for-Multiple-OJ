import java.io.*;
import java.util.*;

public class HDU_4719_Oh_My_Holy_FFF{
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
		TreeMap<Integer,Integer> tree=new TreeMap<Integer,Integer>();
		int h[]=new int[100010];
		long f[]=new long[100010];
		for(int tst=1,ttt=Int();tst<=ttt;tst++){
			out.print("Case #"+tst+": ");
			int n=Int(),m=Int();
			for(int i=1;i<=n;h[i++]=Int());
			Arrays.fill(f,0);
			tree.clear();
			tree.put(h[0],0);
			for(int i=1;i<=n;i++){
				Integer j;
				if(i-m-1>=0&&(j=tree.get(h[i-m-1]))!=null&&j==i-m-1) tree.remove(h[i-m-1]);
				j=tree.lowerKey(h[i]);
				if(j==null) continue;
				f[i]=f[j=tree.get(j)]-h[j]+(long)h[i]*h[i];
				j=tree.floorEntry(h[i]).getValue();
				if(f[i]-f[j]>=h[i]-h[j]) tree.put(h[i],i);
				while(true){
					j=tree.higherKey(h[i]);
					if(j==null) break;
					j=tree.get(j);
					if(f[j]-f[i]>=h[j]-h[i]) break;
					tree.remove(h[j]);
				}
			}
			out.println(f[n]==0?"No solution":f[n]);
		}
		out.flush();
	}
}