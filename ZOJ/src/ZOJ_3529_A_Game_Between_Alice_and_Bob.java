import java.io.*;
import java.util.*;

public class ZOJ_3529_A_Game_Between_Alice_and_Bob{
	static StreamTokenizer in;
	static StringTokenizer stk;
	static PrintWriter out;

	static int nextInt() throws Exception{
		in.nextToken();
		return (int)in.nval;
	}

	public static void main(String[] args) throws Exception{
		in=new StreamTokenizer(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
		int i,sum,sto[],cnt[]=new int[5000001];
		for(i=2;i<cnt.length;i++){
			if(cnt[i]>0) continue;
			for(int j=i;j<cnt.length;j+=i)
				for(int k=j;k%i==0;k/=i,cnt[j]++);
		}
		for(int tst=1;in.nextToken()!=StreamTokenizer.TT_EOF;tst++){
			sto=new int[(int)in.nval];
			for(sum=i=0;i<sto.length;i++)
				sum^=(sto[i]=cnt[nextInt()]);
			out.print("Test #"+tst+": ");
			if(sum==0) out.println("Bob");
			else for(i=0;i<sto.length;i++)
				if(sto[i]>(sum^sto[i])){
					out.println("Alice "+(i+1));
					break;
				}
			out.flush();
		}
		out.close();
	}
}