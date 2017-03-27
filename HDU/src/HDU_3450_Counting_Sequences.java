import java.io.*;
import java.util.*;

public class HDU_3450_Counting_Sequences{
	static BufferedReader in;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static void initiot() throws Exception{
		in=new BufferedReader(new FileReader("test"));
		out=new PrintWriter(new File("outtest"));
	}

	static int Int() throws Exception{
		int ch,x,s=1;
		for(ch=in.read();ch<'0'&&ch!=-1&&ch!='-'||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		if(ch=='-'){
			s=-1;
			ch=in.read();
		}
		for(x=0;ch>='0'&&ch<='9';x=x*10+ch-'0',ch=in.read());
		return x*s;
	}

	static int n,tree[];

	static void update(int pos,int val){
		while(pos<=n){
			tree[pos]+=val;
			pos+=-pos&pos;
		}
	}

	static int query(int pos){
		int ans=0;
		while(pos>0){
			ans+=tree[pos];
			pos-=-pos&pos;
		}
		return ans;
	}

	public static void main(String[] args) throws Exception{
		initio();
		int d,i,l,r,t,f[],s[]=new int[100002];
		TreeSet<Integer> set=new TreeSet<Integer>();
		TreeMap<Integer,Integer> map=new TreeMap<Integer,Integer>();
		while((n=Int())!=-1){
			for(d=Int(),i=1;i<=n;s[i]=Int(),set.add(s[i++]));
			for(int x:set)
				map.put(x,i++-n);
			f=new int[n+1];
			tree=new int[n+1];
			for(i=1;i<=n;i++){
				l=map.ceilingEntry(s[i]-d).getValue();
				r=map.floorEntry(s[i]+d).getValue();
				t=((query(r)-query(l-1)+1)%9901+9901)%9901;
				f[i]=(f[i-1]+t)%9901;
				update(map.get(s[i]),t);
			}
			out.println(((f[n]-n)%9901+9901)%9901);
			set.clear();
			map.clear();
		}
		out.close();
	}
}