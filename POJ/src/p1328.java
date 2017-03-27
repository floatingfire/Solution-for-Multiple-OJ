import java.util.Scanner;
public class p1328{
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);		
		int num=scan.nextInt(),dist=scan.nextInt();
		int rt=0,rw;
		boolean fail;
		while(num!=0){
			rt++;
			fail=false;
			int[][]isl=new int[2][num];
			for(int i=0;i<num;i++){
				isl[0][i]=scan.nextInt();
				isl[1][i]=scan.nextInt();
				if(dist<isl[1][i]){
					fail=true;
				}
			}
			if(fail)
				rw=-1;
			else
				rw=test(num,dist,isl);
			System.out.println("case "+rt+": "+rw);
			num=scan.nextInt();
			dist=scan.nextInt();			
		}	
	}
	public static int test(int num,int dist,int[][]isl){
		int count=1;		
		double[][]reg=new double[2][num];
		for(int i=0;i<num;i++){
			double temp=Math.sqrt(dist*dist+isl[1][i]);
			reg[0][i]=isl[0][i]-temp;
			reg[1][i]=isl[0][i]+temp;
		}
		for(int i=0;i<num;i++){
			int min=i;
			for(int j=i+1;j<num;j++){
				if(reg[0][j]<reg[0][min])
					min=j;
			}
			double temp=reg[0][i];
			reg[0][i]=reg[0][min];
			reg[0][min]=temp;
			temp=reg[1][i];
			reg[1][i]=reg[1][min];
			reg[1][min]=temp;
		}
		double t=reg[1][0];
		for(int i=1;i<num;i++){
			if(reg[0][i]>t){
				count++;
				t=reg[1][i];
			}	
			else{
				if(reg[1][i]<t)t=reg[1][i];													
			}
		}
		return count;
	}
}