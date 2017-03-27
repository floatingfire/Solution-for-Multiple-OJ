import java.io.*;

public class HDU_4552_怪盗基德的挑战书{
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

	static int[] gnxt(char p[]){
		int nxt[]=new int[p.length+1];
		for(int k=0,i=1;i<p.length;i++){
			for(;k!=0&&p[k]!=p[i];k=nxt[k]);
			nxt[i+1]=p[k]==p[i]?++k:k;
		}
		return nxt;
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(String str=in.readLine();str!=null;str=in.readLine()){
			int nxt[]=gnxt(str.toCharArray());
			int ans=0,i,f[]=new int[str.length()+1];
			for(i=1;i<f.length;f[i++]=1);
			for(i=str.length();i>0;f[nxt[i]]+=f[i],ans=(ans+f[i])%256,i--);
			out.println(ans);
			out.flush();
		}
		out.close();
	}
}