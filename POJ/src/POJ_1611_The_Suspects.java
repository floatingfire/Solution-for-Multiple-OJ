import java.io.*;

public class POJ_1611_The_Suspects{
	static StreamTokenizer in=new StreamTokenizer(new BufferedReader(new InputStreamReader(
			System.in)));

	static int nextInt() throws Exception{
		in.nextToken();
		return (int)in.nval;
	}

	static int f[],cnt[];

	static void init(int n){
		f=new int[n];
		cnt=new int[n];
		for(int i=0;i<n;i++){
			f[i]=i;
			cnt[i]=1;
		}
	}

	static int find(int a){
		if(f[a]!=a){
			f[a]=find(f[a]);
		}
		return f[a];
	}

	static void union(int a,int b){
		int ra=find(a),rb=find(b);
		if(ra!=rb){
			f[ra]=rb;
			cnt[rb]+=cnt[ra];
		}
	}

	public static void main(String[] args) throws Exception{
		while(true){
			int n=nextInt(),m=nextInt();
			if(n==0&&m==0){
				break;
			}
			init(n);
			for(int i=0;i<m;i++){
				int k=nextInt(),tmp=nextInt();
				for(int j=1;j<k;j++){
					union(tmp,nextInt());
				}
			}
			System.out.println(cnt[find(0)]);
		}
	}
}