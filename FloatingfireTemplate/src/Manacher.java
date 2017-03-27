public class Manacher{
	// O(n)求最长回文子串的算法
	static char trans(int id,String s){
		// 为了避免在各字符中间插入间隔符，写此方法用于下标转换
		if(id<=0) return '$';
		else if((id&1)==1) return '#';
		else return s.charAt(id-1>>1);
	}

	static boolean check(int lid,int rid,String s){
		// 检查对称位置是否匹配
		return trans(lid,s)==trans(rid,s);
	}

	static int getlength(String s){
		// rds数组记录回文串半径
		int[] rds=new int[s.length()+1<<1];
		// id与lf分别记录当前能覆盖到最左端的回文串的中点与半径，ans记录当前最长回文串的长度
		int id=1,lf=1,ans=0;
		for(int i=1;i<rds.length;i++){
			if(i<id+lf) rds[i]=Math.min(rds[(id<<1)-i],id+lf-i);
			for(int j=i+rds[i];j<rds.length&&check((i<<1)-j,j,s);rds[i]++,j++);
			if(id+lf<i+rds[i]) lf=rds[id=i];
			ans=Math.max(ans,rds[i]-1);
		}
		return ans;
	}
}
