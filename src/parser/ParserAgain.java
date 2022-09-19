package parser;

import lexer.Token;
import parser.node.*;

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
        tokenList.remove(0);
    }

    public Node parse() {
        return expression();

    }
    public Node functionDef(){
//        if(matchAndRemove(Token.OPTokens.DEFINE)){
//            //logic.
//        }
        return new FunctionNode("hello",new ArrayList<>());
    }

    public Node expression() {
        System.out.println("call Expression");
        System.out.println("=======================");

        Node node = term(); //returns a mathOPNode.
        if (matchAndRemove(Token.OPTokens.ADD) != null
                || matchAndRemove(Token.OPTokens.SUBTRACT) != null) {
            System.out.println("left(EXPERSSON): "+node.ToString());
            var right = term();
            System.out.println("Right (expression): "+right.ToString());
            System.out.println("Operator (expression): "+current.getTokenEnum());
            System.out.println("expression OP: "+current);
            System.out.println("=======================");
            return new MathOpNode(node, right, current);
        }
        System.out.println("node(match and remove fail)(EXPERSSON): "+node.ToString());
        System.out.println("=======================");
        return node;

    }

    public Node term() {
        System.out.println("call term");
        System.out.println("=======================");
        Node node = this.factor();
        if(node != null)
            System.out.println(node.ToString());
        System.out.println("ret nill");
        if (matchAndRemove(Token.OPTokens.MULTIPLY) != null
                || matchAndRemove(Token.OPTokens.DIVIDE) != null) {
            System.out.println("Left (term): " + node.ToString());

            var right = factor();
            System.out.println("Right (term): "+right.ToString());
            System.out.println("Operator (term): "+current.getTokenEnum());
            System.out.println("=======================");
            return new MathOpNode(node, right, current);
        }
        System.out.println("node(match and remove fail)(Term): "+node.ToString());
        System.out.println("=======================");
        return node;
    }

    public Node factor() {
        System.out.println("call factor ");
        System.out.println("=======================");
        System.out.println("current: "+current);
        if (esNomeralElFloatar(current) != null) {
            float a = (float) esNomeralElFloatar(current);
            System.out.println("Node output es Nomeral: "+a);
            return new FloatNode(a);
        }else if (matchAndRemove(Token.OPTokens.LParan) != null) {
            Node node = expression();
            System.out.println("Node output es Paran: "+node.ToString());
            System.out.println("================================");
            matchAndRemove(Token.OPTokens.RParan);
            return node;
        }
        System.out.println("factor retruned null");
        System.out.println();
        System.out.println("================================");
        return null;

    }

    private Token matchAndRemove(Token.OPTokens token) {
        System.out.println("in matchAndRemove, looking for: "+token);
        System.out.println("in matchAndRemove, found: "+tokenList.get(0).getTokenEnum());

        if(token.equals(tokenList.get(0).getTokenEnum()) ){
            var retVal = this.tokenList.remove(0);
            current = retVal;
            System.out.println("in matchAndRemove, returning: "+retVal);
            return retVal;
        }
        System.out.println();
        System.out.println("in matchAndRemove, returning (failed): null");
        return null;
//        return null;
//        if(toke){
//
//
//        }
//        if (tokenList.get(0).getTokenAsString().equals(";")) {
////            tokenList.remove(0);
//            return null;
//        }
//        if (token..equals(tokenList.get(0).getTokenAsString())) {
//            this.current = tokenList.get(0);
//            Token returnType = this.tokenList.remove(0);
//            return returnType;
//        }
//        if (token.equals(tokenList.get(0).getTokenName())) {
//            Token returnType = this.tokenList.remove(0);
//            this.current = tokenList.get(0);
////            this.next = tokenList.get(0);
//            return returnType;
//        }


    }

    /**
     * checks if EsElEntero or Is an integer. in La eponala.
     *
     * @param token the current
     * @return new enteroNode
     */
    private Object esNomeralElEntero(Token token) {
        try {
            int t = Integer.parseInt(token.getTokenValue());
            return t;
        } catch (NumberFormatException e) {
            System.out.println("error esNormalElEntero");
            System.out.println(token);
            return null;
        }

    }

    /**
     * @param token
     * @return es floatar
     */
    public Object esNomeralElFloatar(Token token) {
        try {
            float t = Float.parseFloat(token.getTokenValue());
            return t;
        } catch (NumberFormatException e) {
            return null;
        }
    }


}
