import java.io.*;
import java.util.*;

public class HDU_1964_Pipes{
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
			stk=new StringTokenizer(in.readLine()," #\t\n\r\f");
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static int tl(int k,int j,int id,int m){
		for(int c=0,i=j+1;i<=m;i++){
			if(((k>>(i<<1))&3)==1) c++;
			else if(((k>>(i<<1))&3)==2) c--;
			if(c==0) return k-(id<<(j<<1))-(1<<(i<<1));
		}
		return k-(id<<(j<<1));
	}

	static int tr(int k,int j,int id){
		for(int c=0,i=j;i>=0;i--){
			if(((k>>(i<<1))&3)==2) c++;
			else if(((k>>(i<<1))&3)==1) c--;
			if(c==0) return k-(id<<(j<<1))+(1<<(i<<1));
		}
		return k-(id<<(j<<1));
	}

	static int min(int a,int b){
		return a<b?a:b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int tst=nextInt();tst>0;tst--){
			int n=nextInt(),m=nextInt();
			int map[][]=new int[n][m];
			for(int i=0;i<n;i++){
				for(int j=0;j<m-1;map[i][j++]=nextInt()*10);
				if(i<n-1) for(int j=0;j<m;map[i][j++]+=nextInt());
			}
			HashMap<Integer,Integer> f=new HashMap<Integer,Integer>();
			HashMap<Integer,Integer> p=new HashMap<Integer,Integer>();
			p.put(0,0);
			for(int i=0;i<n;i++){
				for(int j=0;j<m;j++){
					for(int k:p.keySet()){
						int id=(k>>(j<<1))&15;
						if(id==0){
							if(i!=n-1&&j!=m-1){
								int l=k+(9<<(j<<1)),c=p.get(k)+map[i][j]/10+map[i][j]%10;
								if(f.containsKey(l)) f.put(l,min(f.get(l),c));
								else f.put(l,c);
							}
						}else if(id>>2==0){
							if(i!=n-1){
								if(f.containsKey(k)) f.put(k,min(f.get(k),p.get(k)+map[i][j]%10));
								else f.put(k,p.get(k)+map[i][j]%10);
							}
							if(j!=m-1){
								int l=k-(id<<(j<<1))+(id<<(j+1<<1));
								if(f.containsKey(l)) f.put(l,min(f.get(l),p.get(k)+map[i][j]/10));
								else f.put(l,p.get(k)+map[i][j]/10);
							}
						}else if((id&3)==0){
							if(j!=m-1){
								if(f.containsKey(k)) f.put(k,min(f.get(k),p.get(k)+map[i][j]/10));
								else f.put(k,p.get(k)+map[i][j]/10);
							}
							if(i!=n-1){
								int l=k-(id<<(j<<1))+(j-1<0?id>>2:id<<(j-1<<1));
								if(f.containsKey(l)) f.put(l,min(f.get(l),p.get(k)+map[i][j]%10));
								else f.put(l,p.get(k)+map[i][j]%10);
							}
						}else if(id==6||(id==9&&i+j==n+m-2)){
							int l=k-(id<<(j<<1));
							if(f.containsKey(l)) f.put(l,min(f.get(l),p.get(k)));
							else f.put(l,p.get(k));
						}else if(id==5){
							int l=tl(k,j,id,m);
							if(f.containsKey(l)) f.put(l,min(f.get(l),p.get(k)));
							else f.put(l,p.get(k));
						}else if(id==10){
							int l=tr(k,j,id);
							if(f.containsKey(l)) f.put(l,min(f.get(l),p.get(k)));
							else f.put(l,p.get(k));
						}
					}
					p=f;
					f=new HashMap<Integer,Integer>();
				}
				for(int k:p.keySet())
					f.put(k<<2,p.get(k));
				p=f;
				f=new HashMap<Integer,Integer>();
			}
			out.println(p.containsKey(0)?p.get(0):0);
		}
		out.close();
	}
}