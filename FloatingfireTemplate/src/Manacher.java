public class Manacher{
	// O(n)��������Ӵ����㷨
	static char trans(int id,String s){
		// Ϊ�˱����ڸ��ַ��м����������д�˷��������±�ת��
		if(id<=0) return '$';
		else if((id&1)==1) return '#';
		else return s.charAt(id-1>>1);
	}

	static boolean check(int lid,int rid,String s){
		// ���Գ�λ���Ƿ�ƥ��
		return trans(lid,s)==trans(rid,s);
	}

	static int getlength(String s){
		// rds�����¼���Ĵ��뾶
		int[] rds=new int[s.length()+1<<1];
		// id��lf�ֱ��¼��ǰ�ܸ��ǵ�����˵Ļ��Ĵ����е���뾶��ans��¼��ǰ����Ĵ��ĳ���
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
