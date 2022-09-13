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
 * @Javadoc legit. been foicused on this all weekend.
 * and i cant T~T
 */
public class Parser {
    private ArrayList<Token> tokens;
    private Token tok, next;
    private Parser rootNode;
    public Node left, right;
    public Node element;
    private Node root;
    private ArrayList<Node> lisOfNodes;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
        lisOfNodes = new ArrayList<>();
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
     * <p>
     * <p>
     * arraylist
     * check for all rules
     */
    public Node parserMethodThis() {
        this.tok = tokens.get(0);
        if (matchAndRemove(tok.getTokenAsString()) != null) {
            if (nomeral(tok) != null) {
                return nomeral(tok);
            } else {
                return esExpression(tok);
            }
        }
        return null;
    }

    public Node Next() {
        this.next = tokens.get(0);
        tokens.remove(0);
        if (nomeral(next) != null) {
            return nomeral(next);
        } else {
            return esExpression(next);
        }


    }

    /**
     * if(!noOp's){
     * <p>
     * <p>
     * <p>
     * }
     * if(factor){
     * <p>
     * <p>
     * }
     * if(term){
     * <p>
     * }
     * if(expression){
     * <p>
     * }
     */
    public Node parse2() {
        try {

            Node curr = parserMethodThis();
            Node nextS = Next();

            if (nextS instanceof MathOpNode && root == null) {
                this.root = nextS;
            }
            if (curr == null || nextS == null) {
                parse2();
            }
//            System.out.println("current: " + curr.ToString() + "  next " + nextS.ToString() + " next one: " + tokens.get(0).toString());
            root.left = nomeral(tokens.get(0));
            root.right = curr;

            if (left == null || curr instanceof MathOpNode) {
                parse2();
            }
            return root;
        } catch (Exception e) {
            System.out.println(e);
            return root;
        }

    }


    public Node term(Node node){

        return new IntegerNode(1);
    }
    public Node expression(Node left, Node right){
        //recurson.
        return new MathOpNode(term(left),term(right));
    }

    public ArrayList<Node> getLisOfNodes() {
        return lisOfNodes;
    }


    /**
     * @param value
     * @return node for number.
     */
    //factor. i just named it nomeral;
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
     * expression. esponal bc I hate english.
     *
     * @return MathOPnode
     */

    public MathOpNode esExpression(Token token) {
        if (token.getTokenAsString().equals(";")) {
            return null;
        }
        return new MathOpNode(token.getTokenAsString());
    }

    /**
     * trying to make it look like i remotely know what im doing :')
     *
     * @return
     */

    public MathOpNode factor() {
        return null;
//        return new MathOpNode(this.left, this.right, tok.getTokenAsString());
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
