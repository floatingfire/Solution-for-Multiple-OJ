import java.io.*;
/*
ID: lloo1351
LANG: JAVA
PROG: sprime
*/
public class sprime {
	static StreamTokenizer in;
	static PrintWriter out;
	static int nextInt()throws Exception{
		in.nextToken();
		return (int)in.nval;
	}
	static int n,sp,map[]={1,2,3,5,7,9};
	static boolean check(int val){
		int up=(int)Math.sqrt(val);
		for(int i=2;i<=up;i++){
			if(val%i==0){
				return false;
			}
		}
		return true;
	}
	static void dfs(int id){
		if(id==n){
			out.println(sp);
		}else{
			for(int i=id==0?1:0;i<map.length;i++){
				sp=10*sp+map[i];
				if(check(sp)){
					dfs(id+1);
				}
				sp/=10;
			}
		}
	}
	public static void main(String[]args)throws Exception{
		in=new StreamTokenizer(new BufferedReader(new FileReader("sprime.in")));
		out=new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		n=nextInt();
		dfs(0);
		out.close();
	}
}
