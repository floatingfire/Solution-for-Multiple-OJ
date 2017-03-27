public class SurfixArray{
	// 此处只有倍增算法，height数组在da()中一起求出
	static final int MAX=128;
	static final double log2=Math.log(2);
	static int[] sa,ra,he,min[];

	static int sort(int l,int m,int n){
		int i,cnt[]=new int[m];
		for(i=0;i<n;cnt[ra[i++]]++);
		for(i=1;i<m;cnt[i]+=cnt[i-1],i++);
		for(i=n-1;i>=0;sa[--cnt[ra[he[i]]]]=he[i],i--);
		int[] tmp=ra;
		ra=he;
		he=tmp;
		for(ra[sa[0]]=m=0,i=1;i<n;i++)
			ra[sa[i]]=he[sa[i]]==he[sa[i-1]]&&he[sa[i]+l]==he[sa[i-1]+l]?m:++m;
		return m+1;
	}

	static void da(String s){
		int n=s.length();
		sa=new int[n];
		ra=new int[n];
		he=new int[n];
		for(int i=0;i<n;ra[i]=s.charAt(i),he[i]=i,i++);
		for(int l=1,m=sort(0,MAX,n);m<n;m=sort(l,m,n),l<<=1){
			int p=0;
			for(int i=n-l;i<n;he[p++]=i++);
			for(int i=0;i<n;i++)
				if(sa[i]>=l) he[p++]=sa[i]-l;
		}
		for(int i=0,k=0;i<n;i++){
			k=k==0?k:k-1;
			int j=ra[i]==0?n:sa[ra[i]-1];
			for(;j<n&&s.charAt(i+k)==s.charAt(j+k);k++);
			he[ra[i]]=k;
		}
	}

	static void initrmq(int n){
		min=new int[(int)(Math.log(n)/log2+1)][n];
		for(int i=0;i<n;i++){
			min[0][i]=he[i];
		}
		for(int i=1;i<min.length;i++){
			for(int j=0;j+(1<<i-1)<n;j++){
				min[i][j]=Math.min(min[i-1][j],min[i-1][j+(1<<i-1)]);
			}
		}
	}

	static int rmq(int l,int r){
		l=ra[l];
		r=ra[r];
		if(l>r){
			int t=l;
			l=r;
			r=t;
		}
		int k=(int)(Math.log(r-l)/log2);
		return Math.min(min[k][l+1],min[k][r-(1<<k)+1]);
	}
}
