package parser;

//custom imports for I have my code in different dir's
//pls comment out if problem.
import lexer.Token;
import parser.node.FloatNode;
import parser.node.IntegerNode;
import parser.node.MathOpNode;
import parser.node.Node;

import java.util.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 *
 * legit. been foicused on this all weekend.
 * and i cant T~T
 */
public class Parser {
    private ArrayList<Token> tokens;
    private Token tok, next;
    private Parser rootNode;
    public Node left, right;
    public Node element;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Parser(Node element) {
        this.element = element;
    }

    public Parser(Parser ro) {
        this.rootNode = ro;
    }

    /**
     * @return Node
     * well. I tried. coding it.
     */
    public Node parserMethod() {

        this.tok = tokens.get(0);
        this.next = tokens.get(1);
        try{

            if (matchAndRemove(tok.getTokenAsString()) != null) {
                //if number
                if (nomeral(tok) != null) {
                    return nomeral(tok);
                    //if expression
                } else {
                    return esExpression();
                }


            }
            parserMethod();
        }catch (IndexOutOfBoundsException e){

        }
        return esExpression(); //IDK wtf im doing :')
    }

    /**
     *
     * @param value
     * @return node for number.
     */
    public Node nomeral(Token value) {
        try {
            float a = Float.parseFloat(value.getTokenName());
            FloatNode b = new FloatNode(a);
            return b;
        } catch (NumberFormatException e1) {
            //number.
            try {
              int a = Integer.parseInt(value.getTokenName());
                IntegerNode a2 = new IntegerNode(a);
                return a2;
            } catch (NumberFormatException e2) {
                return null;
            }
        }
    }

    /**
     *
     * expression. esponal bc I hate english.
     * @return MathOPnode
     */

    public MathOpNode esExpression() {

        //EOL.
        if (next.getTokenAsString().equals(";")) {
            return null;
        }
        if(tok.getTokenName().equals("(")){
            term();
            return term();
        }
        if(tok.getTokenName().equals("*") || tok.getTokenName().equals("/")){
            return factor();
        }
        left = parserMethod();
        right = parserMethod();
        return new MathOpNode(this.left, this.right, tok.getTokenAsString());
    }

    /**
     * trying to make it look like i remotely know what im doing :')
     * @return
     */
    public MathOpNode term(){
        left = parserMethod();
        right = parserMethod();
        return new MathOpNode(this.left, this.right, tok.getTokenAsString());
    }
    public MathOpNode factor(){
        left = parserMethod();
        right = parserMethod();
        return new MathOpNode(this.left, this.right, tok.getTokenAsString());
    }

    /**
     * @param token
     */
    public String matchAndRemove(String token) {
//            System.out.println("token: "+tokens.get(0) +" \n"+token);
        if (token.equals(tokens.get(0).getTokenAsString())) {

            return tokens.remove(0).getTokenName();
        }

        return null;
    }


}
