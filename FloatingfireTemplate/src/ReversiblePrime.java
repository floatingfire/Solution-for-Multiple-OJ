public class ReversiblePrime{
	static final int prime[]={1,2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};
	static long maxsum,bestnum,n;

	static void getantiprime(long num,int k,long sum,int limit){
		// num：当前枚举到的数，k：枚举到的第k大的质因子；sum：该数的约数个数；limit：质因子个数上限；
		long temp;
		if(sum>maxsum){
			maxsum=sum;
			bestnum=num; // 如果约数个数更多，将最优解更新为当前数；
		}
		if(sum==maxsum&&bestnum>num) bestnum=num; // 如果约数个数相同，将最优解更新为较小的数；
		if(k>15) return;
		temp=num;
		for(int i=1;i<=limit;i++){ // 开始枚举每个质因子的个数；
			if(temp*prime[k]>n) break;
			temp=temp*prime[k]; // 累乘到当前数；
			getantiprime(temp,k+1,sum*(i+1),i); // 继续下一步搜索；
		}
	}

	public static void main(String[] args){
		for(n=5000;n>0;n=bestnum-1){
			maxsum=bestnum=0;
			getantiprime(1,1,1,50);
			System.out.print("{"+bestnum+","+maxsum+"}"+",");
		}
	}
}