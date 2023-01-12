package lexicalAnalyzer;

import java.util.ArrayList;

public class LexicalAnalyzerMain {
	static ArrayList<Token> tkList = new ArrayList<Token>();
	
	public static void main(String[] args) {
		LexicalAnalyzer lex = new LexicalAnalyzer("C:\\Users\\JRafael\\eclipse-workspace\\Compilador\\input\\inputTest.txt");
		Token tk = null;
		
		while((tk = lex.nextToken()) != null) {
			System.out.print(tk);
			tkList.add(tk);
		}
		System.out.println("\n\nLinhas: " + lex.getnLinhas() );
	}
}
