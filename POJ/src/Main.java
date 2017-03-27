import java.util.HashMap;
import java.util.Scanner;

public class Main{
	static Scanner in;

	public static void main(String[] args) throws Exception{
		in=new Scanner(System.in);
		String sss[]={
				"January","February","March","April","May","June","July","August","September",
				"October","November","December"
		};
		HashMap<String,Integer> dic=new HashMap<>();
		for(int i=0;i<sss.length;i++)
			dic.put(sss[i],i);
		for(int tst=1,ttt=in.nextInt();tst<=ttt;tst++){
			int a=dic.get(in.next())<=1&Integer.parseInt(in.next().replaceAll(",",""))<=29?-1:0;
			a+=in.nextInt();
			a=a/100*24+a/400+a%100/4;
			int b=dic.get(in.next())<=1&Integer.parseInt(in.next().replaceAll(",",""))<29?-1:0;
			b+=in.nextInt();
			b=b/100*24+b/400+b%100/4;
			System.out.println("Case #"+tst+": "+(b-a));
		}
	}
}
