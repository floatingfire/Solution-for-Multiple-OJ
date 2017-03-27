import java.util.*;
public class SGU_225_Little_Knights {
	long rem[][][];
	int sum[],s[],num,n,k;
	void dfs(int now,int state){
		if(now==n){
			s[++num]=state;
		}else{
			dfs(now+1,state<<2);
			if((state&3)==0){
				dfs(now+1,(state<<2)^1);
			}
		}
	}void dp(){
		
	}void run(){
		Scanner scan=new Scanner(System.in);
		n=scan.nextInt();
		k=scan.nextInt();
		rem=new long[n+1][1<<n][k];
		sum=new int[1<<n];
		s=new int[1<<n];
		dfs(0,0);
		dp();
		long ans=0;
		for(int i=0;i<rem[n].length;i++){
			ans+=rem[n][i][k];
		}System.out.println(ans);
	}
	public static void main(String[]args){
		new SGU_225_Little_Knights().run();
	}
}
