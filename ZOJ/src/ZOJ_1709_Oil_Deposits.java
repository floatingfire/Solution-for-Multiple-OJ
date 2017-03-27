import java.util.*;
import java.io.*;

public class ZOJ_1709_Oil_Deposits{
	class Point{
		int x,y;

		Point(int x,int y){
			this.x=x;
			this.y=y;
		}
	}

	int n,m;
	boolean map[][];

	void dfs(int x,int y){
		if(!map[x][y]) return;
		map[x][y]=false;
		if(x>0) dfs(x-1,y);
		if(x+1<n) dfs(x+1,y);
		if(y>0) dfs(x,y-1);
		if(y+1<m) dfs(x,y+1);
		if(x>0&&y>0) dfs(x-1,y-1);
		if(x>0&&y+1<m) dfs(x-1,y+1);
		if(x+1<n&&y>0) dfs(x+1,y-1);
		if(x+1<n&&y+1<m) dfs(x+1,y+1);
	}

	void run() throws Exception{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		while(true){
			StringTokenizer st=new StringTokenizer(in.readLine());
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			if(n==0&&m==0) break;
			map=new boolean[n][m];
			Queue<Point> que=new LinkedList<Point>();
			for(int i=0;i<n;i++){
				String tmp=in.readLine();
				for(int j=0;j<m;j++){
					if(tmp.charAt(j)=='@'){
						map[i][j]=true;
						que.add(new Point(i,j));
					}
				}
			}
			int cnt=0;
			while(!que.isEmpty()){
				Point tmp=que.poll();
				if(map[tmp.x][tmp.y]){
					dfs(tmp.x,tmp.y);
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}

	public static void main(String[] args) throws Exception{
		new ZOJ_1709_Oil_Deposits().run();
	}
}
