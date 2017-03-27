import java.io.*;
import java.util.*;

public class POJ_2823_Sliding_Window{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static int nextInt(){
		return Integer.parseInt(stk.nextToken());
	}

	public static void main(String[] args) throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
		stk=new StringTokenizer(in.readLine());
		int n=nextInt();
		int k=nextInt();
		stk=new StringTokenizer(in.readLine());
		k=k>n?n:k;
		int headx=0,tailx=0,headn=0,tailn=0;
		int[][] decque=new int[2][2000002];
		int[][] incque=new int[2][2000002];
		int[] max=new int[n-k+1];
		int[] min=new int[n-k+1];
		min[0]=max[0]=incque[0][0]=decque[0][0]=nextInt();
		for(int i=1;i<n;i++){
			int ele=nextInt();
			if(i-decque[1][headx]>=k) headx++;
			while(tailx>=headx&&ele>decque[0][tailx])
				tailx--;
			decque[0][++tailx]=ele;
			decque[1][tailx]=i;
			if(i-incque[1][headn]>=k) headn++;
			while(tailn>=headn&&ele<incque[0][tailn])
				tailn--;
			incque[0][++tailn]=ele;
			incque[1][tailn]=i;
			if(i-k+1>=0){
				max[i-k+1]=decque[0][headx];
				min[i-k+1]=incque[0][headn];
			}
		}
		for(int i:min)
			out.print(i+" ");
		out.println();
		for(int i:max)
			out.print(i+" ");
		out.println();
		out.close();
	}
}