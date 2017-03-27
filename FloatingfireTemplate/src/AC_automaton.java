import java.util.*;

public class AC_automaton{
	// ��ģ��Ϊͳ���ֵ��е��ַ��ڰд��й������˶��ٴΣ������ظ���
	static class Node{
		// f��ʾfailָ�룬g��ʾgoto����
		Node f,g[]=new Node[26];
		// �˴�o��ʾ��ǰ�ڵ�ƥ�䵽�ĵ�����
		int o;
		// ת��Ϊtrieͼʱ��ά���ڵ��ţ�ac�Զ�����dp����
		// int id;
		// Node(int id){this.id=id;}
	}

	// ת��Ϊtrieͼʱ��ͳ�ƽڵ����
	// static int cnt;
	// static Node tree[];
	static Node root;

	// ������ĸ��Χȷ��trans��������
	// sttaic int trans(char c){return c-'a';}
	static void insert(String p){
		// ����trie����goto[i]��ʾ�������Ϊi�ıߵ���Ľڵ�
		Node cur=root;
		for(char c:p.toCharArray()){
			if(cur.g[c-'a']==null) cur.g[c-'a']=new Node();
			cur=cur.g[c-'a'];
		}
		cur.o++;
	}

	static void build(){
		// bfs����failָ�룬ͬʱ��trie����������
		// failָ��ָ���ɸ�����ǰ�ڵ��·������ʾĳ�����ʴ���ǰ׺�������trie���ϵ�����׺��ĩ�ڵ�
		// ���iΪָ��ǰ�ڵ��failָ����ָ�ڵ��goto[i]�ڵ�ı�
		// ��ʱgoto[i]Ϊͨ�����Ϊi�ı߻����������Ľڵ�
		Queue<Node> q=new LinkedList<Node>();
		Node cur;
		for(int i=0;i<26;i++)
			if(root.g[i]!=null){
				q.add(root.g[i]);
				root.g[i].f=root;
			}else root.g[i]=root;
		while(!q.isEmpty()){
			cur=q.poll();
			for(int i=0;i<26;i++)
				if(cur.g[i]!=null){
					q.add(cur.g[i]);
					cur.g[i].f=cur.f.g[i];
					// һ��dp������Ż�����Ҫ����ͳ�ƣ���Ҫȥ��ʱ����
					cur.g[i].o+=cur.g[i].f.o;
				}else cur.g[i]=cur.f.g[i];
		}
	}

	static int search(String t){
		Node cur=root;
		int cnt=0;
		for(char c:t.toCharArray()){
			cur=cur.g[c-'a'];
			cnt+=cur.o;
			// ͳ���������ظ�ʱ��Ҫ��failָ��ͳ�Ʋ�����
			// for(Node tmp=cur;tmp.o!=-1;cnt+=tmp.o,tmp.o=-1tmp=tmp.f);
		}
		return cnt;
	}
}