public class KMP{
	//初始化前缀函数(相当于ac自动机中的fail指针)
	private static int[] gf(char p[]){
		int f[]=new int[p.length+1];
		for(int k=0,i=1;i<p.length;i++){
			for(;k>0&&p[k]!=p[i];k=f[k]);
			if(p[k]==p[i]) k++;
			f[i+1]=k;
		}
		return f;
	}

	public static int sch(char t[],char p[]){
		int cnt=0,f[]=gf(p);
		for(int k=0,i=0;i<t.length;i++){
			for(;k>0&&p[k]!=t[i];k=f[k]);
			if(p[k]==t[i]) k++;
			if(k==p.length){
				System.out.println("Pattern occurs with shift"+(i-k+1));
				cnt++;
				k=f[k];
			}
		}
		return cnt;
	}
}
