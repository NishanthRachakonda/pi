package interpreter;

import java.io.IOException;
import java.util.Scanner;

public class Interpreter {
    Parser parser;

	public Interpreter(String argument) throws IOException {
        this.parser = new Parser(argument);
    }

    public int compute_subtree(AST root) throws IOException {
        if(root.value=="ADD")
            return compute_subtree(root.left)+compute_subtree(root.right);     
        else if(root.value=="SUB")
            return compute_subtree(root.left)-compute_subtree(root.right);     
        else if(root.value=="MUL")
            return compute_subtree(root.left)*compute_subtree(root.right);     
        else if(root.value=="DIV")
            return compute_subtree(root.left)/compute_subtree(root.right);     
        else
            return Integer.parseInt(root.value);
    }

    public int interpret() throws IOException {
        AST tree = this.parser.parse();
        return compute_subtree(tree);
    }

	public static void main(final String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
        while(true){
            System.out.print("calc> ");
            String argument = scan.nextLine();
            if(argument.isEmpty()){
                continue;
            }else if(argument.equals("quit")){
                break;
            }else {
                Interpreter interpreter = new Interpreter(argument);
                System.out.println(interpreter.interpret());
            }
        }
        scan.close();
    }
} 
