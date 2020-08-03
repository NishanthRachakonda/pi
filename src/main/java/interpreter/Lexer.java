package interpreter;

import java.io.IOException;

public class Lexer {
    int index;
    String argument;
    char current_char;

	public Lexer(String argument) throws IOException {
        this.index         = 0;
        this.argument      = argument;
        this.current_char  = this.argument.charAt(this.index);
    }

    public void update() {
        this.index ++;
        if(this.index > this.argument.length() - 1) {
            this.current_char = '\0';
        }else{
            this.current_char = this.argument.charAt(this.index);
        }
    }

    public void skipSpace() {
        while ((this.current_char != '\0') && (Character.isWhitespace(this.current_char)))
            this.update();
    }

    public String getInteger() {
        String result = "";
        while ((this.current_char != '\0') && (Character.isDigit(this.current_char))){
            result += this.current_char;
            this.update();
        }
        return result;
    }

    public Token get_next_token() throws IOException{
        while(this.current_char != '\0'){
            if (Character.isWhitespace(this.current_char)){
                this.skipSpace();
                continue;
            }else if (Character.isDigit(this.current_char)){
                Token token = new Token("INTEGER", this.getInteger());
                return token;
            }else if (this.current_char == '+'){
                this.update();
                Token token = new Token("ADD", Character.toString('+'));
                return token;
            }else if (this.current_char == '-'){
                this.update();
                Token token = new Token("SUB", Character.toString('+'));
                return token;
            }else if (this.current_char == '*'){
                this.update();
                Token token = new Token("MUL", Character.toString('+'));
                return token;
            }else if (this.current_char == '/'){
                this.update();
                Token token = new Token("DIV", Character.toString('-'));
                return token;
            }else if (this.current_char == '('){
                this.update();
                Token token = new Token("LPAREN", Character.toString('('));
                return token;
            }else if (this.current_char == ')'){
                this.update();
                Token token = new Token("RPAREN", Character.toString(')'));
                return token;
            }
            System.err.println(new String("Cannot parse input"));
            System.exit(1);
        }
        Token token = new Token("EOF", null);
        return token;
    }

} 
