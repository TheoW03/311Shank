package parser;

import lexer.Token;
import lexer.UnauthTokenException;
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
    public ArrayList<Token> tokenList, VaraiblesWithnoType;
    private Token current, next;

    public ParserAgain(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
        this.VaraiblesWithnoType = new ArrayList<>();
        this.current = tokenList.get(0);
        this.next = tokenList.get(1);
//        tokenList.remove(0);
    }

    public Node parse() {
//        if (functionDef() != null) {
//
//        }
        Node r = functionDef();
        if(r == null){
            throw new UnauthTokenException("Bro coder moment."); //5 days of non stop coding really puts a toll on u mentally.
            //so pls enjoy the random exception messages from my brain decaying from coding non stop for 5 days.
        }
        return r;
//        return expression();

    }

    /**
     *
     * @return Node.
     * yep. I brute forced :')
     */

    public Node functionDef() {
        matchAndRemove(Token.OPTokens.ENDOFLINE);
        Token functionDef = (matchAndRemove(Token.OPTokens.KEY_WORD) != null) ? current : null;
        if (functionDef != null) {
            if (functionDef.getTokenValue().equals("define")) {
                System.out.println("made it to define");
                Token name = (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null;
                if (name == null) {
                    throw new UnauthTokenException("error. your name is incorrect brocoded."); //this is checks if null
                }
                matchAndRemove(Token.OPTokens.LParan); //method
                //DEFINE NAME Lparan(params,) -> begin body end.
                ArrayList<Node> params = new ArrayList<>();
                while (true) {
                    ArrayList<Node> list = varaible(false);
                    params.addAll(list);
                    if (matchAndRemove(Token.OPTokens.RParan) != null) {
                        break; //checks for params
                    }
                }
                ArrayList<Node> varaibles = new ArrayList<>(); //di vars
                if (matchAndRemove(Token.OPTokens.BEGIN) != null) {
                    matchAndRemove(Token.OPTokens.ENDOFLINE);
                    while (true) {
                        Token constants = matchAndRemove(Token.OPTokens.KEY_WORD);
//                        System.out.println(constants);
                        if(constants != null){
                            System.out.println(constants);
                            matchAndRemove(Token.OPTokens.ENDOFLINE);
                            if(constants.getTokenValue().equals("constants")){
                                ArrayList<Node> list = varaible(true);
                                varaibles.addAll(list); //Im love with lambda coding.
                                System.out.println("past add all");
                            }else if(constants.getTokenValue().equals("varaibles")){
                                ArrayList<Node> list = varaible(false);
                                varaibles.addAll(list); //Im love with lambda coding.
                            }

                        }else{
                            throw new UnauthTokenException("error. your name is incorrect"); //if not existent it crashes.
                        }
                        matchAndRemove(Token.OPTokens.ENDOFLINE); //i really brute forced didnt i :')
                        Token end = matchAndRemove(Token.OPTokens.END); //e
                        System.out.println(end); //end
                        if (end != null) {
                            return new FunctionNode(name.getTokenValue(), params,varaibles);
                        }

                    }

                }
            }
        }
        return null;
    }

    /**
     *
     * @param isConstant
     * @return
     * Idk if im supposed to have isConstant param. but I do because I dont want todo
     * ctrl C + ctrl v en
     */

    public ArrayList<Node> varaible(boolean isConstant) {
        ArrayList<Token> varaiblesWithnoType = new ArrayList<>();
        ArrayList<Node> retList = new ArrayList<>();
        Token type = (matchAndRemove(Token.OPTokens.KEY_WORD) != null) ? current : null; //lamda pro here.
        Token name = (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null;
        if(type == null){ //if type == null
            while (true){
                if(matchAndRemove(Token.OPTokens.KEY_WORD) != null){
                    type = current;
                    break;
                }
                name = (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null;
                if(name == null){
                    throw new UnauthTokenException("imagine coding this good noob");
                }
                varaiblesWithnoType.add(name);
            }
        }
        Token equals = (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null;
        Node valu = null;
        if (name == null) {
            throw new UnauthTokenException("error something name: "+name + " type: "+type);
        }
        if(matchAndRemove(Token.OPTokens.NUMBER) != null){
            valu=new FloatNode(Float.parseFloat(current.getTokenValue()));
        }
        if(varaiblesWithnoType.size() == 0){
            varaiblesWithnoType.add(name);
        }
        for (Token token : varaiblesWithnoType) {
            retList.add(new VaraibleNode(valu, token, isConstant));
        }
        return retList;
    }

    public Node expression() {
        System.out.println("call Expression");
        System.out.println("=======================");

        Node opNode = term(); //returns a mathOPNode.
        Token op = (matchAndRemove(Token.OPTokens.ADD) != null) ? current :
                (matchAndRemove(Token.OPTokens.SUBTRACT) != null) ? current : null;
        if (op != null) {
            Node node = null;
            while (true) {
                if (node != null) {
                    op = (matchAndRemove(Token.OPTokens.ADD) != null) ? current :
                            (matchAndRemove(Token.OPTokens.SUBTRACT) != null) ? current : null;
                }
                if (op == null) {
                    return opNode;
                }
                var right = term();
                System.out.println("left(EXPERSSON): " + opNode.ToString());
                System.out.println("Right (expression): " + right.ToString());
                System.out.println("expression OP: " + op);
                System.out.println("=======================");
                opNode = new MathOpNode(opNode, right, op);
                node = opNode;
            }

        }
        System.out.println("node(match and remove fail)(EXPERSSON): " + opNode.ToString());
        System.out.println("=======================");
        return opNode;

    }

    public Node term() {
        System.out.println("call term");
        System.out.println("=======================");
        Node opNode = this.factor();
        Token op = (matchAndRemove(Token.OPTokens.MULTIPLY) != null) ? current :
                (matchAndRemove(Token.OPTokens.DIVIDE) != null) ? current : null;
        if (op != null) {
            //loop.
            Node node = null;
            //probably while OP != null
            //return something.
            while (true) {
                if (node != null) {
                    op = (matchAndRemove(Token.OPTokens.MULTIPLY) != null) ? current :
                            (matchAndRemove(Token.OPTokens.DIVIDE) != null) ? current : null;
                }
                if (op == null) {
                    return opNode;
                }
                var right = factor();
                System.out.println("Left (term): " + opNode.ToString());
                System.out.println("Right (term): " + right.ToString());
                System.out.println("Operator (term): " + op);
                System.out.println("=======================");
                opNode = new MathOpNode(opNode, right, op);
                node = opNode;

            }

        }
        System.out.println("node(match and remove fail)(Term): " + opNode.ToString());
        System.out.println("=======================");
        return opNode;
    }

    public Node factor() {
        System.out.println("call factor ");
        System.out.println("=======================");
        System.out.println("current: " + current);
        if (esNomeralElFloatar(current) != null || matchAndRemove(Token.OPTokens.NUMBER) != null) {
            float a = (float) esNomeralElFloatar(current);
            matchAndRemove(Token.OPTokens.NUMBER);
            System.out.println("Node output es Nomeral: " + a);
            return new FloatNode(a);
        } else if (matchAndRemove(Token.OPTokens.LParan) != null) {
            Node node = expression();
            System.out.println("Node output es Paran: " + node.ToString());
            System.out.println("================================");
            matchAndRemove(Token.OPTokens.RParan);
            return node;
        }
        System.out.println("factor retruned null");
//        System.out.println();
        System.out.println("================================");
        return null;

    }

    private Token matchAndRemove(Token.OPTokens token) {
//        System.out.println("param matchh and remove: "+token);
//        System.out.println("head of list: "+tokenList.get(0).getTokenEnum());
        if (token.equals(tokenList.get(0).getTokenEnum())) {

            var retVal = this.tokenList.remove(0);
            current = retVal;
            return retVal;
        }
//        System.out.println("returned null");
        return null;
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
