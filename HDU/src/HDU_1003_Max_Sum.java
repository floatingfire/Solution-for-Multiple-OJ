import java.io.*;
import java.util.*;

public class HDU_1003_Max_Sum{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static int Int() throws Exception{
		int ch,x,s=1;
		for(ch=in.read();ch<'0'&&ch!=-1&&ch!='-'||ch>'9';ch=in.read());
		if(ch==-1) return -1;
		if(ch=='-'){
			s*=-1;
			ch=in.read();
		}
		for(x=0,ch-='0';ch>=0&&ch<=9;x=x*10+ch,ch=in.read()-'0');
		return x*s;
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(int ttt=Int(),tst=1;tst<=ttt;tst++){
			int n=Int(),ans=-1000000000,l=0,r=0,tmp=0,tmpl=1;
			for(int i=0;i<n;i++){
				int j=Int();
				if(tmp>=0) tmp+=j;
				else{
					tmp=j;
					tmpl=i+1;
				}
				if(ans<tmp){
					ans=tmp;
					l=tmpl;
					r=i+1;
				}
			}
			out.println("Case "+tst+":");
			out.println(ans+" "+l+" "+r);
			if(tst<ttt) out.println();
		}
		out.close();
	}
}