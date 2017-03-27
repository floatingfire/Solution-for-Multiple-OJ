import java.io.*;
import java.util.*;

public class HDU_2222_Keywords_Search{
	static class Node{
		Node fail,next[]=new Node[26];
		int out;
	}

	static Node root;

	static void insert(String p){
		Node cur=root;
		for(char c:p.toCharArray()){
			if(cur.next[c-'a']==null) cur.next[c-'a']=new Node();
			cur=cur.next[c-'a'];
		}
		cur.out++;
	}

	static void build(){
		Queue<Node> q=new LinkedList<Node>();
		Node cur;
		for(int i=0;i<26;i++)
			if(root.next[i]!=null){
				q.add(root.next[i]);
				root.next[i].fail=root;
			}else root.next[i]=root;
		while(!q.isEmpty()){
			cur=q.poll();
			for(int i=0;i<26;i++){
				if(cur.next[i]!=null){
					q.add(cur.next[i]);
					cur.next[i].fail=cur.fail.next[i];
				}else cur.next[i]=cur.fail.next[i];
			}
		}
	}

	static int search(String t){
		Node cur=root;
		int cnt=0;
		for(char c:t.toCharArray()){
			cur=cur.next[c-'a'];
			for(Node tmp=cur;tmp.out!=-1;tmp=tmp.fail){
				cnt+=tmp.out;
				tmp.out=-1;
			}
		}
		return cnt;
	}

	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception{
		in.nextToken();
		return (int)in.nval;
	}

	static String next() throws Exception{
		in.nextToken();
		return in.sval;
	}

	public static void main(String[] args) throws Exception{
		in=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int cs=nextInt();cs>0;cs--){
			root=new Node();
			root.out=-1;
			for(int n=nextInt();n>0;n--){
				insert(next());
			}
			build();
			out.println(search(next()));
		}
		out.close();
	}
}