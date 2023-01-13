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
		Token nextTk = null;
		//primeiro tentara achar o start;
		/*
		 * nextTk = this.findReserved(); if(nextTk != null) { if(nextTk.getToken() !=
		 * TokenType.IdentStart) { //aqui deve gerar um erro, todos os arquivos devem
		 * possuir o indicar de START return null; } }
		 */
		
		this.findWhiteSpaceAndComments();
		this.freader.accept();
		
		
		nextTk = this.findEof();		
		if(nextTk == null) {
			this.freader.zero();
		}else {
			this.freader.accept();
			return nextTk;
		}
		
		nextTk = this.findReserved();		
		if(nextTk == null) {
			this.freader.zero();
		}else {
			this.freader.accept();
			return nextTk;
		}
		
		nextTk = this.findVar();		
		if(nextTk == null) {
			this.freader.zero();
		}else {
			this.freader.accept();
			return nextTk;
		}
		
		nextTk = this.findNumbers();		
		if(nextTk == null) {
			this.freader.zero();
		}else {
			this.freader.accept();
			return nextTk;
		}
		
		nextTk = this.findString();		
		if(nextTk == null) {
			this.freader.zero();
		}else {
			this.freader.accept();
			return nextTk;
		}
		
		nextTk = this.findOpenParentheses();		
		if(nextTk == null) {
			this.freader.zero();
		}else {
			this.freader.accept();
			return nextTk;
		}
		
		nextTk = this.findCloseParentheses();		
		if(nextTk == null) {
			this.freader.zero();
		}else {
			this.freader.accept();
			return nextTk;
		}
		
		nextTk = this.findOpenBrackets();		
		if(nextTk == null) {
			this.freader.zero();
		}else {
			this.freader.accept();
			return nextTk;
		}
		
		nextTk = this.findCloseBrackets();		
		if(nextTk == null) {
			this.freader.zero();
		}else {
			this.freader.accept();
			return nextTk;
		}
		
		nextTk = this.findRelationalOp();		
		if(nextTk == null) {
			this.freader.zero();
		}else {
			this.freader.accept();
			return nextTk;
		}
		nextTk = this.findLogicalOp();		
		if(nextTk == null) {
			this.freader.zero();
		}else {
			this.freader.accept();
			return nextTk;
		}
		
		nextTk = this.findMathOp();		
		if(nextTk == null) {
			this.freader.zero();
		}else {
			this.freader.accept();
			return nextTk;
		}
		
		nextTk = this.findString();		
		if(nextTk == null) {
			this.freader.zero();
		}else {
			this.freader.accept();
			return nextTk;
		}
		
		nextTk = this.findSemiColon();		
		if(nextTk == null) {
			this.freader.zero();
		}else {
			this.freader.accept();
			return nextTk;
		}
		
		this.findNextLine();		
		

		System.out.println("Erro léxico!");
		System.out.println(this.freader.toString());
		return null;
	}

	public int getnLinhas() {
		return nLinhas;
	}
	
	public void setnLinhas(int nLinhas) {
		this.nLinhas = nLinhas;
	}
	
	public Token findMathOp() {
	
		char currentChar = (char) this.freader.readNextChar();
		if(currentChar == '\n'){
			this.setnLinhas(this.getnLinhas() + 1) ;
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
		
		if(currentChar == '/') {
			return new Token(TokenType.IdentDiv, "/");
		}
		return null;
	}
	
	public Token findSemiColon() {
		
		char currentChar = (char) this.freader.readNextChar();
		if(currentChar == '\n'){
			this.setnLinhas(this.getnLinhas() + 1) ;
		}
		if(currentChar == ';') {
			return new Token(TokenType.IdentSemiColon, ";");
		}
		return null;
	}
	
	public Token findComma() {
		char currentChar = (char) this.freader.readNextChar();
		if(currentChar == '\n'){
			this.setnLinhas(this.getnLinhas() + 1) ;
		}
		if(currentChar == ',') {
			return new Token(TokenType.IdentComma, ",");
		}
		return null;
	}
	
	public boolean findNextLine() {
		char currentChar = (char) this.freader.readNextChar();
		if(currentChar == '\n'){
			this.setnLinhas(this.getnLinhas() + 1) ;
			return true;
		}
		return false;
	}
	
	public boolean findWhiteSpace() {
		char currentChar = (char) this.freader.readNextChar();
		if(currentChar == ' '){
			return true;
		}
		return false;
	}
	
	public Token findOpenParentheses() {
		char currentChar = (char) this.freader.readNextChar();
		if(currentChar == '\n'){
			this.setnLinhas(this.getnLinhas() + 1) ;
		}
		if(currentChar == '(') {
			return new Token(TokenType.IdentOpenParentheses, "(");
		}
		return null;
	}
	
	public Token findCloseParentheses() {
		char currentChar = (char) this.freader.readNextChar();
		if(currentChar == '\n'){
			this.setnLinhas(this.getnLinhas() + 1) ;
		}
		if(currentChar == ')') {
			return new Token(TokenType.IdentCloseParentheses, ")");
		}
		return null;
	}
	
	public Token findOpenBrackets() {
		char currentChar = (char) this.freader.readNextChar();
		if(currentChar == '\n'){
			this.setnLinhas(this.getnLinhas() + 1) ;
		}
		if(currentChar == '{') {
			return new Token(TokenType.IdentOpenBrackets, "{");
		}
		return null;
	}
	
	public Token findCloseBrackets() {
		char currentChar = (char) this.freader.readNextChar();
		if(currentChar == '\n'){
			this.setnLinhas(this.getnLinhas() + 1) ;
		}
		if(currentChar == '}') {
			return new Token(TokenType.IdentCloseBrackets, "}");
		}
		return null;
	}
	
	public Token findDecimalSeparator() {
		char currentChar = (char) this.freader.readNextChar();
		if(currentChar == '.') {
			return new Token(TokenType.IdentDecimalSeparator, ".");
		}
		
		return null;
	}
	
	public Token findRelationalOp() {
		char currentChar = (char) this.freader.readNextChar();
		if(currentChar == '\n'){
			this.setnLinhas(this.getnLinhas() + 1) ;
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
		
		if(currentChar == '=') {
			currentChar = (char) this.freader.readNextChar();
			if(currentChar == '=') {
				return new Token(TokenType.IdentEquals, "==");
			}else {
				this.freader.subPointer();
				return new Token(TokenType.IdentAtribuition, "=");
			}
		}
		
		if(currentChar == '!') {
			currentChar = (char) this.freader.readNextChar();
			if(currentChar == '=') {
				return new Token(TokenType.IdentNotEqual, "!=");
			}else {
				this.freader.subPointer();
			}
		}
		return null;
	}

	public Token findLogicalOp() {
		char currentChar = (char) this.freader.readNextChar();
		if(currentChar == '\n'){
			this.setnLinhas(this.getnLinhas() + 1) ;
		}
		if(currentChar == '&') {
			currentChar = (char) this.freader.readNextChar();
			if(currentChar == '&') {
				return new Token(TokenType.IdentAnd, "&&");
			}else {
				this.freader.subPointer();
			}
			
		}
		if(currentChar == '|') {
			currentChar = (char) this.freader.readNextChar();
			if(currentChar == '|') {
				return new Token(TokenType.IdentOr, "||");
			}else {
				this.freader.subPointer();
			}
		}
		return null;
	}

	public Token findNumbers() {
		int state =1 ;
		while(true) {
			char currentChar = (char) this.freader.readNextChar();
			if(currentChar == '\n'){
				this.setnLinhas(this.getnLinhas() + 1) ;
			}
			if(state == 1 ) {
				if(Character.isDigit(currentChar)) {
					state = 2;
					continue;
				}else {
					return null;
				}
				
			}
			if(state == 2) {
				if(currentChar == '.') {
					currentChar = (char) this.freader.readNextChar();
					if(Character.isDigit(currentChar)) {
						state = 3;
						continue;
					}else {
						return null;
					}
				}
				if(!Character.isDigit(currentChar)) {
					this.freader.subPointer();
					return new Token(TokenType.IdentIntNumber, this.freader.getLex());
				}
			}
			
			if(state == 3) {
				if(!Character.isDigit(currentChar)) {
					this.freader.subPointer();
					return new Token(TokenType.IdentFloatNumber, this.freader.getLex());
				}
			}
		}
	}

	public Token findVar() {
		int state = 1;
		while(true) {
			char currentChar = (char) this.freader.readNextChar();
			if(currentChar == '\n'){
				this.setnLinhas(this.getnLinhas() + 1) ;
			}
			if(state == 1) {
				if(Character.isLetter(currentChar)) {
					state = 2;
					continue;
				}else {
					return null;
				}
			}
			
			if(state == 2) {
				if(!Character.isLetterOrDigit(currentChar)) {
					this.freader.subPointer();
					return new Token(TokenType.IdentVarName,  this.freader.getLex());
				}
			}
		}
	}

	public Token findString() {
		int state = 1;
		while(true) {
			char currentChar = (char) this.freader.readNextChar();
			if(currentChar == '\n'){
				this.setnLinhas(this.getnLinhas() + 1) ;
			}
			if(state == 1) {
				if(currentChar == '\'') {
					state = 2;
					continue;
				}else {
					return null;
				}
			}
			
			if(state == 2) {
				if(currentChar == '\n') {
					this.setnLinhas(getnLinhas() + 1 );
					return null;
				}
				if(currentChar == '\'') {
					return new Token(TokenType.IdentString,  this.freader.getLex());
				}
				if(currentChar == '\\') {
					state = 3;
					continue;
				}
				
			}
			
			if(state == 3) {
				if(currentChar == '\n') {
					this.setnLinhas(getnLinhas() + 1 );
					return null;
				}else {
					state = 2;
					continue;
				}
			}
		}
	}

	public void findWhiteSpaceAndComments() {
		int state = 1;
		while(true) {
			char currentChar = (char) this.freader.readNextChar();
			if(currentChar == '\n'){
				this.setnLinhas(this.getnLinhas() + 1) ;
			}
			if(state == 1) {
				if(Character.isWhitespace(currentChar) || currentChar == ' ') {
					state = 2;
				}else if(currentChar == '#') {
					state = 3;
				}else {
					this.freader.subPointer();
					return ;
				}
				continue;
			}
			if(state == 2) {
				if(currentChar == '#') {
					state = 3;
					continue;
				}
				if(!(Character.isWhitespace(currentChar) || currentChar == ' ')) {
					this.freader.subPointer();
					return;
				}
			}
			
			if(state == 3) {
				if(currentChar =='\n') {
					return;
				}
			}
		}
	}

	public Token findReserved() {
		while(true) {
			char currentChar = (char) this.freader.readNextChar();
			if(currentChar == '\n'){
				this.setnLinhas(this.getnLinhas() + 1) ;
			}
			if(!Character.isLetter(currentChar)) {
				this.freader.subPointer();
				String lex = this.freader.getLex();
				if(lex.equals("START")) {
					return new Token(TokenType.IdentStart, lex);
				}
				if(lex.equals("END")) {
					return new Token(TokenType.IdentEnd, lex);
				}
				if(lex.equals("FLOAT")) {
					return new Token(TokenType.IdentVarFloat, lex);
				}
				if(lex.equals("INT")) {
					return new Token(TokenType.IdentVarInt, lex);
				}
				if(lex.equals("STRING")) {
					return new Token(TokenType.IdentVarString, lex);
				}
				if(lex.equals("WHILE")) {
					return new Token(TokenType.IdentWhile, lex);
				}
				if(lex.equals("START")) {
					return new Token(TokenType.IdentStart, lex);
				}
				if(lex.equals("INPUT")) {
					return new Token(TokenType.IdentInput, lex);
				}
				if(lex.equals("OUTPUT")) {
					return new Token(TokenType.IdentOutput, lex);
				}
				if(lex.equals("IF")) {
					return new Token(TokenType.IdentIf, lex);
				}
				if(lex.equals("ELSE")) {
					return new Token(TokenType.IdentElse, lex);
				}
				if(lex.equals("OR")) {
					return new Token(TokenType.IdentOr, lex);
				}
				if(lex.equals("AND")) {
					return new Token(TokenType.IdentAnd, lex);
				}
				return null;
			}
		}
	}

	public Token findEof() {
		int currentChar = this.freader.readNextChar();
		if(currentChar == -1) {
			return new Token(TokenType.IdentEOF, "EOF");
		}
		return null;
	}
}
