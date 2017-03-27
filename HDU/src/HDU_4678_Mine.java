import java.io.*;
import java.util.*;

public class HDU_4678_Mine{
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

	static double nextDouble() throws Exception{
		return Double.parseDouble(next());
	}

	static int n,m;
	static boolean map[][];
	static int dir[][]={{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};

	static boolean check(int a,int b){
		return a>=0&&a<n&&b>=0&&b<m;
	}

	static int mark(int x,int y){
		int ans=0;
		if(map[x][y]) ans--;
		map[x][y]=true;
		for(int i=0;i<8;i++){
			int a=x+dir[i][0],b=y+dir[i][1];
			if(!check(a,b)||map[a][b]) continue;
			map[a][b]=true;
			ans++;
		}
		return ans;
	}

	static int sg(int x){
		return x==0?0:2-(x&1);
	}

	public static void main(String[] args) throws Exception{
		initio();
		int q[]=new int[3320000];
		for(int tst=1,ttt=nextInt();tst<=ttt;tst++){
			map=new boolean[n=nextInt()][m=nextInt()];
			boolean vst[][]=new boolean[n][m];
			int sum=0,cnt=0;
			for(int i=nextInt();i>0;cnt+=mark(nextInt(),nextInt()),i--);
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++){
					if(map[i][j]||vst[i][j]) continue;
					int h=0,t=0,c=0;
					q[t++]=i*1000+j;
					while(t>h){
						int x=q[h]/1000,y=q[h++]%1000;
						if(vst[x][y]) continue;
						vst[x][y]=true;
						if(map[x][y]){
							c++;
							continue;
						}
						for(int k=0;k<8;k++){
							int a=x+dir[k][0],b=y+dir[k][1];
							if(!check(a,b)||vst[a][b]) continue;
							q[t++]=a*1000+b;
						}
					}
					cnt-=c;
					sum^=sg(c+1);
				}
			out.println("Case #"+tst+": "+((sum^(cnt&1))==0?"Fanglaoshi":"Xiemao"));
		}
		out.flush();
	}
}