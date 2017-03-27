import java.io.*;
import java.util.*;

public class POJ_3899_The_Lucky_Numbers{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static void initiot() throws Exception{
		in=new BufferedReader(new FileReader("test"));
		out=new PrintWriter(new File("outtest"));
	}

	static String next() throws Exception{
		while(stk==null||!stk.hasMoreTokens())
			stk=new StringTokenizer(in.readLine());
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	static long cal(char s[]){
		long ans=0,i=1,l=s.length;
		for(int c:s){
			if(c>'7') ans+=1L<<l-i+1;
			else if(c>'4') ans+=1L<<l-i;
			if(c!='4'&&c!='7') break;
			i++;
		}
		for(i=1;i<l;ans+=1L<<i++);
		return ans;
	}

	static long caleq(char s[],char p[]){
		long ans=0;
		int i=s.length-p.length,m=i;
		boolean b=false;
		for(char c:p){
			if(c<s[i]) if(m==0||s[m-1]=='4'||s[m-1]=='7') b=true;
			if(c!=s[i++]) break;
		}
		if(i==s.length&&s[s.length-1]==p[p.length-1]) b=true;
		for(i=0;i<m;i++){
			if(s[i]>'7') ans+=1L<<m-i;
			else if(s[i]>'4') ans+=1L<<m-i-1;
			if(s[i]!='4'&&s[i]!='7') break;
		}
		return ans+(i==m&&b?1:0);
	}

	static long calrv(char s[],char p[]){
		long ans=0;
		String ls="";
		for(char c:p){
			if(c<'4') ans+=caleq(s,(7+ls).toCharArray())+caleq(s,(4+ls).toCharArray());
			else if(c=='4'){
				ans+=caleq(s,(7+ls).toCharArray());
				ls=4+ls;
			}else if(c<'7') ans+=caleq(s,(7+ls).toCharArray());
			else if(c=='7') ls=7+ls;
			if(c!='4'&&c!='7') break;
		}
		return ans;
	}

	static long max(long a,long b){
		return a>b?a:b;
	}

	public static void main(String[] args) throws Exception{
		initio();
		for(long ans,tst=nextInt();tst>0;tst--){
			char[] a=next().toCharArray(),b=next().toCharArray();
			b[b.length-1]++;
			ans=cal(b)-cal(a);
			b[b.length-1]--;
			a[a.length-1]--;
			ans+=calrv(a,a)+calrv(b,b);
			if(a.length==b.length) ans-=calrv(a,b)<<1;
			out.println(ans);
		}
		out.close();
	}
}