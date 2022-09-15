package parser;

import lexer.Token;
import parser.node.FloatNode;
import parser.node.IntegerNode;
import parser.node.MathOpNode;
import parser.node.Node;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class ParserAgain {
    public ArrayList<Token> tokenList;
    private Token current, next;

    public ParserAgain(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
        this.current = tokenList.get(0);
        this.next = tokenList.get(1);
    }

    public Node parse() {
        return expression();

    }

    public Node expression() {
        System.out.println("call E");

        Node node = term(); //returns a mathOPNode.
        if (matchAndRemove("+") == null || matchAndRemove("-") == null) {
            return node;
        }

        return new MathOpNode(node, term(), next);

    }

    public Node term() {
        System.out.println("call T");
        Node node = this.factor();
        if (matchAndRemove("*") == null && matchAndRemove("/") == null) {
            return node;
        }
        return new MathOpNode(node, factor(), next);
    }

    public Node factor() {
        System.out.println("call F");
        if (esNomeralElEntero(current) != null || esNomeralElFloatar(current) != null) {
            if (esNomeralElFloatar(current) != null) {
                float a = (float) esNomeralElFloatar(current);
                matchAndRemove(tokenList.get(0).getTokenAsString());
                return new FloatNode(a);
            }

        } else if (matchAndRemove("(()") != null) {
            Node node = expression();
            return node;
        }

        return null;

    }

    private String matchAndRemove(String token) {
        if (token.equals(tokenList.get(0).getTokenAsString())) {
            this.current = tokenList.get(0);
            String returnType = this.tokenList.remove(0).getTokenName();
            this.next = tokenList.get(0);
            return returnType;
        }else if(token.equals(tokenList.get(0).getTokenName())){
            this.current = tokenList.get(0);
            String returnType = this.tokenList.remove(0).getTokenName();
            this.next = tokenList.get(0);
            return returnType;
        }
        return null;
    }

    /**
     * checks if EsElEntero or Is an integer. in La eponala.
     *
     * @param token the current
     * @return new enteroNode
     */
    private Object esNomeralElEntero(Token token) {
        try {
            int t = Integer.parseInt(token.getTokenName());
            return t;
        } catch (NumberFormatException e) {
            return null;
        }

    }

    /**
     * @param token
     * @return es floatar
     */
    public Object esNomeralElFloatar(Token token) {
        try {
            float t = Float.parseFloat(token.getTokenName());
            return t;
        } catch (NumberFormatException e) {
            return null;
        }
    }


}
