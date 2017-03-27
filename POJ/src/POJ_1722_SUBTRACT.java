import java.io.*;
import java.util.*;

//状态诚重要，转移价更高
public class POJ_1722_SUBTRACT{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;
	static int rem[][],sec[],ran;

	static String next() throws Exception{
		if(stk==null||!stk.hasMoreTokens()){
			stk=new StringTokenizer(in.readLine());
		}
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static void dp(int n){
		rem[1][ran+sec[0]-sec[1]]=1;
		for(int i=2;i<n;i++){
			for(int j=-ran;j<=ran;j++){
				if(rem[i-1][ran+j]!=0){
					rem[i][ran+j-sec[i]]=1;
					rem[i][ran+j+sec[i]]=2;
				}
			}
		}
	}

	static void find(int n,int t){
		int path[]=new int[n];
		path[0]=2;
		for(int i=n-1;i>0;i--){
			path[i]=rem[i][ran+t];
			if(path[i]==1){
				t+=sec[i];
			}else{
				t-=sec[i];
			}
		}
		int ans[]=new int[n];
		for(int i=1;i<n;i++){
			if(path[i]!=path[i-1]){
				ans[i]=i;
			}else{
				ans[i]=ans[i-1];
			}
		}
		for(int i=n-1;i>0;i--){
			System.out.println(ans[i]);
		}
	}

	public static void main(String[] args) throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
		int n=nextInt();
		int t=nextInt();
		sec=new int[n];
		for(int i=0;i<n;i++){
			sec[i]=nextInt();
			ran+=sec[i];
		}
		rem=new int[n][ran+ran+1];
		dp(n);
		find(n,t);
		out.close();
	}
}