import java.io.*;
import java.util.*;

public class HDU_4314_Save_the_dwarfs{
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

	static class Dwarf implements Comparable<Dwarf>{
		int a,b;

		Dwarf() throws Exception{
			a=Int();
			b=Int();
		}

		public int compareTo(Dwarf d){
			return (d.a+d.b)-(a+b);
		}
	}

	static int min(int a,int b){
		return a<b?a:b;
	}

	static int max(int a,int b){
		return a>b?a:b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		final int MAX=1000000000;
		int i,j,n,h,s[],f[];
		Dwarf d[];
		while((n=Int())!=-1){
			for(f=new int[n+1],d=new Dwarf[n+1],i=1;i<=n;d[i++]=new Dwarf());
			for(Arrays.sort(d,1,d.length),s=new int[n+1],i=1;i<=n;s[i]=s[i-1]+d[i].a,i++);
			for(h=Int(),Arrays.fill(f,MAX),f[0]=0,i=1;i<=n;i++)
				for(j=i;j>=1;f[j]=min(f[j]-d[i].a,max(f[j-1],h-s[i]-d[i].b)),j--);
			for(i=n;i>=0&&f[i]>0;i--);
			out.println(i);
		}
		out.close();
	}
}