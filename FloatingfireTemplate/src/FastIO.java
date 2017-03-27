import java.io.*;

public class FastIO{
	static StreamTokenizer in;
	static PrintWriter out;

	static void initio() throws Exception{
		in=new StreamTokenizer(new InputStreamReader(System.in));
		out=new PrintWriter(System.out);
	}

	static String next() throws Exception{
		in.nextToken();
		return in.sval;
	}

	static int nextInt() throws Exception{
		in.nextToken();
		return (int)in.nval;
	}
}
