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
//    private ArrayList<Token> tokens;
//    private Token tok, next; //remove next
//    private Parser rootNode;
//    public Node left, right;
//    public Node element;
//    private Node root;
//    private Node curr;
//
//    public Parser(ArrayList<Token> tokens) {
//        this.tokens = tokens;
//    }
//
//    /**
//     * @return Node
//     * well. I tried. coding it.
//     * <p>
//     * <p>
//     * arraylist
//     * check for all rules
//     */
//    public Node parserMethodThis() {
//        this.tok = tokens.get(0);
//        if (matchAndRemove(tok.getTokenAsString()) != null) {
//            if (nomeral(tok) != null) {
//                return nomeral(tok);
//            } else {
//                return esExpression(tok);
//            }
//        }
//        return null;
//    }
//
//    /**
//     * @return next node.
//     */
//
//    //remove
//    public Node Next() {
//        this.next = tokens.get(0);
//        tokens.remove(0);
//        if (nomeral(next) != null) {
//            return nomeral(next);
//        } else {
//            return esExpression(next);
//        }
//    }
//
//    /**
//     * yep. Its not Solving. it works but it isnt solving.
//     * remove.
//     */
//    public Node parse() {
////        if (tokens.size() == 2) {
////            // the problem is here. for some reason if
////            I dont terminate higher than 2 it throws a IndexOutOFboundsException
////            //
////            on me. (if your willing to patch that will be nice. but I tried it and IDK how to get rid of it).
////            return root;
//        //enforcing order
//        //3+2*5
//        //
//        // EX
////        }
//        Node curr = parserMethodThis();
//        Node nextS = Next();
//        System.out.println(nextS + " " + curr);
////        if (curr == null || nextS == null) {
////            return root;
////        }
//        //so match and remove is a search method??
//        //how it works
//        /**
//         * find number,
//         * 5+3*5/2
//         * 5 is our 1st token
//         * ex calls term
//         *
//         * term calls factor. calls match and remove on times (Term calls so calls until it finds PEMDAS)
//         * Factor can do match and remove
//         * check if anything is left.
//         * then factor finds a multiplication.
//         * return null if and only if u find nothing.
//         *
//         */
//        this.root = expression();
//        if(nextS == null){
//            return root;
//        }
//        parse();
//        return root;
//    }
//
//    /**
//     * @return new node
//     * EXPRESSION = TERM { (plus or minus) TERM} :')
//     */
//    //match and remove
//    //call term
//    public Node expression() {
//        Node node = term(); //returns a mathOPNode.
//
//        if(next == null){
//            return nomeral(tok);
//        }
//        return new MathOpNode(node, term(), next);
//    }
//
//    /**
//     * FACTOR = {-} number or lparen EXPRESSION rparen
//     *
//     * @return node
//     */
//    //use match and remove */
//    public Node Factor() {
//        if (nomeral(tok) != null) { //and for some reason () has a null prob. Idek. :')
//            return nomeral(tok);
//        } else if (tok.getTokenAsString().equals("(()")) {
//            return expression();
//        }
//        return null;
//
//    }
//
//    /**
//     * TERM = FACTOR { (times or divide) FACTOR}
//     *
//     * @return
//     */
//    public Node term() {
//        Node node = this.Factor();
//        if(matchAndRemove("*")  != null || matchAndRemove("/") != null)
//            return null;
//        return new MathOpNode(node, this.Factor(), next);
//    }
//
//    /**
//     * @param value - token
//     * @return node for number.
//     */
//    //factor. i just named it nomeral;
//    public Node nomeral(Token value) {
//        try {
//            float a = Float.parseFloat(value.getTokenName());
//            FloatNode b = new FloatNode(a);
//            return b;
//        } catch (NumberFormatException e1) {
//            //number.
//            try {
//                int a = Integer.parseInt(value.getTokenName());
//                return new IntegerNode(a);
//            } catch (NumberFormatException e2) {
//                return null;
//            }
//        }
//    }
//
//    /**
//     * expression. esponal bc I hate english.
//     *
//     * @return MathOPnode
//     */
//
//    public MathOpNode esExpression(Token token) {
//        if (token.getTokenAsString().equals(";")) {
//            return null;
//        }
//        return new MathOpNode(token.getTokenAsString());
//    }
//
//    /**
//     * @param token
//     */
//    //compares head of the list. and returns null.
//
//    public String matchAndRemove(String token) {
//        System.out.println("a: "+tokens.size());
//        if (token.equals(tokens.get(0).getTokenAsString())) {
//            return tokens.remove(0).getTokenName();
//        }
//        return null;
//    }


}
