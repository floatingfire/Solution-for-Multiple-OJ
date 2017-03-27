import java.io.BufferedReader;
import java.io.InputStreamReader;

public class POJ_1006_Biorhythms{
	public static int exgcd(int r1,int r2){
		int x1=0,x2=1,y1=1,y2=0;
		int r=r2%r1,q=r2/r1,x=0,y=1;
		while(r!=0){
			x=x2-q*x1;
			y=y2-q*y1;
			x2=x1;
			x1=x;
			y2=y1;
			y1=y;
			r2=r1;
			r1=r;
			r=r2%r1;
			q=r2/r1;
		}
		return x;
	}

	public static void main(String[] args) throws Exception{
		int tst=1,a[]={
				5544,14421,1288,
		};
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String str;
		while(!(str=in.readLine()).startsWith("-1 -1 -1 -1")){
			args=str.split(" ");
			int ans=0;
			for(int i=0;i<3;i++)
				ans+=a[i]*Integer.parseInt(args[i]);
			ans=(ans-Integer.parseInt(args[3])+21252)%21252;
			if(ans==0)ans=21252;
			System.out.println("Case "+tst+++": the next triple peak occurs in "+ans+" days.");
		}
	}
}