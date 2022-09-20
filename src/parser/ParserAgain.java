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
    public ArrayList<Token> tokenList;
    private Token current, next;

    public ParserAgain(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;

        this.current = tokenList.get(0);
        this.next = tokenList.get(1);
//        tokenList.remove(0);
    }

    public Node parse() {
//        if (functionDef() != null) {
//
//        }

        return functionDef();
//        return expression();

    }

    public Node functionDef() {
        Token functionDef = (matchAndRemove(Token.OPTokens.KEY_WORD) != null) ? current : null;
        System.out.println(functionDef);
        if (functionDef != null) {
            if (functionDef.getTokenValue().equals("define")) {
                System.out.println("made it to define");
                Token name = (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null;
                if (name == null) {
                    throw new UnauthTokenException("error. your name is incorrect");
                }
                matchAndRemove(Token.OPTokens.LParan);
                ArrayList<Node> params = new ArrayList<>();
                while (true) {
                    params.add(varaible());
                    if (matchAndRemove(Token.OPTokens.RParan) != null) {
                        break;
                    }
//                    if(matchAndRemove(Token.OPTokens.IDENTIFIER) == null){
//                        throw new UnauthTokenException("bad identifier");
//                    }
                }

                ArrayList<Node> varaibles = new ArrayList<>();
                if (matchAndRemove(Token.OPTokens.BEGIN) != null) {
                    matchAndRemove(Token.OPTokens.ENDOFLINE);
                    while (true) {
                        Token constants = matchAndRemove(Token.OPTokens.KEY_WORD);
                        if(constants != null){
                            matchAndRemove(Token.OPTokens.ENDOFLINE);
                            if(constants.getTokenValue().equals("constants")){
                                System.out.println("Constant");
                                varaibles.add(varaible());
                            }
                            if (constants.getTokenValue().equals("end")) {
                                return new FunctionNode(name.getTokenValue(), params,varaibles);
                            }
                        }

                    }

                }
            }
        }
        return null;
    }

    public Node varaible() {
        Token type = (matchAndRemove(Token.OPTokens.KEY_WORD) != null) ? current : null;
        Token name = (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null;
        Token equals = (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null;
//        Node valu = expression().right;
        if (type == null || name == null) {
            throw new UnauthTokenException("error something name: "+name + " type: "+type);
        }
        return new VaraibleNode(name.getTokenValue());
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
        System.out.println("param matchh and remove: "+token);
        System.out.println("head of list: "+tokenList.get(0).getTokenEnum());
        if (token.equals(tokenList.get(0).getTokenEnum())) {

            var retVal = this.tokenList.remove(0);
            current = retVal;
            return retVal;
        }
        System.out.println("returned null");
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
