import java.util.*;

public class HDU_1159_Common_Subsequence{
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);
		while(scan.hasNext()){
			char x[]=scan.next().toCharArray();
			char y[]=scan.next().toCharArray();
			int cs[][]=new int[x.length+1][y.length+1];
			for(int i=1;i<=x.length;i++)
				for(int j=1;j<=y.length;j++)
					if(x[i-1]==y[j-1]) cs[i][j]=cs[i-1][j-1]+1;
					else cs[i][j]=Math.max(cs[i-1][j],cs[i][j-1]);
			System.out.println(cs[x.length][y.length]);
		}
	}
}
