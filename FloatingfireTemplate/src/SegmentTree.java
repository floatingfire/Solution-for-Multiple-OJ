public class SegmentTree{
	// ��ά���������ֵΪ��
	// �����rst��Ǳ�ʾ�������ã�����ֻ�ڿ�ʼʱ����
	// ����д��ֻ��Ҫ����chg,push,query
	private class Node{
		int l,r,max,lz;

		Node(int l,int r){
			this.l=l;
			this.r=r;
		}

		int mid(){
			return (l+r)>>1;
		}

		void chg(int val){
			// ����ͳ����
			// max += val;
			// lazy += val;
		}
	}

	private Node tree[];

	public void build(int id,int l,int r){
		tree[id]=new Node(l,r);
		if(l==r){
			// ͳ�����ĳ�ʼ��
			// tree[id].max = value[l];
		}else{
			int mid=tree[id].mid();
			build(id<<1,l,mid);
			build(id<<1|1,mid+1,r);
			pushup(id);
		}
	}

	public int query(int id,int l,int r){
		if(l<=tree[id].l&&tree[id].r<=r) return tree[id].max;
		pushdown(id);
		int ans,mid=tree[id].mid();
		if(r<=mid) ans=query(id<<1,l,r);
		else if(mid<l) ans=query(id<<1|1,l,r);
		else ans=max(query(id<<1,l,r),query(id<<1|1,l,r));
		return ans;
	}

	public void update(int id,int l,int r,int k){
		// ���²���Ϊ��ĳ�������ϵ�ֵ����key
		if(l<=tree[id].l&&tree[id].r<=r) tree[id].chg(k);
		else{
			pushdown(id);
			int mid=(tree[id].l+tree[id].r)>>1;
			if(l<=mid) update(id<<1,l,r,k);
			if(mid<r) update(id<<1|1,l,r,k);
			pushup(id);
		}
	}

	private void pushup(int id){
		tree[id].max=max(tree[id<<1].max,tree[id<<1|1].max);
	}

	private void pushdown(int id){
		if(tree[id].lz!=0){
			tree[id<<1].chg(tree[id].lz);
			tree[id<<1|1].chg(tree[id].l);
			tree[id].lz=0;
		}
	}

	private int max(int a,int b){
		return a>b?a:b;
	}
}
