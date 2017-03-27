import java.util.*;

public class AVLTree<T> {
	private class Node {
		T val;
		Node l, r;
		int h;

		private Node(T val) {
			this.val = val;
			this.h = 0;
		}
	}

	private int size;
	private Node root;
	private Comparator<T> cmprtr;

	public AVLTree() {
		root = null;
		size = 0;
	}

	private Node rightrotate(Node nd) {
		Node tmp = nd.l;
		nd.l = tmp.r;
		tmp.r = nd;
		nd.h = Math.max(nd.l.h, nd.r.h);
		tmp.h = Math.max(tmp.l.h, tmp.r.h);
		return tmp;
	}

	private Node leftrotate(Node nd) {
		Node tmp = nd.r;
		nd.r = tmp.l;
		tmp.l = nd;
		nd.h = Math.max(nd.l.h, nd.r.h);
		tmp.h = Math.max(tmp.l.h, tmp.r.h);
		return tmp;
	}

	public void insert(T val) {
		insert(root, val);
	}

	private Node insert(Node rt, T val) {
		if (rt == null) {
			rt = new Node(val);
			size++;
		} else if (cmprtr.compare(val, rt.val) == -1) {
			rt.l = insert(rt.l, val);
			if (rt.l.h - rt.r.h == 2) {
				if (cmprtr.compare(val, rt.l.val) == -1) {
					rt = rightrotate(rt);
				} else {
					rt.l = leftrotate(rt.l);
					rt = rightrotate(rt);
				}
			}
		} else {
			rt.r = insert(rt.r, val);
			if (rt.r.h - rt.l.h == 2) {
				if (cmprtr.compare(val, rt.r.val) == -1) {
					rt.r = rightrotate(rt.r);
					rt = leftrotate(rt);
				} else {
					rt = leftrotate(rt);
				}
			}
		}
		rt.h=Math.max(rt.l.h,rt.r.h)+1;
		return rt;
	}
	public void delete(T val){
		delete(root,val);
	}
	private Node delete(Node rt,T val){
		
		return rt;
	}
}
