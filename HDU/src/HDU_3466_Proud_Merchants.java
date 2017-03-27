import java.io.*;
import java.util.*;

public class HDU_3466_Proud_Merchants{
	static BufferedReader in;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static void initiot() throws Exception{
		in=new BufferedReader(new FileReader("test"));
		out=new PrintWriter(new File("outtest"));
	}

	static int Int() throws Exception{
		int ch,x,s=1;
		for(ch=in.read();ch<'0'&&ch!=-1&&ch!='-'||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		if(ch=='-'){
			s=-1;
			ch=in.read();
		}
		for(x=0;ch>='0'&&ch<='9';x=x*10+ch-'0',ch=in.read());
		return x*s;
	}

	static class Product implements Comparable<Product>{
		int c,p,v;

		Product(int c,int p,int v){
			this.c=c;
			this.p=p-c;
			this.v=v;
		}

		public int compareTo(Product pr){
			return pr.p-p;
		}
	}

	public static void main(String[] args) throws Exception{
		initio();
		int n,i,j,ans,f[];
		Product[] p=new Product[501];
		while((n=Int())!=-1){
			for(i=0,f=new int[Int()+1];i<n;p[i++]=new Product(Int(),Int(),Int()));
			for(Arrays.sort(p,0,n),ans=i=0;i<n;i++)
				for(j=p[i].p;j+p[i].c<f.length;j++){
					if(f[j]<f[j+p[i].c]+p[i].v) f[j]=f[j+p[i].c]+p[i].v;
					if(ans<f[j]) ans=f[j];
				}
			out.println(ans);
			out.flush();
		}
		out.close();
	}
}