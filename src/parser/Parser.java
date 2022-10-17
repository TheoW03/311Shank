package parser;

import lexer.Lexer;
import lexer.Token;
import lexer.UnauthTokenException;
import parser.node.FunctionCallNode.FunctionCallNode;
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
     * @return Function call node.
     */
    public Node FunctionCall() {
        Token name = current;
        boolean builtIn =
                name.getTokenEnum() == Token.OPTokens.SQRT
                        || name.getTokenEnum() == Token.OPTokens.GET_RANDOM
                        || name.getTokenEnum() == Token.OPTokens.WRITE
                        || name.getTokenEnum() == Token.OPTokens.READ
                        || name.getTokenEnum() == Token.OPTokens.FLOAT_CON_INT
                        || name.getTokenEnum() == Token.OPTokens.INT_CON_FLOAT; //Idk if the builtins are supposed to be Keywords
        if (!builtIn && current.getTokenEnum() != Token.OPTokens.IDENTIFIER) {
            return null;
        }
        matchAndRemove(Token.OPTokens.LParan); //() bc i am a C addict. (just wait until winter. Im going to degrade the haskall like shit to the ground)
        ArrayList<Node> params = new ArrayList<>();
        //parese dt.
        while (true) {
            Token p = (matchAndRemove(Token.OPTokens.NUMBER) != null) ? current : (matchAndRemove(Token.OPTokens.STRING) != null) ? current : (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null;

            if (matchAndRemove(Token.OPTokens.VAR) != null) {
                Token returnType = matchAndRemove(Token.OPTokens.IDENTIFIER);
                if (returnType == null) {
                    throw new UnauthTokenException("function " + name + "needs a return type");
                }
                params.add(new VaraibleReferenceNode(returnType));
            }else{
                if (p == null) {
                    break;
                }
                params.add(new VaraibleReferenceNode(p));
            }

            RemoveEOLS();

        }
        RemoveEOLS();
        matchAndRemove(Token.OPTokens.RParan); //L
        RemoveEOLS(); //im condsidering deleting  this.

        return new FunctionCallNode(name, params, builtIn); //return node.
    }

    /**
     * @return returns elseif node
     */
    public Node ElseIfDef() {
        RemoveEOLS();
        Node condition = boolDef();
        RemoveEOLS();
        if (matchAndRemove(Token.OPTokens.THEN) == null) {
            throw new UnauthTokenException("Missing then");
        }
        RemoveEOLS();
        Token begin = matchAndRemove(Token.OPTokens.BEGIN);
        RemoveEOLS();
        ArrayList<Node> statement = statements(); //statenents
        RemoveEOLS();
        Token end = matchAndRemove(Token.OPTokens.END);
        RemoveEOLS();
        if (condition == null || statement == null || begin == null || end == null) {
            throw new UnauthTokenException("ELse if has no condition");
        }
        return new ElseNode(condition, statement); //else if bide
    }

    /**
     * @return else node.
     */
    public Node ElseDef() {
        Token begin = matchAndRemove(Token.OPTokens.BEGIN); //end
        ArrayList<Node> statement = statements();
        Token end = matchAndRemove(Token.OPTokens.END); //begin
        if (begin == null || end == null) {
            throw new UnauthTokenException("syntax error ");
        }
        return new ElseNode(statement); //new node
    }

    /**
     * @return boolean expression node
     */
    public Node boolDef() {
        Node left = expression();
        Token operator = (matchAndRemove(Token.OPTokens.EQUALITY_EUQUALS) != null) ? current :
                (matchAndRemove(Token.OPTokens.LESS_THAN) != null) ? current :
                        (matchAndRemove(Token.OPTokens.LESS_THAN_EQAUALS) != null) ? current :
                                (matchAndRemove(Token.OPTokens.GREATER_THAN) != null) ? current :
                                        (matchAndRemove(Token.OPTokens.GREATER_THAN_EQUALS) != null) ? current :
                                                (matchAndRemove(Token.OPTokens.NOT_EQUAL) != null) ? current : null; //big block checks for more stuff
        if (left == null && operator == null) { //if left and right are null
            return null;
        }
        if (operator == null) { //Return the left for recursion
            return left;
        }
        Node right = expression(); //REEE :D
        return new BooleanNode(right, left, operator);
    }

    /**
     * @return for node. specifically parsing for nodes
     */
    public Node forDef() {
        RemoveEOLS();
        Token varaibleBeginIn = matchAndRemove(Token.OPTokens.IDENTIFIER);
        RemoveEOLS();
        if (matchAndRemove(Token.OPTokens.FROM) == null) {
            throw new UnauthTokenException("syntax error");
        }

        Token beginV = (matchAndRemove(Token.OPTokens.NUMBER) != null) ? current : (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null;
        if (matchAndRemove(Token.OPTokens.TO) == null) {
            throw new UnauthTokenException("syntax error"); //funny thing is these are unneeded :laugh:
        }
        //so u can have a Var condition if u want/number
        Token endV = (matchAndRemove(Token.OPTokens.NUMBER) != null) ? current : (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null;
        RemoveEOLS();
        Token begin = matchAndRemove(Token.OPTokens.BEGIN);
        RemoveEOLS();
        ArrayList<Node> statement = statements();
        Token end = matchAndRemove(Token.OPTokens.END);
        if (begin == null || end == null) {
            throw new UnauthTokenException("No begin or end in for loop");
        }
        return new ForNode(new VaraibleReferenceNode(varaibleBeginIn), new VaraibleReferenceNode(beginV), new VaraibleReferenceNode(endV), statement);
    }

    /**
     * @return do until
     */
    public Node DoUntilDef() {
        RemoveEOLS();
        RemoveEOLS();
        Token begin = matchAndRemove(Token.OPTokens.BEGIN); //begin
        RemoveEOLS();
        ArrayList<Node> statements = statements(); //stats
        Token end = matchAndRemove(Token.OPTokens.END);
        RemoveEOLS();
        if (matchAndRemove(Token.OPTokens.UNTIL) == null || end == null || begin == null) {
            throw new UnauthTokenException("syntax error");
        }
        RemoveEOLS();
        Node condition = boolDef(); //Yk
        RemoveEOLS();
        return new RepeatNode(statements, condition); //return node
    }

    /**
     * @return while nodes
     */
    public Node whileDef() {
        RemoveEOLS();
        Node condition = boolDef();
        RemoveEOLS();
        Token begin = matchAndRemove(Token.OPTokens.BEGIN);
        RemoveEOLS();
        ArrayList<Node> statements = statements();
        RemoveEOLS();
        Token end = matchAndRemove(Token.OPTokens.END);
        if (begin == null || end == null) {
            throw new UnauthTokenException("syntax error");
        }
        RemoveEOLS();
        return new WhileNode(condition, statements);
    }

    /**
     * @return if nodes
     */
    public Node IfDef() {
        Node boolExp = boolDef();
        Token then = matchAndRemove(Token.OPTokens.THEN);
        if (then == null || boolExp == null) {
            throw new UnauthTokenException("not a real if statement");
        }
        RemoveEOLS();
        Token begin = matchAndRemove(Token.OPTokens.BEGIN);
        if (begin == null) {
            return null;
        }
        ArrayList<Node> statementL = statements();
        RemoveEOLS();
        Token end = matchAndRemove(Token.OPTokens.END);
        ArrayList<Node> elseIfNode = new ArrayList<Node>();
        //why a loop. to get chain ELSE IF
        while (true) {
            RemoveEOLS();
            if (matchAndRemove(Token.OPTokens.ELSE_IF) == null) {
                RemoveEOLS();
                break;
            }
            RemoveEOLS();
            elseIfNode.add(ElseIfDef());
            RemoveEOLS();
        }
        RemoveEOLS();
        if (matchAndRemove(Token.OPTokens.ELSE) != null) { //else.
            RemoveEOLS();
            elseIfNode.add(ElseDef());
        }
        RemoveEOLS();

        if (end == null) {
            throw new UnauthTokenException("where does the if end");
        }
        return new ifNode(boolExp, statementL, elseIfNode);
    }


    /**
     * @return statement node list for function node
     */

    public ArrayList<Node> statements() {
        ArrayList<Node> nodeList = new ArrayList<>(); //STatements.
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
        RemoveEOLS();
        if (matchAndRemove(Token.OPTokens.IF) != null) {
            return IfDef();
        }
        if (matchAndRemove(Token.OPTokens.WRITE) != null
                || matchAndRemove(Token.OPTokens.SQRT) != null || matchAndRemove(Token.OPTokens.READ) != null
                || matchAndRemove(Token.OPTokens.FLOAT_CON_INT) != null ||matchAndRemove(Token.OPTokens.INT_CON_FLOAT) != null  || matchAndRemove(Token.OPTokens.GET_RANDOM) != null) {
            return FunctionCall();
        }
        if (matchAndRemove(Token.OPTokens.WHILE) != null) {
            return whileDef();
        }
        if (matchAndRemove(Token.OPTokens.FOR) != null) {
            return forDef();
        }
        if (matchAndRemove(Token.OPTokens.REPEAT) != null) {
            return DoUntilDef();
        }

        RemoveEOLS();
        Token name = (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null; //checks for name
        //begin
        Token equals = (matchAndRemove(Token.OPTokens.EQUALS) != null) ? current : null; //what it equals
        if (equals == null) {
            RemoveEOLS();
            return FunctionCall();
        }
        //end
        Node ifBool = boolDef();
        Node ifExp = expression();
        Node assignement = (ifBool != null) ? ifBool : ifExp; //assignment
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
                        (matchAndRemove(Token.OPTokens.STRING_DT) != null) ? current : null; //lamda pro here.
        Token name = (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) ? current : null;
        Token a = tokenList.get(0);

        if (type == null) {//if type == null
            varaiblesWithnoType.add(name);
            while (true) {
                RemoveEOLS();
                Token checkForType = (matchAndRemove(Token.OPTokens.INTEGER) != null) ? current :
                        (matchAndRemove(Token.OPTokens.FLOAT) != null) ? current : (matchAndRemove(Token.OPTokens.STRING_DT) != null) ? current : null;

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
        valu = expression();
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
                (matchAndRemove(Token.OPTokens.DIVIDE) != null) ? current :
                        (matchAndRemove(Token.OPTokens.MOD) != null) ? current : null;
        if (op != null) {
            //loop.
            Node node = null;
            //probably while OP != null
            //return something.
            while (true) {
                if (node != null) {
                    op = (matchAndRemove(Token.OPTokens.MULTIPLY) != null) ? current :
                            (matchAndRemove(Token.OPTokens.DIVIDE) != null) ? current :
                                    (matchAndRemove(Token.OPTokens.MOD) != null) ? current : null;
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
        Token string = matchAndRemove(Token.OPTokens.STRING);
        if (string != null) {
            return new StringNode(string);
        }
        if (isInt(current) != null ||isInt(current) != null|| matchAndRemove(Token.OPTokens.NUMBER) != null) {
//            float a = (float) isFloat(current);
            if(isInt(current) != null){
                int a = (int) isInt(current);
                matchAndRemove(Token.OPTokens.NUMBER);
                return new IntegerNode(a);
            }else if(isFloat(current) != null){
                float a = (float) isFloat(current);
                matchAndRemove(Token.OPTokens.NUMBER);
                return new FloatNode(a);
            }
//            System.out.println("Node output es Nomeral: " + a);
//            return new FloatNode(a);
        } else if (matchAndRemove(Token.OPTokens.IDENTIFIER) != null) {
            return new VaraibleReferenceNode(current);
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
        System.out.println("param matchh and remove: " + token);
//        System.out.println("head of list: "+tokenList.get(0).getTokenEnum());
        if (token.equals(tokenList.get(0).getTokenEnum())) {

            var retVal = this.tokenList.remove(0);
//            cleanEndOfLine(); //helper function because I need thois
            current = retVal;
//            System.out.println("matched");
            return retVal;
        }
        return null;
    }

    /**
     * @param token
     * @return es floatar
     */
    public Object isFloat(Token token) {
        try {
            float t = Float.parseFloat(token.getTokenValue());
            return t;
        } catch (NumberFormatException e) {
            return null;
        }
    }
    public Object isInt(Token token){
        try {
            int t = Integer.parseInt(token.getTokenValue());
            return t;
        } catch (NumberFormatException e) {
            return null;
        }
    }


}
