package parser;

import lexer.Lexer;
import lexer.Token;
import lexer.UnauthTokenException;
import parser.node.*;
import parser.node.StatementNode.AssignmentNode;
import parser.node.StatementNode.VaraibleReferenceNode;

import java.util.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class Parser {
    public ArrayList<Token> tokenList, VaraiblesWithnoType;
    private Token current, next;

    public Parser(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
        this.VaraiblesWithnoType = new ArrayList<>();
        this.current = tokenList.get(0);
        this.next = tokenList.get(1);
    }

    /**
     *
     * @return entry point parserer
     */

    public Node parse() {
        Node r = functionDef();
        System.out.println(r);
        if (r == null) {
            System.out.println("Es null");
            throw new UnauthTokenException("error parsing" + current);
        }
        return r;
    }

    /**
     *
     * @return statement node list for function node
     */

    public ArrayList<Node> statements() {

        ArrayList<Node> nodeList = new ArrayList<>();
        while (true) {
            Node a = assignments();
            if (a == null) {
                return nodeList; //return list
            }
            nodeList.add(a); //add to list
        }
    }

    /**
     * @return assignment node
     */

    public Node assignments() {
        Token name = (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null; //checks for name
        Token equals = (matchAndRemove(Token.OPTokens.EQUALS) != null) ? current : null; //what it equals
        Node assignement = expression(); //assignment
        RemoveEOLS();
        if (assignement == null && name == null) {
            return null;
        }
        if (equals == null || name == null || assignement == null) {
            throw new UnauthTokenException("Not a real statement");
        }
        return new AssignmentNode(new VaraibleReferenceNode(name), assignement); //return node

    }


    /**
     * helper function to remove eol's
     */
    private void RemoveEOLS() {
        while (true) {
            Token e = matchAndRemove(Token.OPTokens.ENDOFLINE);
            if (e == null) {
                break;
            }

        }
    }

    /**
     * @return Node.
     * yep. I brute forced :')
     */
    public ArrayList<Node> processConstants() {
        ArrayList<Node> constantList = new ArrayList<>();
        while (true) {
            Token constants = (matchAndRemove(Token.OPTokens.VARAIBLES) != null) ? current
                    : (matchAndRemove(Token.OPTokens.ENDOFLINE) != null)
                    ? current : null;
            if (constants != null) {
                if (constants.getTokenEnum() == Token.OPTokens.VARAIBLES) { //recode for enums
                    return constantList;
                } else {
                    RemoveEOLS();
                }
            }
            Token end = matchAndRemove(Token.OPTokens.BEGIN);
            if (end != null) {
                return constantList;
            }
            ArrayList<Node> list = varaible(true);
            RemoveEOLS();
            constantList.addAll(list); //Im love with lambda coding.

        }

    }

    /**
     *
     * @return array list VARnodes.
     */

    public ArrayList<Node> processVaraibles() {
        ArrayList<Node> varList = new ArrayList<>();
        while (true) {

            Token constants = matchAndRemove(Token.OPTokens.ENDOFLINE);
            if (constants != null) {
                RemoveEOLS();
            }
            Token end = bodyFunction();
            if (end != null) {
                if (end.getTokenEnum() == Token.OPTokens.BEGIN) {
                    return varList;
                }
            }

            while (true) {
                Token e = matchAndRemove(Token.OPTokens.ENDOFLINE);
                if (e == null) {
                    break;
                }

            }
            ArrayList<Node> list = varaible(true);
            varList.addAll(list); //Im love with lambda coding.


        }
    }

    /**
     *
     * @return ches for begin and end.
     */

    public Token bodyFunction() {
        //epic lambda moment
        return (matchAndRemove(Token.OPTokens.BEGIN) != null) ? current :
                (matchAndRemove(Token.OPTokens.END) != null) ? current : null;
    }


    public Node functionDef() {
        RemoveEOLS();
        Token functionDef = (matchAndRemove(Token.OPTokens.DEFINE) != null) ? current : null; //define
        System.out.println("func def: " + functionDef);
        if (functionDef != null) {
            if (functionDef.getTokenValue().equals("define")) {
                System.out.println("made it to define");
                Token name = (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null;
                if (name == null) {
                    throw new UnauthTokenException("error parsing" + current); //if not existent it crashes.
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
                RemoveEOLS();
                Token constants = (matchAndRemove(Token.OPTokens.CONSTANTS) != null) ? current
                        : (matchAndRemove(Token.OPTokens.ENDOFLINE) != null) ? current : null;
                if (constants != null) {
                    if (constants.getTokenEnum() == Token.OPTokens.CONSTANTS) {
                        varaibles.addAll(processConstants());
                    }
                    if (this.current.getTokenEnum() == Token.OPTokens.VARAIBLES) {
                        varaibles.addAll(processVaraibles());
                    }
                }
//                tokenList.get(0); //for debugger
//                varaibles.get(0);
                Token b = current;
                Token a = bodyFunction();
                RemoveEOLS();
                ArrayList<Node> statement = statements();
                RemoveEOLS();
                if (bodyFunction().getTokenEnum() == Token.OPTokens.END) {
                    return new FunctionNode(name.getTokenValue(), params, varaibles, statement);
                }
            }
        }
        return null;
    }

    /**
     * @param isConstant
     * @return Idk if im supposed to have isConstant param. but I do because I dont want todo
     * ctrl C + ctrl v en
     */

    public ArrayList<Node> varaible(boolean isConstant) {
        boolean global = (matchAndRemove(Token.OPTokens.VAR) != null);
        while (true) {
            System.out.println("removing end of lines");
            Token e = matchAndRemove(Token.OPTokens.ENDOFLINE);
            if (e == null) {
                break;
            }

        }
        ArrayList<Token> varaiblesWithnoType = new ArrayList<>();
        ArrayList<Node> retList = new ArrayList<>();
        Token type = (matchAndRemove(Token.OPTokens.INTEGER) != null) ? current :
                (matchAndRemove(Token.OPTokens.FLOAT) != null) ? current :
                        null; //lamda pro here.
        Token name = (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null;
        Token a = tokenList.get(0);

        if (type == null) {//if type == null
            varaiblesWithnoType.add(name);
            while (true) {
                RemoveEOLS();
                Token checkForType = (matchAndRemove(Token.OPTokens.INTEGER) != null) ? current :
                        (matchAndRemove(Token.OPTokens.FLOAT) != null) ? current : null;

                if (checkForType != null) { //use sam lambda
                    type = checkForType;
                    break;
                }
                name = (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null;
                varaiblesWithnoType.add(name);
                if (name == null) {
                    System.out.println("reason");
                    throw new UnauthTokenException("error parsing: " + current); //if not existent it crashes.
                }
                System.out.println("name: " + name);

            }
        }
        System.out.println(name);
        Token equals = (matchAndRemove(Token.OPTokens.EQUALS) != null) ? current : null;
        Node valu = null;
        if (name == null || Lexer.keyWords.get(name.getTokenValue()) != null) {
            throw new UnauthTokenException("name is null or key word is name");
        }
        if (matchAndRemove(Token.OPTokens.NUMBER) != null) {
            valu = expression();
        }
        if (varaiblesWithnoType.size() == 0) {
            varaiblesWithnoType.add(name);
        }
        for (Token token : varaiblesWithnoType) {
            retList.add(new VaraibleNode(type, valu, token, isConstant, global));
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
//                System.out.println("left(EXPERSSON): " + opNode.ToString());
//                System.out.println("Right (expression): " + right.ToString());
//                System.out.println("expression OP: " + op);
//                System.out.println("=======================");
                opNode = new MathOpNode(opNode, right, op);
                node = opNode;
            }

        }
//        System.out.println("node(match and remove fail)(EXPERSSON): " + opNode.ToString());
//        System.out.println("=======================");
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
//                System.out.println("Left (term): " + opNode.ToString());
//                System.out.println("Right (term): " + right.ToString());
//                System.out.println("Operator (term): " + op);
//                System.out.println("=======================");
                opNode = new MathOpNode(opNode, right, op);
                node = opNode;

            }

        }
//        System.out.println("node(match and remove fail)(Term): " + opNode.ToString());
//        System.out.println("=======================");
        return opNode;
    }

    public Node factor() {
//        System.out.println("call factor ");
//        System.out.println("=======================");
//        System.out.println("current: " + current);
        if (esNomeralElFloatar(current) != null || matchAndRemove(Token.OPTokens.NUMBER) != null) {
            float a = (float) esNomeralElFloatar(current);
            matchAndRemove(Token.OPTokens.NUMBER);
//            System.out.println("Node output es Nomeral: " + a);
            return new FloatNode(a);
        } else if (matchAndRemove(Token.OPTokens.LParan) != null) {
            Node node = expression();
//            System.out.println("Node output es Paran: " + node.ToString());
//            System.out.println("================================");
            matchAndRemove(Token.OPTokens.RParan);
            return node;
        }
//        System.out.println("factor retruned null");
//        System.out.println();
//        System.out.println("================================");
        return null;

    }

    private Token matchAndRemove(Token.OPTokens token) {
//        System.out.println("param matchh and remove: "+token);
//        System.out.println("head of list: "+tokenList.get(0).getTokenEnum());
        if (token.equals(tokenList.get(0).getTokenEnum())) {

            var retVal = this.tokenList.remove(0);
//            cleanEndOfLine(); //helper function because I need thois
            current = retVal;
//            System.out.println("matched");
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
