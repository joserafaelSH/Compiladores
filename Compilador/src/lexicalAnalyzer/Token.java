package lexicalAnalyzer;

public class Token {
	private TokenType token;
	private String name;
	
	public Token(TokenType token, String name) {
		super();
		this.token = token;
		this.name = name;
	}
	
	public TokenType getToken() {
		return token;
	}
	public void setToken(TokenType token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "<"+ token +"," + name +  ">";
	}
	
}
