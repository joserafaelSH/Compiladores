package lexicalAnalyzer;

public enum TokenType {
	
	//data types
	IdentVarInt, IdentVarFloat, IdentVarString,
	//loop
	IdentWhile,
	//input and output
	IdentInput, IdentOutput,
	//control
	IdentIf, IdentElse,
	//Start and Ending for code
	IdentStart, IdentEnd,
	//Logical
	IdentOr, IdentAnd,
	//comma
	IdentComma,
	//atribuition
	IdentAtribuition,
	IdentSemiColon,
	
	//Math
	IdentAdd, IdentSub, IdentMulti, IdentDiv, IdentEquals,
	IdentLessThan, IdentGreaterThan, IdentLessThanOrEqualTo, IdentGreaterThanOrEqualTo ,IdentNotEqual,
	IdentDecimalSeparator, IdentOpenParentheses, IdentCloseParentheses, IdentOpenBrackets ,IdentCloseBrackets, 
	
}
