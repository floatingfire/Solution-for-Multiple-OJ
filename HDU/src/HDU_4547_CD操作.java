import java.io.*;
import java.util.*;

public class HDU_4547_CD²Ù×÷{
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
		if(ch=='-'){
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

	static class Edge{
		int id;
		Edge nxt;

		Edge(int id,Edge nxt){
			this.id=id;
			this.nxt=nxt;
		}
	}

	static Edge h[];

	static void add(int u,int v){
		h[v]=new Edge(u,h[v]);
	}

	static int cnt,pth[],rem[],vst[],hsh[];

	static void bfs(int rt){
		Queue<Integer> q=new LinkedList<Integer>();
		Arrays.fill(hsh,-1);
		q.add(rt);
		while(!q.isEmpty()){
			Integer tmp=q.poll();
			if(hsh[tmp]!=-1) continue;
			hsh[tmp]=cnt++;
			for(Edge e=h[tmp];e!=null;q.add(e.id),e=e.nxt);
		}
	}

	static void dfs(int id){
		pth[cnt++]=hsh[id];
		for(Edge e=h[id];e!=null;e=e.nxt){
			vst[hsh[e.id]]=vst[hsh[id]]+1;
			dfs(e.id);
			pth[cnt++]=hsh[id];
		}
	}

	static int min[][];

	static void initrmq(int n){
		min=new int[n][(int)(Math.log(n)/Math.log(2)+1)];
		for(int i=0;i<n;i++){
			min[i][0]=pth[i];
		}
		for(int i=1;i<min[0].length;i++){
			for(int j=0;j+(1<<i-1)<n;j++){
				min[j][i]=Math.min(min[j][i-1],min[j+(1<<i-1)][i-1]);
			}
		}
	}

	static int rmq(int l,int r){
		if(l>r){
			int tmp=l;
			l=r;
			r=tmp;
		}
		int len=(int)(Math.log(r-l+1)/Math.log(2));
		return Math.min(min[l][len],min[r-(1<<len)+1][len]);
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int i,j,tst=nextInt();tst>0;tst--){
			int n=Int(),m=Int();
			TreeMap<String,Integer> map=new TreeMap<String,Integer>();
			boolean f[]=new boolean[n];
			for(h=new Edge[n],i=1,j=0;i<n;i++){
				String u=next(),v=next();
				if(!map.containsKey(v)) map.put(v,j++);
				if(!map.containsKey(u)) map.put(u,j++);
				add(map.get(u),map.get(v));
				f[map.get(u)]=true;
			}
			pth=new int[n<<1];
			vst=new int[n];
			hsh=new int[n];
			j=0;
			for(boolean b:f)
				if(b) j++;
				else break;
			cnt=0;
			bfs(j);
			cnt=0;
			dfs(j);
			for(rem=new int[n],i=n-1<<1;i>=0;rem[pth[i]]=i,i--);
			for(initrmq(pth.length),i=0;i<m;i++){
				if(n==1){
					out.println(0);
					continue;
				}
				int l=hsh[map.get(next())],r=hsh[map.get(next())];
				j=rmq(rem[l],rem[r]);
				out.println(vst[l]-vst[j]+(vst[r]!=vst[j]?1:0));
			}
		}
		out.close();
	}
}