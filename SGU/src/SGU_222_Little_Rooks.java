import java.util.*;

//������򵥵�������ϵ�д��^_^?,���¸ĳ�״ѹdp���ԡ���
public class SGU_222_Little_Rooks{
	void run(){
		Scanner scan=new Scanner(System.in);
		int n=scan.nextInt();
		long k=scan.nextLong();
		if(k>n) System.out.println("0");
		else{
			long ans=1;
			int i;
			for(i=2;i<=n;ans*=i++);
			for(i=2;i<=n-k;ans/=i++);
			for(ans*=ans,i=2;i<=k;ans/=i++);
			System.out.println(ans);
		}
	}

	public static void main(String[] args){
		new SGU_222_Little_Rooks().run();
	}
}
