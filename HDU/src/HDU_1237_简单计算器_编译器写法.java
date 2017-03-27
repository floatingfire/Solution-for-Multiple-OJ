/*
 * factor:=number(/*number)
 * expression:=factor(+-factor)
 */

import java.util.*;
import java.io.*;

enum Word {
	NUMBER, MUL, MIN, SUM, DIV
}

class Token {
	String str;
	Word word;

	Token(String str, Word word) {
		this.str = str;
		this.word = word;
	}
}

class Analiser {
	static ArrayList<Token> tokenlist;
	static Token tkn;
	static int j;

	void Analise(String str) throws Exception {
		j = -1;
		StringBuilder sb = new StringBuilder();
		tokenlist = new ArrayList<Token>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ')
				continue;
			else
				sb.append(str.charAt(i));
		}
		str = sb.toString();
		for (int i = 0; i < str.length();) {
			sb = new StringBuilder();
			for (; i < str.length() && Character.isDigit(str.charAt(i)); i++) {
				sb.append(str.charAt(i));
			}
			if (sb.length() != 0) {
				tokenlist.add(new Token(sb.toString(), Word.NUMBER));
			} else if (i < str.length() && str.charAt(i) == '+') {
				tokenlist.add(new Token("+", Word.SUM));
				i++;
			} else if (i < str.length() && str.charAt(i) == '-') {
				tokenlist.add(new Token("-", Word.MIN));
				i++;
			} else if (i < str.length() && str.charAt(i) == '*') {
				tokenlist.add(new Token("*", Word.MUL));
				i++;
			} else if (i < str.length() && str.charAt(i) == '/') {
				tokenlist.add(new Token("/", Word.DIV));
				i++;
			}
		}
	}

	double factor() throws Exception {
		nextToken();
		double result = Double.parseDouble(tkn.str);
		nextToken();
		while (j < tokenlist.size()
				&& (tkn.word.equals(Word.MUL) || tkn.word.equals(Word.DIV))) {
			String s = tkn.str;
			nextToken();
			if (j < tokenlist.size() && s.equals("*")) {
				result *= Double.parseDouble(tkn.str);
				nextToken();
			} else if (j < tokenlist.size() && s.equals("/")) {
				result /= Double.parseDouble(tkn.str);
				nextToken();
			}
		}
		return result;
	}

	double expression() throws Exception {
		double result = factor();
		while (j < tokenlist.size()
				&& (tkn.word.equals(Word.SUM) || tkn.word.equals(Word.MIN))) {
			if (tkn.str.equalsIgnoreCase("+")) {
				result += factor();
			} else if (tkn.str.equalsIgnoreCase("-")) {
				result -= factor();
			}
		}
		return result;
	}

	void nextToken() {
		++j;
		if (j < tokenlist.size())
			tkn = tokenlist.get(j);
	}

	double run(String str) throws Exception {
		Analise(str);
		return expression();
	}
}

public class HDU_1237_¼òµ¥¼ÆËãÆ÷_±àÒëÆ÷Ð´·¨ {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		while (true) {
			String str = in.readLine();
			if (str.equals("0"))
				break;
			double d = new Analiser().run(str);
			out.printf("%.2f", d);
			out.println();
		}
		out.close();
	}
}
