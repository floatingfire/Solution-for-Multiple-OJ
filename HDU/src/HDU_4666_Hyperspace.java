import java.io.*;
import java.util.*;

public class HDU_4666_Hyperspace{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static void initiot() throws Exception{
		in=new BufferedReader(new FileReader("test"));
		out=new PrintWriter("outtest");
	}

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static int cnt(int id,int k,int x[]){
		int ans=0;
		while(k>0){
			ans+=x[--k]*(1-((id&1)<<1));
			id>>=1;
		}
		return ans;
	}

	static void rmv(TreeMap<Integer,Integer> t,int v){
		int x=t.get(v);
		if(x==1) t.remove(v);
		else t.put(v,x-1);
	}

	static void add(TreeMap<Integer,Integer> t,int v){
		if(t.containsKey(v)) t.put(v,t.get(v)+1);
		else t.put(v,1);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
		initio();
		String s;
		while((s=in.readLine())!=null){
			stk=new StringTokenizer(s);
			int q=nextInt(),k=nextInt();
			TreeMap<Integer,Integer> t[]=new TreeMap[1<<k];
			for(int i=0;i<t.length;t[i++]=new TreeMap<Integer,Integer>());
			int p[][]=new int[q][k];
			for(int j=0;j<q;j++){
				if(nextInt()==1){
					int x=nextInt()-1;
					for(int i=0;i<t.length;rmv(t[i],cnt(i,k,p[x])),i++);
				}else{
					for(int i=0;i<k;p[j][i++]=nextInt());
					for(int i=0;i<t.length;add(t[i],cnt(i,k,p[j])),i++);
				}
				int ans=0;
				for(int i=0;i<t.length;ans=Math.max(ans,t[i].lastKey()-t[i].firstKey()),i++);
				out.println(ans);
			}
			out.flush();
		}
		out.close();
	}
}