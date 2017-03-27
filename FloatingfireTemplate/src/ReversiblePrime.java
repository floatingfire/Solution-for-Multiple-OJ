public class ReversiblePrime{
	static final int prime[]={1,2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};
	static long maxsum,bestnum,n;

	static void getantiprime(long num,int k,long sum,int limit){
		// num����ǰö�ٵ�������k��ö�ٵ��ĵ�k��������ӣ�sum��������Լ��������limit�������Ӹ������ޣ�
		long temp;
		if(sum>maxsum){
			maxsum=sum;
			bestnum=num; // ���Լ���������࣬�����Ž����Ϊ��ǰ����
		}
		if(sum==maxsum&&bestnum>num) bestnum=num; // ���Լ��������ͬ�������Ž����Ϊ��С������
		if(k>15) return;
		temp=num;
		for(int i=1;i<=limit;i++){ // ��ʼö��ÿ�������ӵĸ�����
			if(temp*prime[k]>n) break;
			temp=temp*prime[k]; // �۳˵���ǰ����
			getantiprime(temp,k+1,sum*(i+1),i); // ������һ��������
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