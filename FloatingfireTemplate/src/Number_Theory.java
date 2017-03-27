public class Number_Theory{
	// 筛法求素数
	static void primesleve(int n){
		boolean dic[]=new boolean[n+1];
		for(int j,i=2;i<=n;i++)
			if(!dic[i]) for(j=i<<1;j<=n;dic[j]=true,j+=i);
	}

	// 最大公约数
	static int gcd(int a,int b){
		return a==0?b:gcd(b%a,a);
	}

	static long gcd(long a,long b){
		for(long t;a!=0;t=a,a=b%a,b=t);
		return b;
	}

	// 最小公倍数
	static long lcm(long a,long b){
		return a/gcd(a,b)*b;
	}

	// 筛法求欧拉函数
	static void eulersleve(int n){
		int dic[]=new int[n];
		for(int i=2;i<n;i++){
			if(dic[i]!=0) continue;
			for(int j=i;j<n;j+=i){
				if(dic[j]==0) dic[j]=j;
				dic[j]=dic[j]/i*(i-1);
			}
		}
	}

	// 扩展欧几里得
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
}
