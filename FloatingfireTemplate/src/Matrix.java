public class Matrix{
	public long[][] mul(long a[][],long b[][]){
		if(a[0].length==b.length){
			long ans[][]=new long[a.length][b[0].length];
			for(int i=0;i<a.length;i++){
				for(int j=0;j<b[0].length;j++){
					for(int k=0;k<b.length;k++){
						ans[i][j]+=a[i][k]*b[k][j];
					}
				}
			}
			return ans;
		}
		return null;
	}

	public long[][] pow(long a[][],int n){
		if(a.length==a[0].length){
			long ans[][]=new long[a.length][a[0].length];
			for(int i=0;i<a.length;i++){
				ans[i][i]=1;
			}
			while(n>0){
				if((n&1)==1){
					ans=mul(ans,a);
				}
				a=mul(a,a);
				n>>=1;
			}
			return ans;
		}
		return null;
	}
}