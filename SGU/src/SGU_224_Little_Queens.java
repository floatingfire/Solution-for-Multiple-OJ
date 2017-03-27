import java.util.*;

//DFS+位运算，状态太多，状压DPhold不住……
public class SGU_224_Little_Queens{
	int n,k,sum;

	void dfs(int row,int md,int vd,int cou,int now){
		if(now>n) return;
		if(cou<k){
			int pos=((1<<n)-1)&(~(row|md|vd));
			while(pos>0){
				int lbt=-pos&pos;
				pos-=lbt;
				dfs(row|lbt,(md|lbt)<<1,(vd|lbt)>>1,cou+1,now+1);
			}
			dfs(row,md<<1,vd>>1,cou,now+1);
		}else sum++;
	}

	void run(){
		Scanner scan=new Scanner(System.in);
		n=scan.nextInt();
		k=scan.nextInt();
		dfs(0,0,0,0,0);
		System.out.println(sum);
	}

	public static void main(String[] args){
		new SGU_224_Little_Queens().run();
	}
}
