import java.util.Arrays;
import java.util.Scanner;

public class POJ_1007_DNA_Sorting{
	private static Scanner in;

	static class Pair implements Comparable<Pair>{
		int inv;
		String val;

		Pair(){
			val=in.next();
			inv=count(val);
		}

		public int compareTo(Pair p){
			return Integer.compare(inv,p.inv);
		}

		public String toString(){
			return val;
		}
	}

	static int count(String s){
		int ans=0;
		int cnt[]=new int[4];
		for(char c:s.toCharArray()){
			if(c<'T'){
				ans+=cnt[3];
				if(c<'G'){
					ans+=cnt[2];
					if(c<'C'){
						ans+=cnt[1];
						cnt[0]++;
					}else cnt[1]++;
				}else cnt[2]++;
			}else cnt[3]++;
		}
		return ans;
	}

	public static void main(String[] args){
		in=new Scanner(System.in);
		in.nextInt();
		int m=in.nextInt();
		Pair p[]=new Pair[m];
		for(int i=0;i<m;i++)
			p[i]=new Pair();
		Arrays.sort(p);
		for(Pair pa:p)
			System.out.println(pa);
	}
}