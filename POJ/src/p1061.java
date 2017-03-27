import java.util.Scanner;

public class p1061{
	private static Scanner in;

	public static void main(String[] args){
		in=new Scanner(System.in);
		long p=in.nextLong()-in.nextLong();
		long v=in.nextLong()-in.nextLong();
		long l=in.nextLong();
		System.out.println(lcm((l-p)%l,v)/v);
		System.out.println(p+" "+v);
	}
}
