package parser;

import lexer.Token;
import parser.node.FloatNode;
import parser.node.IntegerNode;
import parser.node.MathOpNode;
import parser.node.Node;

import java.lang.reflect.Array;
import java.sql.Struct;
import java.util.*;
import java.io.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class Parser {
    private ArrayList<Token> tokens;
    private Token tok;
    private Parser rootNode;
    public Parser left,right;
    public Node element;
    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }
    public Parser(Node element){
        this.element=element;
    }
    public Parser(Parser ro){
        this.rootNode = ro;
    }

    /**
     *
     * @return node of this
     *
     */
    /**
     * thanks 213 ;)
     * @param op
     * @return
     */
    public int OrderOfOp(String op) {
        if (op.equals("+") || op.equals("-")) {
            return 1;
        }
        if (op.equals("/") || op.equals("*")) {
            return 2;
        }
        if(op.equals("^")){
            return 3;
        }

        return -1;
    }

    public Parser parserMethod() {
        try{
            if (matchAndRemove(tokens.get(0).getTokenAsString()) != null) {
                this.tok = tokens.get(0);
                if (nomeral() != null) {
                    this.left = new Parser(nomeral());
                    parserMethod();
                }
                if (esExpression() != null) {
                    if (OrderOfOp(tok.getTokenAsString()) >= 2) {
                        this.left = new Parser(esExpression());
                    } else {
                        if(esExpression() != null){
                            this.right = new Parser(esExpression());
                        }
                        parserMethod();
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){

        }
        return this;

    }
    public Node nomeral() {

        try {
            float a = Float.parseFloat(tok.getTokenName());
            FloatNode b = new FloatNode(a);
            return b;
//                    nodeLis1.add(b);

        } catch (NumberFormatException e1) {
            try {
                int a = Integer.parseInt(tok.getTokenName());
                IntegerNode b = new IntegerNode(a);
                return b;
//                        nodeLis1.add(b);
            } catch (NumberFormatException e2) {
                esExpression();
                return null;
            }
        }
    }
    public MathOpNode esExpression(){
        if(tok.getTokenAsString().equals(";")){
            return null;
        }
        return new MathOpNode(tok.getTokenAsString());
    }


        public String matchAndRemove (String token){
//            System.out.println("token: "+tokens.get(0) +" \n"+token);
            if (token.equals(tokens.get(0).getTokenAsString())) {

                return tokens.remove(0).getTokenName();
            }

            return null;
        }


    }
