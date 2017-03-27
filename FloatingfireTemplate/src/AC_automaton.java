import java.util.*;

public class AC_automaton{
	// 裸模版为统计字典中的字符在靶串中共出现了多少次，计入重复串
	static class Node{
		// f表示fail指针，g表示goto函数
		Node f,g[]=new Node[26];
		// 此处o表示当前节点匹配到的单词数
		int o;
		// 转化为trie图时需维护节点标号，ac自动机加dp常用
		// int id;
		// Node(int id){this.id=id;}
	}

	// 转化为trie图时需统计节点个数
	// static int cnt;
	// static Node tree[];
	static Node root;

	// 根据字母范围确定trans方法，如
	// sttaic int trans(char c){return c-'a';}
	static void insert(String p){
		// 构建trie树，goto[i]表示经过标记为i的边到达的节点
		Node cur=root;
		for(char c:p.toCharArray()){
			if(cur.g[c-'a']==null) cur.g[c-'a']=new Node();
			cur=cur.g[c-'a'];
		}
		cur.o++;
	}

	static void build(){
		// bfs构建fail指针，同时在trie树上添加虚边
		// fail指针指向由根到当前节点的路径（表示某个单词串的前缀）在这棵trie树上的最长真后缀的末节点
		// 虚边i为指向当前节点的fail指针所指节点的goto[i]节点的边
		// 此时goto[i]为通过标记为i的边或虚边所到达的节点
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
					// 一般dp或矩阵优化是需要叠加统计，需要去重时不用
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
			// 统计量不能重复时需要沿fail指针统计并更新
			// for(Node tmp=cur;tmp.o!=-1;cnt+=tmp.o,tmp.o=-1tmp=tmp.f);
		}
		return cnt;
	}
}