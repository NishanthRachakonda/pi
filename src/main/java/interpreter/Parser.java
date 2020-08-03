package interpreter;

import java.io.IOException;

class AST{
    AST left, right;
    String value;
    public AST(AST left, AST right, String value) {
        this.left   = left;
        this.right  = right;
        this.value  = value;
    }
};

public class Parser {
    Lexer lexer;
    Token current_token;

	public Parser(String argument) throws IOException {
        this.lexer = new Lexer(argument);
        this.current_token = this.lexer.get_next_token();
    }

    public void next_value(String token_type) throws IOException{
        if(this.current_token.type.equals(token_type)) {
            this.current_token = this.lexer.get_next_token();
        }else{
            System.err.println(new String("Cannot parse input"));
            System.exit(1);
        } 
    }

    public AST factor() throws IOException{
        Token token = this.current_token;
        if (token.type.equals("INTEGER")) {
            this.next_value("INTEGER");
            AST node = new AST(null, null, token.value);
            return node;    
        } else if(token.type.equals("LPAREN")) { 
            this.next_value("LPAREN");
            AST node = this.compute();           
            this.next_value("RPAREN");
            return node;
        }
        AST node = new AST(null, null, null);
        return node;
    }

    public AST term() throws IOException{
        AST node = this.factor();
        while(true){
            boolean check = false;
            String[] operators = {"MUL", "DIV"};
            for (String operator : operators) {
                if(operator.equals(this.current_token.type)){
                    check = true;
                    break;
                }
            }
            if(!check) break;
            String operator = this.current_token.type;
            if(operator.equals("MUL"))
                this.next_value("MUL");
            else if(operator.equals("DIV"))
                this.next_value("DIV");
            node = new AST(node, this.factor(), operator);
        }
        return node;
    }

    public AST compute() throws IOException{
        AST node = this.term();
        while(true){
            boolean check = false;
            String[] operators = {"ADD", "SUB"};
            for (String operator : operators) {
                if(operator.equals(this.current_token.type)){
                    check = true;
                    break;
                }
            }
            if(!check) break;
            String operator = this.current_token.type;
            if(operator.equals("ADD"))
                this.next_value("ADD");
            else if(operator.equals("SUB"))
                this.next_value("SUB");
            node = new AST(node, this.term(), operator);
        }
        return node;
    }
    public AST parse() throws IOException{
        return this.compute();
    }
}