import java.io.*;

public class HDU_4091_Zombies_Treasure_Chest{
	static BufferedReader in;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static int Int() throws Exception{
		int ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	static long Int64() throws Exception{
		long ch,x;
		for(ch=in.read();ch<'0'&&ch!=-1||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x;
	}

	static long max(long a,long b){
		return a>b?a:b;
	}

	static long gcd(long a,long b){
		for(long t;a!=0;t=a,a=b%a,b=t);
		return b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int tst=1,ttt=Int();tst<=ttt;tst++){
			long m=Int64(),s1=Int64(),v1=Int64(),s2=Int64(),v2=Int64();
			long i,ans=0,lcm=s1*s2/gcd(s1,s2);
			if(s1<s2){
				i=s1;
				s1=s2;
				s2=i;
				i=v1;
				v1=v2;
				v2=i;
			}
			long cnt=m/lcm;
			if(cnt>0){
				for(cnt=(cnt-1)*lcm,i=0,m=(m%lcm+lcm),lcm=m/s1;i<=lcm;i++)
					ans=max(ans,i*v1+(m-i*s1)/s2*v2);
				if(s1*v2>s2*v1) ans+=cnt/s2*v2;
				else ans+=cnt/s1*v1;
			}else for(i=0,lcm=m/s1;i<=lcm;ans=max(ans,i*v1+(m-i*s1)/s2*v2),i++);
			out.println("Case #"+tst+": "+ans);
		}
		out.close();
	}
}