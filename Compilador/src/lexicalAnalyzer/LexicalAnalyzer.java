package lexicalAnalyzer;

import common.FileReader;

public class LexicalAnalyzer {
	private FileReader freader;
	private int nLinhas;
	
	
	public LexicalAnalyzer(String path) {
		super();
		this.freader = new FileReader(path);
		this.setnLinhas(1);
	}
	
	public Token nextToken() {
		int currentCharInt = -1;
		while((currentCharInt = this.freader.readNextChar()) != -1) {
			char currentChar = (char)currentCharInt;
			if(currentChar == ' ') continue;
			if(currentChar == '\n'){
				this.setnLinhas(this.getnLinhas() + 1) ;
				continue;
			}
			if(currentChar == ',') {
				return new Token(TokenType.IdentComma, ",");
			}
			
			if(currentChar == '+') {
				return new Token(TokenType.IdentAdd, "+");
			}
			
			if(currentChar == '-') {
				return new Token(TokenType.IdentSub, "-");
			}
			
			if(currentChar == '*') {
				return new Token(TokenType.IdentMulti, "*");
			}
			
			if(currentChar == '-') {
				return new Token(TokenType.IdentDiv, "-");
			}
			
			if(currentChar == '>') {
				currentChar = (char) this.freader.readNextChar();
				if(currentChar == '=') {
					return new Token(TokenType.IdentLessThanOrEqualTo, ">=");
				}else {
					this.freader.subPointer();
					return new Token(TokenType.IdentGreaterThan, ">");
				}
				
			}
			if(currentChar == '<') {
				currentChar = (char) this.freader.readNextChar();
				if(currentChar == '=') {
					return new Token(TokenType.IdentLessThanOrEqualTo, "<=");
				}else {
					this.freader.subPointer();
					return new Token(TokenType.IdentLessThan, "<");
				}
			}
			
			if(currentChar == '(') {
				return new Token(TokenType.IdentOpenParentheses, "(");
			}
			if(currentChar == ')') {
				return new Token(TokenType.IdentCloseParentheses, ")");
			}
			if(currentChar == '{') {
				return new Token(TokenType.IdentOpenBrackets, "{");
			}
			if(currentChar == '}') {
				return new Token(TokenType.IdentCloseBrackets, "}");
			}
			
			if(currentChar == '.') {
				return new Token(TokenType.IdentDecimalSeparator, ".");
			}
			if(currentChar == ';') {
				return new Token(TokenType.IdentSemiColon, ";");
			}
			
		}
		return null;
	}

	public int getnLinhas() {
		return nLinhas;
	}

	public void setnLinhas(int nLinhas) {
		this.nLinhas = nLinhas;
	}
	
	
	
}
