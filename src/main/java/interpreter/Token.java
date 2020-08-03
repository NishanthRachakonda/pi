package interpreter;

import java.io.*;

public class Token { 
	String type, value;

	public Token(String type, String value) throws IOException {
		this.type = type;
		this.value = value;
	}

	public String toString() {
		return String.format("Token(%s, %s)", this.type, this.value);
	}

	public static void main(final String[] args) throws IOException {
		final Token token = new Token("PLUS", "1");
		System.out.println(token.toString());
	}
} 
