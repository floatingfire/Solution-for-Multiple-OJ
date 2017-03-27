import java.io.*;
import java.util.*;

public class POJ_1112_Team_Them_Up{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
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
		int v;
		Edge n;

		Edge(int v,Edge n){
			this.v=v;
			this.n=n;
		}
	}

	static Edge h[];

	static void add(int u,int v){
		h[u]=new Edge(v,h[u]);
		h[v]=new Edge(u,h[v]);
	}

	public static void main(String[] args) throws Exception{
		initio();
		int n=nextInt();
		boolean map[][]=new boolean[n][n];
		for(int i=0,j;i<n;i++)
			for(;(j=nextInt())!=0;map[i][j-1]=true);
		h=new Edge[n];
		for(int i=0;i<n;i++)
			for(int j=0;j<i;j++)
				if(!(map[i][j]&map[j][i])) add(i,j);
		boolean vst[]=new boolean[n];
		boolean clr[]=new boolean[n];
		int id,t=0,s[]=new int[n];
		int ans[][][]=new int[n+2][2][n+1];
		int k=0,f[][]=new int[n+2][202];
		f[0][101]=101;
		for(int i=0,cnt;i<n;i++){
			if(vst[i]) continue;
			cnt=-1;
			vst[ans[k][0][ans[k][0][0]=1]=s[t++]=i]=true;
			while(t>0)
				for(Edge e=h[id=s[--t]];e!=null;e=e.n)
					if(!vst[e.v]){
						clr[e.v]=!clr[id];
						int x=clr[e.v]?1:0;
						if(clr[e.v]) cnt++;
						else cnt--;
						vst[ans[k][x][++ans[k][x][0]]=s[t++]=e.v]=true;
					}else if(clr[id]==clr[e.v]){
						out.println("No solution");
						out.flush();
						return;
					}
			for(int j=1;j<202;j++){
				if(f[k][j]==0) continue;
				if(j+cnt>0&&j+cnt<202) f[k+1][j+cnt]=j;
				if(j-cnt>0&&j-cnt<202) f[k+1][j-cnt]=j;
			}
			k++;
		}
		int x=101;
		for(;x<202;x++)
			if(f[k][x]!=0) break;
		for(int i=101;i>0;i--){
			if(f[k][i]==0) continue;
			if(x-101>101-i) x=i;
			break;
		}
		int o[][]=new int[2][n+1];
		for(;k>0;x=f[k--][x]){
			if(x-f[k][x]==ans[k-1][1][0]-ans[k-1][0][0]){
				for(int i=1;i<=ans[k-1][0][0];o[0][++o[0][0]]=ans[k-1][0][i++]+1);
				for(int i=1;i<=ans[k-1][1][0];o[1][++o[1][0]]=ans[k-1][1][i++]+1);
			}else{
				for(int i=1;i<=ans[k-1][1][0];o[0][++o[0][0]]=ans[k-1][1][i++]+1);
				for(int i=1;i<=ans[k-1][0][0];o[1][++o[1][0]]=ans[k-1][0][i++]+1);
			}
		}
		out.print(o[0][0]);
		for(int i=1;i<=o[0][0];out.print(" "+o[0][i++]));
		out.println();
		out.print(o[1][0]);
		for(int i=1;i<=o[1][0];out.print(" "+o[1][i++]));
		out.println();
		out.flush();
	}
}