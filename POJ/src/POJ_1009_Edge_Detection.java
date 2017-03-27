import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class POJ_1009_Edge_Detection{
	static Scanner in;

	static int cmp(int x,int v,int l){
		return Math.max(x,l<0||l>=sum?0:Math.abs(v-dic.floorEntry(l).getValue()));
	}

	static int count(int l){
		int x=0,v=dic.floorEntry(l).getValue();
		if(l%n>0){
			x=cmp(x,v,l-1-n);
			x=cmp(x,v,l-1);
			x=cmp(x,v,l-1+n);
		}
		x=cmp(x,v,l-n);
		x=cmp(x,v,l+n);
		if(l%n<n-1){
			x=cmp(x,v,l+1-n);
			x=cmp(x,v,l+1);
			x=cmp(x,v,l+1+n);
		}
		return x;
	}

	static int n,sum;
	static TreeMap<Integer,Integer> dic;

	public static void main(String[] args){
		in=new Scanner(System.in);
		while((n=in.nextInt())!=0){
			dic=new TreeMap<Integer,Integer>();
			TreeSet<Integer> rem=new TreeSet<Integer>();
			int v,l;
			for(sum=0;(v=in.nextInt())!=0|(l=in.nextInt())!=0;sum+=l){
				rem.add(sum-n-1);
				rem.add(sum-1);
				rem.add(sum+n-1);
				rem.add(sum-n);
				rem.add(sum);
				rem.add(sum+n);
				rem.add(sum-n+1);
				rem.add(sum+1);
				rem.add(sum+n+1);
				rem.add(sum/n*n);
				rem.add(sum/n*n+n);
				dic.put(sum,v);
			}
			rem.add(sum%n*n);
			int cnt=1;
			v=count(l=0);
			System.out.println(n);
			for(int i:rem){
				if(i<=0) continue;
				if(i>sum) break;
				int x=count(i);
				if(x==v){
					cnt+=i-l;
				}else{
					cnt+=i-l-1;
					System.out.println(v+" "+cnt);
					cnt=1;
					v=x;
				}
				l=i;
			}
			cnt+=sum-l-1;
			if(cnt>0) System.out.println(v+" "+cnt);
			System.out.println("0 0");
		}
		System.out.println(0);
	}
}
