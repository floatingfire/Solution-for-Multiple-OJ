import java.io.*;
import java.util.StringTokenizer;

public class POJ_3261_Milk_Patterns{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static int Int() throws Exception{
		int ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		else ch-='0';
		for(x=0;ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	static final int MAX=1000001;
	static int n,k,s[],ra[],sa[],he[];

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

	static void da(){
		int n=s.length;
		sa=new int[n];
		ra=new int[n];
		he=new int[n];
		for(int i=0;i<n;ra[i]=s[i],he[i]=i,i++);
		for(int l=1,m=sort(0,MAX,n);m<n;m=sort(l,m,n),l<<=1){
			int p=0;
			for(int i=n-l;i<n;he[p++]=i++);
			for(int i=0;i<n;i++)
				if(sa[i]>=l) he[p++]=sa[i]-l;
		}
		for(int i=0,k=0;i<n;i++){
			k=k==0?k:k-1;
			int j=ra[i]==0?n:sa[ra[i]-1];
			for(;j<n&&s[i+k]==s[j+k];k++);
			he[ra[i]]=k;
		}
	}

	static boolean check(int len){
		int l=0,r=0;
		for(int i=1;i<n;r=i++)
			if(len>he[i]) if(r-l+1<k) l=i;
			else return true;
		return !(r-l+1<k);
	}

	public static void main(String[] args) throws Exception{
		initio();
		out=new PrintWriter(System.out);
		n=Int();
		k=Int();
		s=new int[++n];
		for(int i=0;i<n-1;s[i++]=Int()+1);
		sa=new int[n];
		ra=new int[n];
		he=new int[n];
		da();
		int l=0,r=n-1,mid=(l+r)>>1;
		while(r-l>1){
			if(check(mid)) l=mid;
			else r=mid;
			mid=(l+r)>>1;
		}
		out.println(check(r)?r:l);
		out.close();
	}
}