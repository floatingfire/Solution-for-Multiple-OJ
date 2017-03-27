import java.io.*;
import java.util.*;

public class HDU_1242_Rescue{
	class Point implements Comparable<Point>{
		int x,y;

		Point(int x,int y){
			this.x=x;
			this.y=y;
		}

		public int compareTo(Point p){
			return time[x][y]-time[p.x][p.y];
		}
	}

	int time[][],n,m;
	boolean map[][],mark[][];

	void bfs(int x,int y){
		PriorityQueue<Point> que=new PriorityQueue<Point>();
		que.add(new Point(x,y));
		map[x][y]=false;
		while(!que.isEmpty()){
			Point p=que.poll();
			if(!mark[p.x][p.y]){
				if(p.x>0&&map[p.x-1][p.y]){
					time[p.x-1][p.y]+=time[p.x][p.y]+1;
					que.add(new Point(p.x-1,p.y));
					map[p.x-1][p.y]=false;
				}
				if(p.x+1<n&&map[p.x+1][p.y]){
					time[p.x+1][p.y]+=time[p.x][p.y]+1;
					que.add(new Point(p.x+1,p.y));
					map[p.x+1][p.y]=false;
				}
				if(p.y>0&&map[p.x][p.y-1]){
					time[p.x][p.y-1]+=time[p.x][p.y]+1;
					que.add(new Point(p.x,p.y-1));
					map[p.x][p.y-1]=false;
				}
				if(p.y+1<m&&map[p.x][p.y+1]){
					time[p.x][p.y+1]+=time[p.x][p.y]+1;
					que.add(new Point(p.x,p.y+1));
					map[p.x][p.y+1]=false;
				}
			}else{
				mark[p.x][p.y]=false;
				time[p.x][p.y]++;
				que.add(p);
			}
		}
	}

	void run() throws Exception{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out=new PrintWriter(System.out);
		while(true){
			String str=in.readLine();
			if(str==null) break;
			StringTokenizer st=new StringTokenizer(str);
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			Queue<Point> pls=new LinkedList<Point>();
			map=new boolean[n][m];
			mark=new boolean[n][m];
			time=new int[n][m];
			int ax=0,ay=0;
			for(int i=0;i<n;i++){
				String tmp=in.readLine();
				for(int j=0;j<m;j++){
					char t=tmp.charAt(j);
					if(t!='#'){
						map[i][j]=true;
						if(t=='a'){
							ax=i;
							ay=j;
						}else if(t=='r') pls.add(new Point(i,j));
						else if(t=='x') mark[i][j]=true;
					}
				}
			}
			bfs(ax,ay);
			for(;!pls.isEmpty()&&map[pls.peek().x][pls.peek().y];pls.poll());
			Point min=pls.poll();
			for(Point p:pls)
				if(!map[p.x][p.y]&&time[p.x][p.y]<time[min.x][min.y]) min=p;
			if(min==null) out.println("Poor ANGEL has to stay in the prison all his life.");
			else out.println(time[min.x][min.y]);
			out.flush();
		}
		out.close();
	}

	public static void main(String[] args) throws Exception{
		new HDU_1242_Rescue().run();
	}
}