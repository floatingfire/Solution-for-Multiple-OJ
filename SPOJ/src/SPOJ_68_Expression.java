import java.io.*;
import java.util.*;

public class SPOJ_68_Expression{
	static BufferedReader in;
	static StringTokenizer stk;
	static PrintWriter out;

	static String next() throws Exception{
		if(stk==null||!stk.hasMoreTokens()){
			stk=new StringTokenizer(in.readLine());
		}
		return stk.nextToken();
	}

	static int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	public static void main(String[] args) throws Exception{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
		for(int tst=nextInt();tst>0;tst--){
			int n=nextInt()>>1,k=nextInt();
			String s=next();
			int f[][][]=new int[n][361][n+1];
			for(int i=0;i<n;i++){
				for(int j=0;j<361;j++){
					for(int l=0;l<=n;l++){
						f[i][j][l]=1000000000;
					}
				}
			}
			if(s.charAt(0)=='+') f[0][180+s.charAt(1)-'0'][0]=0;
			else{
				f[0][180-s.charAt(1)+'0'][0]=0;
				f[0][180-s.charAt(1)+'0'][1]=1;
			}
			for(int i=0;i<n-1;i++){
				int x=s.charAt(i+1<<1|1)-'0';
				for(int j=0;j<361;j++){
					for(int l=0;l<=n;l++){
						if(f[i][j][l]==1000000000) continue;
						for(int o=0;o<=l;o++){
							if(s.charAt(i+1<<1)=='+'){
								if((o&1)==0){
									if(j+x<361&&f[i+1][j+x][o]>f[i][j][l]) f[i+1][j
											+x][o]=f[i][j][l];
								}else{
									if(j-x>=0&&f[i+1][j-x][o]>f[i][j][l]) f[i+1][j
											-x][o]=f[i][j][l];
								}
							}else{
								if((o&1)==0){
									if(j-x>=0){
										if(f[i+1][j-x][o]>f[i][j][l]) f[i+1][j
												-x][o]=f[i][j][l];
										if(o+1<=n
												&&f[i+1][j-x][o+1]>f[i][j][l]+1) f[i+1][j
												-x][o+1]=f[i][j][l]+1;
									}
								}else{
									if(j+x<361){
										if(f[i+1][j+x][o]>f[i][j][l]) f[i+1][j
												+x][o]=f[i][j][l];
										if(o+1<=n
												&&f[i+1][j+x][o+1]>f[i][j][l]+1) f[i+1][j
												+x][o+1]=f[i][j][l]+1;
									}
								}
							}
						}
					}
				}
			}
			int ans=1000000000;
			for(int i:f[n-1][180+k]){
				ans=Math.min(ans,i);
			}
			out.println(ans==1000000000?"NO":ans);
		}
		out.close();
	}
}